package com.tapfoods.daoimpl;

import com.tapfoods.dao.MenuDAO;
import com.tapfoods.dbutils.DBUtils;
import com.tapfoods.model.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDAOImpl implements MenuDAO {
    private static final String ADD_MENU = "INSERT INTO menu (restaurantId, menuName, price, description, isAvailable, imgPath) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_MENU = "SELECT * FROM menu";
    private static final String UPDATE_MENU_ITEM = "UPDATE menu SET restaurantId = ?, menuName = ?, price = ?, description = ?, isAvailable = ?, imgPath = ? WHERE menuId = ?";
    private static final String DELETE_MENU_ITEM = "DELETE FROM menu WHERE menuId = ?";
    private static final String GET_ON_ID = "SELECT * FROM menu WHERE restaurantId = ?";

    @Override
    public int addMenu(Menu menu) {
        int status = 0;
        try (Connection con = DBUtils.myConnect();
             PreparedStatement pstmt = con.prepareStatement(ADD_MENU)) {
            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getItemName());
            pstmt.setDouble(3, menu.getPrice());
            pstmt.setString(4, menu.getDescription());
            pstmt.setBoolean(5, menu.isAvailable());
            pstmt.setString(6, menu.getImagePath());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL Exception occurred while adding menu: " + e.getMessage());
        }
        return status;
    }

    @Override
    public List<Menu> getAllMenu() {
        List<Menu> menuList = new ArrayList<>();
        try (Connection con = DBUtils.myConnect();
             PreparedStatement pstmt = con.prepareStatement(SELECT_ALL_MENU);
             ResultSet resultSet = pstmt.executeQuery()) {
            menuList = extractMenuFromResultSet(resultSet);
        } catch (SQLException e) {
            System.err.println("SQL Exception occurred while retrieving all menus: " + e.getMessage());
        }
        return menuList;
    }

    @Override
    public Menu getMenuItemById(int menuId) {
        Menu res = null;
        String query = "SELECT * FROM menu WHERE menuId = ?";

        try (Connection con = DBUtils.myConnect();
             PreparedStatement psmt = con.prepareStatement(query)) {
            psmt.setInt(1, menuId);
            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    res = new Menu();
                    res.setMenuId(rs.getInt("menuId"));
                    res.setRestaurantId(rs.getInt("restaurantId"));
                    res.setItemName(rs.getString("menuName"));
                    res.setDescription(rs.getString("description"));
                    res.setPrice(rs.getDouble("price"));
                    res.setAvailable(rs.getBoolean("isAvailable"));
                    res.setImagePath(rs.getString("imgPath"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int updateMenuItem(Menu menu) {
        int status = 0;
        try (Connection con = DBUtils.myConnect();
             PreparedStatement pstmt = con.prepareStatement(UPDATE_MENU_ITEM)) {
            pstmt.setInt(1, menu.getRestaurantId());
            pstmt.setString(2, menu.getItemName());
            pstmt.setDouble(3, menu.getPrice());
            pstmt.setString(4, menu.getDescription());
            pstmt.setBoolean(5, menu.isAvailable());
            pstmt.setString(6, menu.getImagePath());
            pstmt.setInt(7, menu.getMenuId());
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL Exception occurred while updating menu item: " + e.getMessage());
        }
        return status;
    }

    @Override
    public int deleteMenuItem(int menuId) {
        int status = 0;
        try (Connection con = DBUtils.myConnect();
             PreparedStatement pstmt = con.prepareStatement(DELETE_MENU_ITEM)) {
            pstmt.setInt(1, menuId);
            status = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQL Exception occurred while deleting menu item: " + e.getMessage());
        }
        return status;
    }

    private List<Menu> extractMenuFromResultSet(ResultSet resultSet) throws SQLException {
        List<Menu> menuList = new ArrayList<>();
        while (resultSet.next()) {
            menuList.add(new Menu(
                resultSet.getInt("menuId"),
                resultSet.getInt("restaurantId"),
                resultSet.getString("menuName"),
                resultSet.getString("description"),
                resultSet.getDouble("price"),
                resultSet.getBoolean("isAvailable"),
                resultSet.getString("imgPath")));
        }
        return menuList;
    }

    public List<Menu> getMenuByRestaurantId(int restaurantId) {
        List<Menu> menuList = new ArrayList<>();
        String query = "SELECT * FROM menu WHERE restaurantId = ?";

        try (Connection connection = DBUtils.myConnect();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, restaurantId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Menu menuItem = new Menu();
                    menuItem.setMenuId(rs.getInt("menuId"));
                    menuItem.setRestaurantId(rs.getInt("restaurantId"));
                    menuItem.setItemName(rs.getString("menuName"));
                    menuItem.setDescription(rs.getString("description"));
                    menuItem.setPrice(rs.getDouble("price"));
                    menuItem.setAvailable(rs.getBoolean("isAvailable"));
                    menuItem.setImagePath(rs.getString("imgPath"));
                    menuList.add(menuItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuList;
    }

	
	

    
}
