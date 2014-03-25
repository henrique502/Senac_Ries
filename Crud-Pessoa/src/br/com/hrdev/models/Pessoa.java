package br.com.hrdev.models;

public class Pessoa {

	private String nome, telefone;
	
	public Pessoa(){
		this(null,null);
	}
	
	public Pessoa(String nome, String telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return this.nome + " | " + this.telefone;
	}
}
