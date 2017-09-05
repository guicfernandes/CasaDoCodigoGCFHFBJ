package com.gcfhfbj.casadocodigo.casadocodigo.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by android7281 on 04/09/17.
 */

public class Livro implements Serializable {
    private long id;
    private String nome;
    private String descricao;
    private int numPaginas;
    private String dataPublicacao;
    private String ISBN;
    private double valorFisico;
    private double valorVirtual;
    private double valorDoisJuntos;
    private String urlFoto;
    private List<Autor> autores;

    public Livro() {

    }
    public Livro(String livro, String descricao, List<Autor> autores) {
        this.nome = livro;
        this.descricao = descricao;
        this.autores = autores;
    }

    public Livro(String livro, String descricao, List<Autor> autores, int numPaginas,
                 String dataPublica, String isbn, double valorFisico, double valorEbook) {
        this.nome = livro;
        this.descricao = descricao;
        this.autores = autores;
        this.numPaginas = numPaginas;
        this.dataPublicacao = dataPublica;
        this.ISBN = isbn;
        this.valorFisico = valorFisico;
        this.valorVirtual = valorEbook;
        this.valorDoisJuntos = this.valorFisico + this.valorVirtual;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public double getValorFisico() {
        return valorFisico;
    }

    public void setValorFisico(double valorFisico) {
        this.valorFisico = valorFisico;
    }

    public double getValorVirtual() {
        return valorVirtual;
    }

    public void setValorVirtual(double valorVirtual) {
        this.valorVirtual = valorVirtual;
    }

    public double getValorDoisJuntos() {
        return valorDoisJuntos;
    }

    public void setValorDoisJuntos(double valorDoisJuntos) {
        this.valorDoisJuntos = valorDoisJuntos;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }
}
