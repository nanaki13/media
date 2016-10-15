package app;

import model.MediaType;

public interface Repositories {
	public MediaEpisodeRepository getMediaEpisodeRepository();
	public SeasonRepository getSeasonRepository();
	public SeriesRepository getSeriesRepository();
	public MediaTypeRepository getMediaTypeRepository();
	public MediaType getSeriesType();
	public MediaType getMovieType();
}
