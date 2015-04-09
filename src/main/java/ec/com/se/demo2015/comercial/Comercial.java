package ec.com.se.demo2015.comercial;

import java.util.List;
import java.util.Map;

public interface Comercial {
	public Long createOrden(OrdenCompra ordenCompra)
			throws ComercialIntegratorException;

	public List<Cliente> getClientes() throws ComercialIntegratorException;

	public OrdenCompra getOrdenCompra(Long ordenCompraId)
			throws ComercialIntegratorException;

	public List<Map<String, Object>> getOrdenesCompra()
			throws ComercialIntegratorException;
}
