package com.huifer.data.graph;

/**
 * <p>Title : DigraphMatrix </p>
 * <p>Description : 邻接矩阵有向图</p>
 *
 * @author huifer
 * @date 2019-05-06
 */
public class DigraphMatrix {

    /**
     * 顶点个数
     */
    int size;

    /**
     * 顶点列表
     */
    GraphNode[] vertex;
    /**
     * 邻接矩阵
     */
    int[][] matrix;

    public DigraphMatrix(GraphNode[] vertex, GraphNode[][] edge) {
        this.vertex = vertex;
        this.size = vertex.length;
        this.matrix = new int[size][size];

        for (GraphNode[] nodes : edge) {
            int g1 = getGraphNodeIndex(nodes[0]);
            int g2 = getGraphNodeIndex(nodes[1]);
            matrix[g1][g2] = 1;
        }
    }

    public static void main(String[] args) {
        GraphNode[] gs = new GraphNode[]{
                new GraphNode<>("a"),
                new GraphNode<>("b"),
                new GraphNode<>("c")
        };

        GraphNode[][] edge = new GraphNode[][]{
                {new GraphNode<>("a"), new GraphNode<>("b")},
                {new GraphNode<>("b"), new GraphNode<>("a")},
                {new GraphNode<>("a"), new GraphNode<>("c")},
        };
        DigraphMatrix youxiangtu;
        youxiangtu = new DigraphMatrix(gs, edge);
        youxiangtu.print();
    }

    /**
     * 输出邻接矩阵
     */
    public void print() {
        for (int[] ints : matrix) {
            for (int j : ints) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    /**
     * 获取结点的下表
     */
    private int getGraphNodeIndex(GraphNode node) {
        for (int i = 0; i < this.vertex.length; i++) {
            if (this.vertex[i].equals(node)) {
                return i;
            }

        }
        return -1;
    }
}
