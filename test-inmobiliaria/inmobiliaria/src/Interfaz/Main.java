

package Interfaz;

import Logica.Verifica;

import javax.swing.JOptionPane;

import Datos.Usuario;


public class Main {

	public static void main(String[] args) {

		Verifica verifica = new Verifica();
		
		String bienvenida;
		do {

			String[] bien = { "Iniciar Sesion", "Crear Usuario", "Salir del sistema" };
			bienvenida = (String) JOptionPane.showInputDialog(null, "Opciones de menu", "Bienvenido a HouseHunters",
					JOptionPane.DEFAULT_OPTION, null, bien, bien[0]);

			switch (bienvenida) {
			case "Iniciar Sesion": // SISTEMA

				if (verifica.verificarLista().isEmpty()) {
					JOptionPane.showMessageDialog(null, "La lista se encuentra vac�a");
				} else {
					boolean validacionLogin = false;
					Usuario usuarioLogueado = null;

					do {
						String email = JOptionPane.showInputDialog("Ingrese mail");
						String clave = JOptionPane.showInputDialog("Ingresar clave");
						for (Usuario usuario : verifica.verificarLista()) {
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
						String[] panelAdmin = { "Eliminar usuario", "Editar usuario", "Visualizar usuario",
								"Cerrar sesion" };
						String opcionesAdmin;
						do {
							opcionesAdmin = (String) JOptionPane.showInputDialog(null, "Opciones administrador",
									" Panel administrador ", JOptionPane.DEFAULT_OPTION, null, panelAdmin,
									panelAdmin[0]);
							switch (opcionesAdmin) {
							
							case "Eliminar usuario": // ELIMINAR USUARIO ADMIN

								JOptionPane.showMessageDialog(null, "Eliminar");
								if (verifica.verificarLista().isEmpty()) {
									JOptionPane.showMessageDialog(null,
											"No se puede eliminar usuarios porque la lista esta vacia");
								} else {

									String[] opcionesEliminar = new String[verifica.verificarLista().size()]; //Recorre lista de usuarios
									for (int i = 0; i < verifica.verificarLista().size(); i++) {
										opcionesEliminar[i] = verifica.verificarLista().get(i).getNombre(); 
										
										// PREGUNTAR AL PROFE COMO SE PUEDE HACER CON INT POR EL ID
									}
									
									
	
									String opcion = (String) JOptionPane.showInputDialog(null, "Opciones de menu", " ",
											JOptionPane.DEFAULT_OPTION, null, opcionesEliminar, opcionesEliminar[0]); 
									
									// opcion es la que elije y despues se compara en verifica.eliminarUsuario(opcion)
									
									// PREGUNTAR AL PROFE COMO SE PUEDE HACER CON INT POR EL ID
									
									
										if (verifica.eliminarUsuario(opcion)) {
											JOptionPane.showMessageDialog(null, "Lo elimino exitosamente");

										} else {
											JOptionPane.showMessageDialog(null, "No se pudo eliminar");
										}

								}
								
								
								break;
							case "Editar usuario":
								JOptionPane.showMessageDialog(null, "Editar"); // EDITAR USUARIO ADMIN
								boolean usuarioEncontrado;
								do {
									int id = Integer.parseInt(JOptionPane.showInputDialog("Escriba el ID que desea editar"));
									usuarioEncontrado = false;
									for (Usuario usuario : verifica.verificarLista()) {
										Usuario buscado = usuario;
										if (buscado.getId_usuario() == id) {
											usuarioEncontrado = true;
											verifica.editarUsuario(id);
										}
										;
									}
									;

									if (!usuarioEncontrado) {
										JOptionPane.showMessageDialog(null, "El id no existe en la base de datos");
									}

								} while (usuarioEncontrado == false);
								break;

							case "Visualizar usuario": // VISUALIZAR USUARIO ADMIN
								JOptionPane.showMessageDialog(null, "visualizar");

								boolean teEncontre;
								do {
									int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresar id: "));
									teEncontre = false;
									for (Usuario usuario : verifica.traerIndiv(id)) {
										Usuario buscado = usuario; // esto crea un objeto de la persona que ingresamos
																	// el id, y se puede usar get y set
										if (usuario.getId_usuario() == id) {
											teEncontre = true;

											JOptionPane.showMessageDialog(null,
													verifica.traerIndiv(id) + buscado.getNombre());
										}
										;
									}
									;
									if (!teEncontre) {
										JOptionPane.showMessageDialog(null, "El id no existe en la base de datos");
									}
								} while (teEncontre == false);

								break;

							}
							;

						} while (!opcionesAdmin.equals("Cerrar sesion"));

						

						break;

					case 2: // INGRESA COMO Propietario
						
						JOptionPane.showMessageDialog(null, "Ingreso el propietario: " + usuarioLogueado.getNombre());
						
						boolean salirPropietario = false;
						
						
						
						do {
							
							String[] opcionPropietario = { "Crear publicacion", "Editar publicacion",
									"Eliminar Publicacion", "Cerrar sesion" };

							String opcionP = (String) JOptionPane.showInputDialog(null, "Opciones", "",
									JOptionPane.DEFAULT_OPTION, null, opcionPropietario, opcionPropietario[0]);
							
							
							switch (opcionP) {
							case "Crear publicacion": // Crear publicacion
								
								boolean temporal = false;
								boolean comprar = false;
								
								String[] pregunta = { "Alquiler", "Compra"};

								String opcionC = (String) JOptionPane.showInputDialog(null, "Propiedad dispobible para:", "",
										JOptionPane.DEFAULT_OPTION, null, pregunta, pregunta[0]);
								
								switch (opcionC) {
								case "Alquiler":
									temporal = true;
									comprar= false;
									break;
								case "Compra":
									temporal = false;
									comprar= true;
									break;
									
								}
								

								int precio = Integer.parseInt(JOptionPane.showInputDialog("Ingresar precio"));
								
								String zona = JOptionPane.showInputDialog("Ingresar zona");
								
								String direccion = JOptionPane.showInputDialog("Ingresar direccion de la propiedad");
								
								int ambientes = Integer.parseInt(JOptionPane.showInputDialog("Ingresa la cantidad de ambientes"));
								
								String estadoActual ="";
								
								String[] preguntaEstado = { "Disponible", "Ocupado"};

								String estado = (String) JOptionPane.showInputDialog(null, "Seleccione el estado de la publicacion", "",
										JOptionPane.DEFAULT_OPTION, null, preguntaEstado, preguntaEstado[0]);

								switch (estado) {
								case "Disponible":
									estadoActual ="Disponible";
									break;
								case "Ocupado":
									estadoActual ="Ocupado";
									break;
								}
								
							
								int id_propietario = Integer.parseInt(JOptionPane.showInputDialog("Ingrese su id de propietario"));
								System.out.println(estadoActual);
								
								verifica.CrearPublicacion(precio,temporal,comprar,zona,direccion,ambientes,estadoActual, id_propietario);
								
								
								

								break;

							case "Editar publicacion": // editar publicacion
								
							

								break;

							case "Eliminar Publicacion": // eliminar publicacion
								

								break;
								
							case "Cerrar sesion": // Cerrar sesion
								JOptionPane.showMessageDialog(null, "Se cerro su sesion");
								salirPropietario = true;
								break;
							}

						} while (!salirPropietario);
						
						

						break;

					case 3: // INGRESA COMO Cliente

						break;

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
