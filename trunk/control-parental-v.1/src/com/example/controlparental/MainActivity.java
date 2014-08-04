package com.example.controlparental;

import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	Conexion conn;
	String Respuesta;
	SoapObject soapcursos; 
	MiHiloConn mihilo;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		//Declaración de variables
		final ArrayList<String> ids= new ArrayList<String>();
		final LinearLayout ly = (LinearLayout) findViewById(R.id.lay_asignatura);
		int end, indexstart, Totalcursoid, cont=0;
		String Curso,  ListaCursoId, Idbutton, Idcurso, respuestamaterias="";
		//fin declaración de variables
		
		mihilo = new MiHiloConn("getMaterias","http://service/getMaterias", "idDocente", "2");
		mihilo.start();
		while(respuestamaterias == "" ){
			respuestamaterias = mihilo.getRespuesta();
		}
		mihilo.interrupt();
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		         LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
		         LayoutParams.WRAP_CONTENT, 1);
		
		
	    params.setMargins(150, 12, 0, 0);
	    params2.setMargins(10, 5, 10, 5);
		if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
		
	   	end = respuestamaterias.indexOf("]");
	    ListaCursoId = CursosIds (respuestamaterias);
	    indexstart = 0;	    
	    Totalcursoid = ContarCursosId(ListaCursoId, ",")+1;
	    //fin de asignación de variables
	    
	    //Construyendo la interfaz
	    do {
	    	if (cont == (Totalcursoid-1))
	    		end = ListaCursoId.length();
	    	else
	    		end = ListaCursoId.indexOf(",",indexstart);
	    	Curso = ListaCursoId.substring(indexstart, end);
	    	ids.add(Curso);
	    	cont++;
	    	if (cont !=(Totalcursoid-1))
	    	{	
	    		indexstart = end+1;
	    		end = ListaCursoId.indexOf(",",indexstart);
	    	}
	    	else {
	    		indexstart = (end+1);
	    		end = ListaCursoId.length();
	    	}

	    	View v = new View(this);
	    	v.setBackgroundResource(R.drawable.separador);
	    	v.setLayoutParams(params2);
	    	Idbutton = ListaCursoId.substring(indexstart,end);
	    	Idcurso=Idbutton.replaceAll("[\\D]", "");//quitar espacios y solo dejar números
    		final Button btncurso = new Button (this);
    		btncurso.setBackgroundResource(R.drawable.btncurso1);	
    		btncurso.setText(Curso);
	    	btncurso.setHeight(20);
	    //	btncurso.setGravity(30);
	    	ids.add(Idcurso);
	    	//btncurso.setGravity(Gravity.CENTER_HORIZONTAL);
	    	btncurso.setLayoutParams(params);
	    	btncurso.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {			
				Intent myIntent = new Intent(); 
				myIntent.setClass(MainActivity.this, AsignaturaActivity.class);
				//
				//System.out.println("Nombre Curso: " btncurso.getText().toString ());
			// pasar parametros a otra actividad (nombre del parametro, y valor)
				myIntent.putExtra("NombreCurso", btncurso.getText().toString());
				imprimir (ids);
				myIntent.putStringArrayListExtra("IdCurso", ids);			
				startActivity(myIntent);
			}
		});
		ly.addView(btncurso);
		ly.addView(v);
		indexstart = end+1;
		cont++;
	   }while (cont < Totalcursoid );
//		fin de construcción de la interfaz	
	   
}
	
	
	// función que devuelve un String con los cursos y los Ids de los curos
	public String CursosIds (String Respuesta)
	{
		int start = Respuesta.indexOf("[");
	    int end = Respuesta.indexOf("]");
	    String ListaCursoId = Respuesta.substring((start+1), (end));
	    return ListaCursoId;
	}
	// Función que devuelve cuantos cursos e ids tengo en la base de datos
	public int ContarCursosId (String sTexto, String sTextoBuscado)
	{
		int contador = 0;
		while (sTexto.indexOf(sTextoBuscado) > -1) {
		      sTexto = sTexto.substring(sTexto.indexOf(
		        sTextoBuscado)+sTextoBuscado.length(),sTexto.length());
		      contador++; 
		}
		return contador;
	}
//	funciones por defecto al utilizar appcompatV7 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    protected void onDestroy ()
    {
    	super.onDestroy();
    }
    public void imprimir (ArrayList<String> ids )
	{
		System.out.println("Arraylist Main Activity");
    	for (int i=0; i < (ids.size()-1); i ++)
		{
			System.out.println("I: "+ i+ " Curso " + ids.get(i) +" Id Curso: " + ids.get(i+1));
			i++;
		}
	}
    //función para mostra un mensaje en la interfaz
    public void MostrarMensaje (CharSequence mostrar)
    {
    	Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        mostrar, Toast.LENGTH_SHORT); 
            toast1.show();
    } 
}


