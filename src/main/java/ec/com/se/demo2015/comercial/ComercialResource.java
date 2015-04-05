package ec.com.se.demo2015.comercial;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/")
public interface ComercialResource {
	@Path("crear")
	@POST
	@Consumes({ "application/json; charset=UTF-8" })
	public boolean crearOrden(OrdenCompra ordenCompra);

	@GET
	@Path("clientes")
	@Produces({ "application/json; charset=UTF-8" })
	public List<Cliente> getClientes();

	@GET
	@Path("ordenCompra/{id}")
	@Produces({ "application/json; charset=UTF-8" })
	public OrdenCompra getOrdenCompra(@PathParam("id") long ordenCompraId);

	@GET
	@Path("ordenesCompra")
	@Produces({ "application/json; charset=UTF-8" })
	public List<OrdenCompra> getOrdenesCompra();
}
