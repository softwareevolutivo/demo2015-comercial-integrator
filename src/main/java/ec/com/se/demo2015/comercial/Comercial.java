package ec.com.se.demo2015.comercial;

public interface Comercial {
	public boolean crearOrden(OrdenCompra ordenCompra)
			throws ComercialIntegratorException;
}
