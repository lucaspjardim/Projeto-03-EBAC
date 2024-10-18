package org.br.lucaspjardim.model.produto;

/**
 * Author: Lucas Jardim
 */
public class Produto {
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private String categoria;
    private Integer estoque;

    public Produto(String nome, String descricao, Double preco, String categoria, Integer estoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.estoque = estoque;
    }

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public void reduzirEstoque(int quantidade) {
        if (quantidade <= estoque) {
            estoque -= quantidade;
        } else {
            throw new IllegalArgumentException("Quantidade excede o estoque disponível.");
        }
    }
}
