package edu.cibertec.capitulo7.dao.impl;

import edu.cibertec.capitulo7.dao.SqlConecta;
import edu.cibertec.capitulo7.dao.VentaDAO;
import edu.cibertec.capitulo7.dto.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VentaDAOImpl implements VentaDAO {

    private final SqlConecta conecta;

    public VentaDAOImpl() {
        this.conecta = new SqlConecta();
    }

    @Override
    public List<Venta> ventasQry() {
        List<Venta> list = null;
        String sql = "SELECT "
                + "codigoventa,"
                + "cliente,"
                + "fecha "
                + "FROM venta";

        Connection cn = conecta.connection();
        if (cn != null) {
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);

                list = new ArrayList<>();

                while (rs.next()) {
                    Venta v = new Venta();

                    v.setCodigoventa(rs.getInt(1));
                    v.setCliente(rs.getString(2));
                    v.setFecha(rs.getTimestamp(3));

                    list.add(v);
                }

            } catch (SQLException e) {
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                }
            }
        }

        return list;
    }

    @Override
    public String ventasIns(Venta ventas) {
        String result = null;
        String sql = "INSERT INTO venta("
                + "codigoventa,"
                + "cliente,"
                + "fecha"
                + ") VALUES(?,?,?)";

        Connection cn = conecta.connection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, ventas.getCodigoventa());
                ps.setString(2, ventas.getCliente());
                ps.setTimestamp(3, ventas.getFecha());

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
    public String ventasDel(List<Integer> ids) {
        String result = null;
        String sql = "DELETE FROM venta WHERE codigoventa=?";

        Connection cn = conecta.connection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                for (Integer cod : ids) {
                    ps.setInt(1, cod);

                    int ctos = ps.executeUpdate();
                    if (ctos == 0) {
                        throw new SQLException("Código " + cod + " Incorrecto");
                    }
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
    public Venta ventasGet(Integer idventa) {
        Venta ventas = null;
        String sql = "SELECT "
                + "codigoventa,"
                + "cliente,"
                + "fecha "
                + "FROM venta WHERE codigoventa=?";

        Connection cn = conecta.connection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, idventa);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ventas = new Venta();

                    ventas.setCodigoventa(rs.getInt(1));
                    ventas.setCliente(rs.getString(2));
                    ventas.setFecha(rs.getTimestamp(3));
                }

            } catch (SQLException e) {
                System.out.println("");
            } finally {
                try {
                    cn.close();
                } catch (SQLException e) {
                }
            }
        }

        return ventas;
    }

    @Override
    public String ventasUpd(Venta ventas) {
        String result = null;
        String sql = "UPDATE venta SET "
                + "cliente=?,"
                + "fecha=? "
                + "WHERE codigoventa=?";

        Connection cn = conecta.connection();
        if (cn != null) {
            try {
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, ventas.getCliente());
                ps.setTimestamp(2, ventas.getFecha());
                ps.setInt(3, ventas.getCodigoventa());

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
    
}
