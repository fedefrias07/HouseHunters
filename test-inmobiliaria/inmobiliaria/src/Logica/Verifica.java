package Logica;

import java.util.LinkedList;
import javax.swing.JOptionPane;

import Datos.Usuario;
import Datos.Administrador;
import Datos.Cliente;
import Datos.Publicacion;
import Datos.Propietario;

public class Verifica {

	Usuario nuevoUsuario = new Usuario("", "", "", "", "", true, 1, 0); // roles -1admin -2propietario -3cliente.

	Publicacion publicacion = new Publicacion(0,true, true, 0, "", "", 0, "", "");

	Cliente cliente = new Cliente("", "", "", "", "", true, 0, "", 0);

	Propietario propietario = new Propietario("", "", "", "", "", true, 0, 0, 0);

	Administrador admin = new Administrador("", "", "", "", "", false, 0, "", 0);

	public LinkedList<Usuario> traerIndiv(int id) {
		return nuevoUsuario.traerUsuarioPorId(id);
	}

	public boolean crearUsuario(String nombre, String apellido, String dni, String email, String clave, int rol) {

		int flag = 0;
		do {
			if (dni.length() == 8) {
				if (nombre.length() >= 3 && nombre.length() < 15) {
					if (apellido.length() >= 3 && apellido.length() < 20) {
						if (email.contains("@") && email.length() < 60) {
							if (clave.matches(".*[A-Z].*") && clave.matches(".*\\d.*")) { // SI LA CLAVE NO TIENE POR LO MENOS UNA MAYUS Y UN NUMERO, NO FUNCA
								if (rol >= 1 && rol <= 3) {

									nuevoUsuario.setNombre(nombre);
									nuevoUsuario.setApellido(apellido);
									nuevoUsuario.setDni(dni);
									nuevoUsuario.setClave(clave);
									nuevoUsuario.setEmail(email);
									nuevoUsuario.setRol(rol);
									JOptionPane.showMessageDialog(null, "Usuario guardado con exito");
									return nuevoUsuario.guardarUsuario();
								} else {
									rol = Integer.parseInt(JOptionPane.showInputDialog(
											"Numero de rol equivocado" + "\nroles:-1admin -2propietario -3cliente."));
									flag++;
								}
							} else {
								clave = JOptionPane.showInputDialog(
										"La clave es incorrecta, Debe tener una letra mayuscula y un numero");
								flag++;
							}
						} else {
							email = JOptionPane.showInputDialog("Error al ingresar el mail, Ingrese un mail valido.");
							flag++;
						}
					} else {
						apellido = JOptionPane
								.showInputDialog("Error al ingresar el apellido" + "\ndebe tener mas de 3 caracteres");
						flag++;
					}
				} else {
					nombre = JOptionPane
							.showInputDialog("Error al ingresar el nombre" + "\ndebe tener entre 3 y 15 caracteres");
					flag++;
				}
			} else {
				dni = JOptionPane.showInputDialog("Error al ingresar el dni" + "\ndebe tener 8 o mas numeros.");
				flag++;

			}

		} while (flag != 0);
		return false;
	}

	public LinkedList<Usuario> verificarLista() {

		return nuevoUsuario.traerUsuario();
	}

	public boolean eliminarUsuario(String nombre) {

		return admin.eliminarUsuario(nombre);

	}

