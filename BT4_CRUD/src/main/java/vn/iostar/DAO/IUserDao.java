package vn.iostar.DAO;

import java.util.List;

import vn.iostar.entity.User;

public interface IUserDao {

	int count();

	List<User> findAll(int page, int pagesize);

	List<User> searchByName(String username);

	List<User> findAll();

	User findByFullname(String name) throws Exception;

	User findById(int id);

	void delete(int id) throws Exception;

	void update(User user);

	void insert(User user);

}
