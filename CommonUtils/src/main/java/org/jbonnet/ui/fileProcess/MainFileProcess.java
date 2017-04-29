package org.jbonnet.ui.fileProcess;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

import org.codehaus.groovy.control.CompilationFailedException;

import groovy.lang.GroovyClassLoader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainFileProcess extends Application {

	public static void main(String[] args) {
		Application.launch(args);

	}

	private Scene scene;
	private MenuItem chooseItem;
	private MenuItem scriptItem;
	private List<File> filesToProcess;
	private Stage primaryStage;
	private Function<File, File> consumer;
	private GroovyClassLoader gcl = new GroovyClassLoader();
	private TreeView<File> treeView;
	private TreeItem<File> root;
	private BorderPane rootPane;
	private TextArea scriptView;
	private Callback<TreeView<File>, TreeCell<File>> cellFactory;

	@Override
	public void start(Stage primaryStage) throws Exception {

		initUI();
		initAction();
		this.primaryStage = primaryStage;
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void initAction() {
		chooseItem.setOnAction((e) -> actionChooseFiles(e));
		scriptItem.setOnAction((e) -> actionChooseConsumer(e));

	}

	private void actionChooseFiles(ActionEvent e) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("choose list of files");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		filesToProcess = fileChooser.showOpenMultipleDialog(primaryStage);

		root.getChildren().clear();

		for (File f : filesToProcess) {
			root.setValue(f.getParentFile());
			TreeItem<File> treeItem = new TreeItem<File>(f);

			root.getChildren().add(treeItem);
		}

	}

	private void actionChooseConsumer(ActionEvent e) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("choose list of files");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		File showOpenDialog = fileChooser.showOpenDialog(primaryStage);
		
		if (showOpenDialog != null) {
			try {
				gcl.clearCache();
				Class<Function<File, File>> parseClass = gcl.parseClass(showOpenDialog);
				consumer = parseClass.newInstance();
				if(filesToProcess != null){
					filesToProcess.forEach((ee)->apllysafe(ee));
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void apllysafe(File ee) {
		try{
			 consumer.apply(ee);
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	private void initUI() {
		rootPane = new BorderPane();

		Menu menuFile = new Menu("File");
		chooseItem = new MenuItem("file to process");
		scriptItem = new MenuItem("process gr");
		MenuBar bar = new MenuBar();
		bar.getMenus().add(menuFile);
		menuFile.getItems().addAll(chooseItem,scriptItem);
		rootPane.setTop(bar);
		root = new TreeItem<>();
		treeView = new TreeView<>(root);
		cellFactory = (tv) -> buildCell(tv);
		treeView.setCellFactory(cellFactory);
		scriptView = new TextArea();
		SplitPane splitPane = new SplitPane();
		splitPane.getItems().addAll(treeView, scriptView);
		splitPane.setDividerPositions(0.3d);

		scene = new Scene(rootPane);
		rootPane.setCenter(splitPane);

	}

	private TreeCell<File> buildCell(TreeView<File> tv) {
		TreeCell<File> t = new TreeCell<File>() {
			private TextField textField;

			@Override
			public void updateItem(File item, boolean empty) {
				super.updateItem(item, empty);

				if (empty) {
					setText(null);
					setGraphic(null);
				} else {
					if (textField == null) {
						createTextField();
					}
					setText(getString());
					setGraphic(getTreeItem().getGraphic());

				}
			}

			private void createTextField() {
				textField = new TextField(getString());
				// textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
				//
				// @Override
				// public void handle(KeyEvent t) {
				// if (t.getCode() == KeyCode.ENTER) {
				// commitEdit(textField.getText());
				// } else if (t.getCode() == KeyCode.ESCAPE) {
				// cancelEdit();
				// }
				// }
				// });
			}

			private String getString() {
				return getItem() == null ? "" : getItem().getName();
			}

		};

		return t;
	}

}
