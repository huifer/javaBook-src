package com.huifer.kafka.core.annot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huifer.kafka.core.bean.*;
import com.huifer.kafka.core.excephandler.ExceptionHandler;
import com.huifer.kafka.core.handlers.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Slf4j
public class KafkaClient implements ApplicationContextAware {
	private ApplicationContext applicationContext;


	private List<KafkaHandlerMeta> meta = new ArrayList<>();

	private List<KafkaHandler> kafkaHandlers = new ArrayList<>();

	public void init() {
		meta = getKafkaHandlerMeta();

		if (meta.isEmpty()) {
			throw new RuntimeException("KafkaHandlerMeta null");
		}

		for (final KafkaHandlerMeta kafkaHandlerMeta : meta) {
			createKafkaHandler(kafkaHandlerMeta);
		}
	}

	private void createKafkaHandler(final KafkaHandlerMeta kafkaHandlerMeta) {
		Class<? extends Object> paramClazz = kafkaHandlerMeta.getParameterType();

		KafkaProducerWithCore kafkaProducer = createProducer(kafkaHandlerMeta);
		List<ExceptionHandler> excepHandlers = createExceptionHandlers(kafkaHandlerMeta);

		MessageHandler beanMessageHandler = null;
		if (paramClazz.isAssignableFrom(JSONObject.class)) {
			beanMessageHandler = createObjectHandler(kafkaHandlerMeta,
					kafkaProducer, excepHandlers);
		} else if (paramClazz.isAssignableFrom(JSONArray.class)) {
			beanMessageHandler = createObjectsHandler(kafkaHandlerMeta,
					kafkaProducer, excepHandlers);
		} else if (List.class.isAssignableFrom(Document.class)) {
			beanMessageHandler = createDocumentHandler(kafkaHandlerMeta, kafkaProducer, excepHandlers);
		} else if (List.class.isAssignableFrom(paramClazz)) {
			beanMessageHandler = createBeansHandler(kafkaHandlerMeta,
					kafkaProducer, excepHandlers);
		} else {
			beanMessageHandler = createBeanHandler(kafkaHandlerMeta,
					kafkaProducer, excepHandlers);
		}

		KafkaConsumerWithCore kafkaConsumer = createConsumer(kafkaHandlerMeta,
				beanMessageHandler);
//		kafkaConsumer.startup();

		KafkaHandler kafkaHandler = new KafkaHandler(kafkaConsumer,
				kafkaProducer, excepHandlers, kafkaHandlerMeta);

		kafkaHandlers.add(kafkaHandler);

	}

	protected KafkaConsumerWithCore createConsumer(
			final KafkaHandlerMeta kafkaHandlerMeta,
			MessageHandler beanMessageHandler) {
		KafkaConsumerWithCore kafkaConsumer = null;


		kafkaConsumer = new KafkaConsumerWithCore(
				kafkaHandlerMeta.getInputConsumer().propertiesFile(),
				kafkaHandlerMeta.getOutputProducer().defaultTopic(),
				beanMessageHandler);


		return kafkaConsumer;
	}

	protected BeanMessageHandler<Object> createBeanHandler(
			final KafkaHandlerMeta kafkaHandlerMeta,
			final KafkaProducerWithCore kafkaProducer,
			List<ExceptionHandler> excepHandlers) {

		@SuppressWarnings("rawtypes")
		BeanMessageHandler beanMessageHandler = new BeanMessageHandler(
				kafkaHandlerMeta.getParameterType(), excepHandlers) {
			@Override
			protected void doExecuteBean(Object bean) {
				invokeHandler(kafkaHandlerMeta, kafkaProducer, bean);
			}

		};

		return beanMessageHandler;
	}

	protected BeansMessageHandler<Object> createBeansHandler(
			final KafkaHandlerMeta kafkaHandlerMeta,
			final KafkaProducerWithCore kafkaProducer,
			List<ExceptionHandler> excepHandlers) {

		@SuppressWarnings("rawtypes")
		BeansMessageHandler beanMessageHandler = new BeansMessageHandler(
				kafkaHandlerMeta.getParameterType(), excepHandlers) {
			@Override
			protected void doExecuteBeans(List bean) {
				invokeHandler(kafkaHandlerMeta, kafkaProducer, bean);
			}

		};

		return beanMessageHandler;
	}


