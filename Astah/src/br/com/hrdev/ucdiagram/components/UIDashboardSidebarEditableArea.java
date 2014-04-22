package br.com.hrdev.ucdiagram.components;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.hrdev.ucdiagram.models.ComponentItem;
import br.com.hrdev.ucdiagram.utils.Icons;

public class UIDashboardSidebarEditableArea extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JTextField nome, x, y;
	private JButton salvar, cancelar;
	private ComponentItem item = null;
	
	public UIDashboardSidebarEditableArea(){
		super(new GridLayout(4, 2, 5, 5));
		setup();
	}
	
	private void setup(){
		add(new JLabel("Nome:"));
		nome = new JTextField(10);
		add(nome);
		add(new JLabel("X:"));
		x = new JTextField("0",10);
		add(x);
		add(new JLabel("Y:"));
		y = new JTextField("0",10);
		add(y);
		salvar = new JButton("Salvar", Icons.Accept);
		salvar.addActionListener(this);
		add(salvar);
		cancelar = new JButton("Cancelar", Icons.Delete);
		cancelar.addActionListener(this);
		add(cancelar);
	}
	
	private void loadInfos(){
		
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		nome.setEnabled(enabled);
		x.setEnabled(enabled);
		y.setEnabled(enabled);
		salvar.setEnabled(enabled);
		cancelar.setEnabled(enabled);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cancelar")){
			clear();
		} else {
			
		}
	}
	
	public void clear(){
		nome.setText("");
		x.setText("0");
		y.setText("0");
		item = null;
		setEnabled(false);
	}
	
	public void setItem(ComponentItem i){
		item = i;
		nome.setText(item.getNome());
		x.setText(item.getX() + "");
		y.setText(item.getY() + "");
		setEnabled(true);
	}
	
	
}
