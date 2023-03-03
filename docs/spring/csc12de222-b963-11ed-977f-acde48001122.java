package com.huifer.bilibili.inteface;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 包扫描工具
 */
public class ScanPackageUtils {
    public static final String FILE_PROTOCOL = "file";
    public static final String UTF_8 = "UTF-8";
    public static final String JAR_PROTOCOL = "jar";

    public static void main(String[] args) {
        Set<Class<?>> classes = scanPackage("com.jw.ypt");

    }

    public static Set<Class<?>> scanPackage(String scanPackPath) {

        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        // 是否循环迭代
        boolean recursive = true;
        String packageName = scanPackPath;
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(
                    packageDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if (FILE_PROTOCOL.equalsIgnoreCase(protocol)) {
                    calcFileProtocol(classes, recursive, packageName, url);
                } else if (JAR_PROTOCOL.equalsIgnoreCase(protocol)) {
                    calcJarProtocol(packageDirName, packageName, classes, url);
                }
            }
        } catch (Exception e) {
        }

        return classes;
    }

    /**
     * file 的扫描
     *
     * @param classes     返回结果
     * @param recursive   是否递归
     * @param packageName 包名
     * @param url         路径
     * @throws UnsupportedEncodingException
     */
    private static void calcFileProtocol(Set<Class<?>> classes, boolean recursive, String packageName, URL url) throws UnsupportedEncodingException {
        String filePath = URLDecoder.decode(url.getFile(), UTF_8);
        findAndAddClassesInPackageByFile(packageName, filePath,
                recursive, classes);
    }

    /**
     * jar 的扫描
     *
     * @param packageDirName 包路径
     * @param packageName    包名
     * @param classes        返回结果
     * @throws ClassNotFoundException
     */
    private static void calcJarProtocol(String packageDirName, String packageName, Set<Class<?>> classes, URL url) throws IOException, ClassNotFoundException {

        JarFile jar;
        // 获取jar
        jar = ((JarURLConnection) url.openConnection())
                .getJarFile();
        Enumeration<JarEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String name = entry.getName();
            if (name.charAt(0) == '/') {
                name = name.substring(1);
            }
            if (name.startsWith(packageDirName)) {
                int idx = name.lastIndexOf('/');
                if (idx != -1) {
                    packageName = name.substring(0, idx)
                            .replace('/', '.');
                }
                // 如果是一个.class文件 而且不是目录
                if (name.endsWith(".class")
                        && !entry.isDirectory()) {
                    // 去掉后面的".class" 获取真正的类名
                    String className = name.substring(
                            packageName.length() + 1, name.length() - 6);

                    // 添加到classes
                    classes.add(Class
                            .forName(packageName + '.'
                                    + className));

                }
            }
        }

    }

    public static void findAndAddClassesInPackageByFile(String packageName,
                                                        String packagePath, final boolean recursive, Set<Class<?>> classes) {
        File dir = new File(packagePath);
        if (twoBooleanOr(!dir.exists(), !dir.isDirectory())) {
            return;
        }
        File[] dirfiles = dir.listFiles(file -> twoBooleanOr(recursive && file.isDirectory(), file.getName().endsWith(".class")));

        if (null != dirfiles && dirfiles.length > 0) {

            for (File file : dirfiles) {
                if (file.isDirectory()) {
                    findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
                } else {
                    String className = file.getName().substring(0, file.getName().length() - 6);
                    try {
                        classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                    } catch (ClassNotFoundException e) {
                    }
                }
            }
        }

    }

    private static boolean twoBooleanOr(boolean b, boolean b2) {
        return b || b2;
    }
}
