package vn.iostar.configs;

import jakarta.persistence.*;


@PersistenceContext
public class JpaConfig {
	public static EntityManager getEntityManager() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-hibernate-mysql");

		return factory.createEntityManager();

	}
	public static void main(String[] args) {
		EntityManager enma =getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		 try {

		 trans.begin();

		 trans.commit();

		 } catch (Exception e) {

		 e.printStackTrace();

		 trans.rollback();

		 throw e;

		 }finally {
		 enma.close();
		 }
	}
}
