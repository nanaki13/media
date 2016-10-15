package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Per {
public final static EntityManager entityManager;

static{
	EntityManagerFactory createEntityManagerFactory = Persistence.createEntityManagerFactory("Test");
	entityManager = createEntityManagerFactory.createEntityManager();
}

public static void main(String[] args) {
	
}
	

}
