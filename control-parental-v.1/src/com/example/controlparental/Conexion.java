package com.example.controlparental;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;




import android.util.Log;

public class Conexion  {
	
	private String Metodo;
	private String namespace;
	private String accionSoap;
	private  String url;
//	private String parametro1;
//	private String parametro2;
	Thread t;
	SoapObject resultado;
	
	
	public Conexion(String metodo, String namespace, String accionSoap,
			String url) {
		//super();
		//t = new Thread(this);
		Metodo = metodo;
		this.namespace = namespace;
		this.accionSoap = accionSoap;
		this.url = url;
	}
	
	/*public void run() {
		try {
			
			SoapObject request = new SoapObject(this.namespace, this.Metodo);
	        request.addProperty(this.parametro1, this.parametro2);
		    SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		    
		     
		    //sobre.XSD dotNet = true;
		    sobre.setOutputSoapObject(request);
		 
		    // Modelo el transporte
		    HttpTransportSE transporte = new HttpTransportSE(this.url);
		 
		    // Llamada
		    transporte.call(this.accionSoap, sobre);
		 
		    // Resultado
		    
		   resultado = (SoapObject) sobre.bodyIn;
		 
		   Log.i("Resultado", resultado.toString());
		  //  Log.i("1 Curso", resultado.getProperty(0).toString());
		   // MostrarMensaje ("REsultado: "+ resultado.getAttributeAsString(0) );
		    
		    if(resultado.toString() != null){
		    	//MostrarMensaje("Conexion Exitosa!");
		    	System.out.println("Conexión Exitosa");
		    	
		    }
		    
			} 
			catch (Exception e) {
				//MostrarMensaje("Error : "+e.toString());
				Log.i("mitag", e.toString());
				//System.out.println("Resultado: " + resultado.toString());
			}
		
	}*/

	
	
	
	
	/*public String fijarparametros (String parametro, String parametro2)
	{
		this.parametro1=parametro;
		this.parametro2= parametro2;
		t.run();
		return resultado.toString();
				
	}*/
	public SoapObject conectar (String parametro, String parametro2)
	{
	try {
		resultado = new SoapObject();	
		SoapObject request = new SoapObject(this.namespace, this.Metodo);
        request.addProperty(parametro, parametro2);
	    SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	     
	    //sobre.XSD dotNet = true;
	    sobre.setOutputSoapObject(request);
	 
	   
	    // Modelo el transporte
	    HttpTransportSE transporte = new HttpTransportSE(this.url);
	    
	 
	    // Llamada
	    transporte.call(this.accionSoap, sobre);
	 
	    // Resultado
	 //   transporte.reset();
	    resultado = (SoapObject) sobre.bodyIn;
	 
	   Log.i("Resultado", resultado.toString());
	  //  Log.i("1 Curso", resultado.getProperty(0).toString());
	   // MostrarMensaje ("REsultado: "+ resultado.getAttributeAsString(0) );
	    
	    if(resultado.toString() != null){
	    	//MostrarMensaje("Conexion Exitosa!");
	    	System.out.println("Conexión Exitosa");
	    }
	    else
	    	System.out.println("Conexion fallida");
	    
	    return (resultado);
		} 
		catch (Exception e) {
			//MostrarMensaje("Error : "+e.toString());
			Log.i("mitag", e.toString());
			//System.out.println("Resultado: " + resultado.toString());
		}
		return null;	
	}
	
}