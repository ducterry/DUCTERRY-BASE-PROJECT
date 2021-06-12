package com.ducterry.base.ducterrybase.utils;


import com.ducterry.base.ducterrybase.commons.constant.FieldConstants;
import com.ducterry.base.ducterrybase.dto.base.ResponseObject;
import com.ducterry.base.ducterrybase.exception.ApiException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;

import static com.ndangducbn.base.dao.enums.ResponseStatus.UNHANDLED_ERROR;


public class RestFactory {
    private static final Logger LOGGER = LogManager.getLogger(RestFactory.class);

    public static <T> T postForEntityBasicToken(String url, Object body, Class<T> clazz, String token) throws ApiException {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            httpHeaders.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
            httpHeaders.add(HttpHeaders.AUTHORIZATION, token);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Object> request = new HttpEntity<>(body, httpHeaders);
            ResponseEntity<T> response = restTemplate.postForEntity(url, request, clazz);
            if (HttpStatus.OK.equals(response.getStatusCode()) && response.getBody() != null) {
                return response.getBody();
            }
            throw new ApiException(UNHANDLED_ERROR.formatMessage(""));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ApiException(UNHANDLED_ERROR.formatMessage(e.getMessage()));
        }
    }


    public static <T> ResponseObject<T> postForEntityBasicToken(String url,
                                                                Object body,
                                                                ParameterizedTypeReference<ResponseObject<T>> responseType,
                                                                String token) throws ApiException {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            httpHeaders.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
            httpHeaders.add(HttpHeaders.AUTHORIZATION, token);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Object> request = new HttpEntity<>(body, httpHeaders);
            ResponseEntity<ResponseObject<T>> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    responseType
            );
            if (HttpStatus.OK.equals(response.getStatusCode()) && response.getBody() != null) {
                return response.getBody();
            }
            throw new ApiException(UNHANDLED_ERROR.formatMessage(""));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ApiException(UNHANDLED_ERROR.formatMessage(e.getMessage()));
        }
    }

    public static <T> ResponseObject<T> postForEntityAuthToken(String url,
                                                               Object body,
                                                               ParameterizedTypeReference<ResponseObject<T>> responseType,
                                                               String token) throws ApiException {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            httpHeaders.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
            httpHeaders.add(HttpHeaders.AUTHORIZATION, FieldConstants.TOKEN_PREFIX + token);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Object> request = new HttpEntity<>(body, httpHeaders);
            ResponseEntity<ResponseObject<T>> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    responseType
            );
            if (HttpStatus.OK.equals(response.getStatusCode()) && response.getBody() != null) {
                return response.getBody();
            }
            throw new ApiException(UNHANDLED_ERROR.formatMessage(""));
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new ApiException(UNHANDLED_ERROR.formatMessage(e.getMessage()));
        }
    }

    public static <T> ResponseObject<T> postForEntityAuthToken(String url,
                                                               Object body,
                                                               ParameterizedTypeReference<ResponseObject<T>> responseType,
                                                               String token,
                                                               Duration timeout) throws ApiException {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            httpHeaders.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
            httpHeaders.add(HttpHeaders.AUTHORIZATION, FieldConstants.TOKEN_PREFIX + token);

            RestTemplate restTemplate = (new RestTemplateBuilder()).setReadTimeout(timeout).build();
            ResponseEntity<ResponseObject<T>> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity<>(body, httpHeaders),
                    responseType
            );
            if (HttpStatus.OK.equals(response.getStatusCode()) && response.getBody() != null) {
                return response.getBody();
            }
            throw new ApiException(UNHANDLED_ERROR.formatMessage(""));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ApiException(UNHANDLED_ERROR.formatMessage(e.getMessage()));
        }
    }

    public static <T> ResponseObject<T> getForEntityAuthToken(String url,
                                                              ParameterizedTypeReference<ResponseObject<T>> responseType,
                                                              String token) throws ApiException {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            httpHeaders.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
            httpHeaders.add(HttpHeaders.AUTHORIZATION, FieldConstants.TOKEN_PREFIX + token);

            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Object> request = new HttpEntity<>(httpHeaders);
            ResponseEntity<ResponseObject<T>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    responseType
            );
            if (HttpStatus.OK.equals(response.getStatusCode()) && response.getBody() != null) {
                return response.getBody();
            }
            throw new ApiException(UNHANDLED_ERROR.formatMessage(""));
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new ApiException(UNHANDLED_ERROR.formatMessage(e.getMessage()));
        }
    }
}
