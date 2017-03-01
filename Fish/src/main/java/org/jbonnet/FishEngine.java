package org.jbonnet;

import javafx.scene.image.ImageView;

public class FishEngine {

	private Plateau<?> plateau;
	private static final int SIZE_EAU_X = 28;
	private static final int SIZE_EAU_Y = 28;
	private int sizeXWater ;
	private int sizeYWater ;
	private int sizeX ;
	private int sizeY;
	/**
	 * @param sizeX
	 * @param sizeY
	 */
	public FishEngine(int sizeXWater, int sizeYWater) {
		super();
		this.sizeXWater = sizeXWater;
		this.sizeYWater = sizeYWater;
		computeSizeXSizeY();
		initPlateau();
	}
	
	private void initPlateau() {
		plateau = new Plateau<>(sizeX, sizeY, Case::new);
		
	}

	private void computeSizeXSizeY() {
		sizeX = sizeXWater+2;
		sizeY = sizeYWater+2;
		
	}

	public FishEngine() {
		this(SIZE_EAU_X,SIZE_EAU_Y);
		
	}

	/**
	 * @return the sizeXWater
	 */
	public int getSizeXWater() {
		return sizeXWater;
	}

	/**
	 * @return the sizeYWater
	 */
	public int getSizeYWater() {
		return sizeYWater;
	}

	/**
	 * @return the sizeX
	 */
	public int getSizeX() {
		return sizeX;
	}

	/**
	 * @return the sizeY
	 */
	public int getSizeY() {
		return sizeY;
	}

	/**
	 * @return the plateau
	 */
	@SuppressWarnings("unchecked")
	public<View> Plateau<Case<CaseContent, View>> getPlateau() {
		return (Plateau<Case<CaseContent, View>>)plateau;
	}
	
	
	
	
	

}
