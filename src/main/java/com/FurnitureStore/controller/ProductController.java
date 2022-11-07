package com.FurnitureStore.controller;

import java.util.List;
import java.util.Optional;

import com.FurnitureStore.model.Brand;
import com.FurnitureStore.model.Category;
import com.FurnitureStore.model.CategoryGroup;
import com.FurnitureStore.model.Product;
import com.FurnitureStore.service.BrandService;
import com.FurnitureStore.service.CategoryGroupService;
import com.FurnitureStore.service.CategoryService;
import com.FurnitureStore.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private CategoryGroupService categoryGroupService;

	@GetMapping("products")
	public String products(Model model, @RequestParam("bid") Optional<Integer> bid,@RequestParam("cid") Optional<Integer> cid) {
		
		List<CategoryGroup> categoryGroups = categoryGroupService.findAll();
		model.addAttribute("categoryGroups", categoryGroups);
		
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		
		List<Brand> brands = brandService.findAll();
		model.addAttribute("brands", brands);
		
		if(cid.isPresent()) {
			Integer countProducts = productService.getCountProductsByCategory(cid.get());
			model.addAttribute("countProducts", countProducts);
			List<Product> list = productService.findByCategory(cid.get());
			model.addAttribute("products", list);
		} else if(bid.isPresent()) {
			Integer countProducts = productService.getCountProductsByBrand(bid.get());
			model.addAttribute("countProducts", countProducts);
			List<Product> list = productService.findByBrand(bid.get());
			model.addAttribute("products", list);
		}
		
		else {
			Integer countProducts = productService.getCountProducts();
			model.addAttribute("countProducts", countProducts);
			
			List<Product> list = productService.findAll();
			model.addAttribute("products", list);
		}
		return "product/list";
	}
	
	@RequestMapping("/product/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        
        return "product/detail";
    }
	
}
