package app;

import java.io.File;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;

import org.jbonnet.bean.form.DefaultFieldContexts;
import org.jbonnet.bean.form.DefaultManipulator;
import org.jbonnet.bean.form.FxObjectFiller;
import org.jbonnet.bean.form.FxViewFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import app.converter.StringConverterBuilder;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.MediaEpisode;
import model.MediaFilePath;
import model.MediaType;
import model.Season;
import model.Sery;

@Component
public class ViewEpisode extends VBox {

	private MediaEpisode episode;
	private MediaType seriesType;

	@Autowired
	private Repositories repositories;

	private Stage primaryStage;
	private ComboBox<Season> comboBoxSeason;
	private JpaTransactionManager transactionManager;

	/**
	 * @param seriesType
	 * @param repositories
	 * @param filmType
	 * @param primaryStage
	 */
	public ViewEpisode() {
		super();

		
		
	}

	/**
	 * @return the seriesType
	 */
	public MediaType getSeriesType() {
		return seriesType;
	}

	/**
	 * @param seriesType the seriesType to set
	 */
	public void setSeriesType(MediaType seriesType) {
		this.seriesType = seriesType;
	}

	/**
	 * @return the repositories
	 */
	public Repositories getRepositories() {
		return repositories;
	}

	/**
	 * @param repositories the repositories to set
	 */
	public void setRepositories(Repositories repositories) {
		this.repositories = repositories;
	}

	/**
	 * @return the primaryStage
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	/**
	 * @param primaryStage the primaryStage to set
	 */
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@PostConstruct
	public void initView() {

		DefaultFieldContexts fieldMapping = new DefaultFieldContexts(MediaEpisode.class);
		fieldMapping.get("id").setHide(true);
		GridPane gridPane = new GridPane();
		gridPane.setHgap(15);
		gridPane.setVgap(15);
		Node fillViewComponent = FxViewFactory.fillViewComponent(MediaEpisode.class, fieldMapping, new DefaultManipulator(), gridPane);
		Integer lastRowIndex = GridPane.getRowIndex(fillViewComponent);
		Label l = new Label("saison n°");
		comboBoxSeason = new ComboBox<>( );
		gridPane.add(l, 0, lastRowIndex + 1);
		gridPane.add(comboBoxSeason, 1, lastRowIndex + 1);
		Label lSerie = new Label("serie");
		ObservableList<Sery> itemsSery = FXCollections.observableArrayList(repositories.getSeriesRepository().findAllOrderByNameAsc());
		ComboBox<Sery> comboBoxSery = new ComboBox<>(itemsSery );
		// listen change on sery for reload season
		comboBoxSery.valueProperty().addListener(( ov,  t,  t1)-> seryChange(ov, t ,t1));
		StringConverter<Sery> converter = StringConverterBuilder.build(Sery::getName);
		comboBoxSery.setButtonCell(new TextFieldListCell<>(converter ));
		comboBoxSery.setCellFactory(TextFieldListCell.forListView(converter));
		gridPane.add(lSerie, 0, lastRowIndex + 2);
		gridPane.add(comboBoxSery, 1, lastRowIndex + 2);
		Button buttonSave = new Button("save");

		ObservableList<Node> boxChildren = this.getChildren();
		

		
		ObservableList<MediaFilePath> mediaFilePathitems = FXCollections.observableArrayList();
		ListView<MediaFilePath> listView = new ListView<>(mediaFilePathitems);
		Callback<ListView<MediaFilePath>, ListCell<MediaFilePath>> callback = TextFieldListCell.forListView(StringConverterBuilder.build(MediaFilePath::getPath));
		listView.setCellFactory(callback);
		boxChildren.addAll(gridPane,listView,buttonSave);

		buttonSave.setOnAction((a) -> {
			episode = new MediaEpisode(seriesType);
			FxObjectFiller.fillObject(episode, this);
			if (episode.getId() == null && !episode.getPaths().isEmpty()) {
				List<MediaFilePath> tmpSave = episode.getPaths();
				episode.setPaths(null);
				episode = repositories.getMediaEpisodeRepository().save(episode);
				episode.addAll(tmpSave);
			}
			episode = repositories.getMediaEpisodeRepository().save(episode);

		});
		listView.setOnMouseClicked((e) -> {
			if (mediaFilePathitems.isEmpty()) {
				FileChooser chooser = new FileChooser();
				File showOpenDialog = chooser.showOpenDialog(primaryStage);
				if (showOpenDialog != null) {
					// episode =
					// repositories.getMediaEpisodeRepository().save(episode);
					MediaFilePath mediaFilePath = new MediaFilePath(episode, 1);
					mediaFilePath.setPath(showOpenDialog.getPath());
					mediaFilePathitems.add(mediaFilePath);
					episode.getPaths().add(mediaFilePath);
				}

			}
		});

	}


	private Object seryChange(ObservableValue ov, Sery t, Sery t1) {
		if(t1 != null){
			comboBoxSeason.getItems().clear();
//			Hibernate.initialize(t1.getSeasons());
			try {
				TransactionStatus transaction = transactionManager.getTransaction(null);
				if(!t1.getSeasons().isEmpty()){
					
					comboBoxSeason.getItems().addAll(t1.getSeasons());
				}
				transactionManager.commit(transaction);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
				
		}
		
		return null;
	}

	public void setTransactionManager(JpaTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
		
	}
}
