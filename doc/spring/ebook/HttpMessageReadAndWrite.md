# HTTP消息读写操作分析

本节将对SpringMVC中HTTP消息的读写进行分析，主要分析对象是HttpMessageReader和HttpMessageWriter。



## HTTP消息读操作分析

在SpringMVC中负责处理HTTP消息读操作的接口是HttpMessageReader，关于它的接口定义如下：

```java
public interface HttpMessageReader<T> {

   List<MediaType> getReadableMediaTypes();

   boolean canRead(ResolvableType elementType, @Nullable MediaType mediaType);

   Flux<T> read(ResolvableType elementType, ReactiveHttpInputMessage message, Map<String, Object> hints);

   Mono<T> readMono(ResolvableType elementType, ReactiveHttpInputMessage message, Map<String, Object> hints);

   default Flux<T> read(ResolvableType actualType, ResolvableType elementType, ServerHttpRequest request,
         ServerHttpResponse response, Map<String, Object> hints) {

      return read(elementType, request, hints);
   }

   default Mono<T> readMono(ResolvableType actualType, ResolvableType elementType, ServerHttpRequest request,
         ServerHttpResponse response, Map<String, Object> hints) {

      return readMono(elementType, request, hints);
   }

}
```

在HttpMessageReader接口中定义的方法可以分为如下三类：

1. 获取支持的媒体类型。
2. 判断是否可以读取。
3. 实际读操作。

在实际读操作时会转换成不同的返回对象，包含Flux、Mono类型，HttpMessageReader接口的类图如图所示：

![HttpMessageReader](images/HttpMessageReader.png)



### DecoderHttpMessageReader 分析

下面将对类图中的DecoderHttpMessageReader对象进行分析，在DecoderHttpMessageReader对象中定义了两个变量，具体代码如下：

```java
private final Decoder<T> decoder;

private final List<MediaType> mediaTypes;
```

这两个变量的含义如下：

1. decoder：解码器。
2. mediaTypes：支持的媒体类型。

在构造方法中要求传递解码器对象，解码器所支持的媒体类型就是当前对象所支持的媒体类型。下面对canRead方法进行分析，具体代码如下：

```java
@Override
public boolean canRead(ResolvableType elementType, @Nullable MediaType mediaType) {
   return this.decoder.canDecode(elementType, mediaType);
}
```

在这段代码中判断是否可读依赖解码器进行。同样的在进行读操作的时候也需要依赖解码器进行，比如解码Flux对象，具体代码如下：

```java
@Override
public Flux<T> read(ResolvableType elementType, ReactiveHttpInputMessage message, Map<String, Object> hints) {
   MediaType contentType = getContentType(message);
   return this.decoder.decode(message.getBody(), elementType, contentType, hints);
}
```

在这段代码中可以发现需要通过消息对象获取媒体类型，再通过解码器进行解码将结果返回。在DecoderHttpMessageReader对象中其他的读操作行为操作类似不做具体单个方法分析。





### MultipartHttpMessageReader 分析

下面在对HttpMessageReader的另一个实现类（MultipartHttpMessageReader）进行分析，这个对象是用于读取HTTP请求的请求头为"multipart/form-data"的数据内容，下面查看getReadableMediaTypes方法，该方法的作用是获取受支持的数据类型，具体代码如下：

```java
@Override
public List<MediaType> getReadableMediaTypes() {
   return Collections.singletonList(MediaType.MULTIPART_FORM_DATA);
}
```

在getReadableMediaTypes方法中可以看到媒体类型为MULTIPART_FORM_DATA（multipart/form-data），如果请求的类型不是MULTIPART_FORM_DATA类型则不作处理，下面查看canRead方法，具体代码如下：

```java
@Override
public boolean canRead(ResolvableType elementType, @Nullable MediaType mediaType) {
   return MULTIPART_VALUE_TYPE.isAssignableFrom(elementType) &&
         (mediaType == null || MediaType.MULTIPART_FORM_DATA.isCompatibleWith(mediaType));
}
```

在这段代码中判断是否可读的标注有两个：

1. 类型是MULTIPART_VALUE_TYPE。
2. 媒体类型是MULTIPART_FORM_DATA。

最后查看read方法，在该对象中核心读取方法是readMono，read方法依赖readMono方法，具体代码如下：

