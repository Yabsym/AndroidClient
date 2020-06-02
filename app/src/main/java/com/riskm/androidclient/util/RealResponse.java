package com.riskm.androidclient.util;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class RealResponse {
    public InputStream inputStream;
    public InputStream errorStream;
    public int code;
    public long contentLength;
    public Exception exception;
    public Map<String, List<String>> cookies;
}
