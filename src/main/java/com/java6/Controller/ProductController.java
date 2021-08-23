package com.java6.Controller;

import org.springframework.data.domain.Pageable;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.java6.Dao.ProductDao;
import com.java6.Entity.Products;

@Controller
@RequestMapping("home")
public class ProductController {
	@Autowired 
	ProductDao dao;

	
	@GetMapping("product")
	public String product(Model model, @RequestParam("listpr") Optional<Integer> p) {
		
		try {
			Pageable pageable = PageRequest.of(p.orElse(0), 8);
			Page<Products> listPro = dao.findAll(pageable);
			model.addAttribute("listpr", listPro);
		} catch (Exception e) {
			// TODO: handle exception
			Pageable pageable = PageRequest.of(0, 8);
			Page<Products> listPro = dao.findAll(pageable);
			model.addAttribute("listpr", listPro);
		}
		Products item = new Products();
		model.addAttribute("listitem", item);
		
		return "home/about";
	}
	
}
