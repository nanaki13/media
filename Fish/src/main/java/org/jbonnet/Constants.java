package org.jbonnet;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.geometry.Point2D;

public class Constants {
	public enum Orientation {

		N,NO,O,SO,S,SE,E,NE;
		
		public static Orientation getOrientation(int i, int j, int maxI, int maxJ){
			j=maxJ-j;
			double orgI = maxI / 2d;
			double orgY = maxJ / 2d;
			Point2D point2d = new Point2D(i, j);
			Point2D org = new Point2D(orgI, orgY);
			Point2D vetorOX = point2d.subtract(org);
			Point2D normalize = vetorOX.normalize();
			
			if(!vetorOX.equals(Point2D.ZERO)){
				double angle = Math.atan(normalize.getY()/normalize.getX());
				double angleFromCos = Math.toDegrees(Math.acos(normalize.getX()));
				return getOrientation(Math.signum(normalize.getY()!=0d ? normalize.getY() : normalize.getX()) * angleFromCos);
			}else{
				return null;
			}
			
		}

		private static Orientation getOrientation(double angle) {
			
			
			if(angle < 0){
				angle = 360 + angle;
			}
			angle = angle - 22.5d;
			if(angle < 0){
				angle = 360 + angle;
			}
			double pos = ((angle) % 360d)  ;
			int ord = (int) (pos / 45) ; 
			return values()[(ord + 7)%values().length];
		}
	}
	public static final short DEEP0 = 0;
	public static final short DEEP1 = 1;
	public static final short DEEP2 = 2;
	public static final short DEEP3 = 3;
	private static Map<Deep,Water> DeepToWater = null;
	public enum Deep{
		DO,D1,D2,D3;

		public Water toWater(List<Water> allWaters) {
			if(DeepToWater == null){
				DeepToWater =allWaters.stream().collect(Collectors.toMap(( w)-> w.toDeep(), ( w)-> w));
			}
			return DeepToWater.get(this);
		}

		public Water toWater() {
			return DeepToWater.get(this);
			
		}
	}
}
