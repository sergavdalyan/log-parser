package com.test.logparser.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * The Blocked Ip.
 */
@Entity
@Table(name = "blocked_ip")
public class BlockedIp {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "ip")
    private String ip;

    @Column(name = "comment")
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockedIp blockedIp = (BlockedIp) o;
        return Objects.equals(id, blockedIp.id) &&
                Objects.equals(ip, blockedIp.ip) &&
                Objects.equals(comment, blockedIp.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ip, comment);
    }

    @Override
    public String toString() {
        return "BlockedIp{" +
                "id='" + id + '\'' +
                ", ip='" + ip + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
