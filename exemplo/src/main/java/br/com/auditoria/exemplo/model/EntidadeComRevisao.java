package br.com.auditoria.exemplo.model;

public class EntidadeComRevisao<T> {

    private Revisao revisao;

    private T entidade;

    public EntidadeComRevisao(Revisao revision, T entity) {
        this.revisao = revision;
        this.entidade = entity;
    }

    public Revisao getRevisao() {
        return revisao;
    }

    public void setRevisao(Revisao revisao) {
        this.revisao = revisao;
    }

    public T getEntidade() {
        return entidade;
    }

    public void setEntidade(T entidade) {
        this.entidade = entidade;
    }
}
