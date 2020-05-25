package br.com.auditoria.exemplo.listener;


import br.com.auditoria.exemplo.model.Revisao;
import org.hibernate.envers.RevisionListener;


public class EntityRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        Revisao rev = (Revisao)revisionEntity;
        rev.setIp("");
    }
}
