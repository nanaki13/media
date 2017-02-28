package org.jbonnet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

public class Plateau<T> implements Iterable<T> {
	List<List<T>> cases;
	private int sizeY;
	private int sizeX;
	
	public Plateau(int sizeX , int sizeY){
		this(sizeX,sizeY,()->null);
	}
	
	public Plateau(int x , int y,Supplier<T> newT){
		this.sizeX = x;
		this.sizeY = y;
		cases = new ArrayList<>(y);
		for(int i = 0 ; i< y ; i++){
			List<T> l = new ArrayList<>(x);
			for(int j = 0 ;j < x ; j++){
				l.add(newT.get());
			}
			
			cases.add(l);
		}
	}
	
	public T getCase(int x, int y){
		return cases.get(y).get(x);
	}
	
	public T setCase(int x, int y, T t){
		return cases.get(y).set(x,t);
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Iterator<List<T>> ite ;
			Iterator<T> iteSub ;
			private void init(){
				if(ite == null){
					ite = Plateau.this.cases.iterator();
					if(ite.hasNext()){
						iteSub = ite.next().iterator();
					}
				}
			}
			@Override
			public boolean hasNext() {
				init();
				if(iteSub.hasNext()){
					return true;
				}else{
					if(ite.hasNext()){
						iteSub = ite.next().iterator();
						return iteSub.hasNext();
					}
				}
				return false;
			}

			@Override
			public T next() {
				return iteSub.next();
			}
		};
	}
}
