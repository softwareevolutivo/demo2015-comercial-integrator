package ec.com.se.demo2015.comercial;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ordenCompra")
public class OrdenCompra {
	private long id;
	private Date fecha;
	private Cliente cliente;
	private Set<OrdenCompraDetalle> detalles = new HashSet<OrdenCompraDetalle>();
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
	public Set<OrdenCompraDetalle> getDetalles() {
		return detalles;
	}
	public void setDetalles(Set<OrdenCompraDetalle> detalles) {
		this.detalles = detalles;
	}
}
