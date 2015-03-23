package ec.com.se.demo2015.comercial;

import javax.ws.rs.POST;
import javax.ws.rs.Path;


public interface ComercialResource {
	@Path("crear")
	@POST
	public boolean crearOrden(OrdenCompra ordenCompra);
}
