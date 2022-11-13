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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@RequestMapping("/products/page/{pageNum}")
	public String listByPage( //
			@PathVariable(name = "pageNum") int pageNum, //
			Model model, //
			@RequestParam(name = "keyword") Optional<String> keyword) //
	{

		getFormModelAttributes(model);
		
		Page<Product> page = productService.listByPageable(pageNum, keyword.orElse(""));
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("keyword", keyword.orElse(""));
		model.addAttribute("page", page);

		return "product/list";
	}

	@GetMapping("/products-by-category/page/{pageNum}")
	public String listProductByCategoryByPage( //
			@RequestParam("cid") Integer cid, //
			@PathVariable("pageNum") Integer pageNum, //
			RedirectAttributes ra, //
			Model model) {
		
		getFormModelAttributes(model);
		
		Category category = categoryService.findById(cid);
		Page<Product> page = productService.listByCategory(pageNum, cid);

		model.addAttribute("category", category);
		model.addAttribute("cid", cid);
		model.addAttribute("page", page);

		return "product/list";
	}
	
	@GetMapping("/products-by-brand/page/{pageNum}")
	public String listProductByBrandByPage( //
			@RequestParam("bid") Integer bid, //
			@PathVariable("pageNum") Integer pageNum, //
			RedirectAttributes ra, //
			Model model) {
		
		getFormModelAttributes(model);
		
		Brand brand = brandService.findById(bid);
		Page<Product> page = productService.listByBrand(pageNum, bid);

		model.addAttribute("brand", brand);
		model.addAttribute("bid", bid);
		model.addAttribute("page", page);

		return "product/list";
	}

	@GetMapping("products")
	public String productsByPage(Model model) {
		return listByPage(1, model, Optional.empty());
	}

	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {

		Product product = productService.findById(id);
		model.addAttribute("product", product);

		return "product/detail";
	}
	
	private void getFormModelAttributes(Model model) {
		List<CategoryGroup> categoryGroups = categoryGroupService.findAll();
		model.addAttribute("categoryGroups", categoryGroups);

		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);

		List<Brand> brands = brandService.findAll();
		model.addAttribute("brands", brands);
	}

}
