package br.com.hrdev;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.hrdev.containers.AddPessoaContainer;
import br.com.hrdev.containers.EditPessoaContainer;
import br.com.hrdev.containers.ListPessoaContainer;
import br.com.hrdev.models.Pessoa;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private Dimension size = new Dimension(500, 400);
	private String title = "Curd Pessoa";
	private List<Pessoa> lista = new ArrayList<Pessoa>();
	
	private JPanel painel;
	
	private ListPessoaContainer listPessoaContainer;
	private AddPessoaContainer addPessoaContainer;
	private EditPessoaContainer editPessoaContainer;
	
	public static void main(String[] args) {
		new Main();
		System.out.println("-- By Henrique Rieger | Senac RS --");
	}
	
	private Main(){
		setSize(size);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle(title);
		
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(10,10,10,10));
		painel.setLayout(new BorderLayout(10,10));
		
		setContainer();
		setContentPane(painel);
		setVisible(true);
	}

	private void setContainer() {
		listPessoaContainer = new ListPessoaContainer(this);

		painel.add(listPessoaContainer);
		
		listPessoaContainer.setVisible(true);
		listPessoaContainer.update();
	}
	
	public void changeViewToAdd(){
		listPessoaContainer.setVisible(false);
		addPessoaContainer = new AddPessoaContainer(this);
		painel.add(addPessoaContainer);
		addPessoaContainer.setVisible(true);
		
	}
	
	public void changeViewToList(){
		if(addPessoaContainer != null){
			addPessoaContainer.setVisible(false);
			addPessoaContainer = null;
		}
		
		if(editPessoaContainer != null){
			editPessoaContainer.setVisible(false);
			editPessoaContainer = null;
		}

		painel.add(listPessoaContainer);
		listPessoaContainer.setVisible(true);
	}

	public void removeListItem(int index){
		Pessoa pessoa = lista.get(index);
		if(pessoa != null){
			String message = "Voce realmente quer remover o/a " + pessoa.getNome() + " da lista?";
			
			int status = JOptionPane.showConfirmDialog(this,message,"Remover Registro",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
			
			if(status == JOptionPane.OK_OPTION){
				lista.remove(index);
				listPessoaContainer.update();
			}
		} else {
			System.out.println("removeListItem: Pessoa na posicao " + index + " e igual a null");
		}
	}

	public void editListItem(int index){
		Pessoa pessoa = lista.get(index);
		if(pessoa != null){
			listPessoaContainer.setVisible(false);
			editPessoaContainer = new EditPessoaContainer(this,pessoa);
			painel.add(editPessoaContainer);
			editPessoaContainer.setVisible(true);	
		} else {
			System.out.println("editListItem: Pessoa na posicao " + index + " e igual a null");
		}
	}
	
	public void savePessoa(Pessoa pessoa) {
		this.lista.add(pessoa);
		listPessoaContainer.update();
		changeViewToList();
	}
	
	public int getListaSize(){
		return this.lista.size();
	}
	
	public Object[] getListaArray(){
		return lista.toArray();
	}

}
