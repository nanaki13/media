package app;

import java.util.function.Function;

import javax.transaction.TransactionManager;

import org.hibernate.SessionFactory;
import org.jbonnet.bean.form.CompomentManipulator;
import org.jbonnet.bean.form.DefaultFieldContexts;
import org.jbonnet.bean.form.DefaultManipulator;
import org.jbonnet.bean.form.FieldContext;
import org.jbonnet.bean.form.FxObjectFiller;
import org.jbonnet.bean.form.FxViewFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MediaEpisode;
import model.MediaType;
import model.Sery;

@SpringBootApplication
// @EntityScan(basePackageClasses=Media.class)
public class AppFx extends AbstractJavaFxApplicationSupport {

	private static final String SAVE = "save";

	@Autowired
	private Repositories repositories;
	


	private MediaType seriesType;

	private MediaType filmType;


	private MediaEpisode episode;

	private Scene sceneEpisode;

	private Stage primaryStage;

	private Pane root;

	private Pane subRoot;

	@Autowired
	private ViewEpisode viewCreateEpisode;

	@Autowired
	private SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		launchApp(AppFx.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			MediaEpisodeRepository mediaEpisodeRepository = repositories.getMediaEpisodeRepository();
			System.out.println(mediaEpisodeRepository);
		};
	}
	

	@Override
	public void init() throws Exception {
		super.init();
		seriesType = repositories.getSeriesType();
		filmType = repositories.getMovieType();
		viewCreateEpisode.setSeriesType(seriesType);
		viewCreateEpisode.setPrimaryStage(primaryStage);
		
//		System.out.println(bean);

	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Media views");
		Menu fileMenu = new Menu("file");
		this.primaryStage = primaryStage;
		MenuItem createEpisode = new MenuItem("créer episode");
		MenuItem createSeries = new MenuItem("créer série");
		MenuItem createSeasons = new MenuItem("créer saison");
		Menu optionMenu = new Menu("option");
		createSeries.setOnAction((e)->createSeriesAction());
		createSeasons.setOnAction((e)->createSaesonAction());
		createEpisode.setOnAction((e)->createEpisodeAction());
		fileMenu.getItems().addAll(createEpisode,createSeries,createSeasons);
		MenuBar menuBar = new MenuBar(fileMenu,optionMenu);
		
//		primaryStage.menu
		
		root = new VBox();
		subRoot  = new StackPane();
		root.getChildren().addAll(menuBar,subRoot);
		subRoot.setMinSize(400, 400);
		sceneEpisode = new Scene(root );
//		root.set
		primaryStage.setScene(sceneEpisode);
//		double d = root.getPrefWidth()*1.4;
//		viewCreateEpisode.setMinWidth(350);
		primaryStage.show();
		
		

	}

	private Object createEpisodeAction() {
//		viewCreateEpisode = new ViewEpisode(seriesType, repositories, filmType, primaryStage);
		subRoot.getChildren().clear();
		subRoot.getChildren().add(viewCreateEpisode);
		return null;
	}

	private Object createSaesonAction() {
		ViewCreateSeason createSeason = new ViewCreateSeason( repositories);
		subRoot.getChildren().clear();
		subRoot.getChildren().add(createSeason);
		return null;
	}

	private Object createSeriesAction() {
		
		GridPane gridPane = new GridPane();
		Function<String, FieldContext> fieldMapping = new DefaultFieldContexts(Sery.class,"id");
		CompomentManipulator compomentManipulator = new DefaultManipulator();
		Node lastAdded = FxViewFactory.fillViewComponent(Sery.class, fieldMapping, compomentManipulator, gridPane);
		Button saveSeriesButton = new Button(SAVE);
		gridPane.add(saveSeriesButton , 0, GridPane.getRowIndex(lastAdded)+1);
		subRoot.getChildren().clear();
		subRoot.getChildren().add(gridPane);
		saveSeriesButton.setOnAction((a) -> {
			Sery sery = new Sery();
			FxObjectFiller.fillObject(sery, gridPane);
			repositories.getSeriesRepository().save(sery );
		});
		return null;
	}
}
