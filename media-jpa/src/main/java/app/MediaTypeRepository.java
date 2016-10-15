package app;

import org.springframework.data.repository.CrudRepository;

import model.MediaType;

public interface MediaTypeRepository extends  CrudRepository<MediaType , Integer> {
	public MediaType findByName(String name);
}
