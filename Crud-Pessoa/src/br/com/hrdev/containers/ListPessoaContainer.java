package br.com.hrdev.containers;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.hrdev.Main;

public class ListPessoaContainer extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Main controller;
	private JList jlist;
	private JButton buttonCadastrar;
	private JButton buttonEditar;
	private JButton buttonRemover;
	
	public ListPessoaContainer(Main controller){
		super();
		this.controller = controller;
		setVisible(false);
		setLayout(new BorderLayout());
		
		jlist = new JList();
		jlist.addListSelectionListener(onListSelect());
		
		buttonCadastrar = new JButton("Cadastrar");
		buttonEditar = new JButton("Editar");
		buttonRemover = new JButton("Remover");
		
		buttonCadastrar.addActionListener(cadastrarAction());
		buttonEditar.addActionListener(editarAction());
		buttonRemover.addActionListener(removerAction());
		
		buttonEditar.setEnabled(false);
		buttonRemover.setEnabled(false);
		
		JPanel painel = new JPanel();
		painel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		painel.add(buttonCadastrar);
		painel.add(buttonEditar);
		painel.add(buttonRemover);
		
		add(jlist,BorderLayout.CENTER);
		add(painel,BorderLayout.SOUTH);
	}
	
	public void update(){
		Object[] data = controller.getListaArray();
		
		buttonEditar.setEnabled(false);
		buttonRemover.setEnabled(false);
		
		jlist.setListData(data);
	}
	
	private ActionListener cadastrarAction(){
		ActionListener action = new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	controller.changeViewToAdd();
            }
		};
		return action;
	}
	
	private ActionListener editarAction(){
		ActionListener action = new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	controller.editListItem(jlist.getSelectedIndex());
            }
		};
		return action;
	}
	
	private ActionListener removerAction(){
		ActionListener action = new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	controller.removeListItem(jlist.getSelectedIndex());
            }
		};
		return action;
	}
	
	private ListSelectionListener onListSelect(){
		ListSelectionListener listiner = new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e){
				if(!e.getValueIsAdjusting()){
					if(jlist.getSelectedValue() != null){
						buttonEditar.setEnabled(true);
						buttonRemover.setEnabled(true);
					}
				}
			}
		};
		return listiner;
	}
}