	public boolean editarUsuario(int id) {

		if (id <= 0) {
			return false;
		}
		
		boolean salir= false;
		
		//int conteo = 0;
		
		
		do {
			String[] opciones = { "Editar nombre", "Editar apellido", "Editar DNI", "Editar email", "Editar clave",
					"Editar rol", "Editar todo", "Salir" };

			String opcion = (String) JOptionPane.showInputDialog(null, "Qué desea editar?", "Editar Usuario",
					JOptionPane.DEFAULT_OPTION, null, opciones, opciones[0]);

			if (opcion == null || opcion.equals("Salir")) { //Para salir de la lista
				salir= true;
				break;
			} 

			switch (opcion) {
			case "Editar nombre":
				String nuevoNombre = JOptionPane.showInputDialog("Ingresar el nuevo nombre");
				if (nuevoNombre != null && nuevoNombre.length() >= 3 && nuevoNombre.length() < 15 && nuevoNombre.matches("[a-zA-Z]+")) {
					admin.setNombre(nuevoNombre);
					JOptionPane.showMessageDialog(null, "Nombre del usuario actualizado con éxito");
					return admin.EditarNombre(id);
				} else {
					JOptionPane.showMessageDialog(null,
							"Error al ingresar el nombre. Debe tener entre 3 y 15 caracteres y solo debe tener letras.");
				}
				break;

			case "Editar apellido":
				String nuevoApellido = JOptionPane.showInputDialog("Ingresar el nuevo apellido");
				if (nuevoApellido != null && nuevoApellido.length() >= 3 && nuevoApellido.length() < 20) {
					admin.setApellido(nuevoApellido);
					JOptionPane.showMessageDialog(null, "Apellido del usuario actualizado con éxito");
					return admin.EditarApellido(id);
				} else {
					JOptionPane.showMessageDialog(null,
							"Error al ingresar el apellido. Debe tener entre 3 y 20 caracteres.");
				}
				break;

			case "Editar DNI":
				String nuevoDni = JOptionPane.showInputDialog("Ingresar el nuevo DNI");
				if (nuevoDni != null && nuevoDni.length() == 8) {
					admin.setDni(nuevoDni);
					JOptionPane.showMessageDialog(null, "DNI del usuario actualizado con éxito");
					return admin.EditarDni(id);
				} else {
					JOptionPane.showMessageDialog(null, "Error al ingresar el DNI. Debe tener 8 caracteres.");
				}
				break;

			case "Editar email":
				String nuevoEmail = JOptionPane.showInputDialog("Ingresar el nuevo email");
				if (nuevoEmail != null && nuevoEmail.contains("@") && nuevoEmail.length() < 60) {
					admin.setEmail(nuevoEmail);
					JOptionPane.showMessageDialog(null, "Email del usuario actualizado con éxito");
					return admin.EditarEmail(id);
				} else {
					JOptionPane.showMessageDialog(null, "Error al ingresar el email. Debe ser un email válido.");
				}
				break;

			case "Editar clave":
				String nuevaClave = JOptionPane.showInputDialog("Ingresar la nueva clave");
				if (nuevaClave != null && nuevaClave.matches(".*[A-Z].*") && nuevaClave.matches(".*\\d.*")) {
					admin.setClave(nuevaClave);
					JOptionPane.showMessageDialog(null, "Clave del usuario actualizada con éxito");
					return admin.EditarClave(id);
				} else {
					JOptionPane.showMessageDialog(null,
							"Error al ingresar la clave. Debe tener al menos una letra mayúscula y un número.");
				}
				break;

			case "Editar rol":
				int nuevoRol = Integer.parseInt(
						JOptionPane.showInputDialog("Ingresar el nuevo rol:\n1-Admin 2-Propietario 3-Cliente"));
				if (nuevoRol >= 1 && nuevoRol <= 3) {
					admin.setRol(nuevoRol);
					JOptionPane.showMessageDialog(null, "Rol del usuario actualizado con éxito");
					return admin.EditarRol(id);
				} else {
					JOptionPane.showMessageDialog(null, "Número de rol equivocado. Debe ser 1, 2 o 3.");
				}
				break;

			case "Editar todo":
				nuevoNombre = JOptionPane.showInputDialog("Ingresar el nuevo nombre");
				nuevoApellido = JOptionPane.showInputDialog("Ingresar el nuevo apellido");
				nuevoDni = JOptionPane.showInputDialog("Ingresar el nuevo DNI");
				nuevoEmail = JOptionPane.showInputDialog("Ingresar el nuevo email");
				nuevaClave = JOptionPane.showInputDialog("Ingresar la nueva clave");
				nuevoRol = Integer.parseInt(
				JOptionPane.showInputDialog("Ingresar el nuevo rol:\n1-Admin 2-Propietario 3-Cliente"));

				if (nuevoNombre != null && nuevoNombre.length() >= 3 && nuevoNombre.length() < 15
						&& nuevoApellido != null && nuevoApellido.length() >= 3 && nuevoApellido.length() < 20
						&& nuevoDni != null && nuevoDni.length() == 8 && nuevoEmail != null && nuevoEmail.contains("@")
						&& nuevoEmail.length() < 60 && nuevaClave != null && nuevaClave.matches(".*[A-Z].*")
						&& nuevaClave.matches(".*\\d.*") && nuevoRol >= 1 && nuevoRol <= 3) {
					admin.setNombre(nuevoNombre);
					admin.setApellido(nuevoApellido);
					admin.setDni(nuevoDni);
					admin.setEmail(nuevoEmail);
					admin.setClave(nuevaClave);
					admin.setRol(nuevoRol);
					JOptionPane.showMessageDialog(null, "Usuario actualizado con éxito");
					return admin.EditarTodo(id);
				} else {
					JOptionPane.showMessageDialog(null,
							"Error en los datos ingresados. Revise las condiciones de cada campo.");
				}
				
				break;

			}
		} while (!salir);

		return true;

	}
	
	

