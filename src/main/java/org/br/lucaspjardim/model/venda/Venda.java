package org.br.lucaspjardim.model.venda;

import jakarta.persistence.*;
import org.br.lucaspjardim.model.cliente.Cliente;
import org.br.lucaspjardim.model.produto.Produto;

import java.util.Date;

@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente; // Referência ao objeto Cliente

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto; // Referência ao objeto Produto

    private int quantidade;

    @Column(name = "valor_total")
    private Double valorTotal;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_venda")
    private Date dataVenda;

    public Venda() {
    }

    public Venda(Cliente cliente, Produto produto, int quantidade, Double valorTotal, Date dataVenda) {
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.dataVenda = dataVenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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
}
