package br.com.auditoria.exemplo.service;

import br.com.auditoria.exemplo.model.Categoria;
import br.com.auditoria.exemplo.model.Produto;
import br.com.auditoria.exemplo.repository.CategoriaRepository;
import br.com.auditoria.exemplo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findByid(Long id){
        return categoriaRepository.findByid(id);
    }
    public Categoria findByNome(String nome){
        return categoriaRepository.findByNome(nome);
    }
    public Categoria save(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

}