	protected DocumentMessageHandler createDocumentHandler(
			final KafkaHandlerMeta kafkaHandlerMeta,
			final KafkaProducerWithCore kafkaProducer,
			List<ExceptionHandler> excepHandlers) {

		DocumentMessageHandler documentMessageHandler = new DocumentMessageHandler(excepHandlers) {
			@Override
			protected void doExecuteDocument(Document document) {
				invokeHandler(kafkaHandlerMeta, kafkaProducer, document);
			}
		};

		return documentMessageHandler;
	}

	protected ObjectsMessageHandler<JSONArray> createObjectsHandler(
			final KafkaHandlerMeta kafkaHandlerMeta,
			final KafkaProducerWithCore kafkaProducer,
			List<ExceptionHandler> excepHandlers) {

		ObjectsMessageHandler<JSONArray> objectMessageHandler = new ObjectsMessageHandler<JSONArray>(excepHandlers) {
			@Override
			protected void doExecuteObjects(JSONArray jsonArray) {
				invokeHandler(kafkaHandlerMeta, kafkaProducer, jsonArray);
			}
		};

		return objectMessageHandler;
	}

	protected ObjectMessageHandler<JSONObject> createObjectHandler(
			final KafkaHandlerMeta kafkaHandlerMeta,
			final KafkaProducerWithCore kafkaProducer,
			List<ExceptionHandler> excepHandlers) {

		ObjectMessageHandler<JSONObject> objectMessageHandler = new ObjectMessageHandler<JSONObject>(excepHandlers) {
			@Override
			protected void doExecuteObject(JSONObject jsonObject) {
				invokeHandler(kafkaHandlerMeta, kafkaProducer, jsonObject);
			}

		};

		return objectMessageHandler;
	}

