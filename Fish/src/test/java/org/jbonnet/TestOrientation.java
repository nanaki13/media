package org.jbonnet;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppBeans.class)
public class TestOrientation {

	private static final Logger logger = Logger.getLogger(TestOrientation.class);


	@Test
	public void testOrientation() {
		Assert.assertEquals(Constants.Orientation.NE, Constants.Orientation.getOrientation(2, 0, 2, 2));
		Assert.assertEquals(Constants.Orientation.E, Constants.Orientation.getOrientation(2, 1, 2, 2));
		Assert.assertEquals(Constants.Orientation.SE, Constants.Orientation.getOrientation(2, 2, 2, 2));
		Assert.assertEquals(Constants.Orientation.N, Constants.Orientation.getOrientation(1, 0, 2, 2));
		Assert.assertEquals(Constants.Orientation.S, Constants.Orientation.getOrientation(1, 2, 2, 2));
		Assert.assertEquals(Constants.Orientation.NO, Constants.Orientation.getOrientation(0, 0, 2, 2));
		Assert.assertEquals(Constants.Orientation.O, Constants.Orientation.getOrientation(0, 1, 2, 2));
		Assert.assertEquals(Constants.Orientation.SO, Constants.Orientation.getOrientation(0, 2, 2, 2));
		
	}
	

}
