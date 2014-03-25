package br.com.hrdev.containers;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.com.hrdev.Main;
import br.com.hrdev.models.Pessoa;

@SuppressWarnings("serial")
public class AddPessoaContainer extends JPanel {

	private Main controller;
	
	private JButton buttonSalvar;
	private JButton buttonCancelar;
	private TextField fieldNome;
	private TextField fieldTelefone;

	public AddPessoaContainer(Main controller){
		super();	

		this.controller = controller;
		
		setVisible(false);
		setLayout(new BorderLayout());
		
		JPanel formularioBox = new JPanel();
		formularioBox.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JPanel formulario = new JPanel();
		
		setFormulario(formulario);

		buttonSalvar = new JButton("Salvar");
		buttonCancelar = new JButton("Cancelar");

		buttonSalvar.addActionListener(salvarAction());
		buttonCancelar.addActionListener(cancelarAction());

		buttonSalvar.setEnabled(false);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		buttons.add(buttonSalvar);
		buttons.add(buttonCancelar);
		
		formularioBox.add(formulario);
		
		add(formularioBox,BorderLayout.CENTER);
		add(buttons,BorderLayout.SOUTH);
	}

	private void setFormulario(JPanel formulario) {
		formulario.setLayout(new GridLayout(2,2,5,5));
		
		JLabel labelNome = new JLabel("Nome:",SwingConstants.RIGHT);
		JLabel labelTelefone = new JLabel("Telefone:",SwingConstants.RIGHT);
		
		
		fieldNome = new TextField(20);
		fieldTelefone = new TextField(20);
		
		fieldNome.addKeyListener(textFieldsListenern());
		fieldTelefone.addKeyListener(textFieldsListenern());
		
		formulario.add(labelNome);
		formulario.add(fieldNome);
		formulario.add(labelTelefone);
		formulario.add(fieldTelefone);
	}

	private ActionListener salvarAction() {
		ActionListener action = new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	controller.savePessoa(new Pessoa(fieldNome.getText(),fieldTelefone.getText()));
            }
		};
		return action;
	}
	
	private ActionListener cancelarAction() {
		ActionListener action = new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	controller.changeViewToList();
            }
		};
		return action;
	}
	
	private KeyAdapter textFieldsListenern() {
		KeyAdapter adapter = new KeyAdapter(){
			public void keyReleased(KeyEvent e){
            	int nome = fieldNome.getText().length();
            	int telefone = fieldTelefone.getText().length();

            	if(telefone > 1 && nome > 1){
            		buttonSalvar.setEnabled(true);
            	} else {
            		buttonSalvar.setEnabled(false);
            	}
            }

            public void keyTyped(KeyEvent e){}

            public void keyPressed(KeyEvent e){}
		};
		return adapter;
	}
	
}
