package org.jbonnet.orm;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class SqlLiteTypeMapping extends AbstractJavaSqlTypeMapping {

	

	@Override
	public void fill(Map<Class<?>, String> mapeTypes) {
		mapeTypes.put(Integer.class, "INTEGER");
		mapeTypes.put(Long.class, "INTEGER");
		mapeTypes.put(Boolean.class, "INTEGER");
		mapeTypes.put(Date.class, "INTEGER");
		mapeTypes.put(Calendar.class, "INTEGER");
		mapeTypes.put(Double.class, "REAL");
		mapeTypes.put(Float.class, "REAL");
		mapeTypes.put(Integer.TYPE, "INTEGER");
		mapeTypes.put(Long.TYPE, "INTEGER");
		mapeTypes.put(Boolean.TYPE, "INTEGER");
		mapeTypes.put(Double.TYPE, "REAL");
		mapeTypes.put(Float.TYPE, "REAL");
		mapeTypes.put(String.class, "TEXT");
		
	}

}
