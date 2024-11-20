package com.tapfoods.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

	 Map<Integer, CartItem> items = new HashMap<Integer, CartItem>();
	  
	 public void addItem(CartItem e)
	 {
		 int itemid = e.getItemId();
		 if(items.containsKey(itemid))
		 {
			 CartItem existingItem = items.get(itemid);
			 existingItem.setQuantity(existingItem.getQuantity()+e.getQuantity());
		 }
		 else {
			 items.put(itemid,e);
		}
	 }
	 
	 public void updateItem(int itemid,int quantity)
	 {
		 if(items.containsKey(itemid))
		 {
			 if(quantity<=0)
			 {
				 items.remove(itemid);
			 }
			 else {
				items.get(itemid).setQuantity(quantity);
				items.get(itemid).setSubtotal(quantity* items.get(itemid).getPrice());
			}
		 }
	 }
	 
	 public void removeItem(int itemid)
	 {
		 items.remove(itemid);
	 }
	 
	 public Map<Integer,CartItem> getItems() 
	 {
		 return items;
	 }
	 
	 public void clearCart()
	 {
		 items.clear();
	 }
}
