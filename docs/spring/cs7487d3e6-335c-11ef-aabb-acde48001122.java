package com.huifer.utils.utils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 备份文件
 *
 * @Date: 2019-11-06
 */
public class BackFileChx {


    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:/back.properties"));
        properties.load(bufferedReader);
        String source = properties.getProperty("source");
        String target = properties.getProperty("target");
        String time = properties.getProperty("time");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = format.parse(time);
        long time1 = parse.getTime();
        runner(source, target, time1);

    }

    /**
     * 核心运行
     *
     * @param source 需要备份文件夹
     * @param target 备份到哪里文件夹
     * @param time   用户自定义时间戳
     * @throws IOException
     */
    private static void runner(String source, String target, long time) throws IOException {
        // 1. 获取所有文件夹
        List<File> fileList = new ArrayList<>();
        List<File> folders = listFiles(new File(source), fileList);

        // 先确认备份文件夹是否才能在，不存在创建
        createFolder(target);
        folderBack(source, target, folders, time);
    }

    /**
     * 文件夹备份
     *
     * @param source  需要备份文件夹
     * @param target  备份到哪里文件夹
     * @param folders 文件夹列表
     * @param time    用户自定义时间戳
     * @throws IOException
     */
    private static void folderBack(String source, String target, List<File> folders, long time) throws IOException {
        System.out.println("执行中.....");
        for (File folder : folders) {
            // 备份文件夹中可能没有这个文件夹创建
            createFolder(folder.getAbsolutePath().replace(source, target));

            File[] files = folder.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    // 2. 对文件夹求所有文件的创建时间
                    long lastEditorTime = getLastEditorTime(file);
                    Long fileCreateTime = getFileCreateTime(file.getAbsolutePath());
                    boolean isCopy = false;
                    if (time > 0) {
                        isCopy = checkCopy(fileCreateTime, lastEditorTime, time);
                    } else {
                        isCopy = checkCopy(fileCreateTime, lastEditorTime, today());
                    }
                    fileWork(source, target, file, isCopy);
                }
            }
        }
    }

    /**
     * 文件工作
     *
     * @param source 需要备份文件夹
     * @param target 备份到哪里文件夹
     * @param file   备份文件
     * @param isCopy 是否需要备份
     * @throws IOException
     */
    private static void fileWork(String source, String target, File file, boolean isCopy) throws IOException {
        if (isCopy) {
            // 如果需要备份则创建
            // absolutePath 这个文件是需要创建的
            String absolutePath = file.getAbsolutePath();
            String replace = absolutePath.replace(source, target);
            // 执行备份
            copyFileUsingFileChannels(new File(absolutePath), new File(replace));
        }
    }

    /**
     * 复制文件
     *
     * @param source 源文件
     * @param dest   目标文件
     * @throws IOException
     */
    private static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
        }
    }

    /**
     * 创建文件夹
     *
     * @param dirPath
     */
    private static void createFolder(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * 今天00:00
     *
     * @return
     */
    private static long today() {
        Long time = System.currentTimeMillis();
        long zero = time / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        return zero;
    }

    /**
     * 获取昨天00:00
     */
    private static long yesterdayTime() {
        return today() - 24 * 60 * 60 * 1000;

    }

    /**
     * 获取文件创建时间
     *
     * @param filePath 文件地址
     * @return 时间戳
     */
    private static Long getFileCreateTime(String filePath) {
        File file = new File(filePath);
        try {
            Path path = Paths.get(filePath);
            BasicFileAttributeView bview = Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
            BasicFileAttributes attr = bview.readAttributes();
            return attr.creationTime().toMillis();
        } catch (Exception e) {
            e.printStackTrace();
            return file.lastModified();
        }
    }

    /**
     * 创建时间
     *
     * @param file
     * @throws IOException
     */
    @Deprecated
    private static void getFileCreateTime(File file) throws IOException {
        String strTime = null;

        Process p = Runtime.getRuntime().exec("cmd /C dir "
                + file.getAbsolutePath()
                + "/tc");
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.endsWith(".txt")) {
                strTime = line.substring(0, 17);
                break;
            }
        }
    }

    /**
     * 最后修改时间
     *
     * @param file
     */
    private static long getLastEditorTime(File file) {
        long lastEditorTime = file.lastModified();
        return lastEditorTime;
    }

    /**
     * 时间戳转换可视化时间
     *
     * @param timestamp 时间戳
     */
    private static void getLocaltime(long timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(cal.getTime()));
    }

    /**
     * 获取所有文件夹
     */
    private static List<File> listFiles(File file, List<File> fileList) {
        if (file.isDirectory()) {
            fileList.add(file);
            for (File listFile : file.listFiles()) {
                listFiles(listFile, fileList);
            }
        }
        return fileList;
    }

    /**
     * 当前文件是否需要备份
     *
     * @param createTime 创建时间
     * @param editorTime 修改时间
     * @param basetime   比较时间
     * @return
     */
    private static boolean checkCopy(long createTime, long editorTime, long basetime) {
        return createTime > basetime || editorTime > basetime;
    }


}
