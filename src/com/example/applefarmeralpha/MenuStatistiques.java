package com.example.applefarmeralpha;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MenuStatistiques extends Activity {
	
	//Nom du fichier de preferences des paramètres
	public static final String RESSOURCES = "Ressources";
	
	Timer _t,timer;
	TextView _tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_statistiques);
		
		//On lit (ou crée, s'il n'existe pas) le fichier de préférences PREF_PARAMS
		final SharedPreferences ressources = getSharedPreferences(RESSOURCES, MODE_PRIVATE);
		
		//Affichage du nombre de pommes par secondes quand l'activité commence
		TextView textPommesSeconde = (TextView) findViewById(R.id.nbPommesSec);
		textPommesSeconde.setText(Integer.toString(ressources.getInt(MenuJeu.NB_POMMES_SECONDE, 0)));
		//Affichage du nombre de pommes quand l'activité commence
		TextView textPommes = (TextView) findViewById(R.id.nbPommes);
		textPommes.setText(Integer.toString(ressources.getInt(MenuJeu.NB_POMMES, 0)));
		//Affichage du nombre de pommes quand l'activité commence
		TextView textPommesTotales = (TextView) findViewById(R.id.nbPommesTotales);
		textPommesTotales.setText(Integer.toString(ressources.getInt(MenuJeu.NB_POMMES_TOTALES, 0)));
		//Affichage du nombre de pommes par secondes quand l'activité commence
		TextView textPommesClic = (TextView) findViewById(R.id.nbPommesParClic);
		textPommesClic.setText(Integer.toString(ressources.getInt(MenuJeu.POMMES_PAR_CLIC, 0)));

		//Ajout des pommes par secondes
	    //Ajout des pommes par secondes
  		if(ressources.getInt(MenuJeu.NB_POMMES_SECONDE, 0) > 0) {
  		//textPommes = (TextView) findViewById(R.id.nbPommes);
  		_t = new Timer();
  		_t.scheduleAtFixedRate(new TimerTask() {
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
		}, 1000, 1000 ); 
	}
}
}
