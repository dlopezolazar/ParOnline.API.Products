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
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import py.una.pol.paronline.commons.domain.entity.Entity;
import py.una.pol.paronline.commons.domain.entity.products.Category;
import py.una.pol.paronline.commons.utils.DataBaseUtil;

/**
 *
 * @author dlopez
 */
public class JdbcCategoryRepository implements CategoryRepository<Category, Integer>{

    private Connection connection;
    private PreparedStatement pstmt;

    public JdbcCategoryRepository() {
    }

    
    public JdbcCategoryRepository(Connection connection, PreparedStatement pstmt) {
        this.connection = connection;
        this.pstmt = pstmt;
    }    
    
    @Override
    public void add(Category entity) {
        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("INSERT INTO categoria (descripcion) values (?)");

            pstmt.setString(1, entity.getNombre());

            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcProductRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }
    }

    @Override
    public Collection getAll() {
        Collection<Category> retValue = new ArrayList<>();
        ResultSet rs = null;

        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM categoria");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new Category(rs.getInt("id_categoria"), rs.getString("categoria")));
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
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcProductRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }

        return retValue;
    }

    @Override
    public void remove(Integer id) {
        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("DELETE FROM categoria WHERE id_categoria = ?");

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcProductRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }
    }

    @Override
    public void update(Category entity) {
        
        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("UPDATE categoria SET descripcion = ? WHERE id_categoria = ?");

            pstmt.setString(1, entity.getNombre());
            pstmt.setInt(2, entity.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcProductRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }
    }

    @Override
    public boolean contains(Integer id) {
        Category cat = (Category) get(id);
        
        return cat != null;
    }

    @Override
    public Entity<Integer> get(Integer id) {
        Entity retValue = null;
        ResultSet rs = null;

        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM category WHERE id_categoria = ?");

            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue = new Category(rs.getInt("id_categoria"), rs.getString("descripcion"));
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
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcProductRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }
        
        return retValue;
    }
    
}
