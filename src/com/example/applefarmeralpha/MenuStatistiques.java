package com.example.applefarmeralpha;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MenuStatistiques extends Activity {
	
	//Nom du fichier de preferences des paramètres
	public static final String RESSOURCES = "Ressources";
	
	
	
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
		//Affichage du nombre de pommes par secondes quand l'activité commence
		TextView textPommesClic = (TextView) findViewById(R.id.nbPommesParClic);
		textPommesClic.setText(Integer.toString(ressources.getInt(MenuJeu.POMMES_PAR_CLIC, 0)));

	}

	

}
