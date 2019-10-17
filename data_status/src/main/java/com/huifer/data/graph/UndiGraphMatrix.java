package com.huifer.data.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>Title : UndiGraphMatrix </p>
 * <p>Description : 无向图</p>
 *
 * @author huifer
 * @date 2019-05-06
 */
public class UndiGraphMatrix {

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

    public UndiGraphMatrix(GraphNode[] vertex, GraphNode[][] edge) {
        this.vertex = vertex;
        this.size = vertex.length;
        this.matrix = new int[size][size];

        for (GraphNode[] nodes : edge) {
            int g1 = getGraphNodeIndex(nodes[0]);
            int g2 = getGraphNodeIndex(nodes[1]);
            matrix[g1][g2] = 1;
            matrix[g2][g1] = 1;
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
                {new GraphNode<>("a"), new GraphNode<>("c")},
                {new GraphNode<>("b"), new GraphNode<>("c")},
        };
        UndiGraphMatrix wuxiangtu;
        wuxiangtu = new UndiGraphMatrix(gs, edge);
        wuxiangtu.print();

        System.out.println("深度优先");
        wuxiangtu.DFSTravel();
        System.out.println("广度优先");
        wuxiangtu.BFSTravel();

    }

    void DFSTravel() {
        // visit 用来记录是否被访问
        boolean[] visit = new boolean[size];
        for (int i = 0; i < this.size; i++) {
            visit[i] = false;
        }
        // 每个结点的访问情况
        DFSVGraph(0, visit);
        System.out.println("=========");
    }


    /**
     * 深度优先遍历
     */
    private void DFSVGraph(int i, boolean[] visited) {

        visited[i] = true;
        System.out.println(this.vertex[i].data);

        for (int j = 0; j < size; j++) {
            if (matrix[i][j] == 1 && !visited[j]) {
                DFSVGraph(j, visited);
            }
        }

    }

    /**
     * 广度优先
     */
    void BFSTravel() {
        // visit 用来记录是否被访问
        boolean[] visit = new boolean[size];
        for (int i = 0; i < this.size; i++) {
            visit[i] = false;
        }
        BFSVGraph(0, visit);
    }

    /**
     * 广度优先，判断是否被访问
     */
    private int getNodeIsVisted(int v, boolean[] visited) {
        for (int i = 0; i < size; i++) {
            if (matrix[v][i] == 1 && visited[i] == false) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 广度优先
     */
    private void BFSVGraph(int k, boolean[] visited) {
        visited[k] = true;
        System.out.println(this.vertex[k].data);
        Queue q = new LinkedList();
        q.offer(k);
        int v2;
        while (!q.isEmpty()) {
            int v1 = (int) q.remove();
            while ((v2 = getNodeIsVisted(v1, visited)) != -1) {
                visited[v2] = true;
                System.out.println(this.vertex[v2].data);
                q.offer(v2);
            }
        }
    }


    /**
     * 输出无向图的邻接矩阵
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
