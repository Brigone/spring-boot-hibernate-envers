package br.com.auditoria.exemplo.repository;

import br.com.auditoria.exemplo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    public Produto findByid(Long id);
    public Produto findBySku(String sku);
}