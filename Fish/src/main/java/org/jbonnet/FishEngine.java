package org.jbonnet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jbonnet.Constants.Deep;
import org.jbonnet.Constants.Orientation;
import org.jbonnet.proba.Shuffle;
import org.jbonnet.util.Print;

public class FishEngine {

	private Plateau<?> plateau;
	private Plateau<Case<CaseContent,?> > plateau_;
	private static final int SIZE_EAU_X = 28;
	private static final int SIZE_EAU_Y = 28;
	private int sizeXWater ;
	private int sizeYWater ;
	private int sizeX ;
	private int sizeY;
	private int nbFish;
	private ModelData modelData;
	private DbConnection dbConnection;
	private List<Fish> allFish;
	private List<Water> allWaters;
	private Map<Water,List<Case<CaseContent,?>>> caseByWater;
	private Map<Water, List<Fish>> plateauFish;
	
	/**
	 * @param sizeX
	 * @param sizeY
	 */
	public FishEngine(int sizeXWater, int sizeYWater) {
		super();
		this.sizeXWater = sizeXWater;
		this.sizeYWater = sizeYWater;
		computeSizeXSizeY();
		nbFish = sizeXWater * sizeYWater / 3;
		initPlateau();
	}
	

	/**
	 * @param dbConnection the dbConnection to set
	 */
	public void setDbConnection(DbConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public void init() throws SQLException{
		
		
		allFish = dbConnection.getAllFish();
		allWaters = dbConnection.getAllWaters();
		caseByWater = plateau.stream().collect(HashMap::new,(m,c)-> addTo(m,(Case<CaseContent, ?>) c) ,HashMap::putAll );
		Print.toConsole(caseByWater);
		modelData = new ModelData(nbFish, allFish, allWaters);
		
		plateauFish = modelData.getPlateauFish();
		giveFishToCase();
		
	}

	private void giveFishToCase() {
		for(Entry<Water,List<Fish>> wfs : plateauFish.entrySet()){
			List<Case<CaseContent, ?>> list = caseByWater.get(wfs.getKey());
			List<Case<CaseContent, ?>> copyList = new ArrayList<>(list);
			Shuffle.randomizeOrder(copyList);
			List<Fish> fs = wfs.getValue();
			for(int i = 0 ; i < fs.size() && i < copyList.size();i++){
				Fish fish = fs.get(i);
				Case<CaseContent, ?> case1 = copyList.get(i);
				case1.getModel().setFish(fish);
			}
		}
		
	}


	private void addTo(HashMap<Water, List<Case<CaseContent, ?>>> m, Case<CaseContent, ?> c) {
		Water water = c.getModel().getDeep().toWater(allWaters);
		List<Case<CaseContent, ?>> list = m.get(water);
		boolean newL = false;
		if(list == null){
			list = new ArrayList<>();
			newL = true;
		}
		list.add(c);
		if(newL){
			m.put(water, list);
		}
		

	}


	private void initPlateau() {
		plateau_ = new Plateau<>(sizeX, sizeY, Case::new);
		plateau = plateau_;
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {


				
				Case<CaseContent,?> case1 =  plateau_.getCase(i, j);
				case1.setX(i);
				case1.setY(j);
				CaseContent caseContent = new CaseContent();
				caseContent.setDeep(getDeep(i,j));
				case1.setModel(caseContent);
				
				case1.setOrientation(getOrientation(i,j));

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
		int ny = j - sizeY / 2;
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
