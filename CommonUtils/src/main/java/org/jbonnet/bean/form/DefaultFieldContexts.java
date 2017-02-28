package org.jbonnet.bean.form;

import java.util.Collection;
import java.util.HashMap;
import java.util.function.Function;

import org.jbonnet.bean.ObjectIOInterface;

public class DefaultFieldContexts extends HashMap<String, FieldContext> implements Function<String,FieldContext> {

	@Override
	public FieldContext apply(String t) {
		return get(t);
	}
	
	public DefaultFieldContexts(Class<?> c, String ... fieldsHided){
		Collection<String> fields = ObjectIOInterface.Factory.getInstance(c).getFields();
		for(String f : fields){
			put(f, new FieldContext(f,f,false));
		}
		for(String fieldHided : fieldsHided){
			FieldContext fieldContext = get(fieldHided);
			if(fieldContext != null){
				fieldContext.setHide(true);
			}
		}
	}

}
