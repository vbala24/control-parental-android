package com.example.controlparental;

import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.content.res.Resources.Theme;
import android.util.Log;

public class MiHiloConn extends Thread {
	 
	private String namespace;
	private String metodo;
	private String url;
	private String accionSoap;
	
	private ArrayList<Integer> paramArray1;
	private String nombreParamArray1;
	
	private ArrayList<Integer> paramArray2;
	private String nombreParamArray2;
	
	private int paramInt;
	private String nombreParamInt;
	
	private String paramString1;
	private String nombreParamString1;
	
	private String paramString2;
	private String nombreParamString2;
	
	private String paramString3;
	private String nombreParamString3;
	
	private String paramString4;
	private String nombreParamString4;
	
	private String nombreparametro;
	private String valorparametro;
	private ArrayList<Integer> idEstudiante;
	
	private String parametroAsistencia;
	private  String respuesta="";
	private String parametros;
	ArrayList<HeaderProperty> headerPropertyArrayList = new ArrayList<HeaderProperty>();
	SoapObject request;
	
	//Constructor para funciones que requieran paramestros String
	public MiHiloConn (String metodo,  String accionSoap,
			String nombreParametro, String valorparametro)
	{
		this.metodo = metodo;
		this.namespace = "http://service/";
		this.accionSoap = accionSoap;
		
		this.paramString1 = valorparametro;
		this.nombreParamString1 = nombreParametro;
		
		this.nombreParamArray1="";
		this.nombreParamArray2="";
		this.nombreParamInt="";
		this.nombreParamString2="";
		this.nombreParamString3="";
		this.nombreParamString4="";
		
		
		//this.nombreparametro = nombreparametro;
		//this.valorparametro = valorparametro;
		this.url = "http://10.0.2.2:8080/WSBasicServer/WSServer?wsdl";
		headerPropertyArrayList.add(new HeaderProperty("Connection", "close"));
		idEstudiante = new ArrayList<Integer>();
	}
	//Constructor para funciones necesiten enviar como parametro un ArrayList()
	public MiHiloConn (String metodo, String accionSoap, String nombreParametro, ArrayList<Integer> paramArray)
	{
		//string parametros = "1*123&2*fabian";
				
		this.metodo = metodo;
		this.accionSoap = accionSoap;
		this.namespace = "http://service/";
		this.url = "http://10.0.2.2:8080/WSBasicServer/WSServer?wsdl";
		headerPropertyArrayList.add(new HeaderProperty("Connection", "close"));
		this.paramArray1 = paramArray;
		this.nombreParamArray1 = nombreParametro;
		
		this.nombreParamString1="";
		this.nombreParamArray2="";
		this.nombreParamInt="";
		this.nombreParamString2="";
		this.nombreParamString3="";
		this.nombreParamString4="";
	//	this.parametroAsistencia = "idEstudiantes";
	//	this.idEstudiante = idEstudiantes;
	//	this.nombreparametro="";
	}
	public MiHiloConn (String metodo, String accionSoap, String nombreParametro, int param)
	{
		this.metodo = metodo;
		this.accionSoap = accionSoap;
		this.namespace = "http://service/";
		this.url = "http://10.0.2.2:8080/WSBasicServer/WSServer?wsdl";
		headerPropertyArrayList.add(new HeaderProperty("Connection", "close"));
		this.nombreParamInt = "nombreParametro";
		this.paramInt = param;
		
		this.nombreParamArray1="";
		this.nombreParamArray2="";
		this.nombreParamString1="";
		this.nombreParamString2="";
		this.nombreParamString3="";
		this.nombreParamString4="";
	}
	public void setParametrosArray (String nombreArray, ArrayList <Integer> paramArray)
	{
		this.paramArray2= paramArray;
		this.nombreParamArray2=nombreArray;
	}
	public void setParametrosString (String nombreParam1, String valorParam1, String nombreParam2, String valorParam2)
	{
		this.nombreParamString2 = nombreParam1;
		this.paramString2 = valorParam1;
		
		this.nombreParamString3 = nombreParam2;
		this.paramString3 = valorParam2;
	}
	public void setParametrosAsistencia (ArrayList <Integer> idestudiantes)
	{
		this.paramArray1= idestudiantes;
		this.nombreParamArray1="idEstudiantes";
	}
	
	
	public void setParametrosAddTarea ( String descripTarea, String tipoTarea, String fecha)
	{
		this.paramString2= descripTarea;
		this.nombreParamString2 = "descripcionTarea";
		
		this.paramString3= tipoTarea;
		this.nombreParamString3 = "tipoTarea";
		
		this.paramString4 = fecha;
		this.nombreParamString4="fechadeEngtrega";
		
	}
	public void setParametrosAddNotificacion( String descripNotificacion, String tipoNotificacion)
	{
		this.paramString2= descripNotificacion;
		this.nombreParamString2 = "descripcionNotificacion";
		
		this.paramString3= tipoNotificacion;
		this.nombreParamString3 = "tipoNotificacion";
		
	}
	public void setParametrosFijarNota ( ArrayList<Integer> idEstudiantes, ArrayList<Integer>idNotas)
	{
		this.paramArray1=idEstudiantes;
		this.nombreParamArray1="idEstudiante";
		
		this.paramArray2 = idNotas;
		this.nombreParamArray2 = "idNotas";
		
	}
	@Override
	public void run() {
		respuesta = obtener_respuesta();
		//System.out.println("Respuesta:" + this.respuesta);
		// TODO Auto-generated method stub
		
	}
	public String obtener_respuesta()
	{
	try {
			SoapObject request = new SoapObject(namespace, metodo);
			
			if (this.nombreParamString1.equals("")==false)
				request.addProperty(this.nombreParamString1, this.paramString1);
			if (this.nombreParamString2.equals("")==false)
				request.addProperty(this.nombreParamString2, this.paramString2);
			if (this.nombreParamString3.equals("")==false)
				request.addProperty(this.nombreParamString3, this.paramString3);
			if (this.nombreParamString4.equals("")==false)
				request.addProperty(this.nombreParamString4, this.paramString4);
			if (this.nombreParamInt.equals("")==false)
				request.addProperty(this.nombreParamInt, this.nombreParamInt);
			if (this.nombreParamArray1.equals("")==false)
			{
				for (int i=0; i<paramArray1.size(); i++)
		    	{
		    		request.addProperty(nombreParamArray1, paramArray1.get(i));
		    	}
			}
			if (this.nombreParamArray2.equals("")==false)
			{
				for (int i=0; i<paramArray2.size(); i++)
		    	{
		    		request.addProperty(nombreParamArray2, paramArray2.get(i));
		    	}
			}
			
		    	//request.addProperty(parametroAsistencia, idEstudiante).;
			SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		    
		  //sobre.XSD dotNet = true;
		    sobre.setOutputSoapObject(request);
		 
		    // Modelo el transporte
		    HttpTransportSE transporte = new HttpTransportSE(url);
		 
		    // Llamada
		    transporte.call(accionSoap, sobre,headerPropertyArrayList);
		    
		
		    // Resultado
		   // String resultado = (String) sobre.bodyIn;
		    SoapObject resultado = (SoapObject) sobre.bodyIn;
		 
		   Log.i("Resultado", resultado.toString());
		  //  Log.i("1 Curso", resultado.getProperty(0).toString());
		   // MostrarMensaje ("REsultado: "+ resultado.getAttributeAsString(0) );
		    
		    if(resultado.toString() != null){
		    	//MostrarMensaje("Conexion Exitosa!");
		    	System.out.println("Conexión Exitosa");
		    }
		    return (resultado.toString());
			} 
			catch (Exception e) {
				//MostrarMensaje("Error : "+e.toString());
				Log.i("mitag", e.toString());
				//System.out.println("Resultado: " + resultado.toString());
			}
			return null;
	}
	public String getRespuesta()
	{
		return this.respuesta;
	}
}
