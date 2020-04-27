package com.example.bff.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Map.Entry;

import java.io.IOException;

@Component
public class LoggingClientInterceptor implements ClientHttpRequestInterceptor {

    /** New line characters in log files */
    private static final String NEWLINE = System.getProperty("line.separator");

    /** Logger */
    private static Logger log = LoggerFactory.getLogger(LoggingClientInterceptor.class);


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        handleRequest(getRequestContent(request, new String(body)));

        ClientHttpResponse response = execution.execute(request, body);
        CachingClientHttpResponseWrapper bufferedResponse = new CachingClientHttpResponseWrapper(response);
        handleResponse(getResponseContent(bufferedResponse));

        return bufferedResponse;
    }

    /**
     * Handles request messages for logging.
     * @param request
     */
    public void handleRequest(String request) {
                log.info("Sending Http request message:" + NEWLINE + request);
    }

    /**
     * Handles response messages for logging.
     * @param response
     */
    public void handleResponse(String response) {
            log.info("Received Http response message:" + NEWLINE + response);
    }


    /**
     * Builds request content string from request and body.
     * @param request
     * @param body
     * @return
     */
    private String getRequestContent(HttpRequest request, String body) {
        StringBuilder builder = new StringBuilder();

        builder.append(request.getMethod());
        builder.append(" ");
        builder.append(request.getURI());
        builder.append(NEWLINE);

        appendHeaders(request.getHeaders(), builder);

        builder.append(NEWLINE);
        builder.append(body);

        return builder.toString();
    }

    /**
     * Builds response content string from response object.
     * @param response
     * @return
     * @throws IOException
     */
    private String getResponseContent(CachingClientHttpResponseWrapper response) throws IOException {
        if (response != null) {
            StringBuilder builder = new StringBuilder();

            builder.append("HTTP/1.1 "); // TODO get Http version from message
            builder.append(response.getStatusCode());
            builder.append(" ");
            builder.append(response.getStatusText());
            builder.append(NEWLINE);

            appendHeaders(response.getHeaders(), builder);

            builder.append(NEWLINE);
            builder.append(response.getBodyContent());

            return builder.toString();
        } else {
            return "";
        }
    }

    /**
     * Append Http headers to string builder.
     * @param headers
     * @param builder
     */
    private void appendHeaders(HttpHeaders headers, StringBuilder builder) {
        for (Entry<String, List<String>> headerEntry : headers.entrySet()) {
            builder.append(headerEntry.getKey());
            builder.append(":");
            builder.append(StringUtils.arrayToCommaDelimitedString(headerEntry.getValue().toArray()));
            builder.append(NEWLINE);
        }
    }
}