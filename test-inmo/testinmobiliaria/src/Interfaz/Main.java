package Interfaz;

import Logica.Verifica;

import java.util.Arrays;

import javax.swing.JOptionPane;

import Datos.Cliente;
import Datos.Propietario;
import Datos.Publicacion;
import Datos.Resena;
import Datos.Usuario;

public class Main {

	public static void main(String[] args) {

		Verifica verifica = new Verifica();

		String bienvenida;

		do {

			String[] opciones = { "Iniciar Sesion", "Crear Usuario", "Salir del sistema" };
			bienvenida = (String) JOptionPane.showInputDialog(null, "Opciones de menu", "Bienvenido a HouseHunters",
					JOptionPane.DEFAULT_OPTION, null, opciones, opciones[0]);

			switch (bienvenida) {
			case "Iniciar Sesion": // SISTEMA

				if (verifica.verificarLista().isEmpty()) {
					JOptionPane.showMessageDialog(null, "La lista se encuentra vacia");
				} else {
					boolean validacionLogin = false;
					Usuario usuarioLogueado = null;

					do {
						String email = JOptionPane.showInputDialog("Ingrese mail");
						String clave = JOptionPane.showInputDialog("Ingresar clave");

						for (Usuario usuario : verifica.verificarLista()) { // Recorre verificarLista
							if (usuario.getEmail().equals(email) && usuario.getClave().equals(clave)) {
								validacionLogin = true;
								usuarioLogueado = usuario;
								System.out.println(usuarioLogueado);
							}
						}

						if (!validacionLogin) {
							JOptionPane.showMessageDialog(null,
									"El email o la clave es incorrecta, ingrese nuevamente");
						}

					} while (!validacionLogin);

					String rol = null; // Verifica el rol

					if (usuarioLogueado.getRol() == 1) {
						rol = "Administrador";
					} else if (usuarioLogueado.getRol() == 2) {
						rol = "Propietario";
					} else if (usuarioLogueado.getRol() == 3) {
						rol = "Cliente";
					}

					JOptionPane.showMessageDialog(null,
							"Ha ingresado correctamente" + "\nID: " + usuarioLogueado.getId_usuario() + "\nNombre: "
									+ usuarioLogueado.getNombre() + " " + usuarioLogueado.getApellido() + "\nRol: "
									+ rol);

					switch (usuarioLogueado.getRol()) {

					case 1: // INGRESA COMO Admin

						JOptionPane.showMessageDialog(null, "Ingreso el administrador: " + usuarioLogueado.getNombre());

						boolean salirAdmin = false;

						do {

							String[] panelAdmin = { "Ver usuario", "Editar usuario", "Eliminar usuario",
									"Cerrar sesion" };

							String opcionesAdmin = (String) JOptionPane.showInputDialog(null, "Opciones administrador",
									" Panel administrador ", JOptionPane.DEFAULT_OPTION, null, panelAdmin,
									panelAdmin[0]);

							switch (opcionesAdmin) {

							case "Ver usuario": // VISUALIZAR USUARIO ADMIN

								boolean salirVisualizar = false;

								do {
									// Recorre lista de publicaciones
									String[] opcionesVerUsuarios = new String[verifica.verificarLista().size()];

									for (int i = 0; i < verifica.verificarLista().size(); i++) {
										opcionesVerUsuarios[i] = String
												.valueOf(verifica.verificarLista().get(i).getId_usuario());
									}
									
									// Agrega la opción "salir"
									opcionesVerUsuarios = Arrays.copyOf(opcionesVerUsuarios,
											opcionesVerUsuarios.length + 1);
									opcionesVerUsuarios[opcionesVerUsuarios.length - 1] = "Salir";

									String uSeleccionado = (String) JOptionPane.showInputDialog(null, "Usuarios", " ",
											JOptionPane.DEFAULT_OPTION, null, opcionesVerUsuarios,
											opcionesVerUsuarios[0]);

									// Obtener el usuario seleccionado por su ID
									Usuario usuarioSeleccionado = null;

									for (Usuario usuario : verifica.verificarLista()) {
										if (String.valueOf(usuario.getId_usuario()).equals(uSeleccionado)) {
											usuarioSeleccionado = usuario;
											break;
										}
									}

									if (uSeleccionado.equals("Salir")) {
										salirVisualizar = true; // Establecer la variable para salir del bucle
									} else {

										if (usuarioSeleccionado != null) {
											JOptionPane.showMessageDialog(null, usuarioSeleccionado);
											salirVisualizar = true;
										} else {
											JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún usuario");
										}
										
										// Pregunta si desea continuar visualizando usuarios
										int opcionContinuar = JOptionPane.showConfirmDialog(null,
												"¿Desea continuar visualizando usuarios?", "Continuar",
												JOptionPane.YES_NO_OPTION);
										salirVisualizar = (opcionContinuar == JOptionPane.NO_OPTION);

									}

								} while (!salirVisualizar);

								break;

							case "Editar usuario": // EDITAR USUARIO ADMIN

								boolean salirEditar = false;

								do {

									String[] opcionesVerUsuarios = new String[verifica.verificarLista().size()];

									for (int i = 0; i < verifica.verificarLista().size(); i++) {
										opcionesVerUsuarios[i] = String
												.valueOf(verifica.verificarLista().get(i).getId_usuario());
									}

									opcionesVerUsuarios = Arrays.copyOf(opcionesVerUsuarios,
											opcionesVerUsuarios.length + 1);
									opcionesVerUsuarios[opcionesVerUsuarios.length - 1] = "Salir";

									String uSeleccionado = (String) JOptionPane.showInputDialog(null, "Usuarios", " ",
											JOptionPane.DEFAULT_OPTION, null, opcionesVerUsuarios,
											opcionesVerUsuarios[0]);

									if (uSeleccionado.equals("Salir")) {
										salirEditar = true;
									} else {
										// salirEditar = false;
										for (Usuario usuario : verifica.verificarLista()) {
											Usuario buscado = usuario;
											if (buscado.getId_usuario() == Integer.parseInt(uSeleccionado)) {
												salirEditar = true;
												verifica.editarUsuario(Integer.parseInt(uSeleccionado));
											}

										}
										int opcionContinuar = JOptionPane.showConfirmDialog(null,
												"¿Desea continuar editando?", "Continuar", JOptionPane.YES_NO_OPTION);
										salirEditar = (opcionContinuar == JOptionPane.NO_OPTION);
									}

								} while (!salirEditar);

								break;

							case "Eliminar usuario": // ELIMINAR USUARIO ADMIN

								boolean salirEliminar = true;

								do {

									if (verifica.verificarLista().isEmpty()) {
										JOptionPane.showMessageDialog(null,
												"No se puede eliminar usuarios porque la lista esta vacia");
										salirEliminar = true;
									} else {

										String[] opcionesEliminar = new String[verifica.verificarLista().size()];

										for (int i = 0; i < verifica.verificarLista().size(); i++) {
											opcionesEliminar[i] = String
													.valueOf(verifica.verificarLista().get(i).getId_usuario());

										}

										opcionesEliminar = Arrays.copyOf(opcionesEliminar, opcionesEliminar.length + 1);
										opcionesEliminar[opcionesEliminar.length - 1] = "Salir";

										String opcion = (String) JOptionPane.showInputDialog(null, "Usuarios", " ",
												JOptionPane.DEFAULT_OPTION, null, opcionesEliminar,
												opcionesEliminar[0]);

										// opcion es la que elije y despues se compara en
										// verifica.eliminarUsuario(opcion)

										Usuario usuarioE = null;

										for (Usuario usuario : verifica.verificarLista()) {
											if (String.valueOf(usuario.getId_usuario()).equals(opcion)) {
												usuarioE = usuario;

												if (opcion.equals("Salir")) {
													salirEliminar = true;
												} else {
													int opcionFinal = Integer.parseInt(opcion);

													int aceptar = JOptionPane.showConfirmDialog(null,
															"Desea continuar?\n" + usuarioE, "Confirmacion",
															JOptionPane.YES_NO_OPTION);
													if (aceptar == JOptionPane.YES_OPTION) {
														verifica.eliminarUsuario(opcionFinal);
														JOptionPane.showMessageDialog(null,
																"Se ha eliminado correctamente");

													} else if (aceptar == JOptionPane.NO_OPTION) {
														JOptionPane.showMessageDialog(null, "No se ha eliminado");
													}

													// Preguntar si desea continuar eliminando usuarios
													int opcionContinuar = JOptionPane.showConfirmDialog(null,
															"¿Desea continuar eliminando usuarios?", "Continuar",
															JOptionPane.YES_NO_OPTION);
													salirEliminar = (opcionContinuar == JOptionPane.NO_OPTION);

												}

												break;
											}
										}

									}

								} while (!salirEliminar);

								break;

							case "Cerrar sesion": // Cerrar sesion
								JOptionPane.showMessageDialog(null, "Se cerro su sesion");
								salirAdmin = true;
								break;

							}

						} while (!salirAdmin);

						break;

					case 2: // INGRESA COMO Propietario

						JOptionPane.showMessageDialog(null, "Ingreso el propietario: " + usuarioLogueado.getNombre());

						boolean salirPropietario = false;

						do {

							String[] opcionPropietario = { "Crear publicacion", "Ver publicacion",
									"Eliminar Publicacion", "Cerrar sesion" };

							String opcionP = (String) JOptionPane.showInputDialog(null, "Opciones", "",
									JOptionPane.DEFAULT_OPTION, null, opcionPropietario, opcionPropietario[0]);

							switch (opcionP) {
							case "Crear publicacion": // Crear publicacion

								if (verifica.listaPropietarios().isEmpty()) {
									JOptionPane.showMessageDialog(null,
											"No se puede eliminar usuarios porque la lista esta vacia");

								} else {

									for (Propietario propietario : verifica.listaPropietarios()) {
										if (propietario.getId_usuario() == usuarioLogueado.getId_usuario()) {

											String nombre = JOptionPane.showInputDialog("Nombre de publicacion");

											boolean temporal = false;
											boolean comprar = false;

											String[] pregunta = { "Alquiler", "Compra" };

											String opcionC = (String) JOptionPane.showInputDialog(null,
													"Propiedad dispobible para:", "", JOptionPane.DEFAULT_OPTION, null,
													pregunta, pregunta[0]);

											switch (opcionC) {
											case "Alquiler":
												temporal = true;
												comprar = false;
												break;
											case "Compra":
												temporal = false;
												comprar = true;
												break;

											}

											int precio = Integer
													.parseInt(JOptionPane.showInputDialog("Ingresar precio"));

											String zona = JOptionPane.showInputDialog("Ingresar zona");

											String direccion = JOptionPane
													.showInputDialog("Ingresar direccion de la propiedad");

											int ambientes = Integer.parseInt(
													JOptionPane.showInputDialog("Ingresa la cantidad de ambientes"));

											String estadoActual = "";

											String[] preguntaEstado = { "Disponible", "Ocupado" };

											String estado = (String) JOptionPane.showInputDialog(null,
													"Seleccione el estado de la publicacion", "",
													JOptionPane.DEFAULT_OPTION, null, preguntaEstado,
													preguntaEstado[0]);

											switch (estado) {
											case "Disponible":
												estadoActual = "Disponible";
												break;
											case "Ocupado":
												estadoActual = "Ocupado";
												break;
											}

											int id_propietario = propietario.getId_propietario();

											verifica.CrearPublicacion(nombre, precio, temporal, comprar, zona,
													direccion, ambientes, estadoActual, id_propietario);

										}

									}
								}

								break;

							case "Ver publicacion":

								boolean salirVerPublicacion = false;

								do {

									if (verifica.listaPublicacion().isEmpty()) {
										JOptionPane.showMessageDialog(null, "No hay publicaciones para mostrar");
										salirVerPublicacion = true;
									} else {
										String[] opcionesVerPublicacion = new String[verifica.listaPublicacion()
												.size()]; // Recorre lista de publicaciones

										for (int i = 0; i < verifica.listaPublicacion().size(); i++) {
											opcionesVerPublicacion[i] = verifica.listaPublicacion().get(i).getNombre();

										}

										opcionesVerPublicacion = Arrays.copyOf(opcionesVerPublicacion,
												opcionesVerPublicacion.length + 1);
										opcionesVerPublicacion[opcionesVerPublicacion.length - 1] = "Salir";

										String opcion = (String) JOptionPane.showInputDialog(null, "Opciones de menu",
												" ", JOptionPane.DEFAULT_OPTION, null, opcionesVerPublicacion,
												opcionesVerPublicacion[0]);

										if (opcion.equals("Salir")) {
											salirVerPublicacion = true; // Establecer la variable para salir del bucle
										} else {
											Publicacion verPublicacion = null;

											if (verifica.listaPublicacion().isEmpty()) {
												JOptionPane.showMessageDialog(null, "La lista se encuentra vacía");
											} else {
												for (Publicacion publicacion : verifica.listaPublicacion()) {
													if (publicacion.getNombre().equals(opcion)) {
														verPublicacion = publicacion;
														salirVerPublicacion = true;
														JOptionPane.showMessageDialog(null, verPublicacion);
														break;
													}
												}
											}
											// Preguntar si desea continuar viendo publicaciones
											int opcionContinuar = JOptionPane.showConfirmDialog(null,
													"¿Desea ver otras publicaciones?", "Continuar",
													JOptionPane.YES_NO_OPTION);
											salirVerPublicacion = (opcionContinuar == JOptionPane.NO_OPTION);

										}

									}

								} while (!salirVerPublicacion);

								break;

							case "Eliminar Publicacion": // eliminar publicacion

								boolean salirEliminar = false;

								do {

									if (verifica.listaPublicacion().isEmpty()) {
										JOptionPane.showMessageDialog(null, "La lista se encuentra vacía");
										salirEliminar = true;
									} else {

										// Recorre lista de publicaciones
										String[] opcionesEliminar = new String[verifica.listaPublicacion().size()];

										for (int i = 0; i < verifica.listaPublicacion().size(); i++) {
											opcionesEliminar[i] = String
													.valueOf(verifica.listaPublicacion().get(i).getId_publicacion());

										}

										// Añadir opción "salir"
										opcionesEliminar = Arrays.copyOf(opcionesEliminar, opcionesEliminar.length + 1);
										opcionesEliminar[opcionesEliminar.length - 1] = "Salir";
										

										String opcion = (String) JOptionPane.showInputDialog(null, "Publicaciones: ",
												" ", JOptionPane.DEFAULT_OPTION, null, opcionesEliminar,
												opcionesEliminar[0]);

										// opcion es la que elije y despues se compara en verifica.eliminarUsuario(opcion)

										if (opcion.equals("Salir")) {
											salirEliminar = true; // Establecer la variable para salir del bucle
										} else {

											for (Propietario propietario : verifica.listaPropietarios()) {
												System.out.println("ID DE PROPIETARIO EN PRIMER FOR "
														+ propietario.getId_propietario());
												for (Publicacion publicacion : verifica.listaPublicacion()) {
													System.out.println(
															"ID DE PROPIETARIO " + propietario.getId_propietario());
													System.out.println("ID DE PROPIETARIO EN PUBLICACION "
															+ publicacion.getId_propietario());
													if (propietario.getId_propietario() == publicacion
															.getId_propietario()
															&& verifica.eliminarPublicacion(opcion)) {

														JOptionPane.showMessageDialog(null, "Lo elimino exitosamente");

														salirEliminar = true;

													} else {
														JOptionPane.showMessageDialog(null, "No se pudo eliminar");
														salirEliminar = true;
													}
													break;
												}
												break;

											}

											// Preguntar si desea continuar visualizando usuarios
											int opcionContinuar = JOptionPane.showConfirmDialog(null,
													"¿Desea eliminar alguna publicacion?", "Continuar",
													JOptionPane.YES_NO_OPTION);
											salirEliminar = (opcionContinuar == JOptionPane.NO_OPTION);
										}
									}

								} while (!salirEliminar);

								break;

							case "Cerrar sesion": // Cerrar sesion
								JOptionPane.showMessageDialog(null, "Se cerro su sesion");
								salirPropietario = true;
								break;
							}

						} while (!salirPropietario);

						break;

					case 3: // INGRESA COMO Cliente

						boolean salirCliente = false;

						do {

							String[] opcionPropietario = { "Ver publicaciones", "Realizar transaccion",
									"Realizar reseña", "Ver reseñas", "Cerrar sesion" };

							String opcionC = (String) JOptionPane.showInputDialog(null, "Opciones", "",
									JOptionPane.DEFAULT_OPTION, null, opcionPropietario, opcionPropietario[0]);

							switch (opcionC) {
							case "Ver publicaciones":

								boolean salirVerPublicacion = false;

								do {

									if (verifica.listaPublicacion().isEmpty()) {
										JOptionPane.showMessageDialog(null, "No hay publicaciones para mostrar");
										salirVerPublicacion = true;
									} else {
										String[] opcionesVerPublicacion = new String[verifica.listaPublicacion()
												.size()]; // Recorre lista de publicaciones

										for (int i = 0; i < verifica.listaPublicacion().size(); i++) {
											opcionesVerPublicacion[i] = verifica.listaPublicacion().get(i).getNombre();

										}

										opcionesVerPublicacion = Arrays.copyOf(opcionesVerPublicacion,
												opcionesVerPublicacion.length + 1);
										opcionesVerPublicacion[opcionesVerPublicacion.length - 1] = "Salir";

										String opcion = (String) JOptionPane.showInputDialog(null, "Opciones de menu",
												" ", JOptionPane.DEFAULT_OPTION, null, opcionesVerPublicacion,
												opcionesVerPublicacion[0]);

										if (opcion.equals("Salir")) {
											salirVerPublicacion = true; // Establecer la variable para salir del bucle
										} else {
											Publicacion verPublicacion = null;

											if (verifica.listaPublicacion().isEmpty()) {
												JOptionPane.showMessageDialog(null, "La lista se encuentra vacía");
											} else {
												for (Publicacion publicacion : verifica.listaPublicacion()) {
													if (publicacion.getNombre().equals(opcion)) {
														verPublicacion = publicacion;
														salirVerPublicacion = true;
														JOptionPane.showMessageDialog(null, verPublicacion);
														break;
													}
												}
											}
											// Preguntar si desea continuar visualizando usuarios
											int opcionContinuar = JOptionPane.showConfirmDialog(null,
													"¿Desea ver otras publicaciones?", "Continuar",
													JOptionPane.YES_NO_OPTION);
											salirVerPublicacion = (opcionContinuar == JOptionPane.NO_OPTION);

										}

									}

								} while (!salirVerPublicacion);

								break;

							case "Realizar transaccion":

								boolean salirTransaccion = false;

								do {

									if (verifica.listaPublicacion().isEmpty()) {
										JOptionPane.showMessageDialog(null, "No hay publicaciones para mostrar");
										salirTransaccion = true;
									} else {
										String[] opcionesVerPublicacion = new String[verifica.listaPublicacion()
												.size()]; // Recorre lista de publicaciones

										for (int i = 0; i < verifica.listaPublicacion().size(); i++) {
											opcionesVerPublicacion[i] = verifica.listaPublicacion().get(i).getNombre();

										}
										// Añadir opción "salir"

										opcionesVerPublicacion = Arrays.copyOf(opcionesVerPublicacion,
												opcionesVerPublicacion.length + 1);
										opcionesVerPublicacion[opcionesVerPublicacion.length - 1] = "Salir";

										String opcion = (String) JOptionPane.showInputDialog(null, "Opciones de menu",
												" ", JOptionPane.DEFAULT_OPTION, null, opcionesVerPublicacion,
												opcionesVerPublicacion[0]);

										if (opcion.equals("Salir")) {
											salirTransaccion = true;
										} else {
											Publicacion verPublicacion = null;

											if (verifica.listaPublicacion().isEmpty()) {
												JOptionPane.showMessageDialog(null, "La lista se encuentra vacía");
											} else {
												for (Publicacion publicacion : verifica.listaPublicacion()) {
													for (Cliente cliente : verifica.listaCliente()) {

														if (publicacion.getNombre().equals(opcion)) {
															verPublicacion = publicacion;

															// ACA SE HACE LA TRANSACCION

															if (publicacion.getEstado()
																	.equalsIgnoreCase("Disponible")) {
																String nro_tarjeta = JOptionPane
																		.showInputDialog("Numero de la tarjeta");

																String clave_seguridad = JOptionPane.showInputDialog(
																		"Ingrese la clave de seguridad");

																String nombre_tarjeta = JOptionPane.showInputDialog(
																		"Ingrese nombre de la tarjeta");

																int clienteActual = cliente.getId_cliente();

																int PublicacionActual = publicacion.getId_publicacion();

																double precio = Double.parseDouble(JOptionPane
																		.showInputDialog("Ingresar monto a pagar: "
																				+ " $" + publicacion.getPrecio()));

																if (publicacion.getPrecio() == precio) {

																	verifica.crearTransaccion(nro_tarjeta,
																			clave_seguridad, nombre_tarjeta,
																			clienteActual, PublicacionActual, precio);

																	verifica.editarEstado(PublicacionActual);
																	JOptionPane.showMessageDialog(null,
																			"TRANSACCION REALIZADA!");

																	salirTransaccion = true;

																} else {
																	JOptionPane.showMessageDialog(null,
																			"La transaccion no se pudo realizar!");
																	salirTransaccion = false;

																}
															} else {
																JOptionPane.showMessageDialog(null,
																		"La publicacion ya esta reservada, no se puede realizar la transaccion");
																salirTransaccion = false;
															}

															JOptionPane.showMessageDialog(null, verPublicacion);
															break;
														}

													}

												}
											}
											// Preguntar si desea continuar viendo publicaciones
											int opcionContinuar = JOptionPane.showConfirmDialog(null,
													"¿Desea ver otras publicaciones?", "Continuar",
													JOptionPane.YES_NO_OPTION);
											salirTransaccion = (opcionContinuar == JOptionPane.NO_OPTION);

										}

									}

								} while (!salirTransaccion);

								break;

							case "Realizar reseña":

								String comentario = JOptionPane
										.showInputDialog("Ingrese un comentario sobre la publicacion");

								String[] estrellas = { "1", "2", "3", "4", "5" };

								String opcionestrellas = (String) JOptionPane.showInputDialog(null,
										"¡Nos encantaría que compartieras tu experiencia con nosotros dejando una reseña! ",
										"", JOptionPane.DEFAULT_OPTION, null, estrellas, estrellas[0]);

								int clienter_id = Integer
										.parseInt(JOptionPane.showInputDialog("Ingrese su id de cliente"));

								int publicacionr_id = Integer
										.parseInt(JOptionPane.showInputDialog("ingrese el id de la publicacion"));

								verifica.crearResena(comentario, opcionestrellas, clienter_id, publicacionr_id);

								JOptionPane.showMessageDialog(null, "Reseña realizada con exito!");

								break;

							case "Ver reseñas":

								boolean salirVerReseña = false;

								do {

									if (verifica.listaResena().isEmpty()) {
										JOptionPane.showMessageDialog(null, "No hay reseñas para mostrar");
										salirVerReseña = true;
									} else {
										String[] opcionesVerReseñas = new String[verifica.listaResena().size()];

										for (int i = 0; i < verifica.listaResena().size(); i++) {
											opcionesVerReseñas[i] = verifica.listaResena().get(i).getEstrellas();

										}

										opcionesVerReseñas = Arrays.copyOf(opcionesVerReseñas,
												opcionesVerReseñas.length + 1);
										opcionesVerReseñas[opcionesVerReseñas.length - 1] = "Salir";

										String opcion = (String) JOptionPane.showInputDialog(null,
												"Cantidad de estrellas", " ", JOptionPane.DEFAULT_OPTION, null,
												opcionesVerReseñas, opcionesVerReseñas[0]);

										if (opcion.equals("Salir")) {
											salirVerReseña = true;
										} else {
											Resena verReseñas = null;

											if (verifica.listaResena().isEmpty()) {
												JOptionPane.showMessageDialog(null, "La lista se encuentra vacía");
											} else {
												for (Resena resena : verifica.listaResena()) {
													if (resena.getEstrellas().equals(opcion)) {
														verReseñas = resena;

														salirVerReseña = true;
														JOptionPane.showMessageDialog(null, verReseñas);
														break;
													}
												}
											}

											// Preguntar si desea continuar visualizando reseñas
											int opcionContinuar = JOptionPane.showConfirmDialog(null,
													"¿Desea ver otras reseñas?", "Continuar",
													JOptionPane.YES_NO_OPTION);
											salirVerReseña = (opcionContinuar == JOptionPane.NO_OPTION);

										}

									}

								} while (!salirVerReseña);

								break;

							case "Cerrar sesion": // Cerrar sesion
								JOptionPane.showMessageDialog(null, "Se cerro su sesion");
								salirCliente = true;
								break;
							}

						} while (!salirCliente);

					}

				}

				break;

			case "Crear Usuario": // CREAR USUARIO
				String crearNombre = JOptionPane.showInputDialog("Ingresar el nombre");
				String crearApellido = JOptionPane.showInputDialog("Ingresar el Apellido");
				String crearDni = JOptionPane.showInputDialog("Ingresar Dni ");
				String crearEmail = JOptionPane.showInputDialog("Ingresar  email");
				String crearClave = JOptionPane.showInputDialog("Ingresar su clave");
				int crearRol = Integer.parseInt(
						JOptionPane.showInputDialog("Ingresar su rol: " + "\n1-Admin 2-propietario 3-Cliente"));
				verifica.crearUsuario(crearNombre, crearApellido, crearDni, crearEmail, crearClave, crearRol);

				break;
			}

		} while (!bienvenida.equals("Salir del sistema"));

	}

}
