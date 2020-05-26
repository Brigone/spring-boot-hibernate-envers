package br.com.auditoria.exemplo.service;

import br.com.auditoria.exemplo.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.auditoria.exemplo.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto findByid(Long id){
        return produtoRepository.findByid(id);
    }
    public Produto findBySku(String sku){
        return produtoRepository.findBySku(sku);
    }
    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }
    public Produto save(Produto produto){
        return produtoRepository.save(produto);
    }

}
