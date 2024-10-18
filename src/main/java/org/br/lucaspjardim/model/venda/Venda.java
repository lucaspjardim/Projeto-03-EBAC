package org.br.lucaspjardim.model.venda;

import java.util.Date;

public class Venda {
    private Long id;
    private Long idCliente; // Referência ao ID do Cliente
    private Long idProduto; // Referência ao ID do Produto
    private int quantidade;
    private Double valorTotal;
    private Date dataVenda;
    private String nomeCliente;
    private String nomeProduto;

    public Venda(Long idCliente, Long idProduto, int quantidade, Double valorTotal, Date dataVenda, String nomeCliente, String nomeProduto) {
        this.idCliente = idCliente;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.dataVenda = dataVenda;
        this.nomeCliente = nomeCliente;
        this.nomeProduto = nomeProduto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

}
