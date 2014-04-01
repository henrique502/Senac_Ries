package hr.com.hrdev.models;

import hr.com.hrdev.models.navios.Navio;

public class Player {
	
	private String nome;
	private int life;
	private Navio[] navios;
	
	public Player(String nome){
		this.nome = nome;
		this.life = 15;
		this.navios = Navio.genrate();
	}
	
	public int getNavioLength(){
		return this.navios.length;
	}
	
	public Navio getNavio(int index){
		if(index >= 0 && index < this.navios.length)
			return this.navios[index];
		
		return null;
	}
	
	public Navio[] getNavios(){
		return this.navios;
	}
	
	public void shotMissing(){
		if(this.life > 0)
			this.life--;
	}
	
	public void shotHit(){
		this.life += 3;
	}
	
	public boolean isDead(){
		return (this.life == 0);
	}
	
	public int getPoints(){
		return this.life;
	}
	
	public boolean hasLivingShips(){
		for (Navio navio : navios){
			if(!navio.isDead())
				return true;
		}
		return false;
	}
	
	public String toString(){
		return this.nome;
	}
	
	
}
