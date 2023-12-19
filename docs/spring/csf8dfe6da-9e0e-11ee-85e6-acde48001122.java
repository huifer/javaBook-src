package com.huifer.kafka.core.annot;

import com.huifer.kafka.core.bean.KafkaConsumerWithCore;
import com.huifer.kafka.core.bean.KafkaProducerWithCore;
import com.huifer.kafka.core.excephandler.ExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KafkaHandler {

    private KafkaConsumerWithCore kafkaConsumer;
    private KafkaProducerWithCore kafkaProducer;


    private List<ExceptionHandler> exceptionHandlers;
    private KafkaHandlerMeta kafkaHandlerMeta;
}
