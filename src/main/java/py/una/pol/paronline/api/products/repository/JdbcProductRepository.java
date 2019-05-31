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
import py.una.pol.paronline.commons.domain.entity.products.Product;
import py.una.pol.paronline.commons.utils.DataBaseUtil;

/**
 *
 * @author dlopez
 */
public class JdbcProductRepository implements ProductRepository<Product, Integer> {

    private Connection connection;
    private PreparedStatement pstmt;

    public JdbcProductRepository() {
    }

    public JdbcProductRepository(Connection connection, PreparedStatement pstmt) {
        this.connection = connection;
        this.pstmt = pstmt;
    }
    
    @Override
    public void add(Product entity) {
        
        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("INSERT INTO producto (descripcion, id_categoria, precio_unit, cantidad) values (?, ?, ?, ?)");

            pstmt.setString(1, entity.getNombre());
            pstmt.setInt(2, entity.getCategory().getId());
            pstmt.setBigDecimal(3, entity.getUnitPrice());
            pstmt.setInt(4, entity.getQuantity());

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
    public void remove(Integer id) {
        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("DELETE FROM producto WHERE id_producto = ?");

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
    public void update(Product entity) {
        
        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("UPDATE producto SET descripcion = ?, id_categoria = ?, precio_unitario = ?, cantidad = ? WHERE id_producto = ?");

            pstmt.setString(1, entity.getNombre());
            pstmt.setInt(2, entity.getCategory().getId());
            pstmt.setBigDecimal(3, entity.getUnitPrice());
            pstmt.setInt(4, entity.getQuantity());
            pstmt.setInt(5, entity.getId());

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entity<Integer> get(Integer id) {
        Entity retValue = null;
        ResultSet rs = null;

        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM producto WHERE id_producto = ?");

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
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcProductRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }

        return retValue;
    }

    @Override
    public Collection<Product> getAll() {
        Collection<Product> retValue = new ArrayList();
        ResultSet rs = null;

        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("SELECT p.id_producto, p.descripcion as producto, c.id_categoria, c.descripcion as categoria, p.precio_unit, p.cantidad "+ 
                            "FROM producto as p JOIN categoria as c ON p.id_categoria = c.id_categoria");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new Product(rs.getInt("id_producto"), rs.getString("producto"), new Category(rs.getInt("id_categoria"), rs.getString("categoria")), rs.getBigDecimal("precio_unit"), rs.getInt("cantidad")));
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
    public Collection<Product> findByDescripcion(String name) throws Exception {
        Collection<Product> retValue = new ArrayList();
        
        ResultSet rs = null;

        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM producto WHERE descripcion = ?");

            pstmt.setString(1, name);

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
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcProductRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }

        return retValue;
    }

    @Override
    public Collection<Product> findByCategory(String category) throws Exception {
        Collection<Product> retValue = new ArrayList();

        ResultSet rs = null;

        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM PRODUCTO P JOIN CATEGORIA C ON P.ID_CATEGORIA = C.ID_CATEGORIA WHERE categoria = ?");

            pstmt.setString(1, category);

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
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcProductRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }

        return retValue;
    }
    
    @Override
    public Collection<Product> findByCategoryId(Integer categoryId) throws Exception {
        Collection<Product> retValue = new ArrayList();

        ResultSet rs = null;

        try {
            connection = DataBaseUtil.getConnection();
            pstmt = connection.prepareStatement("SELECT * FROM PRODUCTO P JOIN CATEGORIA C ON P.ID_CATEGORIA = C.ID_CATEGORIA WHERE ID_CATEGORIA = ?");

            pstmt.setInt(1, categoryId);

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
                DataBaseUtil.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(JdbcProductRepository.class.getName()).log(Level.FATAL, null, ex);
            }
        }

        return retValue;
    }

}
