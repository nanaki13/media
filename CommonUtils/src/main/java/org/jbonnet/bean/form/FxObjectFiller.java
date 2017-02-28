package org.jbonnet.bean.form;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;

import org.jbonnet.bean.ObjectIOInterface;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class FxObjectFiller  {



	public static void fillObject(Object o,Parent parent) {
		ObjectIOInterface instance = ObjectIOInterface.Factory.getInstance(o.getClass());
		Collection<String> fields = instance.getFields();
		for (String f : fields) {
			Node lookup = parent.lookup("#" + o.getClass().getSimpleName() + f);
			if (lookup != null) {
				if (lookup instanceof TextField) {
					TextField tf = (TextField) lookup;
					instance.setToFromString(f, o, tf.getText());
				}else if(lookup instanceof DatePicker){
					DatePicker tf = (DatePicker) lookup;
					LocalDate value = tf.getValue();
					instance.setToFromLocalDate(f, o,value);
				}
			}

		}

	}
	
	public static void fillParent(Object o,Parent parent) {
		ObjectIOInterface instance = ObjectIOInterface.Factory.getInstance(o.getClass());
		Collection<String> fields = instance.getFields();
		for (String f : fields) {
			Node lookup = parent.lookup("#" + o.getClass().getSimpleName() + f);
			if (lookup != null) {
				Object from = instance.getFrom(f, o);
				if (lookup instanceof TextField) {
					TextField tf = (TextField) lookup;
					
					if(from != null){
						tf.setText(from.toString());
					}
					
				}else if(lookup instanceof DatePicker){
					DatePicker tf = (DatePicker) lookup;
					if(from instanceof Calendar){
						Calendar c = (Calendar) from;
						tf.setValue(LocalDate.of(c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DATE)));
					}
				}
			}

		}

	}

}
