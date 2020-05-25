package br.com.auditoria.exemplo.model;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Audited
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "produto_Sequence")
    @SequenceGenerator(name = "produto_Sequence", sequenceName = "PRODUTO_SEQ")
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sku")
    private String sku;

    @Column(name = "estoque")
    private Integer estoque;

    @Column(name = "preco")
    private Double preco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}