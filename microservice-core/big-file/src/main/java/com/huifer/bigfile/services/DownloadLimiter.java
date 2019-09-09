package com.huifer.bigfile.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class DownloadLimiter extends InputStream {
    protected static final Logger log = LoggerFactory.getLogger(DownloadLimiter.class);

    private InputStream is = null;

    private BandwidthLimiter bandwidthLimiter = null;

    public DownloadLimiter(InputStream is, BandwidthLimiter bandwidthLimiter) {
        this.is = is;
        this.bandwidthLimiter = bandwidthLimiter;

    }

    @Override
    public int read() throws IOException {
        if (this.bandwidthLimiter != null) {
            this.bandwidthLimiter.limitNextBytes();
        }
        return this.is.read();

    }

    @Override
    public int read(byte[] bytes, int off, int len) throws IOException {
        if (bandwidthLimiter != null) {
            bandwidthLimiter.limitNextBytes(len);
        }
        return this.is.read(bytes, off, len);
    }
}
