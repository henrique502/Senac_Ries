package br.com.hrdev.storage;

import java.io.Serializable;
import java.util.ArrayList;

public class Projeto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private ArrayList<Ator> atores;
	private ArrayList<Diagrama> diagramas;
	
	public Projeto(){
		this("sem nome");
	}
	
	public Projeto(String nome){
		this.nome = nome;
		this.atores = new ArrayList<Ator>();
		this.diagramas = new ArrayList<Diagrama>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Ator> getAtores() {
		return atores;
	}

	public ArrayList<Diagrama> getDiagramas() {
		return diagramas;
	}
}
