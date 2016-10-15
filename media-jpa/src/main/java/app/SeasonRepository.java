package app;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import model.MediaType;
import model.Season;

public interface SeasonRepository extends  CrudRepository<Season , Integer> {
	public List<Season> findByName(String name);
}
