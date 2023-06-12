package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Publicacion {

	private int id_publicacion;
	private String nombre;
	private boolean temporal;
	private boolean comprar;
	private int precio;
	private String zona;
	private String direccion;
	private int ambientes;
	private String caracteristicas;
	private String estado;
	private int id_propietario;

	Conexion con = new Conexion();
	Connection conexion = con.conectar();
	PreparedStatement stmt;

	public Publicacion(int id_publicacion, String nombre, boolean temporal, boolean comprar, int precio, String zona,
			String direccion, int ambientes, String caracteristicas, String estado, int id_propietario) {
		super();
		this.id_publicacion = id_publicacion;
		this.nombre = nombre;
		this.temporal = temporal;
		this.comprar = comprar;
		this.precio = precio;
		this.zona = zona;
		this.direccion = direccion;
		this.ambientes = ambientes;
		this.caracteristicas = caracteristicas;
		this.estado = estado;
		this.id_propietario = id_propietario;
	}

	public int getId_publicacion() {
		return id_publicacion;
	}

	public void setId_publicacion(int id_publicacion) {
		this.id_publicacion = id_publicacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isTemporal() {
		return temporal;
	}

	public void setTemporal(boolean temporal) {
		this.temporal = temporal;
	}

	public boolean isComprar() {
		return comprar;
	}

	public void setComprar(boolean comprar) {
		this.comprar = comprar;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getAmbientes() {
		return ambientes;
	}

	public void setAmbientes(int ambientes) {
		this.ambientes = ambientes;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getId_propietario() {
		return id_propietario;
	}

	public void setId_propietario(int id_propietario) {
		this.id_propietario = id_propietario;
	}

	@Override
	public String toString() {
		return "\nPublicacion: " + id_publicacion + "\nTitulo: " + nombre + "\nPrecio: $" + precio + "\nZona: " + zona
				+ "\nDireccion: " + direccion + "\nAmbientes: " + ambientes + "\nCaracteristicas: " + caracteristicas
				+ "\nEstado: " + estado;
	}

	public boolean EditarEstado(int id) {
		String sql = "UPDATE publicacion SET estado = ? WHERE id_publicacion = ?";
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, this.getEstado());
			stmt.setInt(2, id);
			stmt.executeUpdate();
			return true;

		} catch (Exception excepcion) {
			System.out.println(excepcion.getMessage());
			return false;
		}
	}

}
