package org.nanaki.db;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

import org.nanaki.model.Film;

public interface Manager<T> {
	public boolean save(T t) throws Exception;
	public boolean update(T t)throws Exception;
	public boolean exists(T t)throws Exception;
	public List<T> getBy(String propName, Object value)throws Exception;
	public boolean isMultipleStatement();
	public boolean finishSaveAll() throws Exception;
	public boolean finishUpdateAll() throws Exception;
	public default void saveOrUpdate(T t)throws Exception{
		if(exists(t)){
			update(t);
		}else{
			save(t);
		}
	}
	
	public default void saveOrUpdateAll(List<T> ts)throws Exception{
		for(T t : ts){
			saveOrUpdate(t);
		}
	}
	
	public default void saveAll(List<T> ts)throws Exception{
		for(T t : ts){
			save(t);
		}
		if(isMultipleStatement()){
			finishSaveAll();
		}
	}
	
	public default void updateAll(List<T> ts)throws Exception{
		for(T t : ts){
			update(t);
		}
		if(isMultipleStatement()){
			finishUpdateAll();
		}
	}
	boolean delete(T t) throws Exception;
	public List<T> getAll() throws Exception;
}
