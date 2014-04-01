package hr.com.hrdev;

import hr.com.hrdev.models.Ocean;
import hr.com.hrdev.models.Player;


public class BatalhaNaval {
	
	public BatalhaNaval(){
		
		new Ocean(new Player("Henrique"));
		
		
	}
	
	public static void main(String[] args) {
		new BatalhaNaval();
	}
	
}
