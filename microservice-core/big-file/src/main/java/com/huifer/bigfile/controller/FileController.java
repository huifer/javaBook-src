package com.huifer.bigfile.controller;

import com.huifer.bigfile.services.BandwidthLimiter;
import com.huifer.bigfile.services.DownloadLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限速查询
 */
@RestController
public class FileController {
    public static final AtomicInteger DOWNLOAD_NUM = new AtomicInteger();
    public static List<String> s = new CopyOnWriteArrayList<>();

    @GetMapping("/")
    public String heat() {
        return "heat";
    }

    @RequestMapping(value = "/download")
    public void getDownload(String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
        s.add(name);
        BandwidthLimiter bandwidthLimiter = new BandwidthLimiter(s.isEmpty() ? 1024 : 1024 / s.size());
        String fullPath = "C:\\Users\\1\\Downloads\\" + name;
        File downloadFile = new File(fullPath);

        ServletContext context = request.getServletContext();
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);
        response.setHeader("Accept-Ranges", "bytes");
        long downloadSize = downloadFile.length();
        long fromPos = 0, toPos = 0;
        if (request.getHeader("Range") == null) {
            response.setHeader("Content-Length", downloadSize + "");
        } else {
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            String range = request.getHeader("Range");
            String bytes = range.replaceAll("bytes=", "");
            String[] ary = bytes.split("-");
            fromPos = Long.parseLong(ary[0]);
            if (ary.length == 2) {
                toPos = Long.parseLong(ary[1]);
            }
            int size;
            if (toPos > fromPos) {
                size = (int) (toPos - fromPos);
            } else {
                size = (int) (downloadSize - fromPos);
            }
            response.setHeader("Content-Length", size + "");
            downloadSize = size;
        }
        OutputStream out = null;
        DownloadLimiter downloadLimiter = null;

        try {
            InputStream fileInputStream = new FileInputStream(fullPath);
            downloadLimiter = new DownloadLimiter(fileInputStream, bandwidthLimiter);
            // 设置下载起始位置
            if (fromPos > 0) {
                downloadLimiter.skip(fromPos);
            }
            // 缓冲区大小
            int bufLen = (int) (downloadSize < 2048 ? downloadSize : 2048);
            byte[] buffer = new byte[bufLen];
            int num;
            int count = 0; // 当前写到客户端的大小
            out = response.getOutputStream();
            while ((num = downloadLimiter.read(buffer)) != -1) {
                bandwidthLimiter.setMaxRate(1024 / s.size());
                System.out.println("当前速度=" + (s.size()));
                out.write(buffer, 0, num);
                count += num;
                if (downloadSize - count < bufLen) {
                    bufLen = (int) (downloadSize - count);
                    if (bufLen == 0) {
                        System.out.println("文件下载完成");
                        s.remove(name);
                        break;
                    }
                    buffer = new byte[bufLen];
                }
            }

            response.flushBuffer();
        } catch (IOException e) {
            System.out.println("用户取消下载");
            s.remove(name);
            bandwidthLimiter.setMaxRate(s.isEmpty() ? 1024 : 1024 / DOWNLOAD_NUM.get());
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != downloadLimiter) {
                try {
                    downloadLimiter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
