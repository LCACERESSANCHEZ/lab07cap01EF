function productosIns() {
    window.location = "productosIns.jsp";
}

function productosDel() {
    var ids = [];

    $("input[name='codigoproductoDel']:checked").each(function () {
        ids.push($(this).val());
    });

    if (ids.length === 0) {
        alert("Seleccione fila(s) a Retirar");
    } else {
        if (confirm("¿Retirar Productos?")) {
            window.location = "Productos?accion=DEL&ids=" + ids.toString();
        }
    }
}

function productosUpd() {
    var id = $("input[name='codigoproductoUpd']:checked").val();

    if (isNaN(id)) {
        alert("Seleccione Fila para Actualizar Datos");
    } else {
        window.location = "Productos?accion=GET&cod=" + id;
    }
}