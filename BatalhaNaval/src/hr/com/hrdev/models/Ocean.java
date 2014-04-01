package hr.com.hrdev.models;

import hr.com.hrdev.models.navios.Navio;

import java.util.Random;

public class Ocean {
	
	private Navio[][] map;
	private int size = 10;
	private Random random;
	
	public Ocean(Player player){
		this.map = new Navio[size][size];
		this.random = new Random();
		allocateShips(player);
	}
	
	public Navio[][] getMap(){
		return this.map;
	}

	private void allocateShips(Player player) {
		Navio[] navios = player.getNavios();
		for (int i = 0; i < navios.length; i++){
			allocate(navios[i]);
		}
	}

	private void allocate(Navio navio) {
		int size = navio.getSize();
		int[][] points = new int[size][2];
		
		if(random.nextBoolean()){ /* x */ 
			int x = getRandomPos(size);
			int y = getRandomPos(0);
			
			for (int i = 0; i < size; i++) {
				points[i][0] = x++;
				points[i][1] = y;
			}
		} else { /* y */
			int x = getRandomPos(0);
			int y = getRandomPos(size);
			
			for (int i = 0; i < size; i++) {
				points[i][0] = x;
				points[i][1] = y++;
			}
		}
		
		if(!tryAllocate(navio,points))
			allocate(navio);
	}
	
	private boolean tryAllocate(Navio navio, int[][] points) {
		for (int i = 0; i < points.length; i++) {
			int x = points[i][0];
			int y = points[i][1];

			if(map[x][y] != null)
				return false;
		}
		
		for (int i = 0; i < points.length; i++) {
			int x = points[i][0];
			int y = points[i][1];

			map[x][y] = navio;
		}
		
		return true;
	}

	private int getRandomPos(int shipSize){
		int limit = size - shipSize;
		return random.nextInt(limit);
	}
}
