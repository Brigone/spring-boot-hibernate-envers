package br.com.auditoria.exemplo.controller;

import br.com.auditoria.exemplo.model.EntidadeComRevisao;
import br.com.auditoria.exemplo.model.Produto;
import br.com.auditoria.exemplo.repository.GenericRevisionRepository;
import br.com.auditoria.exemplo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private GenericRevisionRepository genericRevisionRepository;

    @RequestMapping(value = "/novoProduto", method = RequestMethod.PUT)
    public ResponseEntity<Produto> novoProduto(@RequestBody Produto produto) {
        Produto produtoObject = produtoService.findBySku(produto.getSku());
        if(produtoObject != null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            return new ResponseEntity<Produto>(produtoService.save(produto), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/alteraEstoque", method = RequestMethod.PUT)
    public ResponseEntity<Produto> alteraEstoque(@RequestBody Produto produto) {
        Produto produtoObject = produtoService.findBySku(produto.getSku());
        if(produto.getEstoque() != null)
            produtoObject.setEstoque(produto.getEstoque());
        return new ResponseEntity<Produto>(produtoService.save(produtoObject), HttpStatus.OK);
    }

    @RequestMapping(value = "/alteraPreco", method = RequestMethod.PUT)
    public ResponseEntity<Produto> alteraPreco(@RequestBody Produto produto) {
        Produto produtoObject = produtoService.findBySku(produto.getSku());
        if(produto.getPreco() != null)
            produtoObject.setPreco(produto.getPreco());
        return new ResponseEntity<Produto>(produtoService.save(produtoObject), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Produto>> consulta() {
        List<Produto> p = produtoService.findAll();
        if(p != null)
            return new ResponseEntity<List<Produto>>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{sku}", method = RequestMethod.GET)
    public ResponseEntity<Produto> consulta(@PathVariable String sku) {
        Produto p = produtoService.findBySku(sku);
        if(p != null)
            return new ResponseEntity<Produto>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/revisoes/{sku}")
    public ResponseEntity<List<EntidadeComRevisao<Produto>>> getRevisions(@PathVariable String sku) {
        Produto p = produtoService.findBySku(sku);
        List<EntidadeComRevisao<Produto>> revisoes = genericRevisionRepository.listaRevisoes(p.getId(),Produto.class);
        if(revisoes != null)
            return new ResponseEntity<List<EntidadeComRevisao<Produto>>>(revisoes, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
