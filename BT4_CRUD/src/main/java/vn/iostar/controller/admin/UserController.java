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
import vn.iostar.entity.User;
import vn.iostar.utils.Constant;
import vn.iostar.services.*;



@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = {"/admin/users", "/admin/user/insert", "/admin/user/update",
		"/admin/user/delete"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public IUserServices useService = new UserServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("users")) {

			List<User> list = useService.findAll();
			req.setAttribute("listuse", list);
			req.getRequestDispatcher("/views/admin/user-list.jsp").forward(req, resp);

		} else if (url.contains("/admin/user/insert")) {

			req.getRequestDispatcher("/views/admin/user-insert.jsp").forward(req, resp);

		} else if (url.contains("/admin/user/update")) {

			int id = Integer.parseInt(req.getParameter("id"));
			User user = useService.findById(id);
			req.setAttribute("use", user);
			req.getRequestDispatcher("/views/admin/user-update.jsp").forward(req, resp);

		} else if (url.contains("/admin/user/delete")) {
			String id = req.getParameter("id");
			try {
				if (id != null) {
					useService.delete(Integer.parseInt(id));
				}
				resp.sendRedirect(req.getContextPath() + "/admin/users");
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();

		if (url.contains("/admin/user/insert")) {

			User user = new User();

			String username = req.getParameter("username");
			String fullname = req.getParameter("fullname");
			String password = req.getParameter("password");
			user.setUsername(username);
			user.setFullname(fullname);
			user.setPassword(password);

			String fname = "";
			String uploadPath = Constant.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);

			if (uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					part.write(uploadPath + "/" + fname);

					user.setImages(fname);
				} else {

					user.setImages("avatar.png");
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

			useService.insert(user);
			resp.sendRedirect(req.getContextPath() + "/admin/users");

		}else if(url.contains("/admin/user/update"))

	{
		int id = Integer.parseInt(req.getParameter("id"));
		String username = req.getParameter("username");
		String fullname = req.getParameter("fullname");
		String password = req.getParameter("password");

		User user = new User();
		user.setId(id);
		user.setFullname(fullname);
		user.setUsername(username);
		user.setPassword(password);
		// luu hinh cu
		User useold = useService.findById(id);
		String fileold = useold.getImages();
		// xu ly images
		String fname = "";
		String uploadPath = Constant.UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		System.out.println(id+" "+ fullname+" "+fname);
		if (uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {
			Part part = req.getPart("images");
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
				user.setImages(fname);
			} else {
				user.setImages(fileold);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		useService.update(user);
		resp.sendRedirect(req.getContextPath() + "/admin/users");
	}
}
	
	public static void deleteFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		Files.delete(path);
	}

}
