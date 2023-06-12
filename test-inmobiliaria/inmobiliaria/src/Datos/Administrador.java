package Datos;

import java.sql.PreparedStatement;


public class Administrador extends Usuario {

	private String id_administrador;

	public Administrador(String nombre, String apellido, String dni, String email, String clave, boolean disponible,
			int rol, String id_administrador,int id) {
		super(nombre, apellido, dni, email, clave, disponible, rol,id);
		this.id_administrador = id_administrador;
	}
	
	public String getId_administrador() {
		return id_administrador;
	}

	public void setId_administrador(String id_administrador) {
		this.id_administrador = id_administrador;
	}

	
	public boolean eliminarUsuario(String nombre) {
	    try {
	        
	            // Eliminar propietario
	            String eliminarPropietarioSQL = "DELETE FROM propietario WHERE id_usuario = (SELECT id_usuario FROM usuario WHERE nombre = ?)";
	            PreparedStatement eliminarPropietarioStmt = conexion.prepareStatement(eliminarPropietarioSQL);
	            eliminarPropietarioStmt.setString(1, nombre);
	            eliminarPropietarioStmt.executeUpdate();
	            //System.out.println("Se eliminaron los registros de propietario para el usuario: " + nombre);
	            
	       
	        	// Eliminar cliente
	            String eliminarClienteSQL = "DELETE FROM cliente WHERE id_usuario = (SELECT id_usuario FROM usuario WHERE nombre = ?)";
	            PreparedStatement eliminarClienteStmt = conexion.prepareStatement(eliminarClienteSQL);
	            eliminarClienteStmt.setString(1, nombre);
	            eliminarClienteStmt.executeUpdate();
	            //System.out.println("Se eliminaron los registros de cliente para el usuario: " + nombre);
			
				 // Eliminar usuario
		        String eliminarUsuarioSQL = "DELETE FROM usuario WHERE nombre = ?";
		        PreparedStatement eliminarUsuarioStmt = conexion.prepareStatement(eliminarUsuarioSQL);
		        eliminarUsuarioStmt.setString(1, nombre);
		        eliminarUsuarioStmt.executeUpdate();
		        System.out.println("Se eliminó el usuario: " + nombre);
	        

	       

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

	
	
	public boolean EditarTodo(int id){
		 
		String sql = "	UPDATE `usuario` SET `nombre`=?,`apellido`=?,`dni`=?,`email`=?,`clave`=?,`rol`=? WHERE id_usuario = ? ";		
			try {
			      stmt = conexion.prepareStatement(sql);
		          stmt.setString(1, this.getNombre());
		          stmt.setString(2, this.getApellido());
		          stmt.setString(3, this.getDni());
		          stmt.setString(4, this.getEmail());
		          stmt.setString(5, this.getClave());
		          stmt.setInt(6, this.getRol());
		          stmt.setInt(7, id);
		          stmt.executeUpdate();
		          return true;			
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return false;				
			 }		
		}

	
	
	
	
	/*
	
	public boolean eliminarUsuario(String nombre) {
	    try {
	    	
	    		if (this.getRol() == 2) {
	    			
	    			String eliminarPropietarioSQL = "DELETE FROM propietario WHERE id_usuario IN (SELECT id_usuario FROM usuario WHERE nombre = ?)";
		 	        PreparedStatement eliminarPropietarioStmt = conexion.prepareStatement(eliminarPropietarioSQL);
		 	        eliminarPropietarioStmt.setString(1, nombre);
		 	        eliminarPropietarioStmt.executeUpdate();
		 	        System.out.println("Se eliminaron los registros de propietario para el usuario: " + nombre);
		 	        
					System.out.println("elimino un propietario");
				} else {
					String eliminarClienteSQL = "DELETE FROM cliente WHERE id_usuario IN (SELECT id_usuario FROM usuario WHERE nombre = ?)";
			        PreparedStatement eliminarClienteStmt = conexion.prepareStatement(eliminarClienteSQL);
			        eliminarClienteStmt.setString(1, nombre);
			        eliminarClienteStmt.executeUpdate();
			        System.out.println("Se eliminaron los registros de cliente para el usuario: " + nombre);
				}
	    		 
	    	
	    		
			
	    	
	        String eliminarUsuarioSQL = "DELETE FROM usuario WHERE nombre = ?";
	        PreparedStatement eliminarUsuarioStmt = conexion.prepareStatement(eliminarUsuarioSQL);
	        eliminarUsuarioStmt.setString(1, nombre);
	        eliminarUsuarioStmt.executeUpdate();
	        System.out.println("Se eliminó el usuario: " + nombre);

	        return true;
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	}

	*/
	
	
	
	
	
	
	
	/*
	public boolean eliminarUsuario(String nombre){
		 
		String propietarioSQL = "DELETE FROM propietario WHERE id_usuario IN (SELECT id_usuario FROM usuario WHERE nombre = ?)";		
		String clienteSQL = "DELETE FROM cliente WHERE id_usuario IN (SELECT id_usuario FROM usuario WHERE nombre = ?)";	
		
			try {
				
				if (this.getRol() == 2) {
					stmt = conexion.prepareStatement(propietarioSQL);
			          stmt.setString(1, nombre);
			          System.out.println("Elimine un propietario");
			          stmt.executeUpdate();

			          
				} else if (this.getRol() == 3) {
					stmt = conexion.prepareStatement(clienteSQL);
			          stmt.setString(1, nombre);
			          System.out.println("Elimine un cliente");
			          stmt.executeUpdate();
				} else {
					System.out.println("No se encontro ningun usuario");
				}
					
			      	     
	
		          String eliminarUsuarioSQL  = "DELETE FROM `usuario` WHERE nombre = ? ";
		            PreparedStatement eliminarUsuarioStmt  = conexion.prepareStatement(eliminarUsuarioSQL );
		            eliminarUsuarioStmt .setString(1, nombre);
		            eliminarUsuarioStmt .executeUpdate();    

		          
		          return true;			
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return false;				
			 }		
			}
			*/
	
	
	
	
	
	
	
}
