package br.com.hrdev.utils;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.net.URL;

public class Fonts {
	
	public static final String ShadowsIntoLight = new Fonts().getFont("ShadowsIntoLight");
	
	
	private static final String Ext = ".ttf";
	
	private String getFont(String fontName) {
		try {
			URL imgURL = getClass().getResource("/assets/fonts/" + fontName + Ext);
			if (imgURL != null) {

		    	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    	ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(imgURL.toURI())));
		    	
		    	return fontName;
		    } else {
		        System.err.println("Fonte nao existe: " + "/assets/icons/" + fontName + Ext);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}