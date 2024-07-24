package com.huifer.kafka.serializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>Title : Student </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {

    private String name;
    private String teacherName;

}
