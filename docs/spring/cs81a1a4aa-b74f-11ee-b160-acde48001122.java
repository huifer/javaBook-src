package com.huifer.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import java.net.URI;

import java.io.InputStream;

public class HdfsDemo {
    public static void main(String[] args) throws Exception {


        String url = "hdfs://47.98.225.144:9000/hh/input/1.txt";
        Configuration conf = new Configuration();
//        conf.set("fs.defaultFS", "hdfs://47.98.225.144:9000");

        FileSystem huifer = FileSystem.get(new URI("hdfs://47.98.225.144:9000"), conf, "huifer");

        FileSystem fileSystem = FileSystem.get(conf);
        InputStream in = null;

        try {
            in = fileSystem.open(new Path(url));
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}