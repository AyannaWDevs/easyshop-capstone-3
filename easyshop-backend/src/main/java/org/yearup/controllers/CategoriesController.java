package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

// add the annotations to make this a REST controller
@RestController
// add the annotation to make this controller the endpoint for the following url
    // http://localhost:8080/categories
@RequestMapping(path = "/categories")
// add annotation to allow cross site origin requests
@CrossOrigin
public class CategoriesController
{
    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    // create an Autowired controller to inject the categoryDao and ProductDao
@Autowired
//constructor for dependency injection in Spring (happens at runtime)
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao) {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }


    // add the appropriate annotation for a get action
@GetMapping
    public List<Category> getAll()
    {
        List<Category> getCategories = new ArrayList<>();
        // find and return all categories
        return categoryDao.getAllCategories();
    }

    // add the appropriate annotation for a get action
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable int id)
    {
        Category categoryId = categoryDao.getById(id);

        if(categoryId == null) {
            return ResponseEntity.notFound().build();
        }
        // get the category by id
        return ResponseEntity.ok(categoryId);
    }

    // the url to return all products in category 1 would look like this
    // https://localhost:8080/categories/1/products
    @GetMapping("{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId)
    {
        // get a list of product by categoryId
        return productDao.listByCategoryId(categoryId);
    }

    // add annotation to call this method for a POST action
    @PostMapping
    // add annotation to ensure that only an ADMIN can call this function
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')") //restricts access to admins only
    public Category addCategory(@RequestBody  Category category)
    {
        // insert the category
        return categoryDao.create(category);
    }

    // add annotation to call this method for a PUT (update) action - the url path must include the categoryId
    @PutMapping("/{id}")
    // add annotation to ensure that only an ADMIN can call this function
    @PreAuthorize("hasRole('ADMIN')")
    public void updateCategory(@PathVariable int id, @RequestBody Category category)
    {
        // update the category by id
        categoryDao.update(id,category);
    }


    // add annotation to call this method for a DELETE action - the url path must include the categoryId
   @DeleteMapping("/{id}")
    // add annotation to ensure that only an ADMIN can call this function
   @PreAuthorize("hasRole('ADMIN')")
   @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable ("id") int categoryId)
    {
        // delete the category by id
        categoryDao.delete(categoryId);
        System.out.println("Category "+ categoryId + "was deleted");

    }
}
