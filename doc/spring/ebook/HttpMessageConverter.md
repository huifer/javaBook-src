## HttpMessageConverter 分析
本节将对 HttpMessageConverter 接口进行分析，HttpMessageConverter的作用是进行HTTP消息转换。在HttpMessageConverter接口中国年定义了五个方法，具体代码如下：

```java
public interface HttpMessageConverter<T> {

   boolean canRead(Class<?> clazz, @Nullable MediaType mediaType);

   boolean canWrite(Class<?> clazz, @Nullable MediaType mediaType);

   List<MediaType> getSupportedMediaTypes();

   T read(Class<? extends T> clazz, HttpInputMessage inputMessage)
         throws IOException, HttpMessageNotReadableException;

   void write(T t, @Nullable MediaType contentType, HttpOutputMessage outputMessage)
         throws IOException, HttpMessageNotWritableException;

}
```

下面对上述五个方法进行说明：

1. 方法canRead作用是判断是否可读。
2. 方法canWrite作用是判断是否可写。
3. 方法getSupportedMediaTypes作用是获取支持的数据类型（主要是MediaType对象集合）。
4. 方法read作用是进行读取操作，会进行反序列化操作。
5. 方法write作用是进行写操作。



### HttpMessageConverter 测试用例搭建

本节将介绍HttpMessageConverter测试环境搭建，首先需要在build.gradle文件中添加依赖，具体依赖如下：

```groovy
compile 'com.fasterxml.jackson.core:jackson-databind:2.9.6'
```

依赖添加完成后需要进行SpringXML配置文件添加，本例中需要添加的代码如下：

```xml
<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter"
     p:ignoreDefaultModelOnRedirect="true" >
   <property name="messageConverters">
      <list>
         <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter"/>
      </list>
   </property>
</bean>
```

在完成SpringXML配置后需要编写Controller接口，具体代码如下：

```java

@RestController
public class RestCtr {
    @GetMapping("/json")
    public Object json() {
       Map<String, String> map = new HashMap<>();
       map.put("demo", "demo");
       return map;
    }
}
```

最后进行接口模拟访问，具体请求信息如下：

```
GET http://localhost:8080/json

HTTP/1.1 200 
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 15 Apr 2021 05:10:40 GMT
Keep-Alive: timeout=20
Connection: keep-alive

{
  "demo": "demo"
}
```

从这个请求响应中可以看到具体的数据信息类型变成了json，产生这个变化的原因是MappingJackson2HttpMessageConverter对象。





### 带有@RequestBody注解的整体流程分析

在SpringMVC中关于返回值的处理是通过org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite#handleReturnValue方法进行，具体处理代码如下：

```java
@Override
public void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType,
      ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {

   HandlerMethodReturnValueHandler handler = selectHandler(returnValue, returnType);
   if (handler == null) {
      throw new IllegalArgumentException("Unknown return value type: " + returnType.getParameterType().getName());
   }
   handler.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
}
```

在handleReturnValue方法中主要操作逻辑如下：

1. 根据返回值和返回值类型搜索对应的HandlerMethodReturnValueHandler对象。
2. 通过HandlerMethodReturnValueHandler对象进行返回值处理。

在这个处理过程中首先需要理解returnValueHandlers对象，该对象是HandlerMethodReturnValueHandler的集合，在本例中该对象的数据信息如下：

![image-20210415132637239](images/image-20210415132637239.png)

Spring中关于HandlerMethodReturnValueHandler接口的实现类有下面内容：

1. MapMethodProcessor (org.springframework.web.method.annotation)。
2. ViewNameMethodReturnValueHandler (org.springframework.web.servlet.mvc.method.annotation)。
3. ViewMethodReturnValueHandler (org.springframework.web.servlet.mvc.method.annotation)。
4. StreamingResponseBodyReturnValueHandler (org.springframework.web.servlet.mvc.method.annotation)。
5. HandlerMethodReturnValueHandlerComposite (org.springframework.web.method.support)。
6. DeferredResultMethodReturnValueHandler (org.springframework.web.servlet.mvc.method.annotation)。
7. HttpHeadersReturnValueHandler (org.springframework.web.servlet.mvc.method.annotation)。
8. CallableMethodReturnValueHandler (org.springframework.web.servlet.mvc.method.annotation)。
9. ModelMethodProcessor (org.springframework.web.method.annotation)。
10. ModelAttributeMethodProcessor (org.springframework.web.method.annotation)。
    - ServletModelAttributeMethodProcessor (org.springframework.web.servlet.mvc.method.annotation)。
