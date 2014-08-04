/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.RDBS;

/**
 *
 * @author Carlos Suárez
 */
@WebService(serviceName = "WSServer")
public class WSServer {
    
    String server = "";
    String database = "";
    String user = "";
    String pass = "";
    /**
     * Web service operation
     */
    @WebMethod(operationName = "setConnexion")
    public String setConnexion(@WebParam(name = "server") String server, @WebParam(name = "database") String database, @WebParam(name = "user") String user, @WebParam(name = "password") String password) {
        //TODO write your implementation code here:
       if(server.isEmpty() || database.isEmpty() || user.isEmpty() || server == null || database == null || user == null){
            return null;
        }
        RDBS rdbs = new RDBS(server,database,user,password);
        if(!rdbs.isSetConnexion()){
            return null;
        }
        
        this.server = server;
        this.database = database;
        this.user = user;
        this.pass = password;
        
        return "true";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getCursos")
    public String getCursos(@WebParam(name = "idDocente") String idDocente) {
        //TODO write your implementation code here:
        if(idDocente.isEmpty()){
            return null;
        }
        //RDBS rdbs = new RDBS(this.server,this.database,this.user,this.pass);
        RDBS rdbs = new RDBS("localhost","SCPA","root","");
        ArrayList<String> cursos =  rdbs.getCursos(idDocente);
        
        return cursos.toString();
    }
    @WebMethod(operationName = "getEstudiantes")
    public String getEstudiantes (@WebParam (name="idAsignatura") String idAsignatura)
    {
        if (idAsignatura.isEmpty())
            return null;
        RDBS rdbs = new RDBS("localhost","SCPA","root","");
        ArrayList<String> estudiantes =  rdbs.getEstudiantes(idAsignatura);
        return estudiantes.toString();
                
    }
    
    }
}
