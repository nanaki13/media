package org.nanaki.db;

import java.util.List;

public interface Manager<T> {
	public void save(T t);
	public void update(T t);
	public boolean exists(T t);
	public boolean isMultipleStatement();
	public boolean finishSaveAll();
	public boolean finishUpdateAll();
	public default void saveOrUpdate(T t){
		if(exists(t)){
			update(t);
		}else{
			save(t);
		}
	}
	
	public default void saveOrUpdateAll(List<T> ts){
		for(T t : ts){
			saveOrUpdate(t);
		}
	}
	
	public default void saveAll(List<T> ts){
		for(T t : ts){
			save(t);
		}
		if(isMultipleStatement()){
			finishSaveAll();
		}
	}
	
	public default void updateAll(List<T> ts){
		for(T t : ts){
			update(t);
		}
		if(isMultipleStatement()){
			finishUpdateAll();
		}
	}
}