11. ResponseBodyEmitterReturnValueHandler (org.springframework.web.servlet.mvc.method.annotation)。
12. ModelAndViewMethodReturnValueHandler (org.springframework.web.servlet.mvc.method.annotation)。
13. ModelAndViewResolverMethodReturnValueHandler (org.springframework.web.servlet.mvc.method.annotation)。
14. AbstractMessageConverterMethodProcessor (org.springframework.web.servlet.mvc.method.annotation)。
    - RequestResponseBodyMethodProcessor (org.springframework.web.servlet.mvc.method.annotation)    。
    - HttpEntityMethodProcessor (org.springframework.web.servlet.mvc.method.annotation)。
15. AsyncHandlerMethodReturnValueHandler (org.springframework.web.method.support)。
16. AsyncTaskMethodReturnValueHandler (org.springframework.web.servlet.mvc.method.annotation)。



在HandlerMethodReturnValueHandler接口中存在一个方法，该方法用于判断是否支持返回值处理，方法名为supportsReturnType。该方法也是selectHandler方法的处理核心，下面是selectHandler的完整代码：

```java
@Nullable
private HandlerMethodReturnValueHandler selectHandler(@Nullable Object value, MethodParameter returnType) {
   boolean isAsyncValue = isAsyncReturnValue(value, returnType);
   for (HandlerMethodReturnValueHandler handler : this.returnValueHandlers) {
      if (isAsyncValue && !(handler instanceof AsyncHandlerMethodReturnValueHandler)) {
         continue;
      }
      if (handler.supportsReturnType(returnType)) {
         return handler;
      }
   }
   return null;
}
```

在前文定义的测试用例中返回对象被RequestBody注解进行修饰，在SpringMVC中对包含RequestBody注解的返回值处理是交给RequestResponseBodyMethodProcessor类进行。具体处理代码如下：

```java
@Override
public boolean supportsReturnType(MethodParameter returnType) {
   return (AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseBody.class) ||
         returnType.hasMethodAnnotation(ResponseBody.class));
}
```

在这个方法中对于是否支持处理的判断方式有两个，这两条件只需要满足一个就说明支持处理，具体判断条件如下：

1. 判断类是否存在ResponseBody注解。
2. 判断当前的方法是否存在ResponseBody注解。

通过selectHandler方法处理后此时handler的数据信息如下：

![image-20210415133751667](images/image-20210415133751667.png)

现在明确了handler对象的实际类型，下面对handleReturnValue方法进行分析，当前需要进行分析的方法是org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor#handleReturnValue，具体代码如下：



```java
@Override
public void handleReturnValue(@Nullable Object returnValue, MethodParameter returnType,
      ModelAndViewContainer mavContainer, NativeWebRequest webRequest)
      throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {

   mavContainer.setRequestHandled(true);
   ServletServerHttpRequest inputMessage = createInputMessage(webRequest);
   ServletServerHttpResponse outputMessage = createOutputMessage(webRequest);

   // Try even with null return value. ResponseBodyAdvice could get involved.
   writeWithMessageConverters(returnValue, returnType, inputMessage, outputMessage);
}
```

在RequestResponseBodyMethodProcessor#handleReturnValue方法中主要处理流程如下：

1. 将MVC处理状态设置为true。
2. 创建输入消息对象。
3. 创建输出消息对象
4. 写出消息。

在上述四个操作过程中第二步和第三步的操作的处理模式相同都是通过NativeWebRequest接口获取对象再通过new关键字创建对象，具体代码如下：

```java
protected ServletServerHttpRequest createInputMessage(NativeWebRequest webRequest) {
   HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
   Assert.state(servletRequest != null, "No HttpServletRequest");
   return new ServletServerHttpRequest(servletRequest);
}

protected ServletServerHttpResponse createOutputMessage(NativeWebRequest webRequest) {
    HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
    Assert.state(response != null, "No HttpServletResponse");
    return new ServletServerHttpResponse(response);
}
```

下面对写出消息的方法进行分析，在writeWithMessageConverters方法中主要处理的流程有四个步骤：

1. 三值推论。
2. 进行selectedMediaType对象的推论。
3. 处理返回值数据。
4. 异常信息处理。

下面将对上述四个方法进行分析。

#### 三值推论

下面对三值推论进行分析，下面是关于三个值的介绍：

1. body它表示返回体。
2. valueType它表示数据原始类型。
3. targetType它表示返回值类型

关于三值推论的代码如下：

