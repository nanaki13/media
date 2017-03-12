package org.jbonnet;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppBeans.class)
public class TestFishEngine {

	private static final Logger logger = Logger.getLogger(TestFishEngine.class);
	@Autowired
	FishEngine fish;
	
	@Autowired
	DbConnection db;

	@Test
	public void testFisEngine() {
		Assert.assertNotNull(fish);
		Assert.assertNotNull(fish.getPlateau());
		Assert.assertNotNull(fish.getPlateau().getCase(0, 0));

	}
	
	@Test
	public void testDb() throws SQLException {
		Assert.assertNotNull(db);
		Assert.assertNotNull(db.getAllFish());
		
	}

}
