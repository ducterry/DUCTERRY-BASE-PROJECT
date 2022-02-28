package com.ducterry.base.entity.log;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Lưu log truy cập API
 */
@Entity
@Table(name = "APP_TRACE_LOG")
@Data
public class AppTraceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long  id;
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "BODY_REQUEST")
    private String bodyRequest;
    @Column(name = "HEADER_REQUEST")
    private String headerRequest;
    @Column(name = "RESPONSE_DATA")
    private String responseData;
    @Column(name = "IP_REQUEST")
    private String ipRequest;
    @Column(name = "USER_REQUEST")
    private String userRequest;
    @Column(name = "URL_REQUEST")
    private String urlRequest;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "END_DATE")
    private Date endDate;
    @Column(name = "CLASS_NAME")
    private String className;
    @Column(name = "METHOD_NAME")
    private String methodName;
}
