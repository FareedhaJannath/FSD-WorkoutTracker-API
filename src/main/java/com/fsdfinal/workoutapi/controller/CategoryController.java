/**
 * 
 */
package com.fsdfinal.workoutapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsdfinal.workoutapi.service.CategoryService;
import com.fsdfinal.workoutapi.to.CategoryRequestTO;
import com.fsdfinal.workoutapi.to.CategoryResponseTO;
import com.fsdfinal.workoutapi.valueobject.Category;

/**
 * @author Fareedha
 *
 */
@RestController
@RequestMapping("/")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/getCategories")
	public CategoryResponseTO getCategories() 
	{
		CategoryResponseTO categoryResponse = new CategoryResponseTO();
		List<Category> categories = categoryService.getCategories();
		categoryResponse.setCategories(categories);
		categoryResponse.setSuccess(true);
		categoryResponse.setMessage("Category List");
		return categoryResponse;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveCategory")
	public CategoryResponseTO saveCategory(@RequestBody CategoryRequestTO categoryTO) 
	{
		CategoryResponseTO categoryResponse = new CategoryResponseTO();
		 if(categoryTO.getCategoryId()==null){
			 // set category id to zero for new category
			 categoryTO.setCategoryId(0);
		 }
		categoryResponse= categoryService.saveCategory(categoryTO);
		if(categoryResponse!=null&&categoryResponse.isSuccess()){ 
			List<Category> categories = categoryService.getCategories();
			categoryResponse.setCategories(categories);
		}
		return categoryResponse;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/deleteCategory/{id}")
	public CategoryResponseTO deleteCategory(@PathVariable("id") int categoryId) 
	{
		CategoryResponseTO categoryResponse = new CategoryResponseTO();
		CategoryRequestTO categoryTO = new CategoryRequestTO();
		categoryTO.setCategoryId(categoryId);
		 
		categoryResponse= categoryService.deleteCategory(categoryTO);
		if(categoryResponse!=null&&categoryResponse.isSuccess()){ 
			List<Category> categories = categoryService.getCategories();
			categoryResponse.setCategories(categories);
		}
		
		return categoryResponse;
	} 
}