```java
@Override
public Mono<MultiValueMap<String, Part>> readMono(ResolvableType elementType,
      ReactiveHttpInputMessage inputMessage, Map<String, Object> hints) {


   Map<String, Object> allHints = Hints.merge(hints, Hints.SUPPRESS_LOGGING_HINT, true);

   return this.partReader.read(elementType, inputMessage, allHints)
         .collectMultimap(Part::name)
         .doOnNext(map ->
            LogFormatUtils.traceDebug(logger, traceOn -> Hints.getLogPrefix(hints) + "Parsed " +
                  (isEnableLoggingRequestDetails() ?
                        LogFormatUtils.formatValue(map, !traceOn) :
                        "parts " + map.keySet() + " (content masked)"))
         )
         .map(this::toMultiValueMap);
}
```

在这个方法中关于数据的读取操作需要依赖partReader对象，这个对象是HttpMessageReader类型，具体实现还是需要在各个子类中进行分析，在这个方法中相当于是一个顶层的统筹调用，这里向下找一个子类进行举例，FormHttpMessageReader对象中的read方法，具体代码如下：

```
@Override
public Flux<MultiValueMap<String, String>> read(ResolvableType elementType,
      ReactiveHttpInputMessage message, Map<String, Object> hints) {

   return Flux.from(readMono(elementType, message, hints));
}

@Override
public Mono<MultiValueMap<String, String>> readMono(ResolvableType elementType,
      ReactiveHttpInputMessage message, Map<String, Object> hints) {

   MediaType contentType = message.getHeaders().getContentType();
   Charset charset = getMediaTypeCharset(contentType);

   return DataBufferUtils.join(message.getBody(), this.maxInMemorySize)
         .map(buffer -> {
            CharBuffer charBuffer = charset.decode(buffer.asByteBuffer());
            String body = charBuffer.toString();
            DataBufferUtils.release(buffer);
            MultiValueMap<String, String> formData = parseFormData(charset, body);
            logFormData(formData, hints);
            return formData;
         });
}
```

在这段代码中可以看到他从消息对象（ReactiveHttpInputMessage）中提取消息体在经过代码中的自定义转化处理得到最后的处理对象。在SpringMVC中还有其他的几个实现需要按需进行查询。从操作上可以发现整体处理逻辑和ReactiveHttpInputMessage对象离不开，都需要从ReactiveHttpInputMessage对象中获取body在进行各自的处理从而得到实际的返回对象。







## ReactiveHttpInputMessage 分析

在分析HTTP消息读取操作时发现具体操作与ReactiveHttpInputMessage对象有直接关系，下面将对ReactiveHttpInputMessage进行分析，在SpringMVC中具体定义代码如下：

```java
public interface ReactiveHttpInputMessage extends HttpMessage {

   Flux<DataBuffer> getBody();

}
```

在这个接口定义中定义了getBody方法，该方法的作用是用获取HTTP消息体。在SpringMVC中对于ReactiveHttpInputMessage接口还有两个字接口ServerHttpRequest和ClientHttpResponse。关于ClientHttpResponse接口的定义代码如下：

```java
public interface ClientHttpResponse extends ReactiveHttpInputMessage {

   HttpStatus getStatusCode();

   int getRawStatusCode();

   MultiValueMap<String, ResponseCookie> getCookies();

}
```

在ClientHttpResponse中补充了三个方法：

1. 方法ClientHttpResponse作用是获取HttpStatus对象，HTTP状态对象。
2. 方法getRawStatusCode作用是获取HTTP状态码。
3. 方法getCookies作用是获取cookies。

关于ServerHttpRequest接口的定义代码如下：

```java
public interface ServerHttpRequest extends HttpRequest, ReactiveHttpInputMessage {

   String getId();

   RequestPath getPath();

   MultiValueMap<String, String> getQueryParams();

   MultiValueMap<String, HttpCookie> getCookies();

   @Nullable
   default InetSocketAddress getRemoteAddress() {
      return null;
   }

   @Nullable
   default InetSocketAddress getLocalAddress() {
      return null;
   }


   @Nullable
   default SslInfo getSslInfo() {
      return null;
   }

   default ServerHttpRequest.Builder mutate() {
      return new DefaultServerHttpRequestBuilder(this);
   }

}
```

