/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.paronline.api.products.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import py.una.pol.paronline.api.products.entity.Category;
import py.una.pol.paronline.api.products.entity.Product;
import py.una.pol.paronline.commons.domain.entity.Entity;
import py.una.pol.paronline.commons.utils.DataBaseUtil;

/**
 *
 * @author dlopez
 */
public class JdbcProductRepository  implements ProductRepository<Product, Integer>{

    @Override
    public void add(Product entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Product entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entity<Integer> get(Integer id) {
        Entity retValue = null;

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DataBaseUtil.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM producto WHERE id_producto = ?");

            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue = new Product(rs.getInt("id_producto"), rs.getString("descripcion"), new Category(rs.getInt("id_categoria"), ""), rs.getBigDecimal("precio_unit"), rs.getInt("cantidad"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DataBaseUtil.closeConnection(c);
            } catch (SQLException ex) {
                //Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return retValue;
    }

    @Override
    public Collection<Product> getAll() {
        Collection<Product> retValue = new ArrayList();

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DataBaseUtil.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM producto");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new Product(rs.getInt("id_producto"), rs.getString("descripcion"), new Category(rs.getInt("id_categoria"), ""), rs.getBigDecimal("precio_unit"), rs.getInt("cantidad")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DataBaseUtil.closeConnection(c);
            } catch (SQLException ex) {
                //Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return retValue;
    }
    
}