package edu.cibertec.capitulo7.dao;

import edu.cibertec.capitulo7.dto.DetalleVenta;
import java.util.List;

public interface DetalleVentaDAO {

    public List<Object[]> detallesQry();

    public String detallesIns(DetalleVenta detalleVenta);

    public String detallesDel(List<Integer> ids);

    public DetalleVenta detallesGet(Integer ids);

    public String detallesUpd(DetalleVenta detalleVenta);

}
