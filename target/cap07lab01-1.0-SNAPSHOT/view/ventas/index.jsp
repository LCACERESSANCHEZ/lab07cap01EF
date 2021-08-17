<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAT</title>
        <link href="../../css/main.css" rel="stylesheet" type="text/css"/>
        <link href="../../tema10/table.css" type="text/css" rel="stylesheet"/>
        
        <script src="../../jq/jquery-2.1.3.min.js" type="text/javascript"></script>
        <script src="../../js/ventas.js" type="text/javascript"></script>
    </head>
    <body>

        <div id="caja" style="margin: auto;width: 400px">
            <table class="navy">
                <caption>Lista de Ventas</caption>

                <thead>
                    <tr>
                        <td>Cliente</td>
                        <td>Fecha</td>
                        <th style="width: 26px">
                            <a href="#" onclick="ventasIns()">
                                <img src="../../tema10/images/ins.png" 
                                     alt="Nuevo"/>
                            </a>
                        </th>
                        <th style="width: 26px">
                            <a href="#" onclick="ventasDel()">
                                <img src="../../tema10/images/del.png" 
                                     alt="Retirar"/>
                            </a>
                        </th>
                        <th style="width: 26px">
                            <a href="#" onclick="ventasUpd()">
                                <img src="../../tema10/images/upd.png" 
                                     alt="Actualizar"/>
                            </a>
                        </th>
                    </tr>
                </thead>

                <tfoot>
                    <tr>
                        <th colspan="9">Cibertec - DAT</th>
                    </tr>
                </tfoot>

                <tbody>
                    <c:forEach var="v" items="${list}">
                        <tr>
                            <td>${v.cliente}</td>
                            <td colspan="2">
                                <fmt:formatDate value="${v.fecha}" pattern="dd/MM/yyyy hh:mm:ss a"/>
                            </td>
                            <th>
                                <input type="checkbox" value="${v.codigoventa}" 
                                       name="codigoventaDel"/>
                            </th>
                            <th>
                                <input type="radio" value="${v.codigoventa}" 
                                       name="codigoventaUpd"/>
                            </th>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        
        <p style="text-align: center;color: #900">${msg}</p>
        
        <p style="text-align: center">
            <a href="../../">home</a>
        </p>
    </body>
</html>

<c:remove var="msg"/>