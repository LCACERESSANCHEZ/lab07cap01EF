package edu.cibertec.capitulo7.dao.impl;

import edu.cibertec.capitulo7.dao.ProductoDAO;
import edu.cibertec.capitulo7.dao.SqlConecta;
import edu.cibertec.capitulo7.dto.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    private final SqlConecta conecta;

    public ProductoDAOImpl() {
        this.conecta = new SqlConecta();
    }

    @Override
    public List<Producto> productosQry() {
        List<Producto> list = null;
        String sql = "SELECT "
                + "codigoproducto,"
                + "nombre,"
                + "precio "
                + "FROM producto";

        Connection cn = conecta.connection();
        if (cn != null) {
            try (Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sql)) {

                list = new ArrayList<>();

                while (rs.next()) {
                    Producto p = new Producto();

                    p.setCodigoproducto(rs.getInt(1));
                    p.setNombre(rs.getString(2));
                    p.setPrecio(rs.getDouble(3));

                    list.add(p);
                }

            } catch (Exception e) { e.printStackTrace(); }
        }

        return list;
    }

    @Override
    public String productosIns(Producto productos) {
        String result = null;
        String sql = "INSERT INTO producto("
                + "codigoproducto,"
                + "nombre,"
                + "precio"
                + ") VALUES(?,?,?)";

        Connection cn = conecta.connection();
        if (cn != null) {
            try (PreparedStatement ps = cn.prepareStatement(sql)) {

                ps.setInt(1, productos.getCodigoproducto());
                ps.setString(2, productos.getNombre());
                ps.setDouble(3, productos.getPrecio());

                int ctos = ps.executeUpdate();
                if (ctos == 0) {
                    throw new SQLException("0 filas afectadas");
                }

            } catch (SQLException e) {
                result = e.getMessage();
            }
        }

        return result;
    }

    @Override
    public String productosDel(List<Integer> ids) {
        String result = null;
        String sql = "DELETE FROM producto WHERE codigoproducto=?";

        Connection cn = conecta.connection();
        if (cn != null) {
            try (PreparedStatement ps = cn.prepareStatement(sql)) {

                for (Integer cod : ids) {
                    ps.setInt(1, cod);

                    int ctos = ps.executeUpdate();
                    if (ctos == 0) {
                        throw new SQLException("Código " + cod + " Incorrecto");
                    }
                }

            } catch (SQLException e) {
                result = e.getMessage();
            }
        }

        return result;
    }

    @Override
    public Producto productosGet(Integer idproducto) {
        Producto productos = null;
        String sql = "SELECT "
                + "codigoproducto,"
                + "nombre,"
                + "precio "
                + "FROM producto "
                + "WHERE codigoproducto=?";

        Connection cn = conecta.connection();
        if (cn != null) {
            try (PreparedStatement ps = cn.prepareStatement(sql)) {

                ps.setInt(1, idproducto);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    productos = new Producto();

                    productos.setCodigoproducto(rs.getInt(1));
                    productos.setNombre(rs.getString(2));
                    productos.setPrecio(rs.getDouble(3));
                }

            } catch (SQLException e) {
                System.out.println("");
            }
        }

        return productos;
    }

    @Override
    public String productosUpd(Producto productos) {
        String result = null;
        String sql = "UPDATE producto SET "
                + "nombre=?,"
                + "precio=? "
                + "WHERE codigoproducto=?";

        Connection cn = conecta.connection();
        if (cn != null) {
            try (PreparedStatement ps = cn.prepareStatement(sql)) {

                ps.setString(1, productos.getNombre());
                ps.setDouble(2, productos.getPrecio());
                ps.setInt(3, productos.getCodigoproducto());
                int ctos = ps.executeUpdate();
                if (ctos == 0) {
                    throw new SQLException("0 filas afectadas");
                }

            } catch (SQLException e) {
                result = e.getMessage();
            }
        }

        return result;
    }

}
