package br.com.hrdev.components;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import br.com.hrdev.Window;

public class UITree extends JTree implements MouseListener, TreeSelectionListener {

	private static final long serialVersionUID = 1L;
	private JPopupMenu popup;
	private Window window;
	
	private boolean canOpenMenu = false;
	
	
	public UITree(Window window, DefaultMutableTreeNode node){
		super(node);
		this.window = window;
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
		 DefaultMutableTreeNode node = (DefaultMutableTreeNode) getLastSelectedPathComponent();
		 canOpenMenu = false;
		 if(node == null) return;
		 if(!node.isLeaf()) return;
		 canOpenMenu = true;
		 popup.removeAll();
		 Object objeto = node.getUserObject();
		 
		 popup.add(new JMenuItem("Remover " + objeto));
		 popup.revalidate();
	}
	
}
