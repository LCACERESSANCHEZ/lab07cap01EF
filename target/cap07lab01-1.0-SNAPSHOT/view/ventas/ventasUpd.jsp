<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAT</title>
        <link href="../../css/main.css" rel="stylesheet" type="text/css"/>
        <link href="../../tema10/form.css" type="text/css" rel="stylesheet"/>
        <link href="../../jq/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
        <link href="../../jq/timepicker/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css"/>
        
        <script src="../../jq/jquery-2.1.3.min.js" type="text/javascript"></script>
        <script src="../../jq/jquery-ui.min.js" type="text/javascript"></script>
        <script src="../../jq/timepicker/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
        
        <script src="../../js/ventas.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="caja" style="margin: auto;width: 400px">
            <form class="navy" action="Ventas" method="post">
                <input type="hidden" name="accion" value="UPD"/>
                <input type="hidden" name="codigoventa" value="${ventas.codigoventa}"/>

                <fieldset>
                    <legend>Actualizar Venta</legend>

                    <label style="width: 60px">Cliente</label>
                    <input type="text" name="cliente" value="${ventas.cliente}"
                           style="width: 300px" maxlength="100"/><br/>

                    <label style="width: 60px">Fecha</label>
                    <fmt:formatDate value="${ventas.fecha}" pattern="dd/MM/yyyy hh:mm:ss a" var="fecha"/>
                    <input type="text" id="fecha" name="fecha" value="${fecha}"
                           style="width: 150px" maxlength="50"/>

                    <div class="submit">
                        <input type="submit" value="Enviar Datos"/>
                    </div>
                </fieldset>
            </form>
            
            <div style="margin: auto;display: table;color: #900">${msg}</div>

            <p style="text-align: center">
                <a class="simple" href="Ventas?accion=QRY">Cancelar</a>
            </p>
        </div>
    </body>
</html>

