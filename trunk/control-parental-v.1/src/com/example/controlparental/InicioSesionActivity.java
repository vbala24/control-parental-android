package com.example.controlparental;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class InicioSesionActivity extends Activity
{
	
    private String passwoord;
	private String login;
	MiHiloConn myhilo;
	//EditText edit, editPasswoord;
	
	
	protected void onCreate ( Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sesion);
	}
    public void actividadPrincipal (View v)
    {
    	
    	final EditText edit = (EditText) findViewById(R.id.editTextLogin);
    	String inicioSesion="";
    	EditText editPasswoord = (EditText) findViewById(R.id.editTextPasswoord);
    	
		login = edit.getText().toString();
		passwoord = editPasswoord.getText().toString ();
		
		myhilo = new MiHiloConn("inicioSesion","http://service/iniciarSesion","password",passwoord);
		myhilo.setParametrosString("login", login, "", "");
		myhilo.start();
		while(inicioSesion == "" ){
			inicioSesion = myhilo.getRespuesta().toString();
		}
		myhilo.interrupt();
		System.out.println("Inicio sesion: "+ inicioSesion);
		
		int respuestaResultado = inicioSesion.indexOf("true");
		
		if (respuestaResultado!=-1)
		{
	    	Intent myintent = new Intent();
	    	myintent.setClass(InicioSesionActivity.this, MainActivity.class);
	    	startActivity(myintent);
		}
		else
		{
			mostrarMensaje ("!!Login o Passwoord no registrados");
		}
    }
    public void mostrarMensaje (CharSequence mostrar)
    {
    	Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        mostrar, Toast.LENGTH_SHORT); 
            toast1.show();
    } 
 }
		

