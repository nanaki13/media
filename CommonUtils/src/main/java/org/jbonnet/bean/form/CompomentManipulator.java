package org.jbonnet.bean.form;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public interface CompomentManipulator {
	public default void onFieldChange(Node current,Node prvious, Node root){
		if( prvious != null)
			GridPane.setConstraints(current, 0, GridPane.getRowIndex(prvious)+1, 1, 1);
		else
			GridPane.setConstraints(current, 0, 0, 1, 1);
	}
	
	public default void afterLabel(Node current,Node prvious, Node root){
		GridPane.setConstraints(current,  GridPane.getColumnIndex(prvious)+1,GridPane.getRowIndex(prvious), 1, 1);
	}
}
