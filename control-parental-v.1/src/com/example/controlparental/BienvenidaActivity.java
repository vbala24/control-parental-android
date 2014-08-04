package com.example.controlparental;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



public class BienvenidaActivity extends Activity {
	
	

	TextView linea_ayuda;
	ProgressBar mProgressBar;
	int progreso=0;
	int paso = 500;

	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        mProgressBar=(ProgressBar) findViewById(R.id.progressbar);
       // linea_ayuda = (TextView) findViewById(R.id.linea_ayuda);
    }

	@Override
	protected void onResume() {
		super.onResume();
		
	//	linea_ayuda.setText(getString(R.string.updating_db));
		cuentaAtras(3000);


	}
	
	private void cuentaAtras(long milisegundos){
		CountDownTimer mCountDownTimer;

		mProgressBar.setMax((int)milisegundos);

		mProgressBar.setProgress(paso);
		
		mCountDownTimer=new CountDownTimer(milisegundos, paso) {

		        @Override
		        public void onTick(long millisUntilFinished) {
		            Log.v("Log_tag", "Tick of Progress"+ progreso+ millisUntilFinished);
		            progreso+=paso; 
		            mProgressBar.setProgress(progreso);
		        }

		        @Override
		        public void onFinish() {
		        	 
					//Toast.makeText(getApplicationContext(), getString(R.string.db_ok), Toast.LENGTH_LONG).show();
		        	progreso+=	paso;
		            mProgressBar.setProgress(progreso);
		            mProgressBar.setVisibility(View.INVISIBLE);
		            Intent myintent = new Intent ();
		            myintent.setClass(BienvenidaActivity.this, InicioSesionActivity.class);
		            startActivity(myintent);
		          
					

		        }
		};
		
		mCountDownTimer.start();
		
	}


	




}
