package ec.com.se.demo2015.comercial;

import java.util.Date;
import java.util.Set;

public class OrdenCompra {
	private long id;
	private Date fecha;
	private Cliente cliente;
	private Set<OrdenCompraDetalle> detalle;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Set<OrdenCompraDetalle> getDetalle() {
		return detalle;
	}
	public void setDetalle(Set<OrdenCompraDetalle> detalle) {
		this.detalle = detalle;
	}
	
}
