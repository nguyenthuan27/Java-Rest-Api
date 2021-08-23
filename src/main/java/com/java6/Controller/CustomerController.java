package com.java6.Controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java6.Dao.AccountDao;
import com.java6.Dao.OrderDao;
import com.java6.Dao.OrderDetailDao;
import com.java6.Dao.ProductDao;
import com.java6.Entity.ItemCart;
import com.java6.Entity.OrderDetails;
import com.java6.Entity.Orders;
import com.java6.Entity.myproduct;
import com.java6.Service.ShoppingCartService;

@Controller
@RequestMapping("customer")
public class CustomerController {
	
	@Autowired
	OrderDetailDao adao;
	
	@Autowired
	AccountDao accdao;
	
	@Autowired
	OrderDao orderdao;
	
	@Autowired 
	ShoppingCartService shopService;
	
	@Autowired
	ProductDao prodao;
	

	
	@RequestMapping("orderDetails")
	public String oderDetails(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String a = auth.getName();
		List<myproduct> listCart = adao.findMyProduct(a);
		model.addAttribute("listmycart", listCart);
		
		return "home/orderDetails";
	}
	
	@PostMapping("orderfromcart")
	public String oderCart(@ModelAttribute("itemodercart") Orders item, BindingResult result) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String a = auth.getName();
		item.setAccount(accdao.findById(a).get());
		System.out.println("name"  + a);
		Collection<ItemCart> cart = shopService.getAllItems();
		orderdao.save(item);
		for (ItemCart n : cart) {
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setProducts(prodao.findById(n.getProId()).get());
		orderDetails.setPrice(n.getPrice());
		orderDetails.setStatusdeli("Chờ xác nhận");
		orderDetails.setQuantity(n.getQty());
		orderDetails.setOrders(orderdao.findById(item.getId()).get());
		adao.save(orderDetails);
	}
		return "redirect:/customer/orderDetails";
	}
}
