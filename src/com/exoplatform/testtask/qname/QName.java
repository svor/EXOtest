package com.exoplatform.testtask.qname;

public class QName {
	private String name;
	private String prefix;
	private String localName;
	QName(){
		this.name = "";
		this.prefix = "";
		this.localName = "";
	}
	QName(String s){
		this.name = s;
		initLocalAndPrefix();
	}
	private void initLocalAndPrefix(){
		int indexTwoDot = name.indexOf(":");
		if(indexTwoDot != -1){
			prefix = name.substring(0,indexTwoDot);
			localName = name.substring(indexTwoDot+1,name.length());
		}
		else {
			prefix = "";
			localName = name;
		}
	}
	public String getLocalName(){
		return localName;
	}
	public String getPrefix(){
		return prefix;
	}
	public  String getAsString(){
		return name;
	}
	
}
