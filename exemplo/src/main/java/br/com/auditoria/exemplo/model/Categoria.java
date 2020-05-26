package br.com.auditoria.exemplo.model;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Audited
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "categoria_Sequence")
    @SequenceGenerator(name = "categoria_Sequence", sequenceName = "CATEGORIA_SEQ")
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @ManyToMany
    @JoinTable(
            name="categoria_produto"
    )
    private List<Produto> produtos;

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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}