package org.jbonnet;

public class Fish {
	
	private int waterDeep;
	private int id;
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
	/**
	 * @return the waterDeep
	 */
	public int getWaterDeep() {
		return waterDeep;
	}
	/**
	 * @param waterDeep the waterDeep to set
	 */
	public void setWaterDeep(int waterDeep) {
		this.waterDeep = waterDeep;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Fish [waterDeep=" + waterDeep + ", id=" + id + ", size=" + size + ", weight=" + weight + ", proba="
				+ proba + ", cost=" + cost + "]";
	}
	
	
	
	
	
	

}
