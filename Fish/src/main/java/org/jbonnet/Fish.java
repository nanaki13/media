package org.jbonnet;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Fish {
	private String code;
	private int size;
	private int weight;
	private double proba;
	private double cost;
	
	
	
	/**
	 * 
	 */
	public Fish() {
		super();
	}
	/**
	 * @param size
	 * @param weight
	 * @param proba
	 * @param cost
	 * @param code
	 */
	public Fish(String code,int size, int weight, double proba, double cost) {
		super();
		this.size = size;
		this.weight = weight;
		this.proba = proba;
		this.cost = cost;
		this.code = code;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	/**
	 * @return the proba
	 */
	public double getProba() {
		return proba;
	}
	/**
	 * @param proba the proba to set
	 */
	public void setProba(double proba) {
		this.proba = proba;
	}
	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Fish [code=" + code + ", size=" + size + ", weight=" + weight + ", proba=" + proba + ", cost=" + cost
				+ "]";
	}
	
	
	

}
