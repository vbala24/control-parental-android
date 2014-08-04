/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Suárez
 * Descripcion: Esta clase se encarga de la conexion a la BD y de realizar las consultas sobre la misma
 */
public class RDBS {
    static public String database;
    
    public Connection cnn;

    // constructor que realiza la conexion al la base de datos.
    public RDBS(String server,String database,String user,String password){
        try {
            this.database = database;
            Class.forName("com.mysql.jdbc.Driver");
            String sql = "jdbc:mysql://"+server+"/"+database;
            this.cnn = DriverManager.getConnection(sql, user, password);
                                               
        } catch (SQLException ex) {
            Logger.getLogger(RDBS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RDBS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // Funcion para inspeccionar si la conexion a la BD se realizo
     public boolean isSetConnexion(){
        if(this.cnn == null){
            return false;
        }
        return true;
    }
     /**
      * Funcion que recibe un identificador de Docente y
      * retorna los cursos asociados
      * @param idDocente
      * @return Cursos
      */
     public ArrayList<String> getCursos(String idDocente){
         ArrayList<String> cursos = new ArrayList<String>();
          try {
            PreparedStatement smt = cnn.prepareStatement("SELECT ID_CURSO,NOMBRE_CURSO FROM CURSO WHERE `ID_DOCENTE` =  '"+idDocente+"'");
            //PreparedStatement smt = cnn.prepareStatement("SELECT ID_CURSO,NOMBRE_CURSO FROM CURSO WHERE 1");
            ResultSet resultado = smt.executeQuery();

            while(resultado.next()){
                String table = resultado.getString("NOMBRE_CURSO");
                cursos.add(table);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RDBS.class.getName()).log(Level.SEVERE, null, ex);
        } 
         return cursos;
     }
     public ArrayList <String> getEstudiantes (String idMateria)
     {
         PreparedStatement smt = cnn.prepareStatement("SELECT ")
     }
    
    
}
