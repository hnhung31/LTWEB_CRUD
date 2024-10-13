package vn.iostar.DAO;

import java.util.List;

import vn.iostar.entity.Category;

public interface ICategoryDao {

	void insert(Category category);

	void update(Category category);

	void delete(int cateid) throws Exception;

	int count();

	List<Category> findAll(int page, int pagesize);

	List<Category> searchByName(String catname);

	List<Category> findAll();

	Category findByCategoryname(String name) throws Exception;

	Category findById(int cateid);

}
