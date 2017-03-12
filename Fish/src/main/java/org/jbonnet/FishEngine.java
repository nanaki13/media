package org.jbonnet;

import org.jbonnet.Constants.Deep;
import org.jbonnet.Constants.Orientation;

public class FishEngine {

	private Plateau<?> plateau;
	private Plateau<Case<CaseContent,?> > plateau_;
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
		plateau_ = new Plateau<>(sizeX, sizeY, Case::new);
		plateau = plateau_;
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {

				// Rectangle p = new Rectangle();

				// p.setHeight(SIZE_CASE_X - MARGE);
				// p.setWidth(SIZE_CASE_Y - MARGE);
//				Image c = getRandomImage();
//				ImageView p = new ImageView(c);
//				p.setY(i * SIZE_CASE_X);
//				p.setX(j * SIZE_CASE_Y);
				
				Case<CaseContent,?> case1 =  plateau_.getCase(i, j);
//				case1.setView(p);
				case1.setX(i);
				case1.setY(j);
				CaseContent caseContent = new CaseContent();
				caseContent.setDeep(getDeep(i,j));
				case1.setModel(caseContent);
				
				case1.setOrientation(getOrientation(i,j));
				// p.setFill(c);
//				root.getChildren().add(p);

				// Case c = new Case();
			}
		}
		
	}

	private Orientation getOrientation(int i, int j) {
		
		return Orientation.getOrientation(i, j, sizeX, sizeY);
	}

	private Deep getDeep(int i, int j) {
		if (i == 0 || i == sizeX - 1 || j == 0 || j == sizeY - 1) {
			return Deep.DO;
		}
		int nx = i - sizeX / 2;
		int ny = j - sizeX / 2;
		double rMax = Math.sqrt(sizeXWater * sizeXWater / 4 + sizeYWater * sizeYWater / 4);
		double r = Math.sqrt(nx * nx + ny * ny);
		if (r > rMax * 2 / 3) {
			return Deep.D1;
		} else if (r > rMax * 1 / 3) {
			return Deep.D2;
		} else {
			return Deep.D3;
		}
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
