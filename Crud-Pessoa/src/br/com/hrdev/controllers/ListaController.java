package br.com.hrdev.controllers;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.hrdev.Main;
import br.com.hrdev.events.CloseWindow;

@SuppressWarnings("serial")
public class ListaController extends JPanel implements Controller {

	private Main window;
	
	private JMenuBar barra;
	private JList jlist;
	private JButton buttonCadastrar;
	private JButton buttonEditar;
	private JButton buttonRemover;
	
	JMenuItem itemEditar;
    JMenuItem itemRemover;
		
	public ListaController(Main window){
		this.window = window;
		setup();
	}

	private void setup() {
		
		setLayout(new BorderLayout(10,10));

		setMenus();

		jlist = new JList();
		jlist.addListSelectionListener(new ListaManager());
		
		buttonCadastrar = new JButton("Cadastrar");
		buttonEditar = new JButton("Editar");
		buttonRemover = new JButton("Remover");
		
		buttonCadastrar.addActionListener(new ActionAddItem());
		buttonEditar.addActionListener(new ActionEditItem());
		buttonRemover.addActionListener(new ActionRemoveItem());
		
		buttonEditar.setEnabled(false);
		buttonRemover.setEnabled(false);
		
		JPanel painel = new JPanel();
		painel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		painel.add(buttonCadastrar);
		painel.add(buttonEditar);
		painel.add(buttonRemover);
		
		add(jlist,BorderLayout.CENTER);
		add(painel,BorderLayout.SOUTH);
		
		updateUI();
	}
	
	private void setMenus(){
		barra = new JMenuBar();
		JMenu menuArquivo = new JMenu("Arquivo");
		JMenu ajuda = new JMenu("Ajuda");
		
		menuArquivo.setMnemonic('A');
		
		/* Item Novo */
		JMenuItem itemNovo = new JMenuItem("Novo");  
		itemNovo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		itemNovo.setMnemonic(KeyEvent.VK_N);
		itemNovo.addActionListener(new ActionAddItem());
		
		/* Item Editar */
		itemEditar = new JMenuItem("Editar");  
		itemEditar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK));
		itemEditar.setMnemonic(KeyEvent.VK_E);
		itemEditar.addActionListener(new ActionEditItem());
		
		/* Item Remover */
		itemRemover = new JMenuItem("Remover");  
		itemRemover.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, Event.CTRL_MASK));
		itemRemover.setMnemonic(KeyEvent.VK_R);
		itemRemover.addActionListener(new ActionRemoveItem());
		
		/* Item Sair */
		JMenuItem itemSair = new JMenuItem("Sair");  
		itemSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Event.CTRL_MASK));
		itemSair.setMnemonic(KeyEvent.VK_Q);
		itemSair.addActionListener(new CloseWindow(window));
		
		
        itemEditar.setEnabled(false);
        itemRemover.setEnabled(false);

        menuArquivo.add(itemNovo);
        menuArquivo.add(itemEditar);
        menuArquivo.add(itemRemover);
        menuArquivo.addSeparator();
        menuArquivo.add(itemSair);
        
        barra.add(menuArquivo);
        barra.add(Box.createHorizontalGlue());
        barra.add(ajuda);
	}

	@Override
	public void updateUI(){
		super.updateUI();
		if(window != null){
			Object[] data = window.getStorage().getData().toArray();
			window.setJMenuBar(barra);
			jlist.setListData(data);
		}
	}

	private class ActionAddItem implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//controller.removeListItem(jlist.getSelectedIndex());
		}
	}

	private class ActionEditItem implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//controller.removeListItem(jlist.getSelectedIndex());
	}
}
	
	private class ActionRemoveItem implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//controller.removeListItem(jlist.getSelectedIndex());
		}
	}
	
	private class ListaManager implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(!e.getValueIsAdjusting()){
				if(jlist.getSelectedValue() != null){
					buttonEditar.setEnabled(true);
					buttonRemover.setEnabled(true);
					itemEditar.setEnabled(true);
					itemRemover.setEnabled(true);
				}
			}
		}
	}
}