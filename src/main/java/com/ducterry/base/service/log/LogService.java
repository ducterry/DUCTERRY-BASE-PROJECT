package com.ducterry.base.service.log;

import com.ducterry.base.dto.auth.req.LoginForm;
import com.ducterry.base.entity.log.TraceLog;
import com.ducterry.base.repository.TraceLogRepository;
import com.ducterry.base.utils.JSONFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@Slf4j
public class LogService {
    private static final String REQUEST_ID = "request_id";

    private final ObjectMapper objectMapper;
    private final TraceLogRepository logRepository;

    public LogService(ObjectMapper objectMapper, TraceLogRepository logRepository) {
        this.objectMapper = objectMapper;
        this.logRepository = logRepository;
    }

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

    public TraceLog createLog(TraceLog request) {
        if (request.getUrlRequest().contains("login")) {
            String bodyRequest = request.getBodyRequest();
            LoginForm rqLogin = null;
            try {
                rqLogin = this.objectMapper.readValue(bodyRequest, LoginForm.class);
                rqLogin.setPassword("protected");
                request.setBodyRequest(this.objectMapper.writeValueAsString(rqLogin));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.logRepository.save(request);
    }
}
