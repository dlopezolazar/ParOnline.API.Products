/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.paronline.api.products.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import py.una.pol.paronline.api.products.repository.ProductRepository;
import py.una.pol.paronline.commons.domain.entity.Entity;
import py.una.pol.paronline.commons.domain.entity.products.Product;
import py.una.pol.paronline.commons.domain.service.BaseService;

/**
 *
 * @author dlopez
 */
public class ProductServiceImpl extends BaseService<Product, Integer> 
        implements ProductService{
    
    private ProductRepository<Product, Integer> productRepository;

    public ProductServiceImpl(ProductRepository<Product, Integer> repository) {
        super(repository);
        this.productRepository = repository;
    }

    @Override
    public void update(Product product) throws Exception {
        
        productRepository.update(product);
    }

    @Override
    public void delete(Integer id) throws Exception {
        
        productRepository.remove(id);
    }

    @Override
    public Entity findById(Integer id) throws Exception {
        
        return productRepository.get(id);
    }

    @Override
    public Collection<Product> findByName(String nombre) throws Exception {
        
        return productRepository.findByDescripcion(nombre);
    }

    @Override
    public Collection<Product> findByCriteria(Map<String, ArrayList<String>> name) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Product> findByCategory(String category) throws Exception {
        
        return productRepository.findByCategory(category);
    }
    
}
