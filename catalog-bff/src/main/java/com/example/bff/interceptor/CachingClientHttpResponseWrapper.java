package com.example.bff.interceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.io.InputStream;

final class CachingClientHttpResponseWrapper implements ClientHttpResponse {

    private final ClientHttpResponse response;

    private byte[] body;

    CachingClientHttpResponseWrapper(ClientHttpResponse response) {
        this.response = response;
    }

    public HttpStatus getStatusCode() throws IOException {
        return this.response.getStatusCode();
    }

    public int getRawStatusCode() throws IOException {
        return this.response.getRawStatusCode();
    }

    public String getStatusText() throws IOException {
        return this.response.getStatusText();
    }

    public HttpHeaders getHeaders() {
        return this.response.getHeaders();
    }

    public InputStream getBody() throws IOException {
        if (this.body == null) {
            if (response.getBody() != null) {
                this.body = FileCopyUtils.copyToByteArray(response.getBody());
            } else {
                body = new byte[] {};
            }
        }
        return new ByteArrayInputStream(this.body);
    }

    public String getBodyContent() throws IOException {
        if (this.body == null) {
            getBody();
        }

        return new String(body, Charset.forName("UTF-8"));
    }

    public void close() {
        this.response.close();
    }
}


