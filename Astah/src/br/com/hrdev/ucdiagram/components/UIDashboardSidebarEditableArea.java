package br.com.hrdev.ucdiagram.components;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.hrdev.ucdiagram.models.ComponentItem;
import br.com.hrdev.ucdiagram.utils.Icons;

public class UIDashboardSidebarEditableArea extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JTextField nome, x, y;
	private JButton salvar, cancelar;
	private ComponentItem item = null;
	private UIDashboardSidebar sidebar;
	private MoveObject mo = new MoveObject();
	
	public UIDashboardSidebarEditableArea(UIDashboardSidebar sidebar){
		super(new GridLayout(4, 2, 5, 5));
		this.sidebar = sidebar;
		setup();
	}
	
	private void setup(){
		add(new JLabel("Nome:"));
		nome = new JTextField(10);
		add(nome);
		add(new JLabel("X:"));
		x = new JTextField("0",10);
		x.setEnabled(false);
		
		add(x);
		add(new JLabel("Y:"));
		y = new JTextField("0",10);
		y.setEnabled(false);
		add(y);
		salvar = new JButton("Salvar", Icons.Accept);
		salvar.addActionListener(this);
		add(salvar);
		cancelar = new JButton("Cancelar", Icons.Delete);
		cancelar.addActionListener(this);
		add(cancelar);
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		nome.setEnabled(enabled);
		salvar.setEnabled(enabled);
		cancelar.setEnabled(enabled);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Salvar")){
			String text = nome.getText();
			if(text == null || text.trim().equals("")) return;
			
			item.setNome(nome.getText());
			sidebar.updateDataTree();
		}
		clear();
	}
	
	public void clear(){
		if(item != null){
			item.setSelected(false);
			item.removeMouseMotionListener(mo);
			item.removeMouseListener(mo);
		}
		
		nome.setText("");
		x.setText("0");
		y.setText("0");
		item = null;
		setEnabled(false);
	}
	
	public void setItem(ComponentItem i){
		clear();
		
		item = i;
		item.setSelected(true);
		item.addMouseMotionListener(mo);
		item.addMouseListener(mo);
		item.requestFocus();
		nome.setText(item.getNome());
		setEnabled(true);
		x.setText(item.getX() + "");
		y.setText(item.getY() + "");
	}
	
	private class MoveObject extends MouseAdapter {
		
		private boolean isPressed = false;
		private boolean isDraged = false;
		
		@Override
		public void mousePressed(MouseEvent e) {
			isPressed = true;
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			isDraged = false;
			
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			isDraged = true;
		}
		@Override
		public void mouseMoved(MouseEvent e) {

			if(!isDraged){
				
				Point origin = item.getLocation();
				int x = origin.x - (e.getPoint().x  - item.getWidth());
				int y = origin.y - (e.getPoint().y  - item.getHeight());
				
				System.out.println(x + "/" + y + " = " + origin);
				
				// layer.setLocation(x,y);
			} else {
				
			}
		
		}
	}
	
}
