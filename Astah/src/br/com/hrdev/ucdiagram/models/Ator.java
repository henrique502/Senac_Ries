package br.com.hrdev.ucdiagram.models;

import java.io.Serializable;

public class Ator implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nome;
	
	public Ator(String nome) {
		this.nome = nome;
	}

	public String getName() {
		return this.nome;
	}
	
	public String toString(){
		return this.nome;
	}
}
