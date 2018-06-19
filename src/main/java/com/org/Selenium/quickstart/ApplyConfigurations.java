package com.org.Selenium.quickstart;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.Utilities;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApplyConfigurations {
	
	public String jsonConfFile = Settings.getJSONAbsolutePath();
	public String iniConfFile = Settings.getINIAbsolutePath();
	public ArrayList<String> URLSG1 = new ArrayList<String>();
	public ArrayList<String> URLSG2 = new ArrayList<String>();
	
	public String description = "";
	public boolean hasImage = false;
	public String imageDirectory = "";
	
	public ApplyConfigurations() {}
	
	
	public void getConfiguration(){
		
		String jsonObject = null;
		File f = new File(this.jsonConfFile);
        if (f.exists()){
            InputStream is;
			try {
				is = new FileInputStream(this.jsonConfFile);
				jsonObject = IOUtils.toString(is, "UTF-8");
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        }
		
		//converting to json
		if(jsonObject==null) { return;}
		JSONObject json = new JSONObject(jsonObject);
		
		JSONArray groupType1 = json.getJSONArray("groupType1");
		JSONArray groupType2 = json.getJSONArray("groupType2");
		
		this.URLSG1.removeAll(this.URLSG1);
		this.URLSG2.removeAll(this.URLSG2);
		for(int i=0; i<groupType1.length(); i++) {
			this.URLSG1.add((String) groupType1.get(i));
		}
		for(int i=0; i<groupType2.length(); i++) {
			this.URLSG2.add((String) groupType2.get(i));
		}
		JSONObject post = (JSONObject)json.getJSONArray("post").get(0);
		this.description = post.getString("description");
		this.imageDirectory = post.getString("imageDirectory");
		this.hasImage = post.getBoolean("hasImage");
		
	}
	
	public void showConfiguration(JTable _table, JTable _table2, JTextArea _textArea, JTextField _filePath, JCheckBox _hasImage) {
		if(URLSG1.size() == 0)
			getConfiguration();
		if(this.hasImage == true) {
			_filePath.setText(this.imageDirectory);
			_hasImage.setSelected(true);
			_textArea.setText(this.description);
		}
		
		DefaultTableModel tm1 = (DefaultTableModel) _table.getModel();
		DefaultTableModel tm2 = (DefaultTableModel) _table2.getModel();
		
		
		for(int i=0; i<this.URLSG1.size(); i++) {
			tm1.addRow(new Object[] {this.URLSG1.get(i)});
		}
		for(int i=0; i<this.URLSG2.size(); i++) {
			tm2.addRow(new Object[] {this.URLSG2.get(i)});
		}
	}
	
	public void reloadTables(JTable _table, JTable _table2) {
		if(URLSG1.size() == 0)
			getConfiguration();
		
		DefaultTableModel tm1 = (DefaultTableModel) _table.getModel();
		DefaultTableModel tm2 = (DefaultTableModel) _table2.getModel();
		
		tm1.setRowCount(0);
		tm2.setRowCount(0);
		for(int i=0; i<this.URLSG1.size(); i++) {
			tm1.addRow(new Object[] {this.URLSG1.get(i)});
		}
		for(int i=0; i<this.URLSG2.size(); i++) {
			tm2.addRow(new Object[] {this.URLSG2.get(i)});
		}
	}
	
	public void removeURL (String _url, int _selectedGroup) {
		String jsonObject = null;
		
		//loading json file
		try {	Scanner scanner;
				scanner = new Scanner( new File(this.jsonConfFile), "UTF-8" );
				jsonObject = scanner.useDelimiter("\\A").next();
				scanner.close();
		} catch (FileNotFoundException e) {e.printStackTrace();}
		if(jsonObject==null) { return;}
		JSONObject json = new JSONObject(jsonObject);
		
		JSONArray groupType1 = json.getJSONArray("groupType1");
		JSONArray groupType2 = json.getJSONArray("groupType2");
		
		this.getConfiguration();
		
		if(_selectedGroup==1) {
			URLSG1.remove(_url);
		}
		else {
			URLSG2.remove(_url);
		}
		JSONObject final_obj = new JSONObject();
		final_obj.put("groupType1", this.URLSG1);
		final_obj.put("groupType2", this.URLSG2);
		
		
		JSONObject post_obj = new JSONObject();
		post_obj.put("description", this.description);
		post_obj.put("hasImage", this.hasImage);
		post_obj.put("imageDirectory", this.imageDirectory);
		
		final_obj.put("post", new JSONArray().put(post_obj));
		
		
		
		PrintWriter file;
		try {
			file = new PrintWriter(this.jsonConfFile, "UTF-8");
			file.write(final_obj.toString());
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void addNewURL(String _url, int _selectedGroup) {
		
		String jsonObject = null;
		
		//loading json file
		try {	Scanner scanner;
				scanner = new Scanner( new File(this.jsonConfFile), "UTF-8" );
				jsonObject = scanner.useDelimiter("\\A").next();
				scanner.close();
		} catch (FileNotFoundException e) {e.printStackTrace();}
		if(jsonObject==null) { return;}
		JSONObject json = new JSONObject(jsonObject);
		
		JSONArray groupType1 = json.getJSONArray("groupType1");
		JSONArray groupType2 = json.getJSONArray("groupType2");
		
		this.getConfiguration();
		if(_selectedGroup==1) {
			URLSG1.add(_url);
		}
		else {
			URLSG2.add(_url);
		}
			
		
		JSONObject final_obj = new JSONObject();
		final_obj.put("groupType1", this.URLSG1);
		final_obj.put("groupType2", this.URLSG2);
		
		
		JSONObject post_obj = new JSONObject();
		post_obj.put("description", this.description);
		post_obj.put("hasImage", this.hasImage);
		post_obj.put("imageDirectory", this.imageDirectory);
		
		final_obj.put("post", new JSONArray().put(post_obj));
		
		
		
		PrintWriter file;
		try {
			file = new PrintWriter(this.jsonConfFile, "UTF-8");
			file.write(final_obj.toString());
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void updateImageDescription(boolean _isChecked, String _imgDir, String _description) {
		String jsonObject = null;
		
		//loading json file
		try {	Scanner scanner;
				scanner = new Scanner( new File(this.jsonConfFile), "UTF-8" );
				jsonObject = scanner.useDelimiter("\\A").next();
				scanner.close();
		} catch (FileNotFoundException e) {e.printStackTrace();}
		if(jsonObject==null) { return;}
		JSONObject json = new JSONObject(jsonObject);
		
		JSONArray groupType1 = json.getJSONArray("groupType1");
		JSONArray groupType2 = json.getJSONArray("groupType2");
		
		this.getConfiguration();
		
		JSONObject final_obj = new JSONObject();
		final_obj.put("groupType1", this.URLSG1);
		final_obj.put("groupType2", this.URLSG2);
		
		
		JSONObject post_obj = new JSONObject();
		post_obj.put("description", _description);
		post_obj.put("hasImage", _isChecked);
		post_obj.put("imageDirectory", _imgDir);
		
		final_obj.put("post", new JSONArray().put(post_obj));
		
		
		
		PrintWriter file;
		try {
			file = new PrintWriter(this.jsonConfFile, "UTF-8");
			file.write(final_obj.toString());
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
