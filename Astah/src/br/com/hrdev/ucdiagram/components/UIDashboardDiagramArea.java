package br.com.hrdev.ucdiagram.components;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.hrdev.ucdiagram.UCDiagram;
import br.com.hrdev.ucdiagram.models.Ator;
import br.com.hrdev.ucdiagram.models.Caso;
import br.com.hrdev.ucdiagram.models.ComponentItem;
import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.utils.Icons;
import br.com.hrdev.ucdiagram.views.DashboardView;

public class UIDashboardDiagramArea extends JPanel {

	private static final long serialVersionUID = 1L;
	private UCDiagram window;
	private DashboardView dashboard;
	private JPanel diagramArea;
	private ArrayList<UIToolBarButton> toolbarButtons;
	private ButtonGroup buttonGroup;
	private AddDiagramItem diagramaMouseAdapter = new AddDiagramItem();
	
	
	
	/* Temp Vars */
	private Diagrama currentDiagram = null;
	
	public UIDashboardDiagramArea(UCDiagram window, DashboardView dashboard){
		super(new BorderLayout(0,0));
		this.window = window;
		this.dashboard = dashboard;
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.gray));
		setup();
	}
	
	private void setup(){
		setToolBar();
		setDiagramPanel();
	}
	
	private void setToolBar(){
		JToolBar toolbar = new JToolBar(SwingConstants.HORIZONTAL);
		toolbar.setFloatable(false);

		buttonGroup = new ButtonGroup();
		toolbarButtons = new ArrayList<UIToolBarButton>();

		UIToolBarButton button = new UIToolBarButton(Icons.Ator,"Criar novo ator",UIToolBarButton.Ator);
		buttonGroup.add(button);
		toolbarButtons.add(button);
		toolbar.add(button);
		
		button = new UIToolBarButton(Icons.Error,"Criar novo Caso",UIToolBarButton.Caso);
		buttonGroup.add(button);
		toolbarButtons.add(button);
		toolbar.add(button);
		
		
		add(toolbar,BorderLayout.NORTH);
	}
	
	private void setDiagramPanel(){
		diagramArea = new JPanel(new CardLayout());
		diagramArea.setBackground(Color.gray);
		diagramArea.setBorder(new EmptyBorder(15,15,15,15));
		JScrollPane diagramScrollPane = new JScrollPane(diagramArea);
		diagramScrollPane.setBorder(new EmptyBorder(0,0,0,0));

		add(diagramScrollPane,BorderLayout.CENTER);
	}

	public void updateDiagramAreaData(){
		diagramArea.removeAll();
		currentDiagram = null;
		
		JPanel blank = new JPanel();
		blank.setOpaque(false);
		diagramArea.add(blank,"blank");
		
		for(Diagrama diagrama : window.getProjeto().getDiagramas()){
			diagramArea.add(diagrama,diagrama.getNome());
		}
	}
	
	public void showDiagram(Diagrama diagrama) {
		if(diagrama == null) return;
		
		if(currentDiagram != null){
			currentDiagram.removeMouseListener(diagramaMouseAdapter);
		}
		
		currentDiagram = diagrama;
		currentDiagram.addMouseListener(diagramaMouseAdapter);
		
		CardLayout card = (CardLayout) diagramArea.getLayout();
		card.show(diagramArea, currentDiagram.getNome());

		dashboard.repaint();
	}
	
	public int getSelectedToolbarButton(){
		for(UIToolBarButton button : toolbarButtons)
			if(button.isSelected())
				return button.getTipo();
		
		return -1;
	}
	
	private ComponentItem getSelectedComponent(Point p){
		Component[] layers = currentDiagram.getComponents();
		for (Component layer : layers)
			if(layer instanceof ComponentItem)
				if(layer.contains(p))
					return (ComponentItem) layer;
		
		return null;
	}
	

	private class AddDiagramItem extends MouseAdapter {
		
		private boolean isPressed = false;
		
		
		private void addItem(Point point, int tipo){
			switch(tipo){
				// Cria novo ator
				case UIToolBarButton.Ator : 
					Ator ator = new Ator("Ator " + window.getProjeto().getAtores().size());
					ator.setPoint(point);
					currentDiagram.add(ator);		
					window.getProjeto().getAtores().add(ator);
					
				break;
				// Criar novo caso
				case UIToolBarButton.Caso :
					String titulo = JOptionPane.showInputDialog("Digite o caso:");
					if(titulo == null || titulo.trim().equals("")) break;
					
					Caso caos = new Caso(titulo);
					caos.setPoint(point);
					currentDiagram.add(caos);
				break;
			}
			
			dashboard.getSidebar().updateDataTree();
			buttonGroup.clearSelection();
			dashboard.getSidebar().clearItem();
			dashboard.repaint();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(currentDiagram == null) return;
			
			ComponentItem layer = getSelectedComponent(e.getPoint());
			int tipo = getSelectedToolbarButton();
			
			if(tipo >= 0){
				addItem(e.getPoint(),tipo);
			} else if(layer != null){
				dashboard.getSidebar().editItem(layer);
			} else {
				dashboard.getSidebar().clearItem();
				buttonGroup.clearSelection();
			}
		}
	}
	
}
