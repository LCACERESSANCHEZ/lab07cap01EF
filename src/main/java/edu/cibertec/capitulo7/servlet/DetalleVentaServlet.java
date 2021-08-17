package edu.cibertec.capitulo7.servlet;

import edu.cibertec.capitulo7.dao.DetalleVentaDAO;
import edu.cibertec.capitulo7.dao.ProductoDAO;
import edu.cibertec.capitulo7.dao.VentaDAO;
import edu.cibertec.capitulo7.dao.impl.DetalleVentaDAOImpl;
import edu.cibertec.capitulo7.dao.impl.ProductoDAOImpl;
import edu.cibertec.capitulo7.dao.impl.VentaDAOImpl;
import edu.cibertec.capitulo7.dto.DetalleVenta;
import edu.cibertec.capitulo7.dto.Producto;
import edu.cibertec.capitulo7.dto.Venta;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DetalleVentaServlet", urlPatterns = {"/DetalleVenta",
    "/view/detalles/DetalleVenta"})

public class DetalleVentaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");
        String result = null;
        String target = null;

        DetalleVentaDAO daoDetalleVenta = new DetalleVentaDAOImpl();
        ProductoDAO daoProductos = new ProductoDAOImpl();
        VentaDAO daoVentas = new VentaDAOImpl();

        if (accion == null) {
            result = "Solicitud no recibida";

        } else if (accion.equals("QRY")) {
            List<Object[]> list = daoDetalleVenta.detallesQry();

            if (list != null) {
                request.getSession().setAttribute("list", list);
                target = "index.jsp";

            } else {
                result = "Problemas en Consulta";
            }

        } else if (accion.equals("CBO")) {
            List<Venta> list_ventas = daoVentas.ventasQry();
            List<Producto> list_productos = daoProductos.productosQry();

            request.setAttribute("cbo_ventas", list_ventas);
            request.setAttribute("cbo_productos", list_productos);

            target = "detallesIns.jsp";

        } else if (accion.equals("INS")) {
            DetalleVenta d = new DetalleVenta();
            result = valida(request, d);

            if (result == null) {
                result = daoDetalleVenta.detallesIns(d);
            }

            if (result == null) {
                target = "DetalleVenta?accion=QRY";
            } else {
                request.setAttribute("detalles", d);
                target = "detallesIns.jsp";
            }

        } else if (accion.equals("DEL")) {
            System.out.println("");
            String _ids = request.getParameter("ids");

            List<Integer> ldel = ids(_ids);
            if (ldel == null) {
                result = "Lista de idDetalleVenta(s) incorrecta";
            } else {
                result = daoDetalleVenta.detallesDel(ldel);
            }
            target = "DetalleVenta?accion=QRY";
        } else if (accion.equals("GET")) {
            Integer idDetalleVenta = Integer.parseInt(request.getParameter("cod"));
            if (idDetalleVenta != null) {
                DetalleVenta d = daoDetalleVenta.detallesGet(idDetalleVenta);

                if (d != null) {
                    request.setAttribute("detalle", d);
                    target = "detallesUpd.jsp";

                } else {
                    result = "Código Incorrecto de DetalleVenta";
                    target = "Productos?accion=QRY";
                }
            } else {
                result = "Código Incorrecto de Producto";
                target = "DetalleVenta?accion=QRY";
            }

        } else if (accion.equals("UPD")) {
            DetalleVenta d = new DetalleVenta();
            result = valida(request, d);

            if (result == null) {
                result = daoDetalleVenta.detallesUpd(d);
            }

            if (result == null) {
                target = "DetalleVenta?accion=QRY";
            } else {
                request.setAttribute("detalle", d);
                target = "detallesUpd.jsp";
            }
        } else {
            result = "Solicitud no reconocida";
        }

        if (result != null) {
            request.getSession().setAttribute("msg", result);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(target);
        dispatcher.forward(request, response);

    }

    private String valida(HttpServletRequest request, DetalleVenta detalleVenta) {
        String result = "<ol>";
        Integer idDetalleVenta = Integer.parseInt(request.getParameter("idDetalleVenta"));
        Integer codigoventa = Integer.parseInt(request.getParameter("codigoventa"));
        Integer codigoproducto = Integer.parseInt(request.getParameter("codigoproducto"));
        Double cantidad = Double.parseDouble(request.getParameter("cantidad"));
        Double descuento = Double.parseDouble(request.getParameter("descuento"));

        if ((cantidad == null) || (cantidad <= 0)) {
            result += "<li>Ingrese Cantidad Correcta</li>";
        }

        if ((descuento == null) || (descuento < 0)) {
            result += "<li>Ingrese Descuento Correcta</li>";
        }

        detalleVenta.setId(idDetalleVenta);
        detalleVenta.setCodigoventa(codigoventa);
        detalleVenta.setCodigoproducto(codigoproducto);
        detalleVenta.setCantidad(cantidad);
        detalleVenta.setDescuento(descuento);

        if (result.equals("<ol>")) {
            result = null;
        } else {
            result += "</ol>";
        }

        return result;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private List<Integer> ids(String _ids) {
        List<Integer> list = null;

        if ((_ids != null) && (_ids.trim().length() > 0)) {
            String[] id = _ids.split(",");

            list = new LinkedList<>();
            for (String ix : id) {
                Integer x = Integer.parseInt(ix);

                if (x != null) {
                    list.add(x);
                } else {
                    list = null;
                    break;
                }
            }
        }
        return list;
    }

}
