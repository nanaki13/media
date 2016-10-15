package app;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import model.MediaEpisode;

public interface MediaEpisodeRepository extends  CrudRepository<MediaEpisode , Integer> {
	public List<MediaEpisode> findByName(String name);
}
