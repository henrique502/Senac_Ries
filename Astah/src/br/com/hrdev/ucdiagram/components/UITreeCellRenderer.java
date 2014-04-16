package br.com.hrdev.ucdiagram.components;

import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import br.com.hrdev.ucdiagram.utils.Icons;

@SuppressWarnings("serial")
public class UITreeCellRenderer extends DefaultTreeCellRenderer implements TreeCellRenderer {
	
	public UITreeCellRenderer(){
		super();
		
		setOpenIcon(Icons.Folder_open);  
        setClosedIcon(Icons.Folder_close);  
        setLeafIcon(Icons.Page_empty);  
        //setIcon(Icons.Page);
	}
}
