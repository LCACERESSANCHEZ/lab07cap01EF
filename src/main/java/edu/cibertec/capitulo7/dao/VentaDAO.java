package edu.cibertec.capitulo7.dao;

import edu.cibertec.capitulo7.dto.Venta;
import java.util.List;

public interface VentaDAO {

    public List<Venta> ventasQry();

    public String ventasIns(Venta venta);

    public String ventasDel(List<Integer> ids);

    public Venta ventasGet(Integer idventa);

    public String ventasUpd(Venta venta);

}
