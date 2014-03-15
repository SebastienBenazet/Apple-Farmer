package com.example.applefarmeralpha;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MenuUsine extends Activity {
	
	//Nom du fichier de preferences des ressources
	public static final String RESSOURCES = "Ressources";
	
	public static final String NB_POMMES = "nbPommes";
	public static final String NB_SUCRE = "nbSucre";
	public static final String NB_FUT = "nbFut";
	
	Timer _t,timer;
	TextView _tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_usine);
		
		//On lit (ou crée, s'il n'existe pas) le fichier de préférences PREF_PARAMS
		final SharedPreferences ressources = getSharedPreferences(RESSOURCES, MODE_PRIVATE);
		
		//Affichage du nombre de pommes quand l'activité commence
		TextView textPommes = (TextView) findViewById(R.id.nbPommes);
		textPommes.setText(Integer.toString(ressources.getInt(NB_POMMES, 0)));
		
		//Affichage du nombre de sucre quand l'activité commence
		TextView textSucre = (TextView) findViewById(R.id.nbSucre);
		textSucre.setText(Integer.toString(ressources.getInt(MenuJeu.NB_SUCRE, 0)));

		//Affichage du nombre de fut quand l'activité commence
		TextView textFut = (TextView) findViewById(R.id.nbFut);
		textFut.setText(Integer.toString(ressources.getInt(MenuJeu.NB_FUT, 0)));
		
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
	    			}, 1000, 1000 ); 
  		}
	}
	
   
	
	//lorsque le boutton "Fabriquer" du menu est clické
	public void clickFabrication(View v) {
		//On récupère les valeurs des EditText
		EditText usineTextPomme = (EditText)findViewById(R.id.usineTextPomme);
		String TextPomme = usineTextPomme.getText().toString();
		EditText usineTextSucre = (EditText)findViewById(R.id.usineTextSucre);
		String TextSucre = usineTextSucre.getText().toString();
		EditText usineTextTemperature = (EditText)findViewById(R.id.usineTextTemperature);
		String TextTemperature = usineTextTemperature.getText().toString();
		EditText usineTextDuree = (EditText)findViewById(R.id.usineTextDuree);
		String TextDuree = usineTextDuree.getText().toString();
			
		final SharedPreferences ressources = getSharedPreferences(MenuAmelioration.RESSOURCES, MODE_PRIVATE);
		
		//Nécessaire aux toast de prévention
   	    Context context = getApplicationContext();
   	    int duration = Toast.LENGTH_SHORT;
   	    
   	    //Si l'utilisateur n'entre aucune valeur
		if (TextPomme.length() == 0 || TextSucre.length() == 0 || TextTemperature.length() == 0 || TextDuree.length() == 0) {
			//On indique au joueur qu'il doit entrer des valeurs
       		Toast toast = Toast.makeText(context, "Veuillez entrer une recette compléte", duration);
       		toast.show();
		}
		else if(ressources.getInt(MenuJeu.NB_POMMES, 0) >= Integer.parseInt(TextPomme.toString()) && ressources.getInt(MenuJeu.NB_SUCRE, 0) >= Integer.parseInt(TextSucre.toString())) {
			SharedPreferences.Editor e = ressources.edit();
			//On retire le nombre de pommes et de sucre de ses ressources
			e.putInt(MenuJeu.NB_POMMES, ressources.getInt(MenuJeu.NB_POMMES, 0)- Integer.parseInt(TextPomme.toString()));
			e.putInt(MenuJeu.NB_SUCRE, ressources.getInt(MenuJeu.NB_SUCRE, 0) - Integer.parseInt(TextSucre.toString()));
			//AJOUTER LA RECETTE POUR OBTENIR LES FUTS DE CALVADOS
			e.putInt(MenuJeu.NB_FUT, ressources.getInt(MenuJeu.NB_FUT, 0)+10);
			//
			//
			//
			//
			//
			//
			//
			//									RECETTE
			//
			//
			//
			//
			//
			//
			e.commit();
			//Affichage du nombre de pommes quand l'activité commence
			TextView textPommes = (TextView) findViewById(R.id.nbPommes);
			textPommes.setText(Integer.toString(ressources.getInt(MenuJeu.NB_POMMES, 0)));
			
			//Affichage du nombre de sucre quand l'activité commence
			TextView textSucre = (TextView) findViewById(R.id.nbSucre);
			textSucre.setText(Integer.toString(ressources.getInt(MenuJeu.NB_SUCRE, 0)));
			
			//Affichage du nombre de Futs après que le joueur ai cliquer sur fabriquer
			TextView textFut = (TextView) findViewById(R.id.nbFut);
			textFut.setText(Integer.toString(ressources.getInt(MenuJeu.NB_FUT, 0)));

		}
		else {
		//On indique au joueur qu'il n'a pas assez de ressources
       		Toast toast = Toast.makeText(context, "Pas assez de ressources", duration);
       		toast.show();
		}
	}
}