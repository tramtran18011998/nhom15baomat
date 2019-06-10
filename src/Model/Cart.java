package Model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

	private HashMap<String, Item> cartItems;

	public HashMap<String, Item> getCartItems() {
		return cartItems;
	}

	public void setCartItems(HashMap<String, Item> cartItems) {
		this.cartItems = cartItems;
	}

	public Cart() {
		cartItems=new HashMap<>();
	}
	
	public void plustoCart(String key, Item item) {
		boolean check = cartItems.containsKey(key);
        if (check) {
            int quantity_old = item.getQuantity();
            item.setQuantity(quantity_old + 1);
            cartItems.put(key, item);
        } else {
            cartItems.put(key, item);
        }
	}
	
	public void removeCart(String product) {
		boolean bln = cartItems.containsKey(product);
        if (bln) {
            cartItems.remove(product);
        }
	}
	
	public int countItem()
	{
		return cartItems.size();
	}
	
	
	//count=giaban*quantity
	public int totalCart()
	{
		int count = 0;
		for(Map.Entry<String, Item> list:cartItems.entrySet())
		{
			count += list.getValue().getProduct().getGiaBan() * list.getValue().getQuantity();
		}
		return count;
	}
	
	/*private HashMap<Long, Item> cartItems;


	public Cart() {
		super();
	}

	public Cart(HashMap<Long, Item> cartItems) {
		super();
		this.cartItems = cartItems;
	}

	public HashMap<Long, Item> getCartItems() {
		return cartItems;
	}

	public void setCartItems(HashMap<Long, Item> cartItems) {
		this.cartItems = cartItems;
	}
	
	public void plustoCart(Long key, Item item) {
		boolean check = cartItems.containsKey(key);
        if (check) {
            int quantity_old = item.getQuantity();
            item.setQuantity(quantity_old + 1);
            cartItems.put(key, item);
        } else {
            cartItems.put(key, item);
        }
	}
	
	public void removeCart(Long product) {
		boolean bln = cartItems.containsKey(product);
        if (bln) {
            cartItems.remove(product);
        }
	}
	
	public int countItem()
	{
		int count = 0;
        count = cartItems.size();
        return count;
		return cartItems.size();
	}
	
	
	//count=giaban*quantity
	public int totalCart()
	{
		int count = 0;
		for(Map.Entry<Long, Item> list:cartItems.entrySet())
		{
			count += list.getValue().getProduct().getGiaBan() * list.getValue().getQuantity();
		}
		return count;
	}*/
	
	
	
}
