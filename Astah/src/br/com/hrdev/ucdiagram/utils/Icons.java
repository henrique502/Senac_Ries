package br.com.hrdev.ucdiagram.utils;

import java.net.URL;

import javax.swing.ImageIcon;

public class Icons {
	
	public static final ImageIcon Accept = new Icons().getIcon("accept");
	public static final ImageIcon Add = new Icons().getIcon("add");
	public static final ImageIcon Delete = new Icons().getIcon("delete");
	public static final ImageIcon Edit = new Icons().getIcon("edit");
	public static final ImageIcon Help = new Icons().getIcon("help");
	public static final ImageIcon Favicon = new Icons().getIcon("favicon");
	public static final ImageIcon Disk = new Icons().getIcon("disk");
	public static final ImageIcon Error = new Icons().getIcon("error");
	public static final ImageIcon Exit = new Icons().getIcon("exit");
	public static final ImageIcon Export = new Icons().getIcon("export");
	public static final ImageIcon Folder = new Icons().getIcon("folder");
	public static final ImageIcon Folder_add = new Icons().getIcon("folder_add");
	public static final ImageIcon Folder_edit = new Icons().getIcon("folder_edit");
	public static final ImageIcon Folder_delete = new Icons().getIcon("folder_delete");
	public static final ImageIcon Folder_close = new Icons().getIcon("folder_close");
	public static final ImageIcon Folder_open = new Icons().getIcon("folder_open");
	public static final ImageIcon Page_add = new Icons().getIcon("page_add");
	public static final ImageIcon Page_edit = new Icons().getIcon("page_edit");
	public static final ImageIcon Page_delete = new Icons().getIcon("page_delete");
	public static final ImageIcon Page_empty = new Icons().getIcon("page_empty");
	public static final ImageIcon Page = new Icons().getIcon("page");
	public static final ImageIcon Ator = new Icons().getIcon("ator");
	
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
