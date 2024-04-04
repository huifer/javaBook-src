package com.huifer.jdk.gather;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * <p>Title : UnchangeableGather </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-27
 */
public class UnchangeableGather {

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<Integer>();
        integers.add(1);
        Collection<Integer> integers1 = Collections.unmodifiableCollection(integers);
        integers1.add(1);
        System.out.println(integers.size());
    }

}
