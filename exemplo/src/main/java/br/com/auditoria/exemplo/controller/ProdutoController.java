package br.com.auditoria.exemplo.controller;

import br.com.auditoria.exemplo.model.Produto;
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


    @RequestMapping(value = "/alteraEstoque/{sku}", method = RequestMethod.PUT)
    public ResponseEntity<Produto> alteraEstoque(@PathVariable String sku, @RequestBody Produto produto) {
        Produto produtoObject = produtoService.findBySku(sku);
        if(produto.getEstoque() != null)
            produtoObject.setEstoque(produto.getEstoque());
        return new ResponseEntity<Produto>(produtoService.save(produtoObject), HttpStatus.OK);
    }

    @RequestMapping(value = "/alteraPreco/{sku}", method = RequestMethod.PUT)
    public ResponseEntity<Produto> alteraPreco(@PathVariable String sku, @RequestBody Produto produto) {
        Produto produtoObject = produtoService.findBySku(sku);
        if(produto.getPreco() != null)
            produtoObject.setPreco(produto.getPreco());
        return new ResponseEntity<Produto>(produtoService.save(produtoObject), HttpStatus.OK);
    }

    @RequestMapping(value = "/{sku}", method = RequestMethod.GET)
    public ResponseEntity<Produto> get(@PathVariable String sku) {
        Produto p = produtoService.findBySku(sku);
        if (p != null)
            return new ResponseEntity<Produto>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ResponseEntity<Produto> novo() {
        Produto p = new Produto();
        p.setNome("Produto 1");
        p.setPreco(99d);
        p.setSku("P01");
        p.setEstoque(10);

        return new ResponseEntity<Produto>(produtoService.save(p), HttpStatus.OK);
    }

}
