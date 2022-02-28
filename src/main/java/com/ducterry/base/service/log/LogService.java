package com.ducterry.base.service.log;

import com.ducterry.base.utils.JSONFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class LogService {
    private static final String REQUEST_ID = "request_id";

    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        if (httpServletRequest.getRequestURI().contains("medias")) {
            return;
        }
        Object requestId = httpServletRequest.getAttribute(REQUEST_ID);
        StringBuilder data = new StringBuilder();
        data.append("\n\t(*) --------------------LOGGING REQUEST BODY-------------------\n")
                .append("\t    - [REQUEST-ID]: ").append(requestId).append("\n")
                .append("\t    - [BODY REQUEST]: ").append("\n\n\t    ").append(JSONFactory.toJson(body)).append("\n\n")
                .append("\t --------------------------------------------------------------\n");

        log.info(data.toString());
    }

    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
        if (httpServletRequest.getRequestURI().contains("medias")) {
            return;
        }
        Object requestId = httpServletRequest.getAttribute(REQUEST_ID);
        StringBuilder data = new StringBuilder();
        data.append("\n\t(*) -------------------LOGGING RESPONSE-------------------\n")
                .append("\t    - [REQUEST-ID]: ").append(requestId).append("\n")
                .append("\t    - [BODY RESPONSE]: ").append("\n\n\t    ").append(JSONFactory.toJson(body)).append("\n\n")
                .append("\t --------------------------------------------------------\n");

        log.info(data.toString());
    }
}
