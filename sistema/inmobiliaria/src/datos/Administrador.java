package datos;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Administrador extends Usuario {

	private String id_administrador;

	public Administrador(String nombre, String apellido, String dni, String email, String clave, boolean disponible,
			int rol, String id_administrador) {
		super(nombre, apellido, dni, email, clave, disponible, rol);
		this.id_administrador = id_administrador;
	}
	
	public String getId_administrador() {
		return id_administrador;
	}

	public void setId_administrador(String id_administrador) {
		this.id_administrador = id_administrador;
	}

	

	
	  // M�todo para eliminar un Cliente de la lista de usuarios
    public void eliminarCliente(Cliente cliente, LinkedList<Usuario> listaUsuarios, LinkedList<Cliente> listaClientes) {
        listaUsuarios.remove(cliente);
        listaClientes.remove(cliente);
        JOptionPane.showMessageDialog(null, "El Cliente " + cliente.getNombre() + " " + cliente.getApellido() + " ha sido eliminado correctamente." + "\nAdmin que lo elimino: " + this.getNombre() + this.getApellido());
    }
    
    // M�todo para eliminar un Propietario de la lista de usuarios
    public void eliminarPropietario(Propietario propietario, LinkedList<Usuario> listaUsuarios, LinkedList<Propietario> listaPropietarios) {
        listaUsuarios.remove(propietario);
        listaPropietarios.remove(propietario);
        JOptionPane.showMessageDialog(null, "El Propietario " + propietario.getNombre() + " " + propietario.getApellido() + " ha sido eliminado correctamente." + "\nAdmin que lo elimino: " + this.getNombre() + this.getApellido());
    }
	
	
}
