package edu.cibertec.capitulo7.servlet;

import edu.cibertec.capitulo7.dao.ProductoDAO;
import edu.cibertec.capitulo7.dao.impl.ProductoDAOImpl;
import edu.cibertec.capitulo7.dto.Producto;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/Productos",
    "/view/productos/Productos"})
public class ProductoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");
        String result = null;
        String target = null;

        ProductoDAO daoProductos = new ProductoDAOImpl();

        if (accion == null) {
            result = "Solicitud no recibida";

        } else if (accion.equals("QRY")) {
            List<Producto> list = daoProductos.productosQry();

            if (list != null) {
                request.getSession().setAttribute("list", list);
                target = "index.jsp";

            } else {
                result = "Problemas en Consulta";
            }

        } else if (accion.equals("INS")) {
            Producto p = new Producto();
            result = valida(request, p);

            if (result == null) {
                result = daoProductos.productosIns(p);
            }

            if (result == null) {
                target = "Productos?accion=QRY";
            } else {
                request.setAttribute("productos", p);
                target = "productosIns.jsp";
            }

        } else if (accion.equals("DEL")) {
            String _ids = request.getParameter("ids");

            List<Integer> ldel = ids(_ids);
            if (ldel == null) {
                result = "Lista de idproducto(s) incorrecta";
            } else {
                result = daoProductos.productosDel(ldel);
            }

            target = "Productos?accion=QRY";

        } else if (accion.equals("GET")) {
            Integer codigoproducto = Integer.parseInt(request.getParameter("cod"));

            if (codigoproducto != null) {
                Producto p = daoProductos.productosGet(codigoproducto);

                if (p != null) {
                    request.setAttribute("productos", p);
                    target = "productosUpd.jsp";

                } else {
                    result = "Código Incorrecto de Producto";
                    target = "Productos?accion=QRY";
                }
            } else {
                result = "Código Incorrecto de Producto";
                target = "Productos?accion=QRY";
            }

        } else if (accion.equals("UPD")) {
            Producto p = new Producto();
            result = valida(request, p);

            if (result == null) {
                result = daoProductos.productosUpd(p);
            }

            if (result == null) {
                target = "Productos?accion=QRY";
            } else {
                request.setAttribute("productos", p);
                target = "productosUpd.jsp";
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

    private String valida(HttpServletRequest request, Producto productos) {
        String result = "<ol>";
        Integer codigoproducto = Integer.parseInt(request.getParameter("codigoproducto"));
        String nombre = request.getParameter("nombre");
        Double precio = Double.parseDouble(request.getParameter("precio"));

        if (codigoproducto == null) {
            result += "<li>Código Incorrecto</li>";
        }

        if ((nombre == null) || (nombre.trim().length() == 0)) {
            result += "<li>Ingrese Nombre de Producto</li>";
        }

        if (precio == null) {
            result += "<li>Precio Incorrecto</li>";
        } else {
            if (precio <= 0) {
                result += "<li>Precio debe ser mayor que CERO</li>";
            }
        }

        productos.setCodigoproducto(codigoproducto);
        productos.setNombre(nombre);
        productos.setPrecio(precio);

        if (result.equals("<ol>")) {
            result = null;
        } else {
            result += "</ol>";
        }

        return result;
    }

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

}
