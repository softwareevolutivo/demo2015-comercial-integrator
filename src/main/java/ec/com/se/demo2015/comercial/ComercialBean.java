package ec.com.se.demo2015.comercial;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
								"insert into orden_compra_detalles (id_orden_compra,sku1_producto, cantidad, valor_unitario) values (?,?,?,?)",
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

}
