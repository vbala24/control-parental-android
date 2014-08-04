package com.example.controlparental;

import java.util.ArrayList;

import org.ksoap2.serialization.SoapObject;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ListaActivity extends Activity {
	TextView txtasignatura;
	Conexion conn2;
	String respuestacursos="";
	SoapObject soapcursos2;
	MiHiloConn hilo;
	protected Handler h;
	final ArrayList<Integer> idestudiantes= new ArrayList<Integer>();
	ArrayList <Integer> listaDeEstIncumplidos = new ArrayList<Integer>();
	Bundle bundle;
	protected void onCreate ( Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);	
		final LinearLayout ly = (LinearLayout) findViewById(R.id.linllamarlista);
		
		 bundle = getIntent().getExtras();
		
		String listadodeestudiantes, nombreestudiante="";
		int start, end, tamanio, id=0;
		
		//hilo = new MiHiloConn(bundle.getString("idCurso").toString());
		hilo = new MiHiloConn("getEstudiantesbyMateria","http://service/getEstudiantesbyMateria","idMateria",bundle.getString("idCurso").toString());
		TextView txtasignatura = new TextView(this);
		TableLayout table = new  TableLayout(this);
		Button btnGuadarLista = new Button(this);
		table.setGravity(Gravity.CENTER);
		txtasignatura.setText(bundle.getString("nombreCurso"));
		txtasignatura.setGravity(Gravity.CENTER);
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		         LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		btnGuadarLista.setLayoutParams(params);
		btnGuadarLista.setText("Guardar/Enviar");
		//btnGuadarLista.layout(Gravity.CENTER);
		
		ly.addView(txtasignatura);
		ly.addView(table);
		ly.addView(btnGuadarLista);
		
		int cont = 0;
		hilo.start();
		while(respuestacursos == "" ){
			respuestacursos = hilo.getRespuesta();
			cont++;
			System.out.println("Contador: "+ cont);
		}
		hilo.interrupt();
		listadodeestudiantes = listaEstudiantes(respuestacursos);
		tamanio = listadodeestudiantes.length();
		System.out.println("Lista de Estudiantes: "+ listadodeestudiantes + " Tamaño: "+ tamanio );
		start =0; end = 0; 
		
		if (listadodeestudiantes.length()!=0)
		{
			boolean band = true;
			do{
				TableRow tablerow = new TableRow(this);
				if (cont==2)
				{
				tablerow.setBackgroundColor(Color.LTGRAY);
					cont=1;
				}
				else
				{
					tablerow.setBackgroundColor(Color.GRAY);
					cont=2;
				}	
				tablerow.setGravity(Gravity.CENTER);
				TextView txtestudiante = new TextView(this);
				txtestudiante.setGravity(Gravity.CENTER);
				final CheckBox checkestd = new CheckBox(this);
				end = listadodeestudiantes.indexOf(",",start);
			
				if (end != tamanio)
				{
					nombreestudiante = listadodeestudiantes.substring(start,end);
					start=end+1;
					end = listadodeestudiantes.indexOf(",",start);
					if (end==-1)
					{
						id = (Integer.parseInt((listadodeestudiantes.substring(start,tamanio)).replaceAll("[\\D]","")));
						band = false;
					}
					else
						id = Integer.parseInt((listadodeestudiantes.substring(start,end)).replaceAll("[\\D]",""));
					start= end+1;
				}
				checkestd.setText(nombreestudiante);
				checkestd.setId(id);
				listaDeEstIncumplidos.add(id);
				
				checkestd.setOnClickListener(new CheckBox.OnClickListener(){
		            @Override
		            public void onClick(View v) {
		                if(checkestd.isChecked())
		                {
		                	boolean encontrado = false;
		                	for (int i = 0 ; i < idestudiantes.size(); i++)
		                	{
		                		if (idestudiantes.get(i)==checkestd.getId())
		                		{
		                			encontrado = true;
		                			i = idestudiantes.size();
		                		}
		                	}
		                	if (encontrado == false)
	                			idestudiantes.add(checkestd.getId());
		                	for (int i = 0; i< listaDeEstIncumplidos.size (); i++)
		                	{
		                		if (listaDeEstIncumplidos.get(i)==checkestd.getId())
		                			listaDeEstIncumplidos.remove (i);
		                	}
		                	
		                }
		                else
		                {
		                	for (int i = 0; i < idestudiantes.size(); i++)
		                	{
		                		if (idestudiantes.get(i)==checkestd.getId())
		                			idestudiantes.remove(i);
		                	}
		                	boolean encontrado = false;
		                	for (int i =0; i < listaDeEstIncumplidos.size(); i++)
		                	{
		                		if (checkestd.getId() == listaDeEstIncumplidos.get(i))
		                			encontrado = true;
		                	}
		                	if (encontrado == false)
		                		listaDeEstIncumplidos.add(checkestd.getId());
		                }
		            }
		        });
				
				tablerow.addView(checkestd);
				table.addView(tablerow);
				table.setBaselineAligned(true);
				table.setLeft(12);
				//id++;
			}while (band==true);
		}
		else
		{
			mostrarMensaje ("No hay estudiantes matricualdo en este curso");
		}
		btnGuadarLista.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String llamarAsistencia="";
				hilo = new MiHiloConn("setAsistencia", "http//service/setAsistencia", "idMateria", bundle.getString("idCurso").toString());
				hilo.setParametrosAsistencia(idestudiantes);
				imprimir(idestudiantes);
				hilo.start();
				
				while(llamarAsistencia == "" ){
					llamarAsistencia = hilo.getRespuesta();
				//	System.out.println("Respuesta Hilo: " + llamarAsistencia);
				}
				hilo.interrupt();
				enviarPadreDeFamilia (idestudiantes,listaDeEstIncumplidos);
				//"http://service/getEstudiantesbyMateria"
				System.out.println("Estudiantes Cumplidos: ");
				imprimir (idestudiantes);
				System.out.println("Estudiantes no cumplidos:");
				imprimir (listaDeEstIncumplidos);
				System.out.println();
				System.out.println();
				System.out.println();
				
			}
		});
	}
	//Función que Envia
	public void enviarPadreDeFamilia (ArrayList<Integer> idEstudiantes, ArrayList <Integer> listaDeEstIncumplidos)
	{
		String datosAcudientesdeEstCumplidos = "";
		String datosAcudientesdeEstNoCumplidos = "";
		hilo = new MiHiloConn ("getAcudientes", "http//service/getAcudientes", "idEstudiantes", idEstudiantes);
		hilo.start();
		while (datosAcudientesdeEstCumplidos == "")
		{
			datosAcudientesdeEstCumplidos = hilo.getRespuesta();
		}	
		hilo.interrupt();
		hilo = new MiHiloConn ("getAcudientes", "http//service/getAcudientes", "idEstudiantes", listaDeEstIncumplidos);
		//hilo = new MiHiloConn ("getAcudientesDeEstIncumplidos", "http//service/getAcudientePorMateria", "idMateria",bundle.getString("idCurso").toString() );
		//hilo = new MiHiloConn ("getAcudientePorMateria", "http//service/getAcudientePorMateria","idMateria",  bundle.getString("idCurso").toString() );

		//hilo.setParametrosAsistencia(idEstudiantes);
		hilo.start();
		while (datosAcudientesdeEstNoCumplidos == "")
		{
			datosAcudientesdeEstNoCumplidos = hilo.getRespuesta();
		}
		hilo.interrupt();
	/*	Intent email = new Intent(Intent.ACTION_SEND);
		email.setType("message/rfc822");
		email.putExtra (Intent.EXTRA_EMAIL, "mondra10@gmail.com");
		email.putExtra (Intent.EXTRA_SUBJECT, "Prueba");
		email.putExtra (Intent.EXTRA_TEXT, "hola");
		
		startActivity (Intent.createChooser(email, "Titulo"));*/
		//System.out.println("Servicio 1Estudiantes Cumplidos: " + datosAcudientesdeEstCumplidos);
		//System.out.println("Servicio Estudiantes Incumplidos: " +datosAcudientesdeEstNoCumplidos);
	}
	 public void imprimir (ArrayList<Integer> ids )
		{
			
	    	for (int i=0; i < ids.size(); i ++)
			{
				System.out.println("Ids: "+ ids.get(i));
			}
		}
	public void mostrarMensaje (CharSequence mostrar)
    {
    	Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        mostrar, Toast.LENGTH_SHORT);
 
            toast1.show();
    }
	public int contarEstudiantes (String sTexto, String sTextoBuscado)
	{
		int contador = 0;
		while (sTexto.indexOf(sTextoBuscado) > -1) {
		      sTexto = sTexto.substring(sTexto.indexOf(
		        sTextoBuscado)+sTextoBuscado.length(),sTexto.length());
		      contador++; 
		}
		return contador;
	}
	public String listaEstudiantes (String Respuesta)
	{
		int start = Respuesta.indexOf("[");
	    int end = Respuesta.indexOf("]");
	    String ListaCursoId = Respuesta.substring((start+1), (end));
	    return ListaCursoId;
	}
}
