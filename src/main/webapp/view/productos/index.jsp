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
        <script src="../../js/productos.js" type="text/javascript"></script>
    </head>
    <body>

        <div id="caja" style="margin: auto;width: 400px">
            <table class="navy">
                <caption>Lista de Productos</caption>

                <thead>
                    <tr>
                        <td>Producto</td>
                        <td>Precio</td>
                        <th style="width: 26px">
                            <a href="#" onclick="productosIns()">
                                <img src="../../tema10/images/ins.png" 
                                     alt="Nuevo"/>
                            </a>
                        </th>
                        <th style="width: 26px">
                            <a href="#" onclick="productosDel()">
                                <img src="../../tema10/images/del.png" 
                                     alt="Retirar"/>
                            </a>
                        </th>
                        <th style="width: 26px">
                            <a href="#" onclick="productosUpd()">
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
                    <c:forEach var="p" items="${list}">
                        <tr>
                            <td>${p.nombre}</td>
                            <td colspan="2">${p.precio}</td>
                            <th>
                                <input type="checkbox" value="${p.codigoproducto}" 
                                       name="codigoproductoDel"/>
                            </th>
                            <th>
                                <input type="radio" value="${p.codigoproducto}" 
                                       name="codigoproductoUpd"/>
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