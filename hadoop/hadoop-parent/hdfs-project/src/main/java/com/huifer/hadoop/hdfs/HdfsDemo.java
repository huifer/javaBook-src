package com.huifer.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.InputStream;
import java.net.URI;

public class HdfsDemo {
    public static void main(String[] args) throws Exception {

        Configuration entries = new Configuration();
        String url = "hdfs://:9000/hh/input/1.txt";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(url),conf);
        InputStream in = null;
        try
        {
            in = fs.open(new Path(url));
            IOUtils.copyBytes(in,System.out,4096,false);
        }finally {
            IOUtils.closeStream(in);
        }
    }
}