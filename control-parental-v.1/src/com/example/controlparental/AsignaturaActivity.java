package com.example.controlparental;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import android.widget.TextView;
import android.widget.Toast;


public class AsignaturaActivity extends Activity {
	 TextView txtasignatura; 
	 Bundle bundle;
	private ArrayList<String> curso_ids;
	
	 protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.asignatura);
			
			final RelativeLayout rel = (RelativeLayout) findViewById(R.id.layasignatura);
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
	                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	 
		
			params.addRule(RelativeLayout.CENTER_HORIZONTAL);
			
			bundle = getIntent().getExtras();
			
			txtasignatura = new TextView(this);
			txtasignatura.setId(3);
			txtasignatura.setText(bundle.getString("NombreCurso"));
			txtasignatura.setTranslationX((float) 10.0);
			txtasignatura.setLayoutParams(params);
			rel.addView(txtasignatura);
	 }
	 public void AdicionarActividad (View v)
	 {
		 String auxid="";		 
		 curso_ids = new ArrayList<String>();
		 Intent myIntent = new Intent();
		 myIntent.setClass(AsignaturaActivity.this, AddHomeWorkActivity.class);		 		 
		 curso_ids = bundle.getStringArrayList("IdCurso");	
			for (int i = 0; i <curso_ids.size(); i++)
			{
				auxid = curso_ids.get(i);//recuperar el id
				if (auxid.equalsIgnoreCase(bundle.getString("NombreCurso")))
				{
					auxid = curso_ids.get((i+1));
					break;
				}
			}			
			System.out.println("IDCURSO: " + auxid);
			
			myIntent.putExtra("nombreCurso", bundle.getString("NombreCurso"));
			myIntent.putExtra("idCurso", auxid);
			
			startActivity(myIntent);
	 }
	 public void LlamarLista(View v)
	 {
		
		String auxid="";
		curso_ids = new ArrayList<String>();
		 // MostrarMensaje ("LLamar a lista");	
		Intent myintent = new Intent(); 
		myintent.setClass(AsignaturaActivity.this, ListaActivity.class);
			// pasar parametros a otra actividad (nombre del parametro, y valor)
		
		curso_ids = bundle.getStringArrayList("IdCurso");
		//Recuperar el ID del curso
		for (int i = 0; i <curso_ids.size(); i++)
		{
			auxid = curso_ids.get(i);//recuperar el id
			if (auxid.equalsIgnoreCase(bundle.getString("NombreCurso")))
			{
				auxid = curso_ids.get((i+1));
				break;
			}
		}
		System.out.println("IDCURSO: " + auxid);
		myintent.putExtra("nombreCurso", bundle.getString("NombreCurso"));
		myintent.putExtra("idCurso", auxid);
		
		startActivity(myintent);
	//	finish();
	 }
	 public void Notificar (View v)
	 {
//	  Código a modificar por Alexander Romero por favor basarse en en la función LlamarLista
		 MostrarMensaje ("Notificar");
	 }
	 public void Calificar (View v)
	 {
		 Intent myintent = new Intent ();
		 myintent.setClass(AsignaturaActivity.this,CalificarActividaActivity.class);
		 String valor = "hola";
		 myintent.putExtra("nombreVariable", valor);
		 startActivity (myintent);
		 
		 MostrarMensaje ("Calificar");
	 }
	 public void MostrarMensaje (CharSequence mostrar)
	    {
	    	Toast toast1 =
	                Toast.makeText(getApplicationContext(),
	                        mostrar, Toast.LENGTH_SHORT);
	     
	            toast1.show();
	    }
}
