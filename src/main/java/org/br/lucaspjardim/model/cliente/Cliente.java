package org.br.lucaspjardim.model.cliente;

import java.util.Date;

/**
 * Author: Lucas Jardim
 */
public class Cliente {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private Date dataNascimento;

    public Cliente(String nome, String email, String telefone, String endereco, Date dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() { return endereco; }

    public void setEndereco(String endereco) { this.endereco = endereco; }

    public Date getDataNascimento() { return dataNascimento; }

    public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }
}
