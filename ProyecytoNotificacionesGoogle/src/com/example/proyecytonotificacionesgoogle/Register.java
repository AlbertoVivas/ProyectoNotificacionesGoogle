/**
 * 
 */
package com.example.proyecytonotificacionesgoogle;

import java.util.concurrent.ExecutionException;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author Alberto Vivas
 *
 * 
 */
public class Register implements OnClickListener{
	
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		ComunicacionServidorPost csp = new ComunicacionServidorPost();
		try {
			csp.execute(null,null,null).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
