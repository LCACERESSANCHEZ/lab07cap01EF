package edu.cibertec.capitulo7.dao;

import edu.cibertec.capitulo7.dto.Producto;
import java.util.List;

public interface ProductoDAO {

    public List<Producto> productosQry();

    public String productosIns(Producto productos);

    public String productosDel(List<Integer> ids);

    public Producto productosGet(Integer idproducto);

    public String productosUpd(Producto productos);

}
