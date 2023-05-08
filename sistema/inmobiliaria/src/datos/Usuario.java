package datos;


public class Usuario {

	private String nombre;
	private String apellido;
	private String dni;
	private String email;
	private String clave;
	private boolean disponible;
	private int rol;
	
	public Usuario(String nombre, String apellido, String dni, String email, String clave,boolean disponible,int rol) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.clave = clave;
		this.disponible = disponible;
		this.rol = rol;
	}

	public String getNombre() {
		return nombre;
	}

	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	
	
	
	
}
