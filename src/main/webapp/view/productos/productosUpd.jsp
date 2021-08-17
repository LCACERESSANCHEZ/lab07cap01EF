<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DAT</title>
        <link href="../../css/main.css" type="text/css" rel="stylesheet"/>
        <link href="../../tema10/form.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <div id="caja" style="margin: auto;width: 400px">
            <form class="navy" action="Productos" method="post">
                <input type="hidden" name="accion" value="UPD"/>
                <input type="hidden" name="codigoproducto" value="${productos.codigoproducto}"/>

                <fieldset>
                    <legend>Actualizar Producto</legend>

                    <label style="width: 60px">Producto</label>
                    <input type="text" name="nombre" value="${productos.nombre}"
                           style="width: 300px" maxlength="100"/><br/>

                    <label style="width: 60px">Precio</label>
                    <input type="text" name="precio" value="${productos.precio}"
                           style="width: 150px" maxlength="50"/>

                    <div class="submit">
                        <input type="submit" value="Enviar Datos"/>
                    </div>
                </fieldset>
            </form>
            
            <div style="margin: auto;display: table;color: #900">${msg}</div>

            <p style="text-align: center">
                <a class="simple" href="Productos?accion=QRY">Cancelar</a>
            </p>
        </div>
    </body>
</html>
