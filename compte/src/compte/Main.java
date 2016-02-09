package compte;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) {
		
		float sommeDepenseJonathan = 0;
		float sommeDepenseJulia = 0;
		System.out.println("dépense Jonathan : ( rien pour passer à Julia) ");
		Float value = null;
		while((value = readFloat())!=null){
			System.out.print(value+"+"+sommeDepenseJonathan+"=");
			sommeDepenseJonathan+=value;
			System.out.println(sommeDepenseJonathan);
		}
		
		System.out.println("dépense Julia : ( rien pour passer à la balance ) ");
		value = null;
		while((value = readFloat())!=null){
			System.out.print(value+"+"+sommeDepenseJulia+"=");
			sommeDepenseJulia+=value;
			System.out.println(sommeDepenseJulia);
		}
		
		float total = sommeDepenseJonathan+sommeDepenseJulia;
		System.out.println("dépense Jonathan : "+format(sommeDepenseJonathan));
		System.out.println("dépense Julia : "+format(sommeDepenseJulia));
		System.out.println("total : "+total);
		
		float balanceJonathan = total * 0.6f - sommeDepenseJonathan;
		float balanceJulia = total * 0.4f - sommeDepenseJulia;
		System.out.println("balance Jonathan "+format(total)+" * 0.6 - "+format(sommeDepenseJonathan)+": "+format(balanceJonathan));
		System.out.println("balance Julia "+format(total)+" * 0.4 - "+format(sommeDepenseJulia)+": "+format(balanceJulia));
		
		float deducationJonathan = 0;
		float deducationJulia = 0;
		System.out.println("déduction Jonathan : ( rien pour passer à Julia) ");
		value = null;
		while((value = readFloat())!=null){
			System.out.print(deducationJonathan+"+"+value+"=");
			deducationJonathan+=value;
			System.out.println(deducationJonathan);
		}
		
		System.out.println("déduction Julia : ( rien pour passer à la balance finale) ");
		value = null;
		while((value = readFloat())!=null){
			System.out.print(deducationJulia+"+"+value+"=");
			deducationJulia+=value;
			System.out.println(deducationJulia);
		}
		
		System.out.println("balance finale Jonathan :"+format(balanceJonathan)+" - "+format(deducationJonathan)+" = "+format(balanceJonathan - deducationJonathan));
		System.out.println("balance finale Julia :"+format(balanceJulia)+" - "+format(deducationJulia)+" = "+format(balanceJulia - deducationJulia));
	}
	
	private static String format(float balanceJulia) {
		return String.format("%.2f", balanceJulia);
	}

	public static Float readFloat(){
		try {
			String read = in.readLine();
			float parseFloat = Float.parseFloat(read);
			return parseFloat;
		} catch (IOException | NumberFormatException e ) {
			e.printStackTrace();
			return null;
		}
	}

}
