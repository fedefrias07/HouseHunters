package datos;

public class Resena {
	
	private int calificacion;
	private String comentario;
	private Cliente cliente;
	

	public Resena(int calificacion, String comentario, Cliente cliente) {
		super();
		this.calificacion = calificacion;
		this.comentario = comentario;
		this.cliente = cliente;
	}
	

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

	@Override
	public String toString() {
		return "\nRese�a realizada por: " + cliente.getNombre()  +"\nCalificacion: " + calificacion + " \nComentario: " + comentario + "\n";
	}

	

	
	
	
	
}
