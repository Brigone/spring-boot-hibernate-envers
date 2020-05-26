package br.com.auditoria.exemplo.model;

import br.com.auditoria.exemplo.listener.EntityRevisionListener;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@RevisionEntity(value = EntityRevisionListener.class)
public class Revisao {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "revisao_Sequence")
    @SequenceGenerator(name = "revisao_Sequence", sequenceName = "REVISAO_SEQ")
    @RevisionNumber
    private Long revisaoId;

    @RevisionTimestamp
    private Date revisaoData;

    @Column
    private String ip;

    @Column
    private String usuario;

    public Revisao(Long revisaoId, Date revisaoData, String usuario, String ip) {
        this.revisaoId = revisaoId;
        this.revisaoData = revisaoData;
        this.usuario = usuario;
        this.ip = ip;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
