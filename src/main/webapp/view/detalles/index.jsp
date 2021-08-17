<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAT</title>
        <link href="../../css/main.css" rel="stylesheet" type="text/css"/>
        <link href="../../tema10/table.css" type="text/css" rel="stylesheet"/>
        
        <script src="../../jq/jquery-2.1.3.min.js" type="text/javascript"></script>
        <script src="../../js/detalles.js" type="text/javascript"></script>
    </head>
    <body>

        <div id="caja" style="margin: auto;width: 600px">
            <table class="navy">
                <caption>Detalles de ventas</caption>

                <thead>
                    <tr>
                        <td>Cliente</td>
                        <td>Producto</td>
                        <td>Precio Unit.</td>
                        <td>Cantidad</td>
                        <td>Descuento</td>
                        <th style="width: 26px">
                            <a href="#" onclick="detallesIns()">
                                <img src="../../tema10/images/ins.png" 
                                     alt="Nuevo"/>
                            </a>
                        </th>
                        <th style="width: 26px">
                            <a href="#" onclick="detallesDel()">
                                <img src="../../tema10/images/del.png" 
                                     alt="Retirar"/>
                            </a>
                        </th>
                        <th style="width: 26px">
                            <a href="#" onclick="detallesUpd()">
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
                    <c:forEach var="d" items="${list}">
                        <tr>
                            <td>${d[3]}</td>
                            <td>${d[4]}</td>
                            <td>${d[5]}</td>
                            <td>${d[6]}</td>
                            <td colspan="2">${d[7]}</td>
                            <th>
                                <input type="checkbox" value="${d[0]}" 
                                       name="idDetalleVentaDel"/>
                            </th>
                            <th>
                                <input type="radio" value="${d[0]}" 
                                       name="idDetalleVentaUdp"/>
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

