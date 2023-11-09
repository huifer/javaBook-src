package com.huifer.bigfile.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;

public class SplitMergeFile {

    private static final int SIZE = 1024 * 1024;

    public static void main(String[] args) throws IOException {
        File file = new File("D:\\data\\appfiles\\25-Ringler-VoronoiDelaunay.pdf");
        File dir = new File("D:\\data\\appfiles\\parties");
        splitFile(file);
        mergeFile(dir, "pdf");

    }


    public static void mergeFile(File dir, String fileFormat) throws IOException {
        File[] files = dir.listFiles();

        Properties prop;
        Integer count = null;
        String mergeFilename = null;
        for (File file : files) {
            String filename = file.getAbsoluteFile().getAbsolutePath();
            String prefix = filename.substring(filename.lastIndexOf(".") + 1);
            if ("properties".equals(prefix)) {
                prop = new Properties();
                FileInputStream fis = new FileInputStream(file);
                prop.load(fis);
                count = Integer.parseInt(prop.getProperty("partcount"));
                mergeFilename = prop.getProperty("filename");
            }
        }

        ArrayList<FileInputStream> al = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            al.add(new FileInputStream(new File(dir.getPath() + "/" + (i + 1) + "." + fileFormat)));
        }

        Enumeration en = Collections.enumeration(al);
        SequenceInputStream sis = new SequenceInputStream(en);
        FileOutputStream fos = new FileOutputStream(new File(dir, mergeFilename));
        byte[] buf = new byte[SIZE];
        int len = 0;
        while ((len = sis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        fos.close();
        sis.close();
    }


    public static void splitFile(File file) throws IOException {
        String fileFormat = file.getName().substring(file.getName().lastIndexOf('.'));
        File parentDir = file.getParentFile();

        File destFile = new File(parentDir, "parties");
        if (!destFile.exists()) {
            destFile.mkdirs();
        }
        FileInputStream fis = new FileInputStream(file);
        byte[] buf = new byte[SIZE];
        int len = 0;
        int count = 1;
        FileOutputStream fos = null;
        while ((len = fis.read(buf)) != -1) {
            fos = new FileOutputStream(new File(destFile, (count++) + fileFormat));
            fos.write(buf, 0, len);
            fos.close();
        }
        fis.close();
        Properties prop = new Properties();
        prop.setProperty("partcount", (count - 1) + "");
        prop.setProperty("filename", file.getName());
        fos = new FileOutputStream(new File(destFile, count + ".properties"));
        prop.store(fos, "FileInformation");
        fos.close();
    }


}

