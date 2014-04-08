package br.com.hrdev.libraries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


import br.com.hrdev.models.Pessoa;

public class Storage {
	
	private List<Pessoa> pessoas;
	
	public Storage(){
		pessoas = new ArrayList<Pessoa>();
	}
	
	public List<Pessoa> getData(){
		return this.pessoas;
	}

	public void getData(File file) throws Exception {
		FileReader fileReader = null;
		BufferedReader br = null;
		try {
			fileReader = new FileReader(file);
			br = new BufferedReader(fileReader);
			String stringRead = br.readLine();
			
			while(stringRead != null){
				String[] csv = stringRead.split(";");
				if(csv.length == 2){
					pessoas.add(new Pessoa(csv[0],csv[1]));
				} else {
					throw new Exception("Storage file invalid");
				}
				stringRead = br.readLine();
			}
		} catch (Exception e){
			throw new Exception(e);
		} finally {
			if(br != null) br.close();
			if(fileReader != null) fileReader.close();
		}
	}

	public void saveData() {
		// famfamfam icons
		System.out.println("Salvar");
		
	}
	
}
