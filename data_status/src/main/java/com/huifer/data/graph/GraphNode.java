package com.huifer.data.graph;

import java.util.Objects;

/**
 * <p>Title : GraphNode </p>
 * <p>Description : 图结点</p>
 *
 * @author huifer
 * @date 2019-05-06
 */
public class GraphNode<T> {

    T data;


    public GraphNode(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GraphNode)) {
            return false;
        }
        GraphNode<?> graphNode = (GraphNode<?>) o;
        return Objects.equals(data, graphNode.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
