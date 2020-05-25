package br.com.auditoria.exemplo.repository;

import br.com.auditoria.exemplo.model.EntidadeComRevisao;
import br.com.auditoria.exemplo.model.Produto;
import br.com.auditoria.exemplo.model.Revisao;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class GenericRevisionRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    public List<EntidadeComRevisao<Produto>> listaRevisoes(Long id, Class<T> tipo) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        List<Number> revisoes = auditReader.getRevisions(tipo, id);
        List<EntidadeComRevisao<Produto>> allRevisions = new ArrayList<>();

        for (Number revisao : revisoes) {
            T listaRevisoes = auditReader.find(tipo, id, revisao);
            Date revisaoData = auditReader.getRevisionDate(revisao);

            allRevisions.add(new EntidadeComRevisao(new Revisao(revisao.longValue(), revisaoData), listaRevisoes));
        }
        return allRevisions;
    }
}
