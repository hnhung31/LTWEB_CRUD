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
import vn.iostar.entity.Category;
import vn.iostar.services.*;
import vn.iostar.utils.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = {"/admin/categories", "/admin/category/insert", "/admin/category/update",
		"/admin/category/delete"})
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ICategoryServices cateService = new CategoryServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("categories")) {

			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);

		} else if (url.contains("/admin/category/insert")) {

			req.getRequestDispatcher("/views/admin/category-insert.jsp").forward(req, resp);

		} else if (url.contains("/admin/category/update")) {

			int id = Integer.parseInt(req.getParameter("id"));
			Category category = cateService.findById(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/admin/category-update.jsp").forward(req, resp);

		} else if (url.contains("/admin/category/delete")) {
			String id = req.getParameter("id");
			try {
				if (id != null) {
					cateService.delete(Integer.parseInt(id));
				}
				resp.sendRedirect(req.getContextPath() + "/admin/categories");
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

		if (url.contains("/admin/category/insert")) {

			Category category = new Category();

			String categoryname = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));
			category.setCategoryname(categoryname);
			category.setStatus(status);

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

					category.setImages(fname);
				} else {

					category.setImages("avatar.png");
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");

		}else if(url.contains("/admin/category/update"))

	{
		int categoryid = Integer.parseInt(req.getParameter("categoryId"));
		String categoryname = req.getParameter("categoryname");
		int status = Integer.parseInt(req.getParameter("status"));

		Category category = new Category();
		category.setCategoryId(categoryid);
		category.setCategoryname(categoryname);
		category.setStatus(status);
		// luu hinh cu
		Category cateold = cateService.findById(categoryid);
		String fileold = cateold.getImages();
		// xu ly images
		String fname = "";
		String uploadPath = Constant.UPLOAD_DIRECTORY;
		File uploadDir = new File(uploadPath);
		System.out.println(categoryid+" "+ categoryname+" "+fname);
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
				category.setImages(fname);
			} else {
				category.setImages(fileold);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		cateService.update(category);
		resp.sendRedirect(req.getContextPath() + "/admin/categories");
	}
}
	
	public static void deleteFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		Files.delete(path);
	}

}