package br.com.hrdev.ucdiagram.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.com.hrdev.ucdiagram.Window;
import br.com.hrdev.ucdiagram.models.Projeto;
import br.com.hrdev.ucdiagram.views.DashboardView;

public class NovoProjetoEvent implements ActionListener {

	
	private Window window;
	private DashboardView dashboard;


	public NovoProjetoEvent(Window window){
		this(window,null);
	}
	
	public NovoProjetoEvent(Window window, DashboardView dashboard) {
		this.window = window;
		this.dashboard = dashboard;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		create();
	}
	
	private void create(){
		if(window.getProjeto() != null){
			int option = JOptionPane.showConfirmDialog(window, "Voc\u00ea quer cirar um novo projeto?\nTodas as altera\u00e7\u00f5es n\u00e3o savas ser\u00e3o perdidas");
			if(option != JOptionPane.OK_OPTION)
				return;
		}
		
		String titulo = JOptionPane.showInputDialog(window, "Nome do Projeto:");
		if(titulo == null)
			return;

		window.setProjeto(new Projeto(titulo));
		if(dashboard == null){
			window.changeView(Window.Dashboard);
		} else {
			dashboard.updateAll();
		}
	}
}
