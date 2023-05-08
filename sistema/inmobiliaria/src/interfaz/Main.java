package interfaz;

import java.util.LinkedList;

import javax.swing.JOptionPane;
import datos.Administrador;
import datos.Cliente;
import datos.Propietario;
import datos.Publicacion;
import datos.Resena;
import datos.Usuario;

public class Main {

	public static void main(String[] args) {
		//ESS ESTEEE
		LinkedList<Usuario> listaUsuarios = new LinkedList<Usuario>();
		LinkedList<Administrador> listaAdministrador = new LinkedList<Administrador>();
		LinkedList<Propietario> listaPropietario = new LinkedList<Propietario>();
		LinkedList<Cliente> listaCliente = new LinkedList<Cliente>();
		LinkedList<Publicacion> listaPublicacion = new LinkedList<Publicacion>();
		LinkedList<Resena> listaResena = new LinkedList<Resena>();
		
		listaUsuarios.add(new Administrador("Pedro", "Gutierrez", "20319002", "pedro", "4321", true, 0, "10"));
		listaUsuarios.add(new Administrador("Martin", "oso", "20319002", "martin", "54321", true, 0, "11"));
		
		listaUsuarios.add(new Propietario("Lucas", "Forni", "43400500", "lucas", "1234", true, 1, "25"));
		listaUsuarios.add(new Propietario("Gonzalo", "Fernandez", "43400500", "gonzalo.fernandez@gmail.com", "1234",true, 1, "24"));

		listaUsuarios.add(new Cliente("Fabricio", "Acosta", "42300400", "fabricio", "1234", true, 2, "50"));
		listaUsuarios.add(new Cliente("Gonzalo", "Ledovich", "30435106", "gonzalo", "12345", true, 2, "51"));
		
		
		listaAdministrador.add(new Administrador("Pedro", "Gutierrez", "20319002", "pedro", "4321", true, 0, "10"));
		listaAdministrador.add(new Administrador("Martin", "oso", "20319002", "martin", "54321", true, 0, "11"));
		// Administrador primerAdmin = listaAdministrador.get(0);

		listaPropietario.add(new Propietario("Lucas", "Forni", "43400500", "lucas", "123456", true, 1, "25"));
		listaPropietario.add(new Propietario("Gonzalo", "Fernandez", "43400500", "gonzalo.fernandez@gmail.com", "1234",true, 1, "24"));
		
		listaCliente.add(new Cliente("Fabricio", "Acosta", "42300400", "fabricio", "1234", true, 2, "50"));
		listaCliente.add(new Cliente("Gonzalo", "Ledovich", "30435106", "gonzalo", "12345", true, 2, "51"));

		
		// Propietario primerPropietario = listaPropietario.get(0);

		listaPublicacion.add(new Publicacion(1, true, false, 30000, "Balvanera", "av.corriente 2037", 2, "Casa", "Disponible"));
		listaPublicacion.add(new Publicacion(2, false, true, 20000, "Mar del Plata", "Rio plata 3232", 4,"Departamento", "Disponible"));

		
		boolean iniciarSesion= false;
		
		do {
			int bienvenida;
		
			bienvenida=Integer.parseInt(JOptionPane.showInputDialog("Bienvenido, que desea hacer? "
					+ "\n1. Iniciar sesion \n0. Salir del sistema"));
		
			switch (bienvenida) {
			case 1: // SISTEMA
				String email = JOptionPane.showInputDialog("Ingrese mail");
				String clave = JOptionPane.showInputDialog("Ingresar clave");
								
				boolean usuarioEncontrado = false;
				
				for (Usuario usuario : listaUsuarios) {
					
					
					if (usuario.getEmail().equals(email) && usuario.getClave().equals(clave)) {
						usuarioEncontrado = true;
						
						
						switch (usuario.getRol()) {
						
						case 0: // INGRESO COMO ADMINISTRADOR
							
							//JOptionPane.showMessageDialog(null, "El rol que ingreso es un ADMIN");
							
							
							if (usuario.isDisponible() == true) {
								//JOptionPane.showMessageDialog(null, "El admin esta activo");
								
								JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getNombre() + " " + usuario.getApellido());
								
								boolean salirAdmin = false;
								
								do {
									
									String opcionAdmin;
									opcionAdmin = JOptionPane.showInputDialog("Elegir que operacion desea realizar: "
											+ "\n1. Eliminar cliente" + "\n2. Eliminar Propietario" + "\n0. Cerrar sesion");

									switch (opcionAdmin) {
									case "1":
										// Eliminar cliente
										String IdCliente = JOptionPane
												.showInputDialog("Ingrese el ID del cliente que desea eliminar");
										boolean idClienteEncontrado = false;
										for (Cliente cliente : listaCliente) {
											if (cliente.getId_cliente().equals(IdCliente)) {
												idClienteEncontrado = true;
												for (Administrador administrador : listaAdministrador) {
													// Consulta para el profe - Cuando me devuelve el mensaje, quiero que me
													// diga que admin fue el que lo elimino. con el foreach me mustra el admin
													// de la posicion 0

													administrador.eliminarCliente(cliente, listaUsuarios, listaCliente);
													break; // para que no repita
												}

												break;
											}

										}

										if (!idClienteEncontrado) {
											JOptionPane.showMessageDialog(null,
													"El id que ingreso no pertenece a un cliente o no existe");
										}

										break;
									case "2": // Eliminar propietario
										String IdPropietario = JOptionPane
												.showInputDialog("Ingrese el ID del propietario que desea eliminar");
										boolean idPropietarioEncontrado = false;
										for (Propietario propietario : listaPropietario) {
											if (propietario.getId_propietario().equals(IdPropietario)) {
												idPropietarioEncontrado = true;
												for (Administrador administrador : listaAdministrador) {
													administrador.eliminarPropietario(propietario, listaUsuarios,
															listaPropietario);
													break; // para que no recorra todo el foreach
												}

												break;
											}
										}
										if (!idPropietarioEncontrado) {
											JOptionPane.showMessageDialog(null,
													"El id que ingreso no pertenece a un propietario o no existe");
										}

										break;
									case "0": // Cerrar sesion
										JOptionPane.showMessageDialog(null, "Se cerro su sesion");
										salirAdmin = true;
										break;
									}
								} while (!salirAdmin);

								// termina
								
							} else {
								JOptionPane.showMessageDialog(null, "El admin no esta activo");
							}

							break;

						case 1: // INGRESO COMO PROPIETARIO
							
							//JOptionPane.showMessageDialog(null, "El rol que ingreso es un PROPIETARIO");
							
							if (usuario.isDisponible()) {
								//JOptionPane.showMessageDialog(null, "El propietario  esta disponible");
								
								JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getNombre() + " " + usuario.getApellido());
								
								boolean salirPropietario = false;
								
								do {
									String opcionPropietario;
									opcionPropietario = JOptionPane.showInputDialog("Elegir que operacion desea realizar: "
											+ "\n1. Crear publicacion" + "\n2. Editar publicacion" + "\n3. Eliminar Publicacion" + "\n0. Cerrar sesion");

									switch (opcionPropietario) {
									case "1": // Crear publicacion

										for (Propietario propietario : listaPropietario) {

											for (Publicacion publicacion : listaPublicacion) {

												propietario.agregarPublicacion(publicacion, listaPublicacion);
												
												break;
											}

											break;

										}

										break;

									case "2": // editar publicacion
										int IdPropiedadEditar = Integer.parseInt(JOptionPane
												.showInputDialog("Ingrese el ID de la publicacion que desea editar"));
										boolean idPublicacionEncontrado = false;

										for (Propietario propietario : listaPropietario) {
											for (Publicacion publicacion : listaPublicacion) {
												if (publicacion.getId_publicacion() == IdPropiedadEditar) {
													idPublicacionEncontrado = true;
													propietario.editarPublicacion(publicacion, listaPublicacion);
												}
												break;
											}
											break;
										}

										if (!idPublicacionEncontrado) {
											JOptionPane.showMessageDialog(null, "El id de la publicacion es incorrecto");
										}

										break;

									case "3": // eliminar publicacion
										int IdPropiedadEliminar = Integer.parseInt(JOptionPane
												.showInputDialog("Ingrese el ID de la publicacion que desea eliminar"));

										idPublicacionEncontrado = false;
										for (Propietario propietario : listaPropietario) {
											for (Publicacion publicacion : listaPublicacion) {
												if (publicacion.getId_publicacion() == IdPropiedadEliminar) {
													idPublicacionEncontrado = true;
													propietario.eliminarPublicacion(publicacion, listaPublicacion);
												}
												break;
											}
											break;
										}

										if (!idPublicacionEncontrado) {
											JOptionPane.showMessageDialog(null, "El id de la publicacion es incorrecto");
										}

										break;
										
									case "0": // Cerrar sesion
										JOptionPane.showMessageDialog(null, "Se cerro su sesion");
										salirPropietario = true;
										break;
									}

								} while (!salirPropietario);

							} else {
								JOptionPane.showMessageDialog(null, "El propietario no puede realizar publicaciones");
							}

							break;
							
							
							
							
							
						case 2: // INGRESO COMO CLIENTE
							
							boolean salirCliente = false;
							
							JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getNombre() + " " + usuario.getApellido());
							do {
								int opcionCliente;

								opcionCliente = Integer.parseInt(JOptionPane.showInputDialog("Elija que desea realizar" + "\n1. Ver publicaciones" + "\n2. Crear reserva"
												+ "\n3. Crear rese�a" + "\n4. Ver ultimas rese�as creadas" + "\n0. Cerrar sesion"));

								switch (opcionCliente) {
								case 1:

									for (Cliente cliente : listaCliente) {

										cliente.verPublicaciones(listaPublicacion);

										break;
									}

									break;
								case 2:

									for (Cliente cliente : listaCliente) {

										for (Publicacion publicacion : listaPublicacion) {

											cliente.crearReserva(listaPublicacion, publicacion);
										}

										break;
									}

								case 3:
									for (Cliente cliente : listaCliente) {

										cliente.realizarResena(listaResena, cliente);

										break;
									}

									break;

								case 4:
									for (Cliente cliente : listaCliente) {

										cliente.verResenas(listaResena);

										break;
									}

									break;
									
								case 0: // Cerrar sesion
									JOptionPane.showMessageDialog(null, "Se cerro su sesion");
									salirCliente = true;
									break;
								}

							} while (!salirCliente);

							break;
						default:
							JOptionPane.showMessageDialog(null, "Rol desconocido");
							break; // esto lo puse para saber si por equivocado ponemos un numero de rol equivocado
									// :)
						}
						break; // Esto detiene el bucle for una vez que se encuentra un usuario existente.
					}
				}

				if (!usuarioEncontrado) {
					JOptionPane.showMessageDialog(null, "El email o la clave es incorrecta");
				}
				break;
				
			case 0: //CERRAR SISTEMA
				JOptionPane.showMessageDialog(null, "Salio del sistema");
				iniciarSesion = true;
				break;

			default:
				JOptionPane.showMessageDialog(null, "La opcion no es valida");
				break;
			}
			
		
		
		} while (!iniciarSesion);
	}

}
