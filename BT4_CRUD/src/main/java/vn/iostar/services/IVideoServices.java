package vn.iostar.services;

import java.util.List;

import vn.iostar.entity.Video;

public interface IVideoServices {

	Video findByVideoname(String name);

	List<Video> findAll(int page, int pagesize);

	int count();

	void delete(String id);

	void update(Video video);

	void insert(Video video);

	List<Video> searchByName(String keyword);

	Video findById(String id);

	List<Video> findAll();

	
}
