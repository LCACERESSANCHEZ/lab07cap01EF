$(function () {
    $.timepicker.regional['es'] = {
        timeOnlyTitle: 'Seleccione Hora',
        timeText: 'Selección',
        hourText: 'Hora',
        minuteText: 'Minutos',
        secondText: 'Segundos',
        currentText: 'Ahora',
        closeText: 'Aceptar',
        ampm: false
    };

    $.timepicker.setDefaults($.timepicker.regional['es']);
    $("#fecha").datetimepicker({
        showOn: 'button',
        buttonImage: '../../images/calendar.gif',
        buttonImageOnly: true,
        showAnim: 'slideDown',
        showSecond: true,
        yearRange: '2020:2021',
        changeMonth: true,
        changeYear: true,
        dateFormat: 'dd/mm/yy ',
        timeFormat: 'hh:mm:ss tt',
        hour: 8,
        minute: 30,
        second: 30,
        hourMin: 1,
        hourMax: 23,
        stepHour: 1,
        stepMinute: 1,
        stepSecond: 1,
        addSliderAccess: true,
        sliderAccessArgs: {
            touchonly: false
        }
    });
});

function ventasIns() {
    window.location = "ventasIns.jsp";
}

function ventasDel() {
    var ids = [];

    $("input[name='codigoventaDel']:checked").each(function () {
        ids.push($(this).val());
    });

    if (ids.length === 0) {
        alert("Seleccione fila(s) a Retirar");
    } else {
        if (confirm("¿Retirar Venta(s)?")) {
            window.location = "Ventas?accion=DEL&ids=" + ids.toString();
        }
    }
}

function ventasUpd() {
    var id = $("input[name='codigoventaUpd']:checked").val();

    if (isNaN(id)) {
        alert("Seleccione Fila para Actualizar Datos");
    } else {
        window.location = "Ventas?accion=GET&cod=" + id;
    }
}


