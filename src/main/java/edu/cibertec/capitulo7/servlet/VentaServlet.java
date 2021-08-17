
package edu.cibertec.capitulo7.servlet;

import edu.cibertec.capitulo7.dao.VentaDAO;
import edu.cibertec.capitulo7.dao.impl.VentaDAOImpl;
import edu.cibertec.capitulo7.dto.Venta;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VentaServlet", urlPatterns = {"/Ventas", 
        "/view/ventas/Ventas"})
public class VentaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // ---
        String accion = request.getParameter("accion");
        String result = null;
        String target = null;
        // ---
        VentaDAO daoVentas = new VentaDAOImpl();
        
        if (accion == null) {
            result = "Solicitud no recibida";

        } else if (accion.equals("QRY")) {
            List<Venta> list = daoVentas.ventasQry();

            if (list != null) {
                request.getSession().setAttribute("list", list);
                target = "index.jsp";

            } else {
                result = "Problemas en Consulta";
            }

        } else if (accion.equals("INS")) {
            Venta v = new Venta();
            result = valida(request, v);

            if (result == null) {
                result = daoVentas.ventasIns(v);
            }

            if (result == null) {
                target = "Ventas?accion=QRY";
            } else {
                request.setAttribute("ventas", v);
                target = "ventasIns.jsp";
            }

        } else if (accion.equals("DEL")) {
            String _ids = request.getParameter("ids");

            List<Integer> ldel = ids(_ids);
            if (ldel == null) {
                result = "Lista de id(s) incorrecta";
            } else {
                result = daoVentas.ventasDel(ldel);
            }

            target = "Ventas?accion=QRY";

        } else if (accion.equals("GET")) {
            Integer codigoventa = Integer.parseInt(request.getParameter("cod"));

            if (codigoventa != null) {
                Venta v = daoVentas.ventasGet(codigoventa);

                if (v != null) {
                    request.setAttribute("ventas", v);
                    target = "ventasUpd.jsp";
                    
                } else {
                    result = "Código Incorrecto";
                    target = "Ventas?accion=QRY";
                }
            } else {
                result = "Código Incorrecto";
                target = "Ventas?accion=QRY";
            }
            
        } else if (accion.equals("UPD")) {
            Venta v = new Venta();
            result = valida(request, v);

            if (result == null) {
                result = daoVentas.ventasUpd(v);
            }

            if (result == null) {
                target = "Ventas?accion=QRY";
            } else {
                request.setAttribute("ventas", v);
                target = "ventasUpd.jsp";
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
    
        private String valida(HttpServletRequest request, Venta ventas) {
        String result = "<ol>";
        Integer codigoventa = Integer.parseInt(request.getParameter("codigoventa"));
        String cliente = request.getParameter("cliente");
        String fechita = request.getParameter("fecha");
        SimpleDateFormat sdf
                = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        sdf.setLenient(false);
        java.sql.Timestamp resulta = null;
        try {
            java.util.Date ufechahora = sdf.parse(request.getParameter("fecha"));
            resulta = new java.sql.Timestamp(ufechahora.getTime());
        } catch (ParseException ex) { ex.printStackTrace();
        }
       
        Timestamp fecha = resulta;

        if (codigoventa == null) {
            result += "<li>Código Incorrecto</li>";
        }

        if ((cliente == null) || (cliente.trim().length() == 0)) {
            result += "<li>Ingrese Nombre de Cliente</li>";
        }

        if (fecha == null) {
            result += "<li>Fecha Incorrecta</li>";
        }

        ventas.setCodigoventa(codigoventa);
        ventas.setCliente(cliente);
        ventas.setFecha(fecha);

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
