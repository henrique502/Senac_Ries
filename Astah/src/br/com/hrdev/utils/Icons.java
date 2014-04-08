package br.com.hrdev.utils;

import java.net.URL;

import javax.swing.ImageIcon;

public class Icons {
	
	public static final ImageIcon Accept = new Icons().getIcon("accept");
	public static final ImageIcon Add = new Icons().getIcon("add");
	public static final ImageIcon Delete = new Icons().getIcon("delete");
	public static final ImageIcon Edit = new Icons().getIcon("edit");
	public static final ImageIcon Help = new Icons().getIcon("help");
	public static final ImageIcon Favicon = new Icons().getIcon("favicon");
	
	
	private final static String Ext = ".png";
	
	private ImageIcon getIcon(String iconName) {
		URL imgURL = getClass().getResource("/assets/icons/" + iconName + Ext);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL);
	    } else {
	        System.err.println("Icone nao existe: " + "/assets/icons/" + iconName + Ext);
	        return null;
	    }
	}
}
