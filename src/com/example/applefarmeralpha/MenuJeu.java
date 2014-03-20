package com.example.applefarmeralpha;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MenuJeu extends Activity {

	//Nom du fichier de preferences des paramètres
	public static final String RESSOURCES = "Ressources";

	//Noms des variables du fichier de preferences des paramètres
	public static final String NB_POMMES = "nbPommes";
	public static final String NB_FUT = "nbFut";
	public static final String NB_POMMES_TOTALES = "nbPommesTotales";
	public static final String POMMES_PAR_CLIC = "ppc";
	public static final String NB_SUCRE = "nbSucre";
	public static final String NB_POMMES_SECONDE = "pps";
	public static final String POSSEDE_USINE = "Possede_Usine";
	
	Timer _t,timer;
	TextView _tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_jeu);
		
		//Instanciation du MediaPlayer avec le son de la pomme croquée
		final MediaPlayer mPlayer = MediaPlayer.create(this,  R.raw.sonpomme);
		
		//On lit (ou crée, s'il n'existe pas) le fichier de préférences PREF_PARAMS
		final SharedPreferences ressources = getSharedPreferences(RESSOURCES, MODE_PRIVATE);
		if(!ressources.contains(POMMES_PAR_CLIC)){
			SharedPreferences.Editor e = ressources.edit();
			e.putInt(POMMES_PAR_CLIC, 1);
			e.commit();
		}
		if(!ressources.contains(NB_POMMES)){
			SharedPreferences.Editor e = ressources.edit();
			e.putInt(NB_POMMES, 0);
			e.commit();
		}
		if(!ressources.contains(NB_SUCRE)){
			SharedPreferences.Editor e = ressources.edit();
			e.putInt(NB_SUCRE, 0);
			e.commit();
		}
		if(!ressources.contains(NB_FUT)){
			SharedPreferences.Editor e = ressources.edit();
			e.putInt(NB_FUT, 0);
			e.commit();
		}
		if(!ressources.contains(NB_POMMES_TOTALES)){
			SharedPreferences.Editor e = ressources.edit();
			e.putInt(NB_POMMES_TOTALES, 0);
			e.commit();
		}
		if(!ressources.contains(NB_POMMES_SECONDE)){
			SharedPreferences.Editor e = ressources.edit();
			e.putInt(NB_POMMES_SECONDE, 0);
			e.commit();
		}
		if(!ressources.contains(POSSEDE_USINE)){
			SharedPreferences.Editor e = ressources.edit();
			e.putInt(POSSEDE_USINE, 0);
			e.commit();
		}
		
		//Affichage du nombre de pommes quand l'activité commence
		TextView textPommes = (TextView) findViewById(R.id.nbPommes);
		textPommes.setText(Integer.toString(ressources.getInt(NB_POMMES, 0)));
		
		//Affichage du nombre de sucre quand l'activité commence
		TextView textSucre = (TextView) findViewById(R.id.nbSucre);
		textSucre.setText(Integer.toString(ressources.getInt(MenuJeu.NB_SUCRE, 0)));
		
		//Affichage du nombre de fut quand l'activité commence
		TextView textFut = (TextView) findViewById(R.id.nbFut);
		textFut.setText(Integer.toString(ressources.getInt(MenuJeu.NB_FUT, 0)));
		
		//Affichage du nombre de pommes par secondes quand l'activité commence
		TextView textPommesSeconde = (TextView) findViewById(R.id.nbPommesSec);
		textPommesSeconde.setText(Integer.toString(ressources.getInt(MenuJeu.NB_POMMES_SECONDE, 0)));
		
		ImageButton pommeCentrale = (ImageButton) findViewById(R.id.pommeCentrale);
		pommeCentrale.setOnClickListener(new View.OnClickListener() {
			
		@Override
		public void onClick(View v) {
			//On lit le fichier de préférences PREF_PARAMS
			SharedPreferences params = getSharedPreferences(ActiviteParametres.PREF_PARAMS, MODE_PRIVATE);
			
			TextView textPommes = (TextView) findViewById(R.id.nbPommes);
			SharedPreferences.Editor e = ressources.edit();
			e.putInt(NB_POMMES, ressources.getInt(NB_POMMES, 0) + ressources.getInt(POMMES_PAR_CLIC, 1));
			e.putInt(NB_POMMES_TOTALES, ressources.getInt(NB_POMMES_TOTALES, 0) + ressources.getInt(POMMES_PAR_CLIC, 1));
			e.commit();
			
			textPommes.setText(Integer.toString(ressources.getInt(NB_POMMES, 0)));
			
			//Si le son dans les paramètres est activé
			if(params.getBoolean(ActiviteParametres.MODE_SON, true)){
				mPlayer.start();
			}
			
			//Instanciation du vibreur
			Vibrator vibro = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			
			//Si le vibreur dans les paramètres est activé
			if(params.getBoolean(ActiviteParametres.MODE_VIBREUR, true)){
				vibro.vibrate(100);
			}
		}
	});
		
	//Lorsqu'on appui sur le bouton paramètres en haut à droite de l'écran.
	ImageButton parametres = (ImageButton) findViewById(R.id.parametres);
	parametres.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v){
			Intent i=new Intent(MenuJeu.this, ActiviteParametres.class);
			startActivity(i);
		}
	});
	
	//Lorsqu'on appui sur le bouton statistiques en haut à droite de l'écran.
	ImageButton statistiques = (ImageButton) findViewById(R.id.statistiques);
	statistiques.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v){
			Intent i=new Intent(MenuJeu.this, MenuStatistiques.class);
			startActivity(i);
		}
	});
		
	//Ajout des pommes par secondes
	if(ressources.getInt(NB_POMMES_SECONDE, 0) > 0) {
	_tv = (TextView) findViewById( R.id.nbPommes);
	_t = new Timer();
	_t.scheduleAtFixedRate(new TimerTask() {
		@Override
	    public void run() {
			runOnUiThread(new Runnable() {
				public void run() {
					SharedPreferences.Editor e = ressources.edit();
					e.putInt(NB_POMMES, ressources.getInt(NB_POMMES, 0) + ressources.getInt(NB_POMMES_SECONDE, 0));
					e.commit();
					//Affichage du nombre de pommes quand l'activité commence
       				TextView textPommes = (TextView) findViewById(R.id.nbPommes);
       				textPommes.setText(Integer.toString(ressources.getInt(NB_POMMES, 0)));
					}
	   			});
	   		}
	   	//Period et Delay OU Delay et Period
		}, 1000, 1000 ); 
	}
}
	
	//lorsque le boutton "Carte" est clické
	public void clickCarte(View v) {
		Intent intent = new Intent(this, MenuCarte.class);
		startActivity(intent);
	}
	
	//lorsque le boutton "Amélioration" est clické
	public void clickAmelioration(View v) {
		Intent intent = new Intent(this, MenuAmelioration.class);
		startActivity(intent);
	}
	
	//lorsque le boutton "Usine" est clické
	public void clickUsine(View v) {
		SharedPreferences ressource = getSharedPreferences(RESSOURCES, MODE_PRIVATE);
		if(ressource.getInt(POSSEDE_USINE, 0) >= 1) {
			Intent intent = new Intent(this, MenuUsine.class);
			startActivity(intent);
		} else {
			//Nécessaires au Toast
		    Context context = getApplicationContext();
		    int duration = Toast.LENGTH_SHORT;
			//On indique au joueur qu'il ne possède pas l'usine
        	Toast toast = Toast.makeText(context, "Achetez l'usine dans la carte", duration);
        	toast.show();
		}
	}
}
