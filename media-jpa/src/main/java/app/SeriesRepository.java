package app;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import model.MediaType;
import model.Sery;

public interface SeriesRepository extends  CrudRepository<Sery , Integer> {
	public List<Sery> findByName(String name);
	@Query(value="select s from Sery s order by name")
	public List<Sery> findAllOrderByNameAsc();
}
