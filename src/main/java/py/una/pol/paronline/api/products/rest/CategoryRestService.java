/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.paronline.api.products.rest;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import py.una.pol.paronline.api.products.repository.JdbcCategoryRepository;
import py.una.pol.paronline.api.products.service.CategoryServiceImpl;
import py.una.pol.paronline.commons.domain.entity.Entity;
import py.una.pol.paronline.commons.domain.entity.products.Category;

/**
 *
 * @author dlopez
 */
@Path("/v1/categories")
public class CategoryRestService {
    
    private final CategoryServiceImpl categoryService = new CategoryServiceImpl(new JdbcCategoryRepository());
    
    
    @GET
    @Path("/")
    @Produces("application/json")
    public ArrayList<Category> getCategory() {
        ArrayList<Category> category = null;
        try {
            category = (ArrayList) categoryService.getAll();
        } catch (Exception ex) {
            Logger.getLogger(CategoryRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return category;
    }
    
    @GET
    @Path("/{category_id}")
    @Produces("application/json")
    public Entity getCategory(@PathParam("category_id") Integer categoryId) {
        Entity category = null;
        try {
            category = categoryService.findById(categoryId);
        } catch (Exception ex) {
            Logger.getLogger(CategoryRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return category;
    }
    
    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Category addUser(Category entity) {
        try {
            categoryService.add(entity);
        } catch (Exception ex) {
            Logger.getLogger(CategoryRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    public void updateUser(Category entity) {
        try {
            categoryService.update(entity);
        } catch (Exception ex) {
            Logger.getLogger(CategoryRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("/{id}")
    public void removeUser(@PathParam("id") Integer id) {
        try {
            categoryService.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(CategoryRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
