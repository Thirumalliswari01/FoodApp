package com.tapfoods.dao;
import java.sql.SQLException;
import java.util.List;
import com.tapfoods.model.Menu;
import com.tapfoods.model.Restaurant;

public interface MenuDAO {
    int addMenu(Menu menu); 

    List<Menu> getAllMenu(); 

    Menu getMenuItemById(int menuId); 

    int updateMenuItem(Menu menu); 

    int deleteMenuItem(int menuId);

    

	List<Menu> getMenuByRestaurantId(int restaurantId);


	
	

}