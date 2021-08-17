package edu.cibertec.capitulo7.dao.impl;

import edu.cibertec.capitulo7.dao.DetalleVentaDAO;
import edu.cibertec.capitulo7.dto.DetalleVenta;
import edu.cibertec.capitulo7.dao.SqlConecta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentaDAOImpl implements DetalleVentaDAO {
    private final SqlConecta conecta;

    public DetalleVentaDAOImpl() {
        this.conecta = new SqlConecta();
    }

    @Override
    public List<Object[]> detallesQry() {
        List<Object[]> list = null;
        String sql = "SELECT "
                + "detalleventa.idDetalleVenta,"
                + "detalleventa.codigoventa,"
                + "detalleventa.codigoproducto,"
                + "venta.cliente,"
                + "producto.nombre,"
                + "producto.precio,"
                + "detalleventa.cantidad,"
                + "detalleventa.descuento "
                + "FROM detalleventa "
                + "INNER JOIN venta "
                + "ON detalleventa.codigoventa=venta.codigoventa "
                + "INNER JOIN producto "
                + "ON detalleventa.codigoproducto=producto.codigoproducto "
                + "ORDER BY detalleventa.idDetalleVenta";

        Connection cn = conecta.connection();
        if (cn != null) {
            try (Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

                list = new ArrayList<>();

                while (rs.next()) {
                    Object[] reg = new Object[8];

                    reg[0] = rs.getInt(1);
                    reg[1] = rs.getInt(2);
                    reg[2] = rs.getInt(3);
                    reg[3] = rs.getString(4);
                    reg[4] = rs.getString(5);
                    reg[5] = rs.getDouble(6);
                    reg[6] = rs.getDouble(7);
                    reg[7] = rs.getDouble(8);

                    list.add(reg);
                }

            } catch (SQLException e) {
            } 
        }
        
        return list;
    }

    @Override
    public String detallesIns(DetalleVenta detalleVenta) {
        String result = null;
        String sql = "INSERT INTO detalleventa("
                + "codigoventa,"
                + "codigoproducto,"
                + "cantidad,"
                + "descuento"
                + ") VALUES(?,?,?,?)";

        Connection cn = conecta.connection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, detalleVenta.getCodigoventa());
                ps.setInt(2, detalleVenta.getCodigoproducto());
                ps.setDouble(3, detalleVenta.getCantidad());
                ps.setDouble(4, detalleVenta.getDescuento());

                int ctos = ps.executeUpdate();
                if (ctos == 0) {
                    throw new SQLException("0 filas afectadas");
                }

            } catch (SQLException e) {
                result = e.getMessage();
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                    result = e.getMessage();
                }
            }
        }

        return result;
    }

    @Override
    public String detallesDel(List<Integer> ids) {
        String result = null;
        String sql = "DELETE FROM detalleventa WHERE idDetalleVenta=?";

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
    public DetalleVenta detallesGet(Integer ids) {
        DetalleVenta detalleVenta = null;
        String sql = "SELECT "
                + "detalleventa.idDetalleVenta, "
                + "detalleventa.codigoventa, "
                + "venta.cliente, "
                + "detalleventa.codigoproducto, "
                + "producto.nombre, "
                + "detalleventa.cantidad, "
                + "detalleventa.descuento  "
                + "FROM detalleventa "
                + "INNER JOIN venta "
                + "ON detalleventa.codigoventa=venta.codigoventa "
                + "INNER JOIN producto "
                + "ON detalleventa.codigoproducto=producto.codigoproducto "
                + "WHERE detalleventa.idDetalleVenta = ?";

        Connection cn = conecta.connection();
        if (cn != null) {
            try (PreparedStatement ps = cn.prepareStatement(sql)) {

                ps.setInt(1, ids);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    detalleVenta = new DetalleVenta();

                    detalleVenta.setId(rs.getInt(1));
                    detalleVenta.setCodigoventa(rs.getInt(2));
                    detalleVenta.setVentaCliente(rs.getString(3));
                    detalleVenta.setCodigoproducto(rs.getInt(4));
                    detalleVenta.setProductoNombre(rs.getString(5));
                    detalleVenta.setCantidad(rs.getDouble(6));
                    detalleVenta.setDescuento(rs.getDouble(7));                    
                }

            } catch (SQLException e) {
                System.out.println("");
            }
        }

        return detalleVenta;
    }

    @Override
    public String detallesUpd(DetalleVenta detalleVenta) {
        String result = null;
        String sql = "UPDATE detalleventa SET "
                + "cantidad=?,"
                + "descuento=? "
                + "WHERE idDetalleVenta=?";

        Connection cn = conecta.connection();
        if (cn != null) {
            try (PreparedStatement ps = cn.prepareStatement(sql)) {

                ps.setDouble(1, detalleVenta.getCantidad());
                ps.setDouble(2, detalleVenta.getDescuento());
                ps.setInt(3, detalleVenta.getId());
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
