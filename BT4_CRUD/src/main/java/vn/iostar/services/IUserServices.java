package vn.iostar.services;

import java.util.List;

import vn.iostar.entity.User;

public interface IUserServices {

	User findByFullname(String name);

	List<User> findAll(int page, int pagesize);

	int count();

	void delete(int id);

	void update(User user);

	void insert(User user);

	List<User> searchByName(String keyword);

	User findById(int id);

	List<User> findAll();

}
