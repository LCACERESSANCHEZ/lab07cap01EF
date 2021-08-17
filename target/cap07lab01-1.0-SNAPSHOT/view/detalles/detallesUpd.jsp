<%-- 
    Document   : detallesUpd
    Created on : 15/08/2021, 03:59:46 AM
    Author     : USER
--%>

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
                <input type="hidden" name="accion" value="UPD"/>
                <input type="hidden" name="idDetalleVenta" value="${detalle.id}"/>
                <input type="hidden" name="codigoventa" value="${detalle.codigoventa}"/>
                <input type="hidden" name="codigoproducto" value="${detalle.codigoproducto}"/>

                <fieldset>
                    <legend>Actualizar Detalle/Venta</legend>

                    <label style="width: 60px" value="${detalle.id}">Cliente</label>
                    <input type="text" name="cliente" value="${detalle.ventaCliente}" 
                           style="width: 300px" maxlength="100" disabled="disabled"/><br/>                   

                    <label style="width: 60px">Producto</label>
                    <input type="text" name="producto" value="${detalle.productoNombre}"
                           style="width: 300px" maxlength="100" disabled="disabled"/><br/>

                    <label style="width: 60px">Cantidad</label>
                    <input type="text" name="cantidad" value="${detalle.cantidad}"
                           style="width: 100px" maxlength="50"/><br/>

                    <label style="width: 60px">Descuento</label>
                    <input type="text" name="descuento" value="${detalle.descuento}"
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

