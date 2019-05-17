/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.paronline.api.products.repository;

import java.util.Collection;
import py.una.pol.paronline.commons.domain.repository.Repository;

/**
 *
 * @author dlopez
 * @param <Product> entity
 * @param <Integer> PK data type
 */
public interface ProductRepository <Product, Integer> extends Repository<Product, Integer>{
    
    /**
     *
     * @param name
     * @return
     * @throws Exception
     */
    public Collection<Product> findByName(String name) throws Exception;
    
    
    /**
     *
     * @param category
     * @return
     * @throws Exception
     */
    public Collection<Product> findByCategory(String category) throws Exception;
    
}
