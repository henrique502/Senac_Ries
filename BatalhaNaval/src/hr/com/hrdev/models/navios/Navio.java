package hr.com.hrdev.models.navios;

public abstract class Navio {
	
	protected int size;
	protected String nome;
	protected int life;
	
	public String getNome(){
		return this.nome;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public int getLife(){
		return this.life;
	}
	
	public void hit(){
		if(this.life >= 0)
			this.life--;
	}
	
	public boolean isDead(){
		return (life == 0);
	}
	
	public String toString(){
		return this.nome;
	}

	public static Navio[] genrate() {
		Navio[] navios = {
			new PortaAvioes(),
			new Destroyer(),
			new Destroyer(),
			new Fragata(),
			new Fragata(),
			new Torpedeiro(),
			new Torpedeiro(),
			new Torpedeiro(),
			new Submarino(),
			new Submarino(),
			new Submarino(),
			new Submarino(),
			new Submarino()
		};	
		return navios;
	}
	
}
