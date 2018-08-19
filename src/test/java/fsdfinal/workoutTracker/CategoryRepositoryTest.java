package fsdfinal.workoutTracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

 
import com.fsdfinal.workoutapi.SpringBootWebApplication;
import com.fsdfinal.workoutapi.dao.CategoryRepository;
import com.fsdfinal.workoutapi.valueobject.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest(classes = SpringBootWebApplication.class)
@Transactional
public class CategoryRepositoryTest {
	
	@Autowired
	private transient CategoryRepository categoryRepository;
	
	public void setRepo(final CategoryRepository repository) {
		this.categoryRepository = repository;
	}
	
	@Test
	public void testGetAllCategories() throws Exception{
		Category category = new Category();
		category.setCategoryId(null);
		category.setCategoryTitle("Category 1");
		categoryRepository.save(category);
		final List<Category> workoutCategoryList = categoryRepository.findAll();
		assertNotNull(workoutCategoryList);
	}
	
	@Test
	public void testGetCategoryById() throws Exception {
		Category category = new Category();
		category.setCategoryId(null);
		category.setCategoryTitle("Category 1");
		Category savedEntityObj = categoryRepository.save(category);
		final Category workoutCategory = categoryRepository.findByCategoryId(savedEntityObj.getCategoryId());
		assertNotNull(workoutCategory);
		assertEquals(workoutCategory.getCategoryTitle(), "Category 1");
	}
	
	@Test
	public void testSaveCategory() throws Exception {
		Category category = new Category();
		category.setCategoryId(null);
		category.setCategoryTitle("Category 1");
		Category savedEntityObj = categoryRepository.save(category);
		assertNotNull(savedEntityObj);
		assertNotNull(savedEntityObj.getCategoryId());
	}
	
	@Test
	public void testUpdateCategory() throws Exception {
		Category category = new Category();
		category.setCategoryId(null);
		category.setCategoryTitle("Category 1");
		Category savedEntityObj = categoryRepository.save(category);
		Category updateCategory = new Category();
		updateCategory.setCategoryId(savedEntityObj.getCategoryId());
		updateCategory.setCategoryTitle("Category 2");
		Category updatedEntityObj = categoryRepository.save(updateCategory);
		assertNotNull(updatedEntityObj);
		assertNotNull(updatedEntityObj.getCategoryId());
		assertEquals(updatedEntityObj.getCategoryTitle(), "Category 2");
	}
	
	@Test
	public void testDeleteCategory() throws Exception{
		Category category = new Category();
		category.setCategoryId(null);
		category.setCategoryTitle("Category 1");
		Category savedEntityObj = categoryRepository.save(category);
		Category deleteCategory = new Category();
		deleteCategory.setCategoryId(savedEntityObj.getCategoryId());
		deleteCategory.setCategoryTitle("Category 2");
		categoryRepository.delete(deleteCategory);
		final  Category  workoutCategory = categoryRepository.findByCategoryId(savedEntityObj.getCategoryId());
				
	}
}
