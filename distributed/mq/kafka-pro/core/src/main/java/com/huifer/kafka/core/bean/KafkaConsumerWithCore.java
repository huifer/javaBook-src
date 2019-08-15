package com.huifer.kafka.core.bean;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.Properties;

@Slf4j
@ToString
public class KafkaConsumerWithCore {

	@Getter
	@Setter
	private String propertiesFileName;
	@Getter
	@Setter
	private Properties properties;
	@Getter
	@Setter
	private String topic;


	private KafkaConsumer consumerConnector;

	public void close() {
		consumerConnector.close();
	}


	protected void init() {
		loadProperties();
		consumerConnector = new KafkaConsumer(this.properties);
	}

	protected void loadProperties() {
		properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(
					propertiesFileName
			));
		} catch (IOException e) {
			log.error("kafka-consumer配置文件不存在,文件名={}", propertiesFileName);
			throw new IllegalArgumentException("配置文件不存在");
		}

	}

}
