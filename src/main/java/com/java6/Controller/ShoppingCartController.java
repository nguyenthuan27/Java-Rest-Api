package com.java6.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java6.Entity.ItemCart;
import com.java6.Entity.Products;
import com.java6.Service.ProductService;
import com.java6.Service.ShoppingCartService;
@Controller
@RequestMapping("home")
public class ShoppingCartController {
	@Autowired
	ShoppingCartService cartService;
	@Autowired
	ProductService productService;
	
	
	@RequestMapping("mycart")
	public String myCart(Model model) {
		model.addAttribute("carts",cartService.getAllItems());
		model.addAttribute("total", cartService.getAmount());
		return "home/mycart";
	}
	
	@GetMapping("add/{id}")
	public String addCart(@PathVariable("id") Integer id) {
		Products product = productService.geProductById(id);
		if (product !=null) {
			ItemCart item = new ItemCart();
			item.setProId(product.getId());
			item.setName(product.getName());
			item.setPrice(product.getPrice());
			item.setImage(product.getImage());
			item.setQty(1);
			cartService.add(item);
		}
		
		return "redirect:/home/mycart";
	}
	
	@PostMapping("update")
	public String update(@RequestParam("proId") Integer proId,@RequestParam("") Integer qty)  {
		cartService.update(proId, qty);
		return "redirect:/home/mycart";
	}
	@GetMapping("delete/{id}")
	public String remove(@PathVariable("id") Integer id)  {
		cartService.remove(id);
		return "redirect:/home/mycart";
	}
}
