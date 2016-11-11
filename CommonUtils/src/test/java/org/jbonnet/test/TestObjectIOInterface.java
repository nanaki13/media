/**
 * 
 */
package org.jbonnet.test;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;



/**
 * @author jonathan
 *
 */
public class TestObjectIOInterface {

	@Test
	public void test() throws ReflectiveOperationException, IOException {
		A a =new A();
		a.name = "toto";
		a.prenom = "tata";
		
		a.writeField("name", "Bonnet");
		
		Assert.assertEquals("Bonnet", a.getName());
		Assert.assertEquals("Bonnet", a.readField("name"));
		
	}

	
}
