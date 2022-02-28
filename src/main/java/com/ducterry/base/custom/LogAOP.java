package com.ducterry.base.custom;

import com.ducterry.base.entity.log.AppTraceLog;
import com.ducterry.base.service.log.LogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class LogAOP {
    public static final int LENGTH = 100_000;
    private final LogService logService;
    private final ObjectMapper objectMapper;

    public LogAOP(LogService logService, ObjectMapper objectMapper) {
        this.logService = logService;
        this.objectMapper = objectMapper;
    }

    @Around("execution(@RequestLogger ** (..)) && @annotation (requestLogger)")
    public Object showToTraceLog(ProceedingJoinPoint joinPoint, RequestLogger requestLogger) throws Throwable {

        ContentCachingRequestWrapper request = this.getWrapper(joinPoint);
        String headerRequest = getHeader(request);
        String requestStr = getRequestBody(request);

        Object retVal = joinPoint.proceed();

        String responseStr = getResponseBody(retVal);
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String ipRequest = request.getRemoteAddr();
        String urlRequest = getUrlRequest(request);
        String userRequest = getUserRequest(request);
        try {
            AppTraceLog entity = new AppTraceLog();
            entity.setHeaderRequest(headerRequest);
            entity.setBodyRequest(requestStr);
            entity.setIpRequest(ipRequest);
            entity.setCreatedDate(new Date());
            entity.setUrlRequest(urlRequest);
            entity.setUserRequest(userRequest);
            entity.setStartDate(new Date());
            entity.setEndDate(new Date());
            entity.setClassName(className);
            entity.setMethodName(methodName);
            entity.setResponseData(responseStr);
            this.logService.createLog(entity);


        } catch (Exception ex) {
            ex.getMessage();
        }
        return retVal;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    public ContentCachingRequestWrapper getWrapper(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        ContentCachingRequestWrapper request = null;

        for (Object arg : args) {
            if (arg instanceof ContentCachingRequestWrapper) {
                request = (ContentCachingRequestWrapper) arg;
                break;
            }
        }

        return request;
    }

    private String getRequestBody(ContentCachingRequestWrapper wrapper) {
        String payload = null;
        if (wrapper != null) {

            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                try {
                    int maxLength = buf.length > LogAOP.LENGTH ? LogAOP.LENGTH : buf.length;

                    payload = new String(buf, 0, maxLength, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException e) {
                    e.getStackTrace()
                }
            }
        }
        return payload;
    }

    private String getResponseBody(Object retVal) throws JsonProcessingException {
        if (retVal == null)
            return null;
        String response = objectMapper.writeValueAsString(retVal);
        int maxLength = response.length() > LogAOP.LENGTH ? LogAOP.LENGTH : response.length();

        return response.substring(0, maxLength);
    }

    private String getHeader(ContentCachingRequestWrapper wrapper) {
        StringBuilder header = new StringBuilder();
        ArrayList<String> headerList = Collections.list(wrapper.getHeaderNames());

        for (String item : headerList) {
            header
                    .append(item)
                    .append(":")
                    .append(wrapper.getHeader(item))
                    .append("\n");
        }

        return header.toString();
    }

    private String getUrlRequest(ContentCachingRequestWrapper wrapper) {
        String urlRequest = wrapper.getRequestURL().toString();
        if (StringUtils.isNotBlank(wrapper.getQueryString())) {
            urlRequest = urlRequest + "?" + wrapper.getQueryString();
        }
        return urlRequest;
    }

    private String getUserRequest(ContentCachingRequestWrapper wrapper) {
        String userRequest = null;
        if (wrapper.getUserPrincipal() != null) {
            userRequest = wrapper.getUserPrincipal().getName();
        }
        return userRequest;
    }
}
