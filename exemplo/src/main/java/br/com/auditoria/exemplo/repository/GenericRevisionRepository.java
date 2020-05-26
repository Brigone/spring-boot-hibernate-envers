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
import java.util.*;

@Repository
@Transactional
public class GenericRevisionRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    public List<EntidadeComRevisao<T>> listaRevisoes(Long id, Class<T> tipo) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        List<Number> idsRevisao = auditReader.getRevisions(tipo, id);
        List<EntidadeComRevisao<T>> allRevisions = new ArrayList<>();
        Map<Number, Revisao> revisoes =  auditReader.findRevisions(Revisao.class,new HashSet<Number>(idsRevisao));

        for (Number revisao : idsRevisao) {
            T listaRevisoes = auditReader.find(tipo, id, revisao);
            Revisao r = revisoes.get(revisao);
            auditReader.findRevisions(Revisao.class,new HashSet<Number>(idsRevisao));
            allRevisions.add(new EntidadeComRevisao(new Revisao(r.getRevisaoId(), r.getRevisaoData(),r.getIp(),r.getUsuario()), listaRevisoes));
        }
        return allRevisions;
    }
}
