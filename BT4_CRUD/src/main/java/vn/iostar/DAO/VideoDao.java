package vn.iostar.DAO;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iostar.configs.JpaConfig;
import vn.iostar.entity.Video;

public class VideoDao implements IVideoDao{
	@Override
	public void insert(Video video) {
		EntityManager enma = JpaConfig.getEntityManager();
		 EntityTransaction trans = enma.getTransaction();
		 try {
		 trans.begin();
		 enma.persist(video);
		 trans.commit();
		 } catch (Exception e) {
		 e.printStackTrace();
		 trans.rollback();
		 throw e;
		 }finally {
		 enma.close();
		 }
	}
	@Override
	public void update(Video video) {
		EntityManager enma = JpaConfig.getEntityManager();
		 EntityTransaction trans = enma.getTransaction();
		 try {
		 trans.begin();
		 enma.merge(video); // update vào bảng
		 trans.commit();
		 } catch (Exception e) {
		 e.printStackTrace();
		 trans.rollback();
		 throw e;
		 }finally {
		 enma.close();
		 }
	}
	@Override
	 public void delete(String videoid) throws Exception {
		EntityManager enma = JpaConfig.getEntityManager();
		 EntityTransaction trans = enma.getTransaction();
		 try {
		 trans.begin();
		 Video video = enma.find(Video.class,videoid);
		 if(video != null) {
		 enma.remove(video);
		 }else {
		 throw new Exception("Không tìm thấy");
		 }
		 trans.commit();
		 } catch (Exception e) {
		 e.printStackTrace();
		 trans.rollback();
		 throw e;
		 }finally {
		 enma.close();
		 }
	}
	@Override
	 public Video findById(String videoid) {
	 EntityManager enma = JpaConfig.getEntityManager();
	 Video video = enma.find(Video.class,videoid);
	 return video;
	 }
	@Override
	 public Video findByVideoname(String name) throws Exception {
	 EntityManager enma = JpaConfig.getEntityManager();
	 String jpql = "SELECT c FROM Video c WHERE c.title =:videotitle";
	 try {
	 TypedQuery<Video> query= enma.createQuery(jpql, Video.class);
	 query.setParameter("videotitle", name);
	 Video video= query.getSingleResult();
	 if(video==null) {
	 throw new Exception("Video title đã tồn tại");
	 }
	 return video;
	 } finally {
	 enma.close();
	 } 
	 }
	@Override
	 public List<Video> findAll() {
	 EntityManager enma = JpaConfig.getEntityManager();
	 TypedQuery<Video> query= enma.createNamedQuery("Video.findAll", Video.class);
	 return query.getResultList();
	 }
	@Override
	 public List<Video> searchByName(String videotitle) {
	 EntityManager enma = JpaConfig.getEntityManager();
	 String jpql = "SELECT c FROM Video c WHERE c.title like :videotitle";
	 TypedQuery<Video> query= enma.createQuery(jpql, Video.class);
	 query.setParameter("videotitle", "%" + videotitle + "%");
	 return query.getResultList();
	 }
	@Override
	 public List<Video> findAll(int page, int pagesize) {
	 EntityManager enma = JpaConfig.getEntityManager();
	 TypedQuery<Video> query= enma.createNamedQuery("Video.findAll", Video.class);
	 query.setFirstResult(page*pagesize);
	 query.setMaxResults(pagesize);
	 return query.getResultList();
	 }
	@Override
	 public int count() {
	 EntityManager enma = JpaConfig.getEntityManager();
	 String jpql = "SELECT count(c) FROM Video c";
	 Query query = enma.createQuery(jpql);
	 return ((Long)query.getSingleResult()).intValue();
	 }
}