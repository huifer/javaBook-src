package com.huifer.utils.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang.StringUtils;

public class ExtractZip {
    public static final int BUFFER_SIZE = 1024;

    /**
     * 解压 zip 文件
     *
     * @param zipFile zip 压缩文件
     * @param destDir zip 压缩文件解压后保存的目录
     * @return 返回 zip 压缩文件里的文件名的 list
     * @throws Exception
     */
    public static Map<String, String> unZip(File zipFile, String destDir) throws Exception {
        // 如果 destDir 为 null, 空字符串, 或者全是空格, 则解压到压缩文件所在目录
        if (StringUtils.isBlank(destDir)) {
            destDir = zipFile.getParent();
        }
        destDir = destDir.endsWith(File.separator) ? destDir : destDir + File.separator;
        ZipArchiveInputStream is = null;
        Map<String, String> map = new HashMap<>();
        try {
            // 处理中文乱码问题
            is = new ZipArchiveInputStream(new BufferedInputStream(new FileInputStream(zipFile), BUFFER_SIZE), "GBK",
                    false, true);
            ZipArchiveEntry entry = null;
            String zname = zipFile.getName();
            String substring = zname.substring(0, zname.lastIndexOf("."));
            String mubiaoString = destDir + File.separator + substring;//解壓文件目標路徑
            File file = new File(mubiaoString);
            if (!file.exists()) {
                file.mkdirs();
            }
            int newFileName = 1;
            while ((entry = is.getNextZipEntry()) != null) {
                String name = entry.getName();
                if (entry.isDirectory()) {
                    continue;
                } else {
                    OutputStream os = null;
                    try {
                        String suffix = name.substring(name.lastIndexOf("."));
                        map.put(name.substring(name.indexOf("/") + 1), newFileName + suffix);
                        os = new BufferedOutputStream(new FileOutputStream(new File(mubiaoString, newFileName + suffix)), BUFFER_SIZE);
                        IOUtils.copy(is, os);
                    } finally {
                        os.close();
                        IOUtils.closeQuietly(os);
                    }
                }
                newFileName++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            IOUtils.closeQuietly(is);
        }
        return map;
    }

    /**
     * 解压 zip 文件
     *
     * @param zipFile zip 压缩文件的路径
     * @param destDir zip 压缩文件解压后保存的目录
     * @return 返回 zip 压缩文件里的文件名的 list
     * @throws Exception
     */
    public static Map<String, String> unZip(String zipFile, String destDir) throws Exception {
        File zp = new File(zipFile);
        return unZip(zp, destDir);
    }

}