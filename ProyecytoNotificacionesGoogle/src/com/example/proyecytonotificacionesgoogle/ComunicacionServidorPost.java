/**
 * 
 */
package com.example.proyecytonotificacionesgoogle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import android.os.AsyncTask;

/**
 * @author Alberto Vivas
 *
 * 
 */
public class ComunicacionServidorPost extends AsyncTask<Void, Void, String>{
	private final String URL = "http://172.16.1.57:8090/ServidorNotificacionesGoogle/ServletServer";
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
	 */
	@Override
	protected String doInBackground(Void... params) {
		String respuesta = "";
		java.net.URL url;
		try {
			url = new java.net.URL(URL);
			HttpURLConnection conexionHttp = (HttpURLConnection) url.openConnection();
			String mensaje = "mensaje post al servidor";
			conexionHttp.setDoOutput(true);
			conexionHttp.setRequestMethod("POST");
			conexionHttp.setRequestProperty("Content-type", "text/plain;charset=UTF=8");
			//body
			
			OutputStream out = conexionHttp.getOutputStream();
			out.write(mensaje.getBytes());
			out.close();
			conexionHttp.getResponseCode();
			
			int rc = conexionHttp.getResponseCode();
			String rm = conexionHttp.getResponseMessage();
			
			if (200 == rc) {

				if ("OK" == rm) {
					BufferedReader in = new BufferedReader(new InputStreamReader(conexionHttp.getInputStream()));
					respuesta = in.readLine();
					in.close();
					conexionHttp.disconnect();
				}
			} else {
				System.out.println("El response code es: " + rc + " y mi response message: " + rm);
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return respuesta;
	}
	
	
	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

}
