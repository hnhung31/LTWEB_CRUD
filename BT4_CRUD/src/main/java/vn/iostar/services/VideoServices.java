package vn.iostar.services;

import java.util.List;

import vn.iostar.DAO.VideoDao;
import vn.iostar.DAO.IVideoDao;
import vn.iostar.entity.Video;

public class VideoServices implements IVideoServices{
	 public IVideoDao videoDao = new VideoDao();
	 @Override
	 public List<Video> findAll() {
	 return videoDao.findAll();
	 }
	 @Override
	 public Video findById(String id) {
	 return videoDao.findById(id);
	 }
	 @Override
	 public List<Video> searchByName(String keyword) {
	 return videoDao.searchByName(keyword);
	 }
	 @Override
	 public void insert(Video video) {
	 Video vi = this.findByVideoname(video.getTitle());
	 if(vi==null) {
	 videoDao.insert(video);
	 }
	 }
	 @Override
	 public void update(Video video) {
	 videoDao.update(video); 
	 }
	 @Override
	 public void delete(String id){
	 try {
	 videoDao.delete(id);
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 }
	 @Override
	 public int count() {
	 return videoDao.count();
	 }
	 @Override
	 public List<Video> findAll(int page, int pagesize) {
	 return videoDao.findAll(page, pagesize);
	 }
	 @Override
	 public Video findByVideoname(String name) {
	 try {
	 return videoDao.findByVideoname(name);
	 } catch (Exception e) {
	 e.printStackTrace();
	 }
	 return null;
	 }
	 

}