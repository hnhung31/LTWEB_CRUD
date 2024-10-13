package vn.iostar.services;

import java.util.List;

import vn.iostar.entity.Category;

public interface ICategoryServices {
	



	 int count();


	 List<Category> findAll(int page, int pagesize);


	 List<Category> searchByName(String catname);


	 List<Category> findAll();


	 Category findById(int cateid);	 
	 
	 
	 Category findByCategoryname(String name);


	 void delete(int cateid) throws Exception;


	 void update(Category category);

	 void insert(Category category);


}
