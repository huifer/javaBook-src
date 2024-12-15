package com.huifer.hbase.api;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;

public class ClientAppMain {

    private static Connection connection = null;

    public static void main(String[] args) throws Exception {

        createTable("test_fr", "name", "pwd", "ex");
        getTableInfo("test_fr");
    }

    /**
     * 单例链接对象
     */
    public static Connection connection() throws IOException {
        if (connection == null) {
            synchronized (ClientAppMain.class) {
                Configuration configuration = HBaseConfiguration.create();
                connection = ConnectionFactory.createConnection(configuration);
            }
        }
        return connection;
    }

    public static void getTableInfo(String tableName) throws IOException {
        Table table = connection().getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        ResultScanner scanner = table.getScanner(scan);
        for (Result result : scanner) {
            System.out.println("result = " + result);
        }
    }

    public static void createTable(String createTableName, String... cols) throws IOException {

        TableName tableName = TableName.valueOf(createTableName);
        Admin admin = connection().getAdmin();
        if (admin.tableExists(tableName)) {
            System.out.println("表已存在！");
        } else {
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            for (String col : cols) {
                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(col);
                hTableDescriptor.addFamily(hColumnDescriptor);
            }
            admin.createTable(hTableDescriptor);
        }
    }
}
