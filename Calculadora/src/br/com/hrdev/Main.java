package br.com.hrdev;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.hrdev.library.MathParser;
import br.com.hrdev.ui.Button;
import br.com.hrdev.ui.Visor;


@SuppressWarnings("serial")
public class Main extends JFrame implements KeyListener {
	
	private Dimension size = new Dimension(250, 300);
	private String title = "Calculadora";

	private JPanel painel;
	private Visor visor;
	
	private char[] labels = {'7','8','9','/','4','5','6','*','1','2','3','-',' ','0','=','+'};
	
	public static void main(String[] args) {
		new Main();
		System.out.println("-- By Henrique Rieger | Senac RS --");
	}
	
	private Main(){
		setPreferredSize(size);
		setMinimumSize(size);
		
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle(title);
		setFocusable(true);
		addKeyListener(this);
		
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(10,10,10,10));
		painel.setLayout(new BorderLayout(10,10));
		
		setVisorConfig();
		setPanelConfig();

		setContentPane(painel);
		setVisible(true);
		pack();
	}
	
	private void setVisorConfig(){
		visor = new Visor();
		painel.add(visor,BorderLayout.NORTH);
	}
	
	private void setPanelConfig() {
		JPanel numberField = new JPanel();
		numberField.setLayout(new GridLayout(4,4,15,15));
		
		for (int i = 0; i < labels.length; i++) {
			if(labels[i] == ' '){
				numberField.add(new JPanel());
			} else {
				Button button = new Button(labels[i]);
				button.addActionListener(mainAction());
				numberField.add(button);
			}
		}
		
		painel.add(numberField,BorderLayout.CENTER);
	}
	
	private ActionListener mainAction() {
		ActionListener action = new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	Button button = (Button) e.getSource();
            	calcule(button.getValue());
            }

		};
		return action;
	}
	
	private void calcule(char value) {
		if(visor.isError()){
			visor.clear();
		}
		
		try {
			switch (value) {
				case '=':
					String[] valores = visor.getText().split(" ");
					if(valores.length == 3){
						int total = MathParser.evaluate(valores[0], valores[1].charAt(0), valores[2]);
						visor.clear();
						visor.setText("" + total);
					} else {
						throw new Exception("Argumento ilegal");
					}
					
				break;
				
				case '+': 
				case '-': 
				case '*': 
				case '/': 
					visor.setText(" " + value + " ");
				break;

				default: visor.setText("" + value); break;
			}
		} catch(Exception e){
			visor.setError(e.getMessage());
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		char key = e.getKeyChar();
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			key = '=';
			
		for (int i = 0; i < labels.length; i++) {
			if(key == labels[i]){
				calcule(key);
				break;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}
