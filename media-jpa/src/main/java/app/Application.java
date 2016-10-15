package app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import model.Media;
import model.MediaEpisode;
import model.Season;
import model.SeasonPK;
import model.Sery;

@SpringBootApplication
@EntityScan(basePackageClasses=Media.class)
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);


	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	
//	@Bean
//	public CommandLineRunner demo(MediaEpisodeRepository repository,
//			SeasonTypeRepository seasonTypeRepository,
//			SeriesTypeRepository seriesTypeRepository,
//			MediaTypeRepository repositoryType)
//	{
//		return (args) -> {};
//	}

}