	public boolean CrearPublicacion(int precio, boolean temporal, boolean comprar,String zona, String direccion, int ambientes,String estado, int id_propietario) {

		if (temporal == true) {
			publicacion.setTemporal(true);
			publicacion.setComprar(false);
		} else if (comprar == true) {
			publicacion.setComprar(true);
			publicacion.setTemporal(false);
		}
		
		boolean salir=false; 
		
		do {

		if (precio > 1000) {
			if (zona.length() > 2) {
				if (direccion.length() > 2) {
					if (ambientes > 0) {	
						//if (id_propietario == propietario.getId_propietario()) {

							publicacion.setPrecio(precio);
							publicacion.setZona(zona);
							publicacion.setDireccion(direccion);
							publicacion.setAmbientes(ambientes);
							publicacion.setEstado(estado);			
			
							
							salir=true;
							return propietario.crearPublicacion(publicacion, id_propietario);
						//} else {
						//	id_propietario = Integer.parseInt(JOptionPane.showInputDialog("Error, su id de propietario no es correcto"));
						//}

					} else {
						ambientes = Integer.parseInt(JOptionPane.showInputDialog("Error debe ingresar al menos 1 ambiente"));
					}
				} else {
					direccion = JOptionPane.showInputDialog("Error la direccion debe tener mas de 2 caracteres");
				}
			} else {
				zona = JOptionPane.showInputDialog("Error la zona debe tener mas de 2 caracteres");
			}
		} else {
			precio = Integer.parseInt(JOptionPane.showInputDialog("Error, el precio debe ser mayor a $1000"));
		}
		
		} while (!salir);
		
		return false;

	}
	
	
	public boolean verificaEliminarPublicacion(Publicacion publicacion) {

		return propietario.eliminarPublicacion(publicacion);

	}
	


	


}

/*
 * public boolean verificarReserva( String zona, String direccion, int
 * ambientes, String caracteristicas, Cliente cliente) { boolean salir=false;
 * 
 * do {
 * 
 * if (zona.length()<30) { if (direccion.length()<30) {
 * 
 * if (ambientes >= 1 && ambientes <= 10) {
 * 
 * if (caracteristicas.length() < 300) {
 * 
 * nuevareserva.setZona(zona); nuevareserva.setDireccion(direccion);
 * nuevareserva.setAmbientes(ambientes);
 * nuevareserva.setCaracteristicas(caracteristicas); return
 * cliente.guardarReserva(nuevareserva);
 * 
 * 
 * } else { caracteristicas =
 * JOptionPane.showInputDialog("Error al ingresar las caracteristicas." +
 * "\ndebe tener menos de 300 caracteres."); }
 * 
 * } else { ambientes = Integer.parseInt(JOptionPane.
 * showInputDialog("solo se pueden ingresar numeros numeros del 1 al 10 para indicar el total de ambientes."
 * )); }
 * 
 * } else { direccion=
 * JOptionPane.showInputDialog("Error al ingresar la direccion." +
 * "\ndebe tener menos de 30 caracteres."); } } else { zona =
 * JOptionPane.showInputDialog("Error al ingresar la zona." +
 * "\ndebe tener menos de 30 caracteres.");
 * 
 * }
 * 
 * 
 * } while (!salir); return false; }
 */
