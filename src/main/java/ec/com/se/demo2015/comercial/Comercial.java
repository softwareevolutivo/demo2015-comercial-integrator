package ec.com.se.demo2015.comercial;

import java.util.List;

public interface Comercial {
	public boolean crearOrden(OrdenCompra ordenCompra)
			throws ComercialIntegratorException;

	public List<Cliente> getClientes() throws ComercialIntegratorException;
}
