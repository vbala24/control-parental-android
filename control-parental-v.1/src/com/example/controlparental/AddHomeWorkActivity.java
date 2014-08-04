package com.example.controlparental;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class AddHomeWorkActivity extends Activity {
	TextView tvCurso;
	CheckBox myCheck;
	
	//variables para el manejo de datePickerDialog - ingreso de fecha
	private TextView mDateDisplay;
	private Button mPickDate;
	private Button btnMateriales;
	private int anio;
	private int mes;
	private int dia;
	static final int DATE_DIALOG_ID = 0;
	//private Dialog ventana;
	private Button btnCancelar;
	private Button btnAceptar;
	private Button btnAgregarMaterial;
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView (R.layout.activity_homework);
		final Bundle myBundle = getIntent().getExtras();
		
		String curso = myBundle.getString("nombreCurso");
		
		tvCurso=(TextView)findViewById(R.id.tvNombreCurso);
		tvCurso.setText(curso);
		mDateDisplay = (TextView) findViewById(R.id.dateDisplay);
		mPickDate = (Button) findViewById(R.id.pickDate);
mPickDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			showDialog(DATE_DIALOG_ID);	
				
			}
		});			
	
		
		final Calendar c = Calendar.getInstance();
		anio=c.get(Calendar.YEAR);
		mes=c.get(Calendar.MONTH);
		dia=c.get(Calendar.DAY_OF_MONTH);
		updateDisplay();
		
		

		
	}


	public void agregarMateriales(View v )
	{		
		Intent myIntent = new Intent();
	//	myIntent.setClass(AddHomeWorkActivity.this, MaterialesActivity.class);
		startActivity(myIntent);
	}
	
	/*
	public void cancelar(View v)
	{		
		btnCancelar = (Button) findViewById(R.id.btnCancelar);
		
		btnCancelar.setOnClickListener(new View.OnClickListener(){
		    public void onClick(View v) {
		           ventana.cancel();//Cerrar ventana
		}});
		
		ventana.cancel();
	}
	
	*/
	
	public void ventanaEmergente(View v)
	{
		
	final Dialog ventana = new Dialog(this);
	ventana.setContentView(R.layout.materiales);
	ventana.setTitle("Seleccione los elementos a listar");
	ventana.show();
	
	
	
	//código para el manejo de ventana emergente
		btnCancelar = (Button)ventana.findViewById(R.id.btnCancelar);
		
		btnCancelar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println("Ingreso");
				ventana.cancel();
				
			}
		});
		
		btnAceptar = (Button)ventana.findViewById(R.id.btnAceptar);
		
		btnAceptar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				ventana.cancel();
				CheckBox miChBox1 = (CheckBox)ventana.findViewById(R.id.checkBox1);
				if(miChBox1.isChecked()==true)
				{					
					Toast toast1 = Toast.makeText(getApplicationContext(), "CheckBox1 Seleccionado", Toast.LENGTH_SHORT);
			        toast1.show();
				}
				
			}
		});
		
		
		btnAgregarMaterial = (Button)ventana.findViewById(R.id.btnAgregarMaterial);
		
		btnAgregarMaterial.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {					
					Toast toast1 = Toast.makeText(getApplicationContext(), "Agregar Material", Toast.LENGTH_SHORT);
			        toast1.show();				
			}
		});
		
	
	  }
	
	private void updateDisplay() {
		mDateDisplay.setText(
				new StringBuilder()
				.append(dia).append("-")
				.append(mes + 1).append("-")				
				.append(anio).append("")
				);		
		
	}
	
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			anio=year;
			mes=monthOfYear;
			dia=dayOfMonth;
			updateDisplay();
		}
	};
	
	
	@Override
	protected Dialog onCreateDialog(int id){
		switch(id){
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, anio, mes, dia);		
		}
		return null;
	}
		
}

