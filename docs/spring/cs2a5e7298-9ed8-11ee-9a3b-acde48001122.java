package com.huifer.bigfile.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流下载service
 */
@Service
public class LimiterFileService {
    /**
     * 下载计数器:记录有多少个下载请求
     */
    public static final AtomicInteger DOWNLOAD_NUM = new AtomicInteger();
    protected static final Logger log = LoggerFactory.getLogger(LimiterFileService.class);
    /**
     * 最大下载速度,单位：kb
     */
    @Value("${max.download.speed}")
    public int maxDownloadSpeed;


    public void downloadBreak(String name, HttpServletRequest request, HttpServletResponse response) {


    }


    /**
     * 限流下载
     * // TODO: 2019/9/2 下载暂停会出现异常
     *
     * @param exportUrl 下载文件url
     * @param speed     速度
     * @param response  response
     */
    public void downloadLimit(String exportUrl, int speed, HttpServletResponse response) {
        OutputStream out = null;
        String fileName = exportUrl.substring(exportUrl.lastIndexOf('/') + 1);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        int downloadSpeed = speed;

        try (
                InputStream fileInputStream = new FileInputStream(exportUrl);
        ) {
            BandwidthLimiter bandwidthLimiter = new BandwidthLimiter(downloadSpeed);
            DownloadLimiter downloadLimiter = new DownloadLimiter(fileInputStream, bandwidthLimiter);

            out = response.getOutputStream();
            int len = 0;
            while ((len = downloadLimiter.read()) != -1) {
                out.write(len);
                bandwidthLimiter.setMaxRate(speed);
            }
            out.flush();
            out.close();
            downloadLimiter.close();
        } catch (IOException e) {
            log.error("下载失败={}", e);
        } finally {
            closeStream(out, null);
        }
    }

    /**
     * 限流下载
     *
     * @param exportUrl 下载文件url
     * @param response  response
     */
    public void downloadLimit(String exportUrl, HttpServletResponse response) {
        DOWNLOAD_NUM.incrementAndGet();
        OutputStream out = null;
        DownloadLimiter downloadLimiter = null;
        String fileName = exportUrl.substring(exportUrl.lastIndexOf('/') + 1);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        try (InputStream fileInputStream = new FileInputStream(exportUrl);) {
            int downloadSpeed = maxDownloadSpeed / DOWNLOAD_NUM.get();
            BandwidthLimiter bandwidthLimiter = new BandwidthLimiter(downloadSpeed);
            downloadLimiter = new DownloadLimiter(fileInputStream, bandwidthLimiter);
            out = response.getOutputStream();
            int len = 0;
            while ((len = downloadLimiter.read()) != -1) {
                out.write(len);
                bandwidthLimiter.setMaxRate(maxDownloadSpeed / DOWNLOAD_NUM.get());
            }
            out.flush();
            out.close();
            downloadLimiter.close();
        } catch (IOException e) {
            log.error("下载失败={}", e);
        } finally {
            closeStream(out, downloadLimiter);
        }
        DOWNLOAD_NUM.decrementAndGet();
    }

    /**
     * 关闭流
     *
     * @param out             OutputStream
     * @param downloadLimiter DownloadLimiter
     */
    protected void closeStream(OutputStream out, DownloadLimiter downloadLimiter) {
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                log.error("输出流关闭失败{}", e);
            }
        }
        if (downloadLimiter != null) {
            try {
                downloadLimiter.close();
            } catch (IOException e) {
                log.error("下载限流关闭失败={}", e);
            }
        }
    }


}
