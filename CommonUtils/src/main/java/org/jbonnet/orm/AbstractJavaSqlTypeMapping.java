package org.jbonnet.orm;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractJavaSqlTypeMapping implements TypeMapping {
		
		private Map<Class<?>,String> mapeTypes;

		
		@Override
		public String apply(Class<?> c){
			return mapeTypes.get(c);
		}
		
		public void init(){
			mapeTypes = new HashMap<>();
			fill(mapeTypes);
		}
		
		
		
		/**
		 * 
		 */
		public AbstractJavaSqlTypeMapping() {
			super();
			init();
		}

		public abstract void fill(Map<Class<?>,String> mapeTypes);
}