	private void invokeHandler(final KafkaHandlerMeta kafkaHandlerMeta,
	                           final KafkaProducerWithCore kafkaProducer, Object parameter) {
		Method kafkaHandlerMethod = kafkaHandlerMeta.getMethod();
		try {
			Object result = kafkaHandlerMethod.invoke(kafkaHandlerMeta.getBean(), parameter);

			if (kafkaProducer != null) {
				if (result instanceof JSONObject)
					kafkaProducer.send(((JSONObject) result).toJSONString());
				else if (result instanceof JSONArray)
					kafkaProducer.send(((JSONArray) result).toJSONString());
				else if (result instanceof Document)
					kafkaProducer.send(((Document) result).getTextContent());
				else
					kafkaProducer.send(JSON.toJSONString(result));
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
		}
	}

	private List<ExceptionHandler> createExceptionHandlers(
			final KafkaHandlerMeta kafkaHandlerMeta) {
		List<ExceptionHandler> excepHandlers = new ArrayList<>();
		Map<ErrorHandler, Method> errorHandlers = kafkaHandlerMeta.getErrorHandlers();
		for (Map.Entry<ErrorHandler, Method> errorHandler : errorHandlers.entrySet()) {
			ExceptionHandler exceptionHandler = new ExceptionHandler() {
				public boolean support(Throwable t) {
					return errorHandler.getKey().exception() == t.getClass();
				}

				public void handle(Throwable t, String message) {

					Method excepHandlerMethod = errorHandler.getValue();
					try {
						excepHandlerMethod.invoke(kafkaHandlerMeta.getBean(),
								t, message);

					} catch (Exception e) {
						throw new IllegalStateException(e);
					}
				}
			};

			excepHandlers.add(exceptionHandler);
		}

		return excepHandlers;
	}

	private KafkaProducerWithCore createProducer(KafkaHandlerMeta kafkaHandlerMeta) {
		KafkaProducerWithCore kafkaProducer = null;

		if (kafkaHandlerMeta.getOutputProducer() != null) {
			kafkaProducer = new KafkaProducerWithCore(kafkaHandlerMeta.getOutputProducer().propertiesFile(),
					kafkaHandlerMeta.getOutputProducer().defaultTopic());
		}
		return kafkaProducer;
	}

	private List<KafkaHandlerMeta> getKafkaHandlerMeta() {

		List<KafkaHandlerMeta> meta = new ArrayList<>();

		String[] kafkaHandlerBeanNames = applicationContext.getBeanNamesForAnnotation(KafkaHandlers.class);
		for (String kafkaHandlerBeanName : kafkaHandlerBeanNames) {
			Object kafkaHandlerBean = applicationContext.getBean(kafkaHandlerBeanName);
			Class<? extends Object> kafkaHandlerBeanClazz = kafkaHandlerBean
					.getClass();
			Map<Class<? extends Annotation>, Map<Method, Annotation>> mapData = extractAnnotationMaps(kafkaHandlerBeanClazz);

			meta.addAll(convertAnnotationMaps2Meta(mapData, kafkaHandlerBean));
		}

		return meta;
	}

	private Collection<? extends KafkaHandlerMeta> convertAnnotationMaps2Meta(Map<Class<? extends Annotation>, Map<Method, Annotation>> mapData, Object bean) {
		List<KafkaHandlerMeta> meta = new ArrayList<>();

		Map<Method, Annotation> inputConsumerMap = mapData
				.get(InputConsumer.class);
		Map<Method, Annotation> outputProducerMap = mapData
				.get(OutputProducer.class);
		Map<Method, Annotation> exceptionHandlerMap = mapData
				.get(ErrorHandler.class);

		for (Map.Entry<Method, Annotation> entry : inputConsumerMap.entrySet()) {
			InputConsumer inputConsumer = (InputConsumer) entry.getValue();

			KafkaHandlerMeta kafkaHandlerMeta = new KafkaHandlerMeta();

			kafkaHandlerMeta.setBean(bean);
			kafkaHandlerMeta.setMethod(entry.getKey());

			Parameter[] kafkaHandlerParameters = entry.getKey().getParameters();
			if (kafkaHandlerParameters.length != 1)
				throw new IllegalArgumentException(
						"The kafka handler method can contains only one parameter.");
			kafkaHandlerMeta.setParameterType(kafkaHandlerParameters[0].getType());

			kafkaHandlerMeta.setInputConsumer(inputConsumer);

			if (outputProducerMap != null
					&& outputProducerMap.containsKey(entry.getKey()))
				kafkaHandlerMeta.setOutputProducer((OutputProducer) outputProducerMap
						.get(entry.getKey()));

			if (exceptionHandlerMap != null)
				for (Map.Entry<Method, Annotation> excepHandlerEntry : exceptionHandlerMap.entrySet()) {
					ErrorHandler eh = (ErrorHandler) excepHandlerEntry.getValue();
					if (StringUtils.isEmpty(eh.topic()) ||
							eh.topic().equals(inputConsumer.topic())) {
						kafkaHandlerMeta.addErrorHandlers(eh, excepHandlerEntry.getKey());
					}
				}

			meta.add(kafkaHandlerMeta);
		}

		return meta;
	}

	private Map<Class<? extends Annotation>, Map<Method, Annotation>> extractAnnotationMaps(Class<?> clazz) {
		AnnotationTranversor<Class<? extends Annotation>, Method, Annotation> annotationTranversor = new AnnotationTranversor<>(
				clazz);

		Map<Class<? extends Annotation>, Map<Method, Annotation>> data = annotationTranversor
				.tranverseAnnotation(new AnnotationHandler<Class<? extends Annotation>, Method, Annotation>() {
					public void handleMethodAnnotation(
							Class<? extends Object> clazz, Method method, Annotation annotation, TranversorContext<Class<? extends Annotation>, Method, Annotation> context) {
						if (annotation instanceof InputConsumer)
							context.addEntry(InputConsumer.class, method, annotation);
						else if (annotation instanceof OutputProducer)
							context.addEntry(OutputProducer.class, method, annotation);
						else if (annotation instanceof ErrorHandler)
							context.addEntry(ErrorHandler.class, method, annotation);
					}

					public void handleClassAnnotation(
							Class<? extends Object> clazz, Annotation annotation, TranversorContext<Class<? extends Annotation>, Method, Annotation> context) {
						if (annotation instanceof KafkaHandlers)
							log.warn(
									"There is some other annotation {} rather than @KafkaHandlers in the handler class {}.",
									annotation.getClass().getName(),
									clazz.getName());
					}
				});

		return data;

	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
