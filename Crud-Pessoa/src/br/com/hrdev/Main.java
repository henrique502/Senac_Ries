package br.com.hrdev;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.hrdev.controllers.ListaController;
import br.com.hrdev.events.CloseWindow;
import br.com.hrdev.libraries.Storage;

@SuppressWarnings("serial")
public class Main extends JFrame {

	private Dimension size = new Dimension(500, 400);
	private String title = "Navbars";

	private JPanel painel;
	private CardLayout layout;
	
	private Storage storage;
	private File currentDir;
	
	
	public final static String Lista = "lista";
	public final static String Cadastro = "";
	public final static String Remover = "";
	
	public static void main(String[] args) {
		new Main();
		System.out.println("-- By Henrique Rieger | Senac RS --");
	}
	
	private Main(){
		setSize(size);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle(title);
		
		storage = new Storage();
		layout = new CardLayout();
		painel = new JPanel();
		painel.setBorder(new EmptyBorder(10,10,10,10));
		painel.setLayout(layout);
		
		initData();
		setupControllers();
		
		JPanel painel1 = new JPanel();
		painel1.setBackground(Color.BLUE);
		painel.add(painel1,"blue");
		
		getContentPane().add(painel);

		swapController(Lista);
		
		
		addWindowListener(new CloseWindow(this));
		setVisible(true);
	}
	
	private void setupControllers() {
		painel.add(new ListaController(this),Lista);
		//painel.add(new MainController(this),"main");
		//painel.add(new MainController(this),"main");
	}
	
	private void initData(){
		currentDir = new File(System.getProperty("user.dir"));
		JFileChooser fileChoose = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".*storage", "storage");
		
		fileChoose.setCurrentDirectory(getDir());
		fileChoose.setFileFilter(filter);
		
		try {
			int respose = fileChoose.showDialog(this, "Abrir");
			
			if (respose == JFileChooser.APPROVE_OPTION) {
				File file = fileChoose.getSelectedFile();
				if(file.isFile()){
					getStorage().getData(file);
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

	public void swapController(String controller){
		layout.show(painel,controller);
	}
	
	public Storage getStorage(){
		return this.storage;
	}
	
	public File getDir(){
		return this.currentDir;
	}
	

	
/*
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
*/
}
