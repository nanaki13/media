package org.jbonnet.bean.form;

import java.util.Calendar;
import java.util.Collection;
import java.util.function.Function;

import org.jbonnet.bean.ObjectIOInterface;

import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class FxViewFactory {
	public static Node fillViewComponent(Class<?> c, Function<String, FieldContext> fieldMapping,
			CompomentManipulator compomentManipulator, Pane parent) {
		ObjectIOInterface instance = ObjectIOInterface.Factory.getInstance(c);
		Collection<String> fields = instance.getFields();

		Node compoment = null;
		Label label = null;
		boolean findMatch = false;
		for (String s : fields) {
			FieldContext apply = fieldMapping.apply(s);
			if (!apply.isHide()) {
				Class<?> type = instance.getType(s);
				if (isSimple(type)) {
					
					label = new Label(fieldMapping.apply(s).getLabel());
					compomentManipulator.onFieldChange(label, compoment, parent);
					compoment = new TextField();
					findMatch = true;
				} else if (Calendar.class.isAssignableFrom(type)) {
					
					label = new Label(fieldMapping.apply(s).getLabel());
					compomentManipulator.onFieldChange(label, compoment, parent);
					compoment = new DatePicker();
					findMatch = true;
				}
				if (findMatch) {
					compoment.setId(c.getSimpleName() + "" + s);
					
					compomentManipulator.afterLabel(compoment, label, parent);
					parent.getChildren().addAll(label, compoment);
					findMatch = false;
				}

			}

		}
		return compoment;
	}

	private static boolean isSimple(Class<?> type) {
		return type == String.class || Number.class.isAssignableFrom(type) || type == Boolean.class;
	}
}
