package com.huifer.idgen.mysql;

/**
 * @author: wang
 * @description: 步长id
 */
public class StepIdDemo {

    /**
     * 假设有3个数据库a、b、c，设步长3，a的起始id=0,b的起始id=1,c的起始id=2\
     * <p>
     * a,0,3,6...
     * <p>
     * b,1,4,7...
     * <p>
     * c,2,5,8...
     * <p>
     * `id = 起始id+步长x操作次数`
     */
    public static void main(String[] args) {
        String[] strings = {"A", "B", "C"};
        int step = strings.length;
        int operationNumber = 10;

        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < operationNumber; j++) {
                String dbName = strings[i];
                int id = i + step * j;
                System.out.println(String.format("数据库%s,第%s次操作,id=%s", dbName, j, id));
            }
        }
    }
}