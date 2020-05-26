package br.com.auditoria.exemplo.controller;

import br.com.auditoria.exemplo.model.Categoria;
import br.com.auditoria.exemplo.model.EntidadeComRevisao;
import br.com.auditoria.exemplo.model.Produto;
import br.com.auditoria.exemplo.repository.GenericRevisionRepository;
import br.com.auditoria.exemplo.service.CategoriaService;
import br.com.auditoria.exemplo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private GenericRevisionRepository genericRevisionRepository;

    @RequestMapping(value = "/novaCategoria", method = RequestMethod.PUT)
    public ResponseEntity<Categoria> novaCategoria(@RequestBody Categoria categoria) {
        Categoria cat = categoriaService.findByNome(categoria.getNome());
        if(cat != null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<Categoria>(categoriaService.save(categoria), HttpStatus.OK);
    }

    @RequestMapping(value = "/adicionaProduto/{sku}", method = RequestMethod.PUT)
    public ResponseEntity<Categoria> adicionaProduto(@PathVariable String sku, @RequestBody Categoria categoria) {
        Produto produto = produtoService.findBySku(sku);
        Categoria cat = categoriaService.findByNome(categoria.getNome());
        cat.getProdutos().add(produto);
        categoriaService.save(cat);
        return new ResponseEntity<Categoria>(cat, HttpStatus.OK);
    }

    @RequestMapping(value = "/removeProduto/{sku}", method = RequestMethod.PUT)
    public ResponseEntity<Categoria> removeProduto(@PathVariable String sku, @RequestBody Categoria categoria) {
        Produto produto = produtoService.findBySku(sku);
        Categoria cat = categoriaService.findByNome(categoria.getNome());
        cat.getProdutos().remove(produto);
        categoriaService.save(cat);
        return new ResponseEntity<Categoria>(cat, HttpStatus.OK);
    }

    @RequestMapping("/{nome}")
    public ResponseEntity<Categoria> consulta(@PathVariable String nome) {
        Categoria cat = categoriaService.findByNome(nome);
        if(cat != null)
            return new ResponseEntity<Categoria>(cat, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/revisoes/{nome}")
    public ResponseEntity<List<EntidadeComRevisao<Categoria>>> getRevisions(@PathVariable String nome) {
        Categoria cat = categoriaService.findByNome(nome);
        List<EntidadeComRevisao<Categoria>> revisoes = genericRevisionRepository.listaRevisoes(cat.getId(),Categoria.class);
        if(revisoes != null)
            return new ResponseEntity<List<EntidadeComRevisao<Categoria>>>(revisoes, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
