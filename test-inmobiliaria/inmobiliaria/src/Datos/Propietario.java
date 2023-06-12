package Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import com.mysql.jdbc.Statement;

public class Propietario extends Usuario {

	private int id_propietario;
	
	Conexion con = new Conexion();
	Connection conexion = con.conectar();
	PreparedStatement stmt;

		public Propietario(String nombre, String apellido, String dni, String email, String clave, boolean disponible,
			int rol, int id_usuario, int id_propietario) {
		super(nombre, apellido, dni, email, clave, disponible, rol, id_usuario);
		this.id_propietario = id_propietario;
	}
		


		public int getId_propietario() {
			return id_propietario;
		}

		public void setId_propietario(int id_propietario) {
			this.id_propietario = id_propietario;
		}




		public boolean crearPublicacion(Publicacion publicacion, int id_propietario) {
		    String sql = "INSERT INTO publicacion (temporal, comprar, precio, zona, direccion, ambientes, caracteristicas, estado, id_propietario) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
		   
		    try {
		        //PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		        
		        stmt = conexion.prepareStatement(sql); //PREPARO LA CONEXION
		        stmt.setBoolean(1, publicacion.isTemporal());
		        stmt.setBoolean(2, publicacion.isComprar());
		        stmt.setLong(3, publicacion.getPrecio());
		        stmt.setString(4, publicacion.getZona());
		        stmt.setString(5, publicacion.getDireccion());
		        stmt.setLong(6, publicacion.getAmbientes());
		        stmt.setString(7, publicacion.getCaracteristicas());
		        stmt.setString(8, publicacion.getEstado());
		        stmt.setLong(9, id_propietario);
		        
		        System.out.println("ID PROPIETARIO " +id_propietario);
		        stmt.executeUpdate();
		        
		        JOptionPane.showMessageDialog(null, "Se creo la publicacion");
		        
		     
		        return true;
		    } catch (Exception e) {
		        System.out.println(e.getMessage());
		        return false;
		    }
		    
		    
		}
		
	
		
		
		
		
		   /*
        ResultSet generatedKeys = stmt.getGeneratedKeys();
        int idFK = 0;
        
        if (generatedKeys.next()) {
        	idFK = generatedKeys.getInt(1);
        }
       
       
            String propietarioSql = "INSERT INTO publicacion (id_propietario) VALUES (?)";
            PreparedStatement propietarioStmt = conexion.prepareStatement(propietarioSql);
            propietarioStmt.setLong(1, idFK);
            propietarioStmt.executeUpdate();
            
        */
        
		

	/*	//ESTA FUNCIONA PERO SUMA ID DE A 2
	public boolean crearPublicacion(Publicacion publicacion) {
	    String sql = "INSERT INTO publicacion (temporal, comprar, precio, zona, direccion, ambientes, caracteristicas, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	    try {
	        PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	        stmt.setBoolean(1, publicacion.isTemporal());
	        stmt.setBoolean(2, publicacion.isComprar());
	        stmt.setLong(3, publicacion.getPrecio());
	        stmt.setString(4, publicacion.getZona());
	        stmt.setString(5, publicacion.getDireccion());
	        stmt.setLong(6, publicacion.getAmbientes());
	        stmt.setString(7, publicacion.getCaracteristicas());
	        stmt.setString(8, publicacion.getEstado());
	        stmt.executeUpdate();

	        ResultSet generatedKeys = stmt.getGeneratedKeys();
	        int idPropietario = 0;

	        if (generatedKeys.next()) {
	            idPropietario = generatedKeys.getInt(1);
	        }
	        
	       
	        String propietarioSql = "INSERT INTO `publicacion` (`propietario_id`) VALUES (?)";
	        PreparedStatement propietarioStmt = conexion.prepareStatement(propietarioSql);
	        propietarioStmt.setInt(1, idPropietario);
	        propietarioStmt.executeUpdate();
	       

	        System.out.println("Se creó la publicación");
	        return true;
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        return false;
	    }
	}
 
	*/
	
	

	
	public boolean eliminarPublicacion(Publicacion publicacion) {

	    String sql = "DELETE FROM publicacion WHERE id_publicacion = ?";
	    try {

	        stmt = conexion.prepareStatement(sql); //PREPARO LA CONEXION

	        stmt.setLong(1, publicacion.getId_publicacion()); //Enteros se pasan como long

	        stmt.executeUpdate(); //EJECUTA LA CONSULTA
	        return true;
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, e.getMessage());
	        return false;
	    }
	}
	


}