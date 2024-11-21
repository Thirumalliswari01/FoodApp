package com.tapfoods.controller;
	
	import java.io.IOException;
	import java.util.List;
	
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	
	import com.tapfoods.dao.MenuDAO;
	import com.tapfoods.daoimpl.MenuDAOImpl;
	import com.tapfoods.model.Menu;
	
	@WebServlet("/menu")
	public class MenuServlet extends HttpServlet {

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    String restaurantIdParam = req.getParameter("restaurantId");
		    String restaurantName = req.getParameter("restaurantName");

		    if (restaurantIdParam == null || restaurantIdParam.isEmpty()) {
		        req.setAttribute("errorMessage", "Restaurant ID is missing.");
		        req.getRequestDispatcher("error.jsp").forward(req, resp);
		        return;
		    }

		    try {
		        int restaurantId = Integer.parseInt(restaurantIdParam);

		        // Create the MenuDAO instance
		        MenuDAO menuDAO = new MenuDAOImpl();
		        List<Menu> menuList = menuDAO.getMenuByRestaurantId(restaurantId);  // This should now work

		        req.setAttribute("menuList", menuList);
		        req.setAttribute("restaurantName", restaurantName);
		        req.setAttribute("restaurantId", restaurantId);

		        req.getRequestDispatcher("menu.jsp").forward(req, resp);

		    } catch (NumberFormatException e) {
		        req.setAttribute("errorMessage", "Invalid restaurant ID format.");
		        req.getRequestDispatcher("error.jsp").forward(req, resp);
		    } catch (Exception e) {
		        req.setAttribute("errorMessage", "An error occurred while processing the menu request.");
		        e.printStackTrace();
		        req.getRequestDispatcher("error.jsp").forward(req, resp);
		    }
		}
}