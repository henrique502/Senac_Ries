package br.com.hrdev.ucdiagram.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import br.com.hrdev.ucdiagram.models.Ator;
import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.views.DashboardView;

public class UITree extends JTree implements MouseListener, TreeSelectionListener {

	private static final long serialVersionUID = 1L;
	private JPopupMenu popup;
	private DashboardView view;
	
	private boolean canOpenMenu = false;
	
	
	public UITree(DashboardView view){
		super(new DefaultMutableTreeNode("Carregando..."));
		this.view = view;
		setDragEnabled(false);
		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		setFocusable(true);
		addMouseListener(this);
		addTreeSelectionListener(this);
		popup = new JPopupMenu();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(canOpenMenu && SwingUtilities.isRightMouseButton(e)){
			popup.show(this, e.getX(), e.getY());
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mousePressed(MouseEvent e) {}
	
	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		if (isSelectionEmpty()) return;
		TreePath path = getSelectionPath();
		DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
		
		if(!selectedNode.isLeaf()) return;
		
		Object objeto = selectedNode.getUserObject();

		canOpenMenu = false;
		popup.removeAll();
		 
		if(objeto instanceof Diagrama){
			Diagrama diagrama = (Diagrama) objeto;
			view.showDiagram(diagrama);
			 
			canOpenMenu = true;
			//JMenuItem item = new JMenuItem("Remover diagrama " + diagrama);
			 
			popup.add(new JMenuItem("Remover diagrama " + diagrama.getNome()));
			 
		}
		 
		if(objeto instanceof Ator){
			Ator ator = (Ator) objeto;
			canOpenMenu = true;
			popup.add(new JMenuItem("Remover ator " + ator.getName()));
			popup.revalidate();
		}
		popup.revalidate();
	}
	
	public void updateAll(DefaultMutableTreeNode rootNode) {
		DefaultTreeModel model = (DefaultTreeModel) getModel();
		model.setRoot(rootNode);
	}
	
}
