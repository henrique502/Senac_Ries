package br.com.hrdev.ucdiagram.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import br.com.hrdev.ucdiagram.Window;
import br.com.hrdev.ucdiagram.libraries.FileManager;

public class SalvarProjetoEvent implements ActionListener {
	
	private Window window;

	public SalvarProjetoEvent(Window window){
		this.window = window;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		salvar();
	}
	
	private void salvar(){
		File file = null;
		if(window.getProjetoArquivo() == null){
			System.out.println("Abre Choser");
		} else {
			file = window.getProjetoArquivo();
		}
		
		FileManager fm = new FileManager();
		if(fm.checkFile(file)){
			fm.save(window.getProjeto(), file);
			window.setProjetoArquivo(file);
			System.out.println("Arquivo Salvo");
		} else {
			System.out.println("Erro");
		}
	}

}
