package vn.iostar.services;

import java.util.List;

import vn.iostar.entity.User;
import vn.iostar.DAO.*;

public class UserServices implements IUserServices {
	public IUserDao useDao = new UserDao();

	@Override
	public List<User> findAll() {

		return useDao.findAll();

	}

	@Override
	public User findById(int id) {

		return useDao.findById(id);

	}

	@Override
	public List<User> searchByName(String keyword) {

		return useDao.searchByName(keyword);
	}

	@Override
	public void insert(User user) {

		User use = this.findByFullname(user.getFullname());
		if (use == null) {
			useDao.insert(user);
		}
	}

	@Override
	public void update(User user) {
		User use = this.findById(user.getId());

		if (use != null) {

			useDao.update(user);

		}
	}

	@Override

	public void delete(int id) {

		try {

			useDao.delete(id);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	@Override

	public int count() {
		return useDao.count();
	}

	@Override
	public List<User> findAll(int page, int pagesize) {
		return useDao.findAll(page, pagesize);
	}

	@Override
	public User findByFullname(String name) {
		try {
			return useDao.findByFullname(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
