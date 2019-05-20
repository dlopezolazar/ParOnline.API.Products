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
import javax.ws.rs.QueryParam;
import py.una.pol.paronline.api.products.entity.Product;
import py.una.pol.paronline.api.products.repository.JdbcProductRepository;
import py.una.pol.paronline.api.products.service.ProductServiceImpl;

/**
 *
 * @author dlopez
 */
@Path("/v1/products")
public class ProductRestService {
    
    private final ProductServiceImpl productService = new ProductServiceImpl(new JdbcProductRepository());

    @GET
    @Path("/")
    @Produces("application/json")
    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = (ArrayList) productService.getAll();
        return products;
    }
    
    @GET
    @Path("/category")
    @Produces("application/json")
    public ArrayList<Product> getProductsByCategory(@QueryParam("category_name") String categoryName) {
        ArrayList<Product> products = null;
        try {
            products = (ArrayList) productService.findByCategory(categoryName);
        } catch (Exception ex) {
            Logger.getLogger(ProductRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Product getProduct(@PathParam("id") Integer id) {
        Product entity = null;
        try {
            entity = (Product) productService.findById(id);
        } catch (Exception ex) {
            Logger.getLogger(ProductRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Product addProduct(Product entity) {
        try {
            productService.add(entity);
        } catch (Exception ex) {
            Logger.getLogger(ProductRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    public void updateProduct(Product entity) {
        try {
            productService.update(entity);
        } catch (Exception ex) {
            Logger.getLogger(ProductRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("/{id}")
    public void removeProduct(@PathParam("id") Integer id) {
        try {
            productService.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(ProductRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
