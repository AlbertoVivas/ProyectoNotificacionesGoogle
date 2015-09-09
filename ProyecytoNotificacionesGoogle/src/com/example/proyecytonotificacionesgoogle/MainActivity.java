package com.example.proyecytonotificacionesgoogle;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Config;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button botonRegistroGCM;
	Button botonRegMiServer;
	Context context;
	String regId;
	
	GoogleCloudMessaging gcm;//
	
	/*Esta es la clave que usaremos en el servidor para comunicar con GCM */
	private static final String GOOGLE_SERVER_KEY = "AIzaSyDg_kr-INpY67zcOFbsnPLJcWjd5Ay3ew4";


	/*Este ID será el que usemos en la aplicación para comunicar con GCM */
	static final String GOOGLE_PROJECT_ID = "976784217664";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = getApplicationContext();
		botonRegistroGCM= (Button) findViewById(R.id.gcm_button);
		botonRegMiServer= (Button) findViewById(R.id.registrar_button);
	}

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
	
	private String registrarEnGsm(){
		
		regId=getRegistrationId(getApplicationContext());
		if(TextUtils.isEmpty(regId)){
			
			registerInBackground();
			Log.d("Register Activity", "Registrado correctamente en el servidor GCM - registro: "+regId);
			
		}else{
			Toast.makeText(getApplicationContext(), "Ya tiene un RegId", Toast.LENGTH_LONG).show();
		}
		
		
		
		return regId;
	}
	
	/**
	 * 
	 */
	private void registerInBackground() {
		// TODO Auto-generated method stub
		new AsyncTask<Void, Void, String>() {
			/* (non-Javadoc)
			 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
			 */
			@Override
			protected String doInBackground(Void... params) {
				String msg = "";
				
				try {
					if(null==gcm){
						gcm=GoogleCloudMessaging.getInstance(context);
					}
					regId=gcm.register(GOOGLE_PROJECT_ID);
					msg="Device registered, registration ID="+regId;
					
					storeRegistrationId();
					
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				
				
				
				
				
				
				Log.d("RegisterActivity", "AsynTask completed:");
				return msg;
			}
			/* (non-Javadoc)
			 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
			 */
			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				Toast.makeText(context, "Registered with GCM server"+result, Toast.LENGTH_SHORT).show();
			}
			
		}.execute(null,null,null);
	}

	
	protected void storeRegistrationId() {
		// TODO Auto-generated method stub
		
	}

	private String getRegistrationId(Context context){
		String registrationId=null;
		SharedPreferences prefs = getSharedPreferences(MainActivity.class.getSimpleName(), context.MODE_PRIVATE);
		registrationId = prefs.getString("REG_ID", "");
		
		if(registrationId.isEmpty()){
			Log.i("MainActivity", "No esta registrado");
		}
		
		
		return registrationId;
	}
}
