package br.com.hrdev.controllers;

import java.awt.Color;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.hrdev.Main;

@SuppressWarnings("serial")
public class MainController extends JPanel {

	private Main window;

	public MainController(Main window){
		this.window = window;
		setBackground(Color.BLUE);
		//window.swapController("lista");
		//setup();
	}
	
	private void setup(){
		JFileChooser fileChoose = new JFileChooser("teste");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".*storage", "storage");
		
		fileChoose.setCurrentDirectory(window.getDir());
		fileChoose.setFileFilter(filter);
		
		try {
			int respose = fileChoose.showDialog(this, "Abrir");
			
			if (respose == JFileChooser.APPROVE_OPTION) {
				File file = fileChoose.getSelectedFile();
				if(file.isFile()){
					window.getStorage().getData(file);
					window.swapController("lista");
				} else {
					System.exit(0);
				}
			} else {
				System.exit(0);
			}
		} catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
}
