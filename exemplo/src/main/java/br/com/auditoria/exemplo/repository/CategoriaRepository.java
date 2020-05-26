package br.com.auditoria.exemplo.repository;

import br.com.auditoria.exemplo.model.Categoria;
import br.com.auditoria.exemplo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    public Categoria findByid(Long id);
    public Categoria findByNome(String nome);
}