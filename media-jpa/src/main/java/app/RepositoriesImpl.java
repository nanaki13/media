package app;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import model.MediaType;

@Component
public class RepositoriesImpl implements Repositories {

	@Autowired
	private MediaEpisodeRepository mediaEpisodeRepository;
	@Autowired
	private MediaTypeRepository mediaTypeRepository;
	@Autowired
	private SeasonRepository seasonRepository;
	@Autowired
	private SeriesRepository seriesRepository;
	
	private MediaType seriesType;
	private MediaType movieType;

	@PostConstruct
	public void initMediaTypes(){
		seriesType = mediaTypeRepository.findByName("series");
		if (seriesType == null) {
			seriesType = new MediaType();
			seriesType.setName("series");
			seriesType = mediaTypeRepository.save(seriesType);
		}
		movieType = mediaTypeRepository.findByName("movie");
		if (movieType == null) {
			movieType = new MediaType();
			movieType.setName("movie");
			movieType = mediaTypeRepository.save(movieType);
		}
	}
	
	public MediaType getMovieType(){
		return movieType;
	}
	
	public MediaType getSeriesType(){
		return seriesType;
	}
	
	@Override
	public MediaEpisodeRepository getMediaEpisodeRepository() {
		// TODO Auto-generated method stub
		return mediaEpisodeRepository;
	}

	@Override
	public SeasonRepository getSeasonRepository() {
		// TODO Auto-generated method stub
		return seasonRepository;
	}

	@Override
	public SeriesRepository getSeriesRepository() {
		// TODO Auto-generated method stub
		return seriesRepository;
	}

	@Override
	public MediaTypeRepository getMediaTypeRepository() {
		// TODO Auto-generated method stub
		return mediaTypeRepository;
	}

	/**
	 * @param mediaEpisodeRepository the mediaEpisodeRepository to set
	 */
	public void setMediaEpisodeRepository(MediaEpisodeRepository mediaEpisodeRepository) {
		this.mediaEpisodeRepository = mediaEpisodeRepository;
	}

	/**
	 * @param mediaTypeRepository the mediaTypeRepository to set
	 */
	public void setMediaTypeRepository(MediaTypeRepository mediaTypeRepository) {
		this.mediaTypeRepository = mediaTypeRepository;
	}

	/**
	 * @param seasonRepository the seasonRepository to set
	 */
	public void setSeasonRepository(SeasonRepository seasonRepository) {
		this.seasonRepository = seasonRepository;
	}

	/**
	 * @param seriesRepository the seriesRepository to set
	 */
	public void setSeriesRepository(SeriesRepository seriesRepository) {
		this.seriesRepository = seriesRepository;
	}
	

}
