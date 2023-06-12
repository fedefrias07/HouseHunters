package Datos;

public class Transaccion {
	private int id_transaccion;
	private String nro_tarjeta;
	private String clave_seguridad;
	private String nombretarjeta;
	private int cliente_id;
	private int publicacion_id;
	private double precio;

	public Transaccion(int id_transaccion, String nro_tarjeta, String clave_seguridad, String nombretarjeta,
			int cliente_id, int publicacion_id, double precio) {
		super();
		this.id_transaccion = id_transaccion;
		this.nro_tarjeta = nro_tarjeta;
		this.clave_seguridad = clave_seguridad;
		this.nombretarjeta = nombretarjeta;
		this.cliente_id = cliente_id;
		this.publicacion_id = publicacion_id;
		this.precio = precio;
	}

	public int getId_transaccion() {
		return id_transaccion;
	}

	public void setId_transaccion(int id_transaccion) {
		this.id_transaccion = id_transaccion;
	}

	public String getNro_tarjeta() {
		return nro_tarjeta;
	}

	public void setNro_tarjeta(String nro_tarjeta) {
		this.nro_tarjeta = nro_tarjeta;
	}

	public String getClave_seguridad() {
		return clave_seguridad;
	}

	public void setClave_seguridad(String clave_seguridad) {
		this.clave_seguridad = clave_seguridad;
	}

	public String getNombretarjeta() {
		return nombretarjeta;
	}

	public void setNombretarjeta(String nombretarjeta) {
		this.nombretarjeta = nombretarjeta;
	}

	public int getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(int cliente_id) {
		this.cliente_id = cliente_id;
	}

	public int getPublicacion_id() {
		return publicacion_id;
	}

	public void setPublicacion_id(int publicacion_id) {
		this.publicacion_id = publicacion_id;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Transaccion [id_transaccion=" + id_transaccion + ", nro_tarjeta=" + nro_tarjeta + ", clave_seguridad="
				+ clave_seguridad + ", nombretarjeta=" + nombretarjeta + ", cliente_id=" + cliente_id
				+ ", publicacion_id=" + publicacion_id + ", precio=" + precio + "]";
	}

}
