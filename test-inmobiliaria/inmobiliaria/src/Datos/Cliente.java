package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;

import javax.swing.JOptionPane;


public class Cliente extends Usuario {

	private String id_cliente;
	
	
	
	Conexion con =  new Conexion();
    Connection conexion = con.conectar();
    PreparedStatement stmt;

	public Cliente(String nombre, String apellido, String dni, String email, String clave, boolean disponible, int rol,
			String id_cliente, int id) {
		super(nombre, apellido, dni, email, clave, disponible, rol, id);
		this.id_cliente = id_cliente;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}
	

	public void verPublicaciones(LinkedList<Publicacion> listaPublicacion) {

		for (Publicacion publicacion : listaPublicacion) {

			JOptionPane.showMessageDialog(null, this.getNombre() + " " + this.getApellido()
					+ " estas son las publicaciones disponibles: " + publicacion);

		}

	}

	
	
	

	
	
	
	}

	


