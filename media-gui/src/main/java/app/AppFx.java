package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.MediaEpisode;
import model.MediaType;
import model.Season;
import model.SeasonPK;
import model.Sery;

@SpringBootApplication
// @EntityScan(basePackageClasses=Media.class)
public class AppFx extends AbstractJavaFxApplicationSupport {

	@Autowired
	private Repositories repositories;

	private MediaType seriesType;

	private MediaType filmType;

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
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello World!");
		Button btn = new Button();
		btn.setText("Say 'Hello World'");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
			}
		});

		StackPane root = new StackPane();
		root.getChildren().add(btn);
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
		MediaEpisode episode = new MediaEpisode();
		episode.setName("le retour");
		episode.setMediaType(repositories.getSeriesType());
		Sery sery = new Sery();
		sery.setName("Game of thrones");
		sery = repositories.getSeriesRepository().save(sery);
		Season season = new Season();
		season.setName("Saison 1");
		SeasonPK seasonPK = new SeasonPK();
		seasonPK.setSeasonNumber(1);
		season.setId(seasonPK);
		season.setSeries(sery);
		season = repositories.getSeasonRepository().save(season);
		episode.setSeason(season);
		episode.setEpisodeNumber(1);

		episode = repositories.getMediaEpisodeRepository().save(episode);

	}
}
