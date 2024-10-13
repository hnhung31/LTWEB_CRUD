package vn.iostar.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iostar.entity.Video;
import vn.iostar.entity.Category;
import vn.iostar.services.VideoServices;
import vn.iostar.services.IVideoServices;
import vn.iostar.services.CategoryServices;
import vn.iostar.services.ICategoryServices;
import vn.iostar.utils.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = {"/admin/videos", "/admin/video/insert", "/admin/video/update",
		"/admin/video/delete", "/admin/video/search"})
public class VideoController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public IVideoServices videoService = new VideoServices();
	public ICategoryServices categoryService = new CategoryServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("videos")) {

			List<Video> list = videoService.findAll();
			req.setAttribute("listvideo", list);
			
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);

		} else if (url.contains("/admin/video/insert")) {
			List<Category> categoryList = categoryService.findAll();
			req.setAttribute("categoryList", categoryList);
			req.getRequestDispatcher("/views/admin/video-insert.jsp").forward(req, resp);

		} else if (url.contains("/admin/video/update")) {
			List<Category> categoryList = categoryService.findAll();
			req.setAttribute("categoryList", categoryList);
			String id =req.getParameter("id");
			Video video = videoService.findById(id);
			req.setAttribute("vi", video);
			req.getRequestDispatcher("/views/admin/video-update.jsp").forward(req, resp);

		} else if (url.contains("/admin/video/delete")) {
			String id = req.getParameter("id");
			try {
				if (id != null) {
					videoService.delete(id);
				}
				resp.sendRedirect(req.getContextPath() + "/admin/videos");
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		else if (url.contains("search")) {
			
			List<Video> vi = videoService.findAll();
			req.setAttribute("videoService", vi);
			req.getRequestDispatcher("/views/admin/video-search.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();

		if (url.contains("/admin/video/insert")) {
			String videoId = req.getParameter("videoId");
			String videotitle = req.getParameter("title");
			boolean active = Boolean.parseBoolean(req.getParameter("active"));
			String des = req.getParameter("description");
			String viewsStr = req.getParameter("views"); // Nhận giá trị từ yêu cầu
			int views = Integer.parseInt(viewsStr); // Chuyển đổi sang int
			int cateid = Integer.parseInt(req.getParameter("categoryid"));
			System.out.println(cateid);
			 Category category = categoryService.findById(cateid);
			 
			 Video video = new Video();
			video.setVideoId(videoId);
			video.setTitle(videotitle);
			video.setActive(active);
			video.setDescription(des);
			video.setViews(views);
			video.setCategory(category);
				
			String fname = "";
			String uploadPath = Constant.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);

			if (uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("poster");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
// doi ten
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					// up load file
					part.write(uploadPath + "/" + fname);

					// ghi ten file vao data
					video.setPoster(fname);
				} else {

					video.setPoster("avatar.png");
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

			videoService.insert(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");

		}else if(url.contains("/admin/video/update"))

	{
			String videoId = req.getParameter("videoId");
			String title = req.getParameter("title");
			boolean active = Boolean.parseBoolean(req.getParameter("active"));
			String des = req.getParameter("description");
			String viewsStr = req.getParameter("views"); // Nhận giá trị từ yêu cầu
			int views = Integer.parseInt(viewsStr); // Chuyển đổi sang int
			int cateid = Integer.parseInt(req.getParameter("categoryid"));

		 Category category = categoryService.findById(cateid);
		 
		Video video = new Video();
		video.setVideoId(videoId);
		video.setTitle(title);
		video.setActive(active);
		video.setDescription(des);
		video.setViews(views);
		video.setCategory(category);
		// luu hinh cu
		Video viid = videoService.findById(videoId);
		String fileold = viid.getPoster();
		// xu ly images
		String fname = "";
		String uploadPath = Constant.UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		System.out.println(videoId+" "+ title +" "+fname);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {
			Part part = req.getPart("poster");
				if (part.getSize() > 0) {
					if (fileold != null && !fileold.substring(0, 5).equals("https")) {
					    deleteFile(uploadPath + "\\" + fileold);
					}
				String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
				// doi ten
				int index = filename.lastIndexOf(".");
				String ext = filename.substring(index + 1);
				fname = System.currentTimeMillis() + "." + ext;
				// up load file
				part.write(uploadPath + "/" + fname);
				// ghi ten file vao data
				video.setPoster(fname);
			} else {
				video.setPoster(fileold);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		videoService.update(video);
		resp.sendRedirect(req.getContextPath() + "/admin/videos");

	}
		else if (url.contains("/admin/video/search")) {
		    String findTitle = req.getParameter("search");  // Lấy giá trị tìm kiếm từ request
		   
		    if (findTitle != null && !findTitle.trim().isEmpty()) {
		        List<Video> listVideos = videoService.searchByName(findTitle);  // Tìm kiếm trong service
		        req.setAttribute("listVideos", listVideos);  // Gán kết quả vào request attribute
		        req.setAttribute("search", findTitle);  // Để giữ lại giá trị tìm kiếm trên form
		    } else {
		        List<Video> listVideos = videoService.findAll();  // Hiển thị tất cả nếu không có từ khóa
		        req.setAttribute("listVideos", listVideos);
		    }

		    // Chuyển hướng tới trang JSP để hiển thị kết quả
		    req.getRequestDispatcher("/views/admin/video-search.jsp").forward(req, resp);
		}
	}

	public static void deleteFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		Files.delete(path);
	}
}