```java
// 第一部分: body valueType targetType 推论
// 值
Object body;
// 数据类型(原始类型)
Class<?> valueType;
// 返回值类型
Type targetType;

if (value instanceof CharSequence) {
   body = value.toString();
   valueType = String.class;
   targetType = String.class;
}
else {
   body = value;
   valueType = getReturnValueType(body, returnType);
   targetType = GenericTypeResolver.resolveType(getGenericType(returnType), returnType.getContainingClass());
}

// value 和 returnType 是否匹配
if (isResourceType(value, returnType)) {
   // 请求头设置
   outputMessage.getHeaders().set(HttpHeaders.ACCEPT_RANGES, "bytes");
   if (value != null && inputMessage.getHeaders().getFirst(HttpHeaders.RANGE) != null &&
         outputMessage.getServletResponse().getStatus() == 200) {
      Resource resource = (Resource) value;
      try {
         List<HttpRange> httpRanges = inputMessage.getHeaders().getRange();
         outputMessage.getServletResponse().setStatus(HttpStatus.PARTIAL_CONTENT.value());
         body = HttpRange.toResourceRegions(httpRanges, resource);
         valueType = body.getClass();
         targetType = RESOURCE_REGION_LIST_TYPE;
      }
      catch (IllegalArgumentException ex) {
         outputMessage.getHeaders().set(HttpHeaders.CONTENT_RANGE, "bytes */" + resource.contentLength());
         outputMessage.getServletResponse().setStatus(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE.value());
      }
   }
}
```

在writeWithMessageConverters方法中的第一部分可以发现关于三值推论的细节，具体推论有三种方式：

1. 当参数value是CharSequence类型时，body将直接采用value#toString作为值，valueType和targetType都将采用String.class。

2. 当参数value不是CharSequence类型时，body将直接采用value本身，valueType的判断过程如下：

   - value对象不为空采用value的类型作为valueType的数据值。
   - value对象为空采用returnType的数据类型作为valueType的数据值。

   关于targetType的推论可以简单的认为就是方法的返回值，该方法指代Controller中的方法。

3. 当value对象和returnType对象能够匹配，body将通过HttpRange.toResourceRegions(httpRanges, resource)方法获取，valueType采用body的类型，targetType采用RESOURCE_REGION_LIST_TYPE静态变量。



在前文的测试用例中经过第一部分代码得到的数据信息如图所示：

![image-20210415141609837](images/image-20210415141609837.png)





#### selectedMediaType推论

下面将对selectedMediaType推论过程进行分析，selectedMediaType表示当前的媒体类型，具体的推论代码如下：

```java
// 第二部分:处理 selectedMediaType 数据
MediaType selectedMediaType = null;
MediaType contentType = outputMessage.getHeaders().getContentType();
boolean isContentTypePreset = contentType != null && contentType.isConcrete();
if (isContentTypePreset) {
   if (logger.isDebugEnabled()) {
      logger.debug("Found 'Content-Type:" + contentType + "' in response");
   }
   selectedMediaType = contentType;
}
else {
   HttpServletRequest request = inputMessage.getServletRequest();
   List<MediaType> acceptableTypes = getAcceptableMediaTypes(request);
   List<MediaType> producibleTypes = getProducibleMediaTypes(request, valueType, targetType);

   if (body != null && producibleTypes.isEmpty()) {
      throw new HttpMessageNotWritableException(
            "No converter found for return value of type: " + valueType);
   }
   List<MediaType> mediaTypesToUse = new ArrayList<>();
   for (MediaType requestedType : acceptableTypes) {
      for (MediaType producibleType : producibleTypes) {
         if (requestedType.isCompatibleWith(producibleType)) {
            mediaTypesToUse.add(getMostSpecificMediaType(requestedType, producibleType));
         }
      }
   }
   if (mediaTypesToUse.isEmpty()) {
      if (body != null) {
         throw new HttpMediaTypeNotAcceptableException(producibleTypes);
      }
      if (logger.isDebugEnabled()) {
         logger.debug("No match for " + acceptableTypes + ", supported: " + producibleTypes);
      }
      return;
   }

   MediaType.sortBySpecificityAndQuality(mediaTypesToUse);

   for (MediaType mediaType : mediaTypesToUse) {
      if (mediaType.isConcrete()) {
         selectedMediaType = mediaType;
         break;
      }
      else if (mediaType.isPresentIn(ALL_APPLICATION_MEDIA_TYPES)) {
         selectedMediaType = MediaType.APPLICATION_OCTET_STREAM;
         break;
      }
   }

   if (logger.isDebugEnabled()) {
      logger.debug("Using '" + selectedMediaType + "', given " +
            acceptableTypes + " and supported " + producibleTypes);
   }
}
```

