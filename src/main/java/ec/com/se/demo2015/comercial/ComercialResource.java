package ec.com.se.demo2015.comercial;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;


@Path("/")
public interface ComercialResource {
	@Path("crear")
	@POST
	@Consumes({"application/json; charset=UTF-8" })
	public boolean crearOrden(OrdenCompra ordenCompra);
}
