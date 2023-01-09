package com.huifer.jdk.serializer;

import io.protostuff.Tag;
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

    @Tag(1)
    private String name;
    @Tag(2)
    private int age;
}
