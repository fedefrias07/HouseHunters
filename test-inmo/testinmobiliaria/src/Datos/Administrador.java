package Datos;

import java.sql.PreparedStatement;

public class Administrador extends Usuario {

	private String id_administrador;

	public Administrador(String nombre, String apellido, String dni, String email, String clave, boolean disponible,
			int rol, String id_administrador, int id) {
		super(nombre, apellido, dni, email, clave, disponible, rol, id);
		this.id_administrador = id_administrador;
	}

	public String getId_administrador() {
		return id_administrador;
	}

	public void setId_administrador(String id_administrador) {
		this.id_administrador = id_administrador;
	}

	public boolean eliminarUsuario(int idUsuario) {
		try {
			// Eliminar la publicación asociada al propietario
			String eliminarPublicacionSQL = "DELETE FROM publicacion WHERE id_propietario = (SELECT id_propietario FROM propietario WHERE id_usuario = ?)";
			PreparedStatement eliminarPublicacionStmt = conexion.prepareStatement(eliminarPublicacionSQL);
			eliminarPublicacionStmt.setInt(1, idUsuario);
			eliminarPublicacionStmt.executeUpdate();

			// Eliminar el propietario
			String eliminarPropietarioSQL = "DELETE FROM propietario WHERE id_usuario = ?";
			PreparedStatement eliminarPropietarioStmt = conexion.prepareStatement(eliminarPropietarioSQL);
			eliminarPropietarioStmt.setInt(1, idUsuario);
			eliminarPropietarioStmt.executeUpdate();

			// Eliminar el cliente
			String eliminarClienteSQL = "DELETE FROM cliente WHERE id_usuario = ?";
			PreparedStatement eliminarClienteStmt = conexion.prepareStatement(eliminarClienteSQL);
			eliminarClienteStmt.setInt(1, idUsuario);
			eliminarClienteStmt.executeUpdate();

			// Eliminar el usuario
			String eliminarUsuarioSQL = "DELETE FROM usuario WHERE id_usuario = ?";
			PreparedStatement eliminarUsuarioStmt = conexion.prepareStatement(eliminarUsuarioSQL);
			eliminarUsuarioStmt.setInt(1, idUsuario);
			eliminarUsuarioStmt.executeUpdate();

			System.out.println("Se eliminó el usuario con ID: " + idUsuario);

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean EditarNombre(int id) {
		String sql = "UPDATE usuario SET nombre = ? WHERE id_usuario = ?";
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, this.getNombre());
			stmt.setInt(2, id);
			stmt.executeUpdate();
			return true;
		} catch (Exception excepcion) {
			System.out.println(excepcion.getMessage());
			return false;
		}
	}

	public boolean EditarApellido(int id) {
		String sql = "UPDATE usuario SET apellido = ? WHERE id_usuario = ?";
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, this.getApellido());
			stmt.setInt(2, id);
			stmt.executeUpdate();
			return true;
		} catch (Exception excepcion) {
			System.out.println(excepcion.getMessage());
			return false;
		}
	}

	public boolean EditarDni(int id) {
		String sql = "UPDATE usuario SET dni = ? WHERE id_usuario = ?";
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, this.getDni());
			stmt.setInt(2, id);
			stmt.executeUpdate();
			return true;
		} catch (Exception excepcion) {
			System.out.println(excepcion.getMessage());
			return false;
		}
	}

	public boolean EditarEmail(int id) {
		String sql = "UPDATE usuario SET email = ? WHERE id_usuario = ?";
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, this.getEmail());
			stmt.setInt(2, id);
			stmt.executeUpdate();
			return true;
		} catch (Exception excepcion) {
			System.out.println(excepcion.getMessage());
			return false;
		}
	}

	public boolean EditarClave(int id) {
		String sql = "UPDATE usuario SET clave = ? WHERE id_usuario = ?";
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setString(1, this.getClave());
			stmt.setInt(2, id);
			stmt.executeUpdate();
			return true;
		} catch (Exception excepcion) {
			System.out.println(excepcion.getMessage());
			return false;
		}
	}

	public boolean EditarRol(int id) {
		String sql = "UPDATE usuario SET rol = ? WHERE id_usuario = ?";
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			stmt.setLong(1, this.getRol());
			stmt.setInt(2, id);
			stmt.executeUpdate();
			return true;
		} catch (Exception excepcion) {
			System.out.println(excepcion.getMessage());
			return false;
		}
	}

	public boolean EditarTodo(int id) {

		String sql = "	UPDATE `usuario` SET `nombre`= ?,`apellido`= ?,`dni`= ?,`email`= ?,`clave`= ? WHERE id_usuario = ? ";
		try {
			stmt = conexion.prepareStatement(sql);
			stmt.setString(1, this.getNombre());
			stmt.setString(2, this.getApellido());
			stmt.setString(3, this.getDni());
			stmt.setString(4, this.getEmail());
			stmt.setString(5, this.getClave());
			//stmt.setInt(6, this.getRol());
			stmt.setInt(6, id);
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
