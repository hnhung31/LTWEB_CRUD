package vn.iostar.DAO;

import java.util.List;

import jakarta.persistence.*;
import vn.iostar.configs.JpaConfig;
import vn.iostar.entity.User;

public class UserDao implements IUserDao {
	@Override
	public void insert(User user) {

		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {

			trans.begin();
			enma.persist(user);// insert vào bảng
			trans.commit();

		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(User user) {

		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {

			trans.begin();
			enma.merge(user);// update vào bảng
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void delete(int id) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			User user = enma.find(User.class, id);
			if (user != null) {
				enma.remove(user);
			} else {
				throw new Exception("Không tìm thấy");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public User findById(int id) {
		EntityManager enma = JpaConfig.getEntityManager(); 
		User user = enma.find(User.class, id);
		return user;
	}

	@Override
	public User findByFullname(String name) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager();
		String jpql = "SELECT c FROM User c WHERE c.fullname =:fullname";
		try {
			TypedQuery<User> query = enma.createQuery(jpql, User.class);

			query.setParameter("fullname", name);

			User user = query.getSingleResult();

			if (user == null) {
				throw new Exception("Full Name đã tồn tại");
			}
			return user;
		} finally {
			enma.close();
		}
	}
	@Override
	public List<User> findAll() {
		 EntityManager enma = JpaConfig.getEntityManager();
		 TypedQuery<User> query= enma.createNamedQuery("User.findAll", User.class);
		 return query.getResultList();
		 }
	@Override
	public List<User> searchByName(String username) {
		 EntityManager enma = JpaConfig.getEntityManager();
		 String jpql = "SELECT c FROM User c WHERE c.fullname like :username";
		 TypedQuery<User> query= enma.createQuery(jpql, User.class);
		 query.setParameter("username", "%" + username + "%");
		 return query.getResultList();
		 }
	@Override
	public List<User> findAll(int page, int pagesize) {
		 EntityManager enma = JpaConfig.getEntityManager();
		 TypedQuery<User> query= enma.createNamedQuery("User.findAll", User.class);
		 query.setFirstResult(page * pagesize);
		 query.setMaxResults(pagesize);
		 return query.getResultList();
		 }
	@Override
	public int count() {
		 EntityManager enma = JpaConfig.getEntityManager();
		 String jpql = "SELECT count(c) FROM User c";
		 Query query = enma.createQuery(jpql);
		 return ((Long)query.getSingleResult()).intValue();
		 }
}
