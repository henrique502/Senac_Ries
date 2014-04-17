package br.com.hrdev.ucdiagram.components;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import br.com.hrdev.ucdiagram.models.Ator;
import br.com.hrdev.ucdiagram.utils.Icons;

// https://community.oracle.com/thread/2075008?start=0&tstart=0

@SuppressWarnings("serial")
public class UITreeCellRenderer extends DefaultTreeCellRenderer implements TreeCellRenderer {
	
	public UITreeCellRenderer(){
		super();
		
		setOpenIcon(Icons.Folder_open);  
        setClosedIcon(Icons.Folder_close);  
        
        setLeafIcon(Icons.Page_empty);  
        //setIcon(Icons.Page);
	}
	
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		if(value instanceof Ator){
			
		} else {
			return super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		}
	}
}
