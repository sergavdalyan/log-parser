package com.test.logparser.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The Log Entity.
 */
@Entity
@Table(name = "log")
public class LogEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "ip")
    private String ip;

    @Column(name = "request")
    private String request;

    @Column(name = "status")
    private int status;

    @Column(name = "user_agent")
    private String userAgent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogEntity logEntity = (LogEntity) o;
        return status == logEntity.status &&
                Objects.equals(id, logEntity.id) &&
                Objects.equals(date, logEntity.date) &&
                Objects.equals(ip, logEntity.ip) &&
                Objects.equals(request, logEntity.request) &&
                Objects.equals(userAgent, logEntity.userAgent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, ip, request, status, userAgent);
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", ip='" + ip + '\'' +
                ", request='" + request + '\'' +
                ", status=" + status +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
