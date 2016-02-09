/**
 * 
 */
package org.nanaki.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.nanaki.ClassAccess;
import org.nanaki.ClassAccessException;

/**
 * @author jonathan
 *
 */
public class TestClassAccess {

	@Test
	public void test() throws ClassAccessException {
		Date d = new Date();
		Pojo p = new Pojo("test", false, d, null, 4);
		ClassAccess classAccess = new ClassAccess(p.getClass());

		assertEquals("test", classAccess.get("name", p));
		assertEquals(null, classAccess.get("list", p));
		assertEquals(4, classAccess.get("number", p));
		assertEquals(d, classAccess.get("date", p));

		classAccess.set("name", p, "jonathan");
		assertNotEquals("test", classAccess.get("name", p));
		assertEquals("jonathan", classAccess.get("name", p));
		System.out.println(ClassAccess.buildString(p));

	}

	public static class Pojo {
		private String name;
		private boolean up;
		private Date date;
		private List<String> list;
		private Integer number;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isUp() {
			return up;
		}

		public void setUp(boolean up) {
			this.up = up;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public List<String> getList() {
			return list;
		}

		public void setList(List<String> list) {
			this.list = list;
		}

		public Integer getNumber() {
			return number;
		}

		public void setNumber(Integer number) {
			this.number = number;
		}

		public Pojo(String name, boolean up, Date date, List<String> list, Integer number) {
			super();
			this.name = name;
			this.up = up;
			this.date = date;
			this.list = list;
			this.number = number;
		}

	}

}
