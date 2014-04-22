package br.com.hrdev.ucdiagram.components;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.hrdev.ucdiagram.UCDiagram;
import br.com.hrdev.ucdiagram.models.Ator;
import br.com.hrdev.ucdiagram.models.ComponentItem;
import br.com.hrdev.ucdiagram.models.Diagrama;
import br.com.hrdev.ucdiagram.utils.Icons;
import br.com.hrdev.ucdiagram.views.DashboardView;

public class UIDashboardDiagramArea extends JPanel {

	private static final long serialVersionUID = 1L;
	private UCDiagram window;
	private DashboardView dashboard;
	private JPanel diagramArea;
	private Diagrama currentDiagram;
	private ArrayList<UIToolBarButton> toolbarButtons;
	private ButtonGroup buttonGroup;
	private AddDiagramItem diagramaMouseAdapter = new AddDiagramItem();

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
		
		for(Diagrama diagrama : window.getProjeto().getDiagramas()){
			if(currentDiagram == null)
				currentDiagram = diagrama;
			
			diagramArea.add(diagrama,diagrama.getNome());
		}
		
		System.out.println(currentDiagram);
		
		if(currentDiagram != null)
			showDiagram(currentDiagram);
	}
	
	public void showDiagram(Diagrama diagrama) {
		if(diagrama == null) return;
		
		currentDiagram.removeMouseListener(diagramaMouseAdapter);
		currentDiagram.addMouseMotionListener(diagramaMouseAdapter);
		
		currentDiagram = diagrama;
		currentDiagram.addMouseListener(diagramaMouseAdapter);
		currentDiagram.addMouseMotionListener(diagramaMouseAdapter);
		
		CardLayout card = (CardLayout) diagramArea.getLayout();
		card.show(diagramArea, currentDiagram.getNome());
		
		dashboard.repaint();
	}
	
	
	

	private class AddDiagramItem implements MouseListener, MouseMotionListener {
		
		private void addItem(Point point){
			if(currentDiagram != null){
				for(UIToolBarButton button : toolbarButtons){
					if(button.isSelected()){
						if(button.getTipo() == UIToolBarButton.Ator){
							Ator ator = new Ator("Ator " + window.getProjeto().getAtores().size());

							ator.setPoint(point);
							currentDiagram.add(ator);
							
							window.getProjeto().getAtores().add(ator);
							dashboard.getSidebar().updateDataTree();
							dashboard.repaint();
						}
						buttonGroup.clearSelection();
						return;
					}
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			Component[] layers = currentDiagram.getComponents();
			for (Component layer : layers) {
				if(layer.contains(e.getPoint())){
					dashboard.getSidebar().editItem((ComponentItem) layer);
					return;
				}
			}
			addItem(e.getPoint());
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
		public void mouseDragged(MouseEvent e) {}

		@Override
		public void mouseMoved(MouseEvent e) {
			if(currentDiagram == null) return;
			
			
			currentDiagram.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			
			for(UIToolBarButton button : toolbarButtons){
				if(button.isSelected()){
					currentDiagram.setCursor(new Cursor(Cursor.HAND_CURSOR));
					return;
				}
			}
		}
	}
	
}
