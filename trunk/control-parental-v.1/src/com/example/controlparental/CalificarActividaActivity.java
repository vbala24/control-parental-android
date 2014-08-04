package com.example.controlparental;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class CalificarActividaActivity extends Activity {
	
	 private Bundle bundle;

	protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_calificar);
	        bundle = getIntent().getExtras();
	       // CharSequence nuevavariable = bundle.getString("nombreVariable");
	        final LinearLayout rel = (LinearLayout) findViewById(R.id.prueba);
	        TextView text = new TextView(this);
	        text.findViewById(R.id.textView1);
	        text.setText(bundle.getString("nombreVariable"));
	        rel.addView(text);
	        
	        
	        
	       
	    }

}
