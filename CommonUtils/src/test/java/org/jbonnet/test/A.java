package org.jbonnet.test;

import org.jbonnet.bean.SetterGetterStringAccess;

public class A implements SetterGetterStringAccess{
	String name;
	String prenom;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
}