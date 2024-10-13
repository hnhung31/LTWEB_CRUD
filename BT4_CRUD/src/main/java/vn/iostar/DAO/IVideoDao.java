package vn.iostar.DAO;

import java.util.List;

import vn.iostar.entity.Video;

public interface IVideoDao {

	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> searchByName(String videotitle);

	List<Video> findAll();

	Video findByVideoname(String name) throws Exception;

	Video findById(String videoid);

	void delete(String videoid) throws Exception;

	void update(Video video);

	void insert(Video video);

	
}