在ServerHttpRequest接口中定义了几个方法作为原始接口的补充：

1. 方法getId作用是获取序号。
2. 方法getPath作用是获取请求地址。
3. 方法getQueryParams作用是获取请求参数。
4. 方法getCookies作用是获取cookies对象。
5. 方法getRemoteAddress作用是获取请求地址。
6. 方法getLocalAddress作用是获取本地地址。
7. 方法getSslInfo作用是获取Ssl配置对象。

### JettyClientHttpResponse 分析

下面将以JettyClientHttpResponse对象作为分析目标，从而理解ClientHttpResponse的子类处理模式，首先需要查看成员变量，具体代码如下：

```java
private final ReactiveResponse reactiveResponse;

private final Flux<DataBuffer> content;
```

1. 变量reactiveResponse表示本体。
2. 变量content表示`Publisher<DataBuffer>`，消息体。

下面进入方法分析，首先是getRawStatusCode方法，具体处理代码如下：

```java
public int getRawStatusCode() {
   return this.reactiveResponse.getStatus();
}
```

在这个方法中会通过成员变量reactiveResponse获取状态码。其次是getCookies方法，具体处理代码如下：

```java
@Override
public MultiValueMap<String, ResponseCookie> getCookies() {
   MultiValueMap<String, ResponseCookie> result = new LinkedMultiValueMap<>();
   List<String> cookieHeader = getHeaders().get(HttpHeaders.SET_COOKIE);
   if (cookieHeader != null) {
      cookieHeader.forEach(header ->
         HttpCookie.parse(header)
               .forEach(cookie -> result.add(cookie.getName(),
                     ResponseCookie.from(cookie.getName(), cookie.getValue())
               .domain(cookie.getDomain())
               .path(cookie.getPath())
               .maxAge(cookie.getMaxAge())
               .secure(cookie.getSecure())
               .httpOnly(cookie.isHttpOnly())
               .build()))
      );
   }
   return CollectionUtils.unmodifiableMultiValueMap(result);
}
```

在这段代码中关于cookie的获取需要依赖头信息，cookie的数据来源是头信息中SET_COOKIE键对应的内容。在得到头信息中的数据后需要进行Cookie格式化将字符串对象转换成实际的ResponseCookie对象放入到返回值容器。













## HTTP消息写操作分析

在SpringMVC中负责处理HTTP消息写操作的接口是HttpMessageWriter，关于它的接口定义如下：

```java
public interface HttpMessageWriter<T> {

   List<MediaType> getWritableMediaTypes();

   boolean canWrite(ResolvableType elementType, @Nullable MediaType mediaType);

   Mono<Void> write(Publisher<? extends T> inputStream, ResolvableType elementType,
         @Nullable MediaType mediaType, ReactiveHttpOutputMessage message, Map<String, Object> hints);

   default Mono<Void> write(Publisher<? extends T> inputStream, ResolvableType actualType,
         ResolvableType elementType, @Nullable MediaType mediaType, ServerHttpRequest request,
         ServerHttpResponse response, Map<String, Object> hints) {

      return write(inputStream, elementType, mediaType, response, hints);
   }

}
```

在HttpMessageWriter接口中定义的方法可以分为如下三类：

1. 获取支持的媒体类型。
2. 判断是否可以进行写操作。
3. 实际读操作。

在实际读操作时会转换成不同的返回对象，包含Flux、Mono类型，HttpMessageReader接口的类图如图所示：

![HttpMessageWriter](images/HttpMessageWriter.png)

在这个类图中只对EncoderHttpMessageWriter对象进行分析，其他内容不做分析，在HTTP消息写操作的处理过程中和读操作的过程类似。EncoderHttpMessageWriter对象中关于判断是否可写的方法如下：

```java
@Override
public boolean canWrite(ResolvableType elementType, @Nullable MediaType mediaType) {
   return this.encoder.canEncode(elementType, mediaType);
}
```

在这个判断是否可写的方法中核心对象是编码器Encoder，由它的canEncode方法判断是否可写。最后进行写操作方法的分析，具体处理代码如下：

