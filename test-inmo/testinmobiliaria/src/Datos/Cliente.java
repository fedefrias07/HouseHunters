package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Cliente extends Usuario {

	private int id_cliente;
	private int id_usuario;

	Conexion con = new Conexion();
	Connection conexion = con.conectar();
	PreparedStatement stmt;

	public Cliente(String nombre, String apellido, String dni, String email, String clave, boolean disponible, int rol,
			int id_usuario, int id_cliente, int id_usuario2) {
		super(nombre, apellido, dni, email, clave, disponible, rol, id_usuario);
		this.id_cliente = id_cliente;
		id_usuario = id_usuario2;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public boolean crearTransaccion(Transaccion transaccion) {
		String sql = "INSERT INTO transaccion (nro_tarjeta, clave_seguridad, nombre, cliente_id, publicacion_id, precio) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			stmt = conexion.prepareStatement(sql); // PREPARO LA CONEXION
			stmt.setString(1, transaccion.getNro_tarjeta());
			stmt.setString(2, transaccion.getClave_seguridad());
			stmt.setString(3, transaccion.getNombretarjeta());
			stmt.setLong(4, transaccion.getCliente_id());
			stmt.setLong(5, transaccion.getPublicacion_id());
			stmt.setDouble(6, transaccion.getPrecio());

			stmt.executeUpdate();

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public boolean crearResena(Resena resena) {
		String sql = "INSERT INTO resena (comentario, estrellas, cliente_id, publicacion_id) VALUES (?, ?, ?, ?)";

		try {
			stmt = conexion.prepareStatement(sql); // PREPARO LA CONEXION
			stmt.setString(1, resena.getComentario());
			stmt.setString(2, resena.getEstrellas());
			stmt.setLong(3, resena.getCliente_id());
			stmt.setLong(4, resena.getPublicacion_id());

			stmt.executeUpdate();

			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public LinkedList<Resena> traerResena() {
		LinkedList<Resena> resena = new LinkedList<Resena>();

		String sql = "SELECT * FROM `resena` ";
		String[] datos = new String[5];
		try {
			stmt = conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				datos[0] = String.valueOf(result.getInt(1));
				datos[1] = result.getString(2);
				datos[2] = result.getString(3);
				datos[3] = String.valueOf(result.getInt(4));
				datos[4] = String.valueOf(result.getInt(5));

				resena.add(new Resena(Integer.parseInt(datos[0]), datos[1], datos[2], Integer.parseInt(datos[3]),
						Integer.parseInt(datos[4])));

			}
			return resena;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public LinkedList<Cliente> traerCliente() {
		LinkedList<Cliente> clientes = new LinkedList<>();

		String sql = "SELECT * FROM cliente";
		try {
			PreparedStatement stmt = conexion.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				int idCliente = result.getInt("id_cliente");
				int idUsuario = result.getInt("id_usuario");

				// Realiza una nueva consulta para obtener los datos específicos del propietario
				String sqlPropietario = "SELECT * FROM usuario WHERE id_usuario = ?";
				PreparedStatement stmtPropietario = conexion.prepareStatement(sqlPropietario);
				stmtPropietario.setInt(1, idUsuario);
				ResultSet resultPropietario = stmtPropietario.executeQuery();

				if (resultPropietario.next()) {
					String nombre = resultPropietario.getString("nombre");
					String apellido = resultPropietario.getString("apellido");
					String dni = resultPropietario.getString("dni");
					String email = resultPropietario.getString("email");
					String clave = resultPropietario.getString("clave");
					boolean disponible = resultPropietario.getBoolean("disponible");
					int rol = resultPropietario.getInt("rol");

					// Crear un nuevo objeto Propietario y agregarlo a la lista
					Cliente cliente = new Cliente(nombre, apellido, dni, email, clave, disponible, rol, idUsuario,
							idCliente, idUsuario);

					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setDni(dni);
					cliente.setEmail(email);
					cliente.setClave(clave);
					cliente.setDisponible(disponible);
					cliente.setRol(rol);
					cliente.setId_cliente(idCliente);
					cliente.setId_usuario(idUsuario);

					clientes.add(cliente);
				}

				resultPropietario.close();
				stmtPropietario.close();
			}

			result.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return clientes;
	}

}
