package br.dev.weslei.tarefas.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Tarefa {
	
	private String titulo;
	private String descricao;
	private LocalDate dataInicial;
	private LocalDate prazo;
	private LocalDate dataConclusao;
	private Status status;
	private Funcionario responsavel;
	
	// Método construtor
	public Tarefa(String titulo) {
		this.titulo = titulo;
	}
	
	public Tarefa(String titulo, LocalDateTime dataInicial) {
		
	}
	
	public Tarefa() {
		
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	public LocalDate getDataConclusao() {
		return dataConclusao;
	}
	
	public void setDataConclusao(LocalDate dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	
	public void setPrazo(LocalDate prazo) {
		this.prazo = prazo;
	}
	
	public LocalDate getPrazo() {
		return prazo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Funcionario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Funcionario responsavel) {
		this.responsavel = responsavel;
	}

	public void setPrazo(String text) {
		// TODO Auto-generated method stub
		
	}

}