在这个推论过程中首先需要获取返回对象请求头中的媒体类型，在测试用例中contentType的具体数据信息如下：

![image-20210415142607317](images/image-20210415142607317.png)

在得到contentType数据信息后会对其进行是否包含通配符`*`或者通配符`*+`，如果包含就将contentType数据设置给selectedMediaType，在本例中不包含将进行下面的处理操作：

1. 获取请求的媒体类型，存储数据对象是acceptableTypes。
2. 获取Controller方法上的媒体类型，存储数据对象是producibleTypes。
3. 将acceptableTypes和producibleTypes之间互相兼容的对象进行提取，存储数据对象是mediaTypesToUse。
4. 异常处理：如果mediaTypesToUse数据为空并且body不为空抛出HttpMediaTypeNotAcceptableException异常。
5. 遍历mediaTypesToUse对元素进行两个判断，如果符合这两个判断中的任意一个就将其作为selectedMediaType的数据，判断如下：
   - mediaType.isConcrete()。
   - mediaType.isPresentIn(ALL_APPLICATION_MEDIA_TYPES)。

在本例中acceptableTypes和producibleTypes的数据信息如下：

![image-20210415143050731](images/image-20210415143050731.png)

推论后selectedMediaType的数据信息如下：

![image-20210415144106990](images/image-20210415144106990.png)

#### 处理返回值数据

下面对处理返回值数据进行分析，在这个过程中会进行消息转换。此时会涉及到messageConverters对象，在本例中messageConverters数据信息如下：

![image-20210415144331740](images/image-20210415144331740.png)

具体处理返回值数据的代码如下：

```java
// 第三部分:处理返回值数据
if (selectedMediaType != null) {
   selectedMediaType = selectedMediaType.removeQualityValue();
   // 消息转换器进行解析
   for (HttpMessageConverter<?> converter : this.messageConverters) {
      GenericHttpMessageConverter genericConverter = (converter instanceof GenericHttpMessageConverter ?
            (GenericHttpMessageConverter<?>) converter : null);
      // 判断是否可写
      if (genericConverter != null ?
            ((GenericHttpMessageConverter) converter).canWrite(targetType, valueType, selectedMediaType) :
            converter.canWrite(valueType, selectedMediaType)) {

         // 写之前的处理
         body = getAdvice().beforeBodyWrite(body, returnType, selectedMediaType,
               (Class<? extends HttpMessageConverter<?>>) converter.getClass(),
               inputMessage, outputMessage);

         if (body != null) {
            Object theBody = body;
            LogFormatUtils.traceDebug(logger, traceOn ->
                  "Writing [" + LogFormatUtils.formatValue(theBody, !traceOn) + "]");

            // 添加 Content-Disposition 头
            addContentDispositionHeader(inputMessage, outputMessage);
            if (genericConverter != null) {
               // 进行写操作
               genericConverter.write(body, targetType, selectedMediaType, outputMessage);
            }
            else {
               // 进行写操作
               ((HttpMessageConverter) converter).write(body, selectedMediaType, outputMessage);
            }
         }
         else {
            if (logger.isDebugEnabled()) {
               logger.debug("Nothing to write: null body");
            }
         }
         return;
      }
   }
}
```

在这段代码中主要处理流程如下：

1. 判断messageConverters集合中的单个元素是否可以进行写操作，如果可以进行写操作则进行下面处理。
   1. 写操作前的前置处理。
   2. 添加Content-Disposition头数据。
   3. 进行写操作。

在上述流程中主要关注写操作，具体进行写操作的类是org.springframework.http.converter.AbstractGenericHttpMessageConverter，具体处理代码如下：

```java
@Override
public final void write(final T t, @Nullable final Type type, @Nullable MediaType contentType,
      HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

   final HttpHeaders headers = outputMessage.getHeaders();
   addDefaultHeaders(headers, t, contentType);

   if (outputMessage instanceof StreamingHttpOutputMessage) {
      StreamingHttpOutputMessage streamingOutputMessage = (StreamingHttpOutputMessage) outputMessage;
      streamingOutputMessage.setBody(outputStream -> writeInternal(t, type, new HttpOutputMessage() {
         @Override
         public OutputStream getBody() {
            return outputStream;
         }
         @Override
         public HttpHeaders getHeaders() {
            return headers;
         }
      }));
   }
   else {
      writeInternal(t, type, outputMessage);
      outputMessage.getBody().flush();
   }
}
```

在write代码处理中主要逻辑如下：

1. 获取response的头数据。
2. 向response的头数据中添加默认数据。
3. 写出数据。

