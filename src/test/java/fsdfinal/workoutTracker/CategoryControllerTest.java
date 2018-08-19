/**
 * 
 */
package fsdfinal.workoutTracker;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsdfinal.workoutapi.controller.CategoryController;
import com.fsdfinal.workoutapi.service.CategoryService;
import com.fsdfinal.workoutapi.to.CategoryRequestTO;
import com.fsdfinal.workoutapi.to.CategoryResponseTO;
import com.fsdfinal.workoutapi.valueobject.Category;

/**
 * @author Fareedha
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {	
   
	private MockMvc mockMvc;
	
	@Mock
	private CategoryService categoryMockService;
	
    @InjectMocks
    private CategoryController categoryController;
    
    @InjectMocks
    private CategoryResponseTO categoryResponse;
	
	 @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        categoryResponse = new CategoryResponseTO();
        mockMvc = MockMvcBuilders
                .standaloneSetup(categoryController)
                .build();
    }

	@Test
	public void test_get_Categories() throws Exception 
	{
		 
		List<Category> categories = Arrays.asList(
	            new Category(1, "Category 1",false),
	            new Category(2, "Category 2",false));
		
		categoryResponse.setCategories(categories);
	 
 
		 when(categoryMockService.getCategories()).thenReturn(categories);
 
		
		 mockMvc.perform(MockMvcRequestBuilders.get("/getCategories"))
		 .andExpect(status().isOk())
		 .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
		 .andExpect(jsonPath("$.categories", hasSize(2)))
		 .andExpect(jsonPath("$.categories[0].categoryId", is(1)))
		 .andExpect(jsonPath("$.categories[0].categoryTitle", is("Category 1")))
		 .andExpect(jsonPath("$.categories[0].editable", is(false)))
		 .andExpect(jsonPath("$.categories[1].categoryId", is(2)))
		 .andExpect(jsonPath("$.categories[1].categoryTitle", is("Category 2")))
		 .andExpect(jsonPath("$.categories[1].editable", is(false)));
				
	}
	
	@Test
	public void test_save_Category() throws Exception 
	{
		 
		 CategoryRequestTO category1 = new CategoryRequestTO();
		 category1.setCategoryId(0);
		 category1.setCategoryTitle("Category 4");
		 
			
		 categoryResponse.setSuccess(true);
		 categoryResponse.setMessage("Category Saved Successfully");
		 String body = (new ObjectMapper()).valueToTree(category1).toString();
	 	 when(categoryMockService.saveCategory(category1)).thenReturn(categoryResponse);

			mockMvc.perform(MockMvcRequestBuilders.post("/saveCategory").contentType(MediaType.APPLICATION_JSON_VALUE).content(body))
						.andExpect(status().isOk());
	}
	
	@Test
	public void test_update_Category() throws Exception {
		CategoryRequestTO categoryTO1 = new CategoryRequestTO();
		categoryTO1.setCategoryId(4);
		categoryTO1.setCategoryTitle("Category 4 edited");
		
		 categoryResponse.setSuccess(true);
		 categoryResponse.setMessage("Category Saved Successfully");
		 String body = (new ObjectMapper()).valueToTree(categoryTO1).toString();
		 when(categoryMockService.saveCategory(categoryTO1)).thenReturn(categoryResponse);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/saveCategory").contentType(MediaType.APPLICATION_JSON_VALUE).content(body))
					.andExpect(status().isOk());
	}
	
	@Test
	public void test_delete_Category() throws Exception{
		CategoryRequestTO categoryTO1 = new CategoryRequestTO();
		categoryTO1.setCategoryId(4);
	 
		
		categoryResponse.setSuccess(true);
		categoryResponse.setMessage("Category Deleted successfully!!");
		when(categoryMockService.deleteCategory(categoryTO1)).thenReturn(categoryResponse);
		 
		
		mockMvc.perform(MockMvcRequestBuilders.put("/deleteCategory/4").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
				
	}
}