```java
@Override
public Mono<Void> write(Publisher<? extends T> inputStream, ResolvableType elementType,
      @Nullable MediaType mediaType, ReactiveHttpOutputMessage message, Map<String, Object> hints) {

   MediaType contentType = updateContentType(message, mediaType);

   if (inputStream instanceof Mono) {
      return Mono.from(inputStream)
            .switchIfEmpty(Mono.defer(() -> {
               message.getHeaders().setContentLength(0);
               return message.setComplete().then(Mono.empty());
            }))
            .flatMap(value -> {
               DataBufferFactory factory = message.bufferFactory();
               DataBuffer buffer = this.encoder.encodeValue(value, factory, elementType, contentType, hints);
               message.getHeaders().setContentLength(buffer.readableByteCount());
               return message.writeWith(Mono.just(buffer)
                     .doOnDiscard(PooledDataBuffer.class, PooledDataBuffer::release));
            });
   }

   Flux<DataBuffer> body = this.encoder.encode(
         inputStream, message.bufferFactory(), elementType, contentType, hints);

   if (isStreamingMediaType(contentType)) {
      return message.writeAndFlushWith(body.map(buffer ->
            Mono.just(buffer).doOnDiscard(PooledDataBuffer.class, PooledDataBuffer::release)));
   }

   return message.writeWith(body);
}
```

在这个方法中可以简单理解整体操作步骤为ReactiveHttpOutputMessage对象将输入楼写出，在写出操作执行过程中还会做一些其他操作，这个操作会在每个实现类中有自实现，在当前类中的处理是通过Encode接口进行编码操作。







## ReactiveHttpOutputMessage分析

在分析HTTP消息写操作时发现具体操作与ReactiveHttpOutputMessage对象有直接关系，下面将对ReactiveHttpOutputMessage进行分析，在SpringMVC中具体定义代码如下：

```java
public interface ReactiveHttpOutputMessage extends HttpMessage {

   DataBufferFactory bufferFactory();

   void beforeCommit(Supplier<? extends Mono<Void>> action);

   boolean isCommitted();

   Mono<Void> writeWith(Publisher<? extends DataBuffer> body);

   Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body);

   Mono<Void> setComplete();

}
```

在ReactiveHttpOutputMessage接口中定义了六个方法，它们的作用说明如下：

1. 方法bufferFactory作用是获取数据工厂。
2. 方法beforeCommit作用是提交前的处理。
3. 方法isCommitted作用是判断是否已经提交。
4. 方法writeWith写出数据。
5. 方法writeAndFlushWith作用是写出数据。
6. 方法setComplete作用是设置是否完成处理。

在SpringMVC中ReactiveHttpOutputMessage还有额外的三个子类接口：ZeroCopyHttpOutputMessage、ServerHttpResponse和ClientHttpRequest。关于ZeroCopyHttpOutputMessage的接口定义如下：

```
public interface ZeroCopyHttpOutputMessage extends ReactiveHttpOutputMessage {

   default Mono<Void> writeWith(File file, long position, long count) {
      return writeWith(file.toPath(), position, count);
   }

   Mono<Void> writeWith(Path file, long position, long count);

}
```

在ZeroCopyHttpOutputMessage接口中增加了writeWith方法，该方法的作用是用于写出信息。关于ServerHttpResponse的接口定义如下：

```java
public interface ServerHttpResponse extends ReactiveHttpOutputMessage {

   boolean setStatusCode(@Nullable HttpStatus status);

   @Nullable
   HttpStatus getStatusCode();

   MultiValueMap<String, ResponseCookie> getCookies();

   void addCookie(ResponseCookie cookie);

}
```

在ServerHttpResponse接口中增加了四个方法，方法说明如下：

1. 方法setStatusCode作用是设置状态码。
2. 方法getStatusCode作用是获取状态对象。
3. 方法getCookies作用是获取cookie对象。
4. 方法addCookie作用是添加cookie对象。

最后还有ClientHttpRequest对象，它的定义代码如下：

```java
public interface ClientHttpRequest extends ReactiveHttpOutputMessage {

   HttpMethod getMethod();

   URI getURI();

   MultiValueMap<String, HttpCookie> getCookies();

}
```

在ClientHttpRequest接口中新增了三个方法，方法说明如下：

1. 方法getMethod作用是获取HTTP请求方法。
2. 方法getURI作用是获取URI对象。
3. 方法getCookies作用是获取cookie对象。

