package br.com.auditoria.exemplo.model;

import br.com.auditoria.exemplo.listener.EntityRevisionListener;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@Entity
@RevisionEntity(value = EntityRevisionListener.class)
public class Revisao {

    @Id
    @GeneratedValue
    @RevisionNumber
    private Long revisaoId;

    @RevisionTimestamp
    private Date revisaoData;

    @Column
    private String ip;

    public Revisao(Long revisaoId, Date revisaoData) {
        this.revisaoId = revisaoId;
        this.revisaoData = revisaoData;
    }

    public Revisao() {
    }

    public Long getRevisaoId() {
        return revisaoId;
    }

    public void setRevisaoId(Long revisaoId) {
        this.revisaoId = revisaoId;
    }

    public Date getRevisaoData() {
        return revisaoData;
    }

    public void setRevisaoData(Date revisaoData) {
        this.revisaoData = revisaoData;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
