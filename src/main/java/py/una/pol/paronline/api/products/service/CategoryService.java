/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.paronline.api.products.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import py.una.pol.paronline.commons.domain.entity.Entity;
import py.una.pol.paronline.commons.domain.entity.products.Category;

/**
 *
 * @author dlopez
 */
public interface CategoryService{
    
    /**
     *
     * @param category
     * @throws Exception
     */
    public void add(Category category) throws Exception;

    /**
     *
     * @param category
     * @throws Exception
     */
    public void update(Category category) throws Exception;

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
    public Collection<Category> findByName(String nombre) throws Exception;
    
    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<Category> findByCriteria(Map<String, ArrayList<String>> name) throws Exception;
    
}
