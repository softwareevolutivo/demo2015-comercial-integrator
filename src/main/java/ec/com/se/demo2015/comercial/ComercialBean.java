package ec.com.se.demo2015.comercial;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.switchyard.component.bean.Service;

@Service(Comercial.class)
public class ComercialBean implements Comercial {

	@Resource(mappedName = "java:jboss/datasources/Demo2015Comercial")
	private DataSource comercialDS;

	@Override
	public boolean crearOrden(OrdenCompra ordenCompra)
			throws ComercialIntegratorException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {

			conn = comercialDS.getConnection();
			ps = conn
					.prepareStatement(
							"insert into ordenes_compra (identificacion_cliente,fecha) values (?,?)",
							Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, ordenCompra.getCliente().getIdentificacion());
			ps.setDate(2, new Date(ordenCompra.getFecha().getTime()));
			int affectedRows = ps.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException(
						"Creating user failed, no rows affected.");
			}
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			Long ordenCompraId = rs.getLong(1);
			System.out.println("OrdenCompra id -> " + ordenCompraId);
			rs.close();
			ps.close();

			for (OrdenCompraDetalle detalle : ordenCompra.getDetalles()) {
				ps = conn
						.prepareStatement(
								"insert into orden_compra_detalles (id_orden_compra,sku_producto, cantidad, valor_unitario) values (?,?,?,?)",
								Statement.RETURN_GENERATED_KEYS);
				ps.setLong(1, ordenCompraId);
				ps.setString(2, detalle.getSkuProducto());
				ps.setInt(3, detalle.getCantidad());
				ps.setDouble(4, detalle.getValorUnitario());
				affectedRows = ps.executeUpdate();
				if (affectedRows == 0) {
					throw new SQLException(
							"Creating user failed, no rows affected.");
				}
				rs = ps.getGeneratedKeys();
				rs.next();
				Long ordenCompraDetalleId = rs.getLong(1);
				System.out.println("OrdenCompraDetalle id -> "
						+ ordenCompraDetalleId);
				rs.close();
				ps.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComercialIntegratorException(e.getMessage());
		} finally {
			try {
				if (null != conn)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public List<Cliente> getClientes() throws ComercialIntegratorException {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		try {

			conn = comercialDS.getConnection();
			ps = conn.createStatement();
			rs = ps.executeQuery("select * from clientes");
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdentificacion(rs.getString(1));
				cliente.setNombre(rs.getString(2));
				cliente.setDireccion(rs.getString(3));
				cliente.setTelefono(rs.getString(4));
				clientes.add(cliente);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComercialIntegratorException(e.getMessage());
		} finally {
			try {
				if (null != conn)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return clientes;
	}

	@Override
	public OrdenCompra getOrdenCompra(Long ordenCompraId)
			throws ComercialIntegratorException {
		OrdenCompra ordenCompra = new OrdenCompra();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			conn = comercialDS.getConnection();
			ps = conn
					.prepareStatement("select * from ordenes_compra where id = ?");
			ps.setLong(1, ordenCompraId);
			rs = ps.executeQuery();
			while (rs.next()) {
				ordenCompra.setId(rs.getLong(1));
				Cliente cliente = new Cliente();
				cliente.setIdentificacion(rs.getString(2));
				ordenCompra.setCliente(cliente);
				ordenCompra.setFecha(rs.getDate(3));
			}
			rs.close();
			ps.close();

			ps = conn
					.prepareStatement("select * from orden_compra_detalles where id_orden_compra = ?");
			ps.setLong(1, ordenCompraId);
			rs = ps.executeQuery();
			while (rs.next()) {
				OrdenCompraDetalle detalle = new OrdenCompraDetalle();
				detalle.setId(rs.getLong(1));
				detalle.setSkuProducto(rs.getString(3));
				detalle.setCantidad(rs.getInt(4));
				detalle.setValorUnitario(rs.getDouble(5));
				ordenCompra.getDetalles().add(detalle);
			}
			rs.close();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComercialIntegratorException(e.getMessage());
		} finally {
			try {
				if (null != conn)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return ordenCompra;
	}

}
