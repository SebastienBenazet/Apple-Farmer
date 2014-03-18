package com.example.applefarmeralpha;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuCarte extends Activity {

	//Nom du fichier de preferences des paramètres
	public static final String RESSOURCES = "Ressources";
	
	public static final String NB_POMMES = "nbPommes";
	public static final String NB_SUCRE = "nbSucre";
	public static final String NB_FUT = "nbFut";
	
	Timer _t,timer;
	TextView _tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_carte);
		
		//On lit (ou crée, s'il n'existe pas) le fichier de préférences PREF_PARAMS
		final SharedPreferences ressources = getSharedPreferences(RESSOURCES, MODE_PRIVATE);
		
		//Affichage du nombre de pommes quand l'activité commence
		TextView textPommes = (TextView) findViewById(R.id.nbPommes);
		textPommes.setText(Integer.toString(ressources.getInt(NB_POMMES, 0)));
		
		//Affichage du nombre de sucre quand l'activité commence
		TextView textSucre = (TextView) findViewById(R.id.nbSucre);
		textSucre.setText(Integer.toString(ressources.getInt(NB_SUCRE, 0)));
		
		//Affichage du nombre de fut quand l'activité commence
		TextView textFut = (TextView) findViewById(R.id.nbFut);
		textFut.setText(Integer.toString(ressources.getInt(NB_FUT, 0)));
		
		//Ajout des pommes par secondes
		if(ressources.getInt(MenuJeu.NB_POMMES_SECONDE, 0) > 0) {
		//textPommes = (TextView) findViewById(R.id.nbPommes);
		_t = new Timer();
		_t.scheduleAtFixedRate( new TimerTask() {
			@Override
		    public void run() {
				runOnUiThread(new Runnable() {
					public void run() {
	    								//Affichage du nombre de pommes quand l'activité commence
	    		        				TextView textPommes = (TextView) findViewById(R.id.nbPommes);
	    		        				textPommes.setText(Integer.toString(ressources.getInt(MenuJeu.NB_POMMES, 0)));
	    						}
	    					});
	    				}
	    				//Period et Delay OU Delay et Period
	    			}, 1000, 1000 
	    		);
  		}		
	}
	
	//lorsque le boutton "Batiment" est clické
	public void clickAmeliorationCarte(View v) {
		Intent intent = new Intent(this, MenuAmeliorationCarte.class);
		startActivity(intent);
	}
}
