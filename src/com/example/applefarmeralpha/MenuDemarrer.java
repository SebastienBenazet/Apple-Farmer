package com.example.applefarmeralpha;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuDemarrer extends Activity {

	//lorsque l'activité est créée
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_demarrer);
	}
	
	//lorsque le boutton physique "retour" est pressée
	@Override
	public void onBackPressed()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Êtes-vous sûr de vouloir quitter ?").setPositiveButton("Oui", dialogClickListener).setNegativeButton("Non", dialogClickListener).show();
	}
	
	//methode qui permet d'afficher un OUI/NON pour quitter l'application
	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
	    @Override
	    public void onClick(DialogInterface dialog, int which) {
	        switch (which){
	        //OUI a été choisi
	        case DialogInterface.BUTTON_POSITIVE:
	        	finish();
	    		System.exit(0);
	            break;

	        //NON a été choisi
	        case DialogInterface.BUTTON_NEGATIVE:
	            break;
	        }
	    }
	};

	//lorsque le boutton "Nouvelle partie" du menu est clické
	public void clickNouvellePartie(View v) {
		Intent intent = new Intent(this, MenuJeu.class);
		startActivity(intent);
	}
	
	//lorsque le boutton "Parametres" du menu est clické
	public void clickParametres(View v) {
		Intent intent = new Intent(this, ActiviteParametres.class);
		startActivity(intent);
	}
	
	//lorsque le boutton "Quitter" du menu est clické
	public void clickQuitter(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Êtes-vous sûr de vouloir quitter ?").setPositiveButton("Oui", dialogClickListener).setNegativeButton("Non", dialogClickListener).show();
	}
}
