package datos;

public class Transaccion {
	
	private boolean tarjeta_disponible;
	private int nro_tarjeta;
	private int clave_seguridad;
	private String nombre;
	private String apellido ;
	
	public Transaccion(boolean tarjeta_disponible, int nro_tarjeta, int clave_seguridad, String nombre,
			String apellido) {
		super();
		this.tarjeta_disponible = tarjeta_disponible;
		this.nro_tarjeta = nro_tarjeta;
		this.clave_seguridad = clave_seguridad;
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public boolean isTarjeta_disponible() {
		return tarjeta_disponible;
	}

	public void setTarjeta_disponible(boolean tarjeta_disponible) {
		this.tarjeta_disponible = tarjeta_disponible;
	}

	public int getNro_tarjeta() {
		return nro_tarjeta;
	}

	public void setNro_tarjeta(int nro_tarjeta) {
		this.nro_tarjeta = nro_tarjeta;
	}

	public int getClave_seguridad() {
		return clave_seguridad;
	}

	public void setClave_seguridad(int clave_seguridad) {
		this.clave_seguridad = clave_seguridad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	
	
	
	
	
	
	
}
