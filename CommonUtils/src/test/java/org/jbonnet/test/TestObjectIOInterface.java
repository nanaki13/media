/**
 * 
 */
package org.jbonnet.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.jbonnet.io.ObjectIOInterface;
import org.jbonnet.io.ReflectIOInterface;
import org.junit.Test;

/**
 * @author jonathan
 *
 */
public class TestObjectIOInterface implements ObjectIOInterface<A>{

	@Test
	public void test() throws ReflectiveOperationException, IOException {
		A a =new A();
		a.name = "toto";
		a.prenom = "tata";
		System.out.println((new TestObjectIOInterface()).string(a));
		System.out.println(ReflectIOInterface.getInstance(A.class).getFrom("name", a));
		
		ReflectIOInterface<A> instance = ReflectIOInterface.getInstance(A.class);
		A readProperties = instance.readProperties("conf.properties");
		System.out.println(readProperties.name);
		
	}

	@Override
	public Collection<String> getFields() {
		return Arrays.asList("name","prenom");
	}

	@Override
	public Object getFrom(String field, A read) {
		switch (field) {
		case "name":
			return read.name;

		default:
			return read.prenom;
		}
	}

	@Override
	public void setTo(String field, A write, Object value) {
		switch (field) {
		case "name":
			write.name = String.valueOf(value);

		default:
			write.prenom = String.valueOf(value);
		}
		
	}

}
