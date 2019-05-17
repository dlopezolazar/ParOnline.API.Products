/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.paronline.api.products.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import py.una.pol.paronline.api.products.entity.Product;
import py.una.pol.paronline.commons.domain.entity.Entity;

/**
 *
 * @author dlopez
 */
public interface ProductService {
    
    /**
     *
     * @param user
     * @throws Exception
     */
    public void add(Product user) throws Exception;

    /**
     *
     * @param user
     * @throws Exception
     */
    public void update(Product user) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    public void delete(Integer id) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Entity findById(Integer id) throws Exception;

    /**
     *
     * @param nombre
     * @return
     * @throws Exception
     */
    public Collection<Product> findByName(String nombre) throws Exception;
    
    /**
     *
     * @param category
     * @return
     * @throws Exception
     */
    public Collection<Product> findByCategory(String category) throws Exception;

    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<Product> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;
}
