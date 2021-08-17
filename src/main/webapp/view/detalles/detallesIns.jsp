<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAT</title>
        <link href="../../css/main.css" type="text/css" rel="stylesheet"/>
        <link href="../../tema10/form.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <div id="caja" style="margin: auto;width: 400px">
            <form class="navy" action="DetalleVenta" method="post">
                <input type="hidden" name="accion" value="INS"/>

                <fieldset>
                    <legend>Nuevo Detalle/Venta</legend>

                    <label style="width: 60px">Cliente</label>
                    <select name="codigoventa" style="width: 306px">
                        <c:forEach var="v" items="${cbo_ventas}">
                            <option value="${v.codigoventa}">${v.cliente}</option>
                        </c:forEach>
                    </select>

                    <label style="width: 60px">Producto</label>
                    <select name="codigoproducto" style="width: 306px">
                        <c:forEach var="p" items="${cbo_productos}">
                            <option value="${p.codigoproducto}">${p.nombre}</option>
                        </c:forEach>
                    </select>

                    <label style="width: 60px">Cantidad</label>
                    <input type="text" name="cantidad" value="${detalles.cantidad}"
                           style="width: 100px" maxlength="50"/><br/>

                    <label style="width: 60px">Descuento</label>
                    <input type="text" name="descuento" value="${detalles.descuento}"
                           style="width: 100px" maxlength="50"/>

                    <div class="submit">
                        <input type="submit" value="Enviar Datos"/>
                    </div>
                </fieldset>
            </form>
            
            <div style="margin: auto;display: table;color: #900">${msg}</div>

            <p style="text-align: center">
                <a class="simple" href="DetalleVenta?accion=QRY">Cancelar</a>
            </p>
        </div>
    </body>
</html>