首先进行步骤一和步骤二的数据比较，在本例中步骤一中的数据信息如图所示

![image-20210415150932134](images/image-20210415150932134.png)

经过步骤二后的数据信息如图所示：

![image-20210415150951141](images/image-20210415150951141.png)

最后对写出数据进行详细分析，先查看MappingJackson2HttpMessageConverter对象的类图，具体如图所示:

![MappingJackson2HttpMessageConverter](images/MappingJackson2HttpMessageConverter.png)

在写出数据时所使用的方法是writeInternal，该方法是一个抽象方法，在本例中具体实现是org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter#writeInternal，具体实现代码如下：

```java
@Override
protected void writeInternal(Object object, @Nullable Type type, HttpOutputMessage outputMessage)
      throws IOException, HttpMessageNotWritableException {

   MediaType contentType = outputMessage.getHeaders().getContentType();
   JsonEncoding encoding = getJsonEncoding(contentType);
   JsonGenerator generator = this.objectMapper.getFactory().createGenerator(outputMessage.getBody(), encoding);
   try {
      writePrefix(generator, object);

      Object value = object;
      Class<?> serializationView = null;
      FilterProvider filters = null;
      JavaType javaType = null;

      if (object instanceof MappingJacksonValue) {
         MappingJacksonValue container = (MappingJacksonValue) object;
         value = container.getValue();
         serializationView = container.getSerializationView();
         filters = container.getFilters();
      }
      if (type != null && TypeUtils.isAssignable(type, value.getClass())) {
         javaType = getJavaType(type, null);
      }

      ObjectWriter objectWriter = (serializationView != null ?
            this.objectMapper.writerWithView(serializationView) : this.objectMapper.writer());
      if (filters != null) {
         objectWriter = objectWriter.with(filters);
      }
      if (javaType != null && javaType.isContainerType()) {
         objectWriter = objectWriter.forType(javaType);
      }
      SerializationConfig config = objectWriter.getConfig();
      if (contentType != null && contentType.isCompatibleWith(MediaType.TEXT_EVENT_STREAM) &&
            config.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
         objectWriter = objectWriter.with(this.ssePrettyPrinter);
      }
      objectWriter.writeValue(generator, value);

      writeSuffix(generator, object);
      generator.flush();
   }
   catch (InvalidDefinitionException ex) {
      throw new HttpMessageConversionException("Type definition error: " + ex.getType(), ex);
   }
   catch (JsonProcessingException ex) {
      throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getOriginalMessage(), ex);
   }
}
```

在这个方法过程中主要目的是将对象object进行反序列化，在本例中object的数据信息如下：

![image-20210415152712825](images/image-20210415152712825.png)

经过该方法具体序列化数据会存储在`((UTF8JsonGenerator) generator)._outputBuffer`中，通过调试编写下面代码

```java
new String(((UTF8JsonGenerator) generator)._outputBuffer)
```

将这个方法在调试时运行，可以看到如下结果：

![image-20210415152837968](images/image-20210415152837968.png)

在得到这个数据后就可以进行写出操作，具体方法是com.fasterxml.jackson.core.JsonGenerator#flush。





#### 异常信息处理

下面将对异常信息处理进行分析，具体处理代码如下：

```
if (body != null) {
   Set<MediaType> producibleMediaTypes =
         (Set<MediaType>) inputMessage.getServletRequest()
               .getAttribute(HandlerMapping.PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE);

   if (isContentTypePreset || !CollectionUtils.isEmpty(producibleMediaTypes)) {
      throw new HttpMessageNotWritableException(
            "No converter for [" + valueType + "] with preset Content-Type '" + contentType + "'");
   }
   throw new HttpMediaTypeNotAcceptableException(this.allSupportedMediaTypes);
}
```

在这段代码中会抛出两个异常，这两个异常抛出的前提分别是：

1. 数据isContentTypePreset为true或者producibleMediaTypes不为空抛出HttpMessageNotWritableException异常。
2. 在不满足isContentTypePreset为true或者producibleMediaTypes不为空条件时抛出HttpMediaTypeNotAcceptableException异常。

在这两个异常处理前还有一个公共的条件：body不为空。







## HttpMessageConverter 总结

本节对HttpMessageConverter中返回JSON对象做了相关分析，具体处理依赖jackson进行，在这个处理过程中主要的行为操作是遍历HttpMessageConverter集合，判断哪一个元素可以进行处理，当确认能够处理的元素后进行写操作将数据写出。本节主要做的是大体流程的分析，在SpringMVC中关于HttpMessageConverter 的实现有太多不做一一分析。