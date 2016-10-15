/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.assertj.core.util.Files;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import model.MediaEpisode;
import model.MediaFilePath;
import model.MediaType;
import model.Season;
import model.SeasonPK;
import model.Sery;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { Application.class })
@TestPropertySource(locations = "classpath:test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Per {
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private MediaEpisodeRepository mediaEpisodeRepository;
	@Autowired
	private MediaTypeRepository mediaTypeRepository;
	@Autowired
	private SeasonRepository seasonTypeRepository;
	@Autowired
	private SeriesRepository seriesTypeRepository;

	
	private MediaType seriesType;

	@BeforeClass
	public static void deleteDatabase(){
		Resource resource = new ClassPathResource("/test.properties");
		try {
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			String urlJdbc = (String) props.get("spring.datasource.url");
			String dbPath = urlJdbc.substring(urlJdbc.lastIndexOf(':')+1);
			Files.delete(new File(dbPath));
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Before
	public void initMediaType() {
		seriesType = mediaTypeRepository.findByName("series");
		if (seriesType == null) {
			seriesType = new MediaType();
			seriesType.setName("series");
			seriesType = mediaTypeRepository.save(seriesType);
		}
	}

	@Test
	public void _01_testCreate() {
		MediaEpisode episode = new MediaEpisode();
		episode.setName("le retour");
		episode.setMediaType(seriesType);
		Sery sery = new Sery();
		sery.setName("Game of thrones");
		sery = seriesTypeRepository.save(sery);
		Season season = new Season();
		season.setName("Saison 1");
		SeasonPK seasonPK = new SeasonPK();
		seasonPK.setSeasonNumber(1);
		season.setId(seasonPK);
		season.setSeries(sery);
		season = seasonTypeRepository.save(season);
		episode.setSeason(season);
		episode.setEpisodeNumber(1);
		
		
		
		episode = mediaEpisodeRepository.save(episode);
		MediaFilePath path = new MediaFilePath(episode,1);
		path.setPath("/home/jonathan/Vidéos/data_video/Bruce.zone-telechargement.com.avi");
		episode.getPaths().add(path);
		episode = mediaEpisodeRepository.save(episode);
		String path2 = episode.getPaths().get(0).getPath();
		assertThat(path2).isEqualTo("/home/jonathan/Vidéos/data_video/Bruce.zone-telechargement.com.avi");
	}

	@Test
	@Transactional
	public void _02_testGet() {

		MediaEpisode findOne = mediaEpisodeRepository.findOne(1);
		System.out.println(findOne.getName());
		Season season = findOne.getSeason();
		System.out.println(season.getName());
		List<MediaEpisode> episodes = season.getEpisodes();
//		if(!episodes.isEmpty()){
			assertThat(episodes.get(0)).isEqualTo(findOne);
//		}
		
	}

	/**
	 * @return the entityManager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager
	 *            the entityManager to set
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * @return the mediaEpisodeRepository
	 */
	public MediaEpisodeRepository getMediaEpisodeRepository() {
		return mediaEpisodeRepository;
	}

	/**
	 * @param mediaEpisodeRepository
	 *            the mediaEpisodeRepository to set
	 */
	public void setMediaEpisodeRepository(MediaEpisodeRepository mediaEpisodeRepository) {
		this.mediaEpisodeRepository = mediaEpisodeRepository;
	}

}