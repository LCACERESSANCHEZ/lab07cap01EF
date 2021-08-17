function detallesIns() {
    window.location = "DetalleVenta?accion=CBO";
}

function detallesDel() {
    var ids = [];

    $("input[name='idDetalleVentaDel']:checked").each(function () {
        ids.push($(this).val());
    });

    if (ids.length === 0) {
        alert("Seleccione fila(s) a Retirar");
    } else {
        if (confirm("¿Eliminar detalle de venta?")) {
            window.location = "DetalleVenta?accion=DEL&ids=" + ids.toString();
        }
    }
}

function detallesUpd() {
    var id = $("input[name='idDetalleVentaUdp']:checked").val();

    if (isNaN(id)) {
        alert("Seleccione Fila para Actualizar Datos");
    } else {
        window.location = "DetalleVenta?accion=GET&cod=" + id;
    }
}



