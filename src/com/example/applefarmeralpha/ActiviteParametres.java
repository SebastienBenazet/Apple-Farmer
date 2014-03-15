package com.example.applefarmeralpha;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ActiviteParametres extends Activity {
	
	//Nom du fichier de preferences des paramètres
	public static final String PREF_PARAMS = "PreferencesParams";
	
	//Noms des variables du fichier de preferences des paramètres
	public static final String MODE_SON = "Mode_Son";
	public static final String MODE_VIBREUR = "Mode_Vibreur";
	
	//Messages nécéssaires aux Toasts pour les bouttons
	private static final CharSequence texteSonOn = "Son activé";
	private static final CharSequence texteSonOff = "Son désactivé";
	private static final CharSequence texteVibreurOn = "Vibreur activé";
	private static final CharSequence texteVibreurOff = "Vibreur désactivé";
	private static final CharSequence texteReinitialiser= "Partie réinitialisée";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activite_parametres);
		
		//On lit (ou crée, s'il n'existe pas) le fichier de préférences PREF_PARAMS
		SharedPreferences params = getSharedPreferences(PREF_PARAMS, MODE_PRIVATE);
		
		final ToggleButton toggleSon = (ToggleButton) findViewById(R.id.bouttonSons);
		toggleSon.setOnClickListener(new OnClickListener() {
		    public void onClick(View v) {
		        if (toggleSon.isChecked()) {
		        	//On active le son
		    		activerSon(true);
		        } else {
		        	//On désactive le son
		    		activerSon(false);
		        }
		    }
		});
		
		final ToggleButton toggleVibreur = (ToggleButton) findViewById(R.id.bouttonVibreur);
		toggleVibreur.setOnClickListener(new OnClickListener() {
		    public void onClick(View v) {
		        if (toggleVibreur.isChecked()) {
		        	//On active le vibreur
		    		activerVibreur(true);
		        } else {
		        	//On désactive le vibreur
		    		activerVibreur(false);
		        }
		    }
		});
		
		toggleSon.setChecked(params.getBoolean(MODE_SON, true));
		toggleVibreur.setChecked(params.getBoolean(MODE_VIBREUR, true));
	}

	public void activerSon(boolean activer) {
		//Nécessaires au Toast
	    Context context = getApplicationContext();
	    int duration = Toast.LENGTH_SHORT;
		
	    SharedPreferences params = getSharedPreferences(PREF_PARAMS, MODE_PRIVATE);
	    
		if(activer){
			//On édite le fichier params, on met la variable MODE_SON à true, on sauvegarde l'édition
			SharedPreferences.Editor e = params.edit();
			e.putBoolean(MODE_SON, true);
			e.commit();
			
			//Instantiation et affichage du Toast
    		Toast toast = Toast.makeText(context, texteSonOn, duration);
    		toast.show();
		}
		else{
			//On édite le fichier params, on met la variable MODE_SON à false, on sauvegarde l'édition
			SharedPreferences.Editor e = params.edit();
			e.putBoolean(MODE_SON, false);
			e.commit();
			
			//Instantiation et affichage du Toast
			Toast toast = Toast.makeText(context, texteSonOff, duration);
			toast.show();
		}
	}
	
	public void activerVibreur(boolean activer) {
		//Nécessaires au Toast
	    Context context = getApplicationContext();
	    int duration = Toast.LENGTH_SHORT;
		
	    SharedPreferences params = getSharedPreferences(PREF_PARAMS, MODE_PRIVATE);
	    
		if(activer){
			//On édite le fichier params, on met la variable MODE_VIBREUR à true, on sauvegarde l'édition
			SharedPreferences.Editor e = params.edit();
			e.putBoolean(MODE_VIBREUR, true);
			e.commit();
			
			//Instantiation et affichage du Toast
    		Toast toast = Toast.makeText(context, texteVibreurOn, duration);
    		toast.show();
		}
		else{
			//On édite le fichier params, on met la variable MODE_VIBREUR à false, on sauvegarde l'édition
			SharedPreferences.Editor e = params.edit();
			e.putBoolean(MODE_VIBREUR, false);
			e.commit();
			
			//Instantiation et affichage du Toast
    		Toast toast = Toast.makeText(context, texteVibreurOff, duration);
    		toast.show();
		}
	}
	
	//lorsque le boutton "Réinitialiser" du menu est clické
	public void clickReinitialiser(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Êtes-vous sûr de vouloir réinitialiser votre partie?").setPositiveButton("Oui", dialogClickListener).setNegativeButton("Non", dialogClickListener).show();
	}
	
	//methode qui permet d'afficher un OUI/NON pour réinitialiser les données
	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
	    @Override
	    public void onClick(DialogInterface dialog, int which) {
	    	
	    	//Nécessaires au Toast
		    Context context = getApplicationContext();
		    int duration = Toast.LENGTH_SHORT;
		    
	        switch (which){
	        //OUI a été choisi
	        case DialogInterface.BUTTON_POSITIVE:
	        	SharedPreferences clearRess = getSharedPreferences(MenuJeu.RESSOURCES, MODE_PRIVATE);
	        	SharedPreferences.Editor e = clearRess.edit();
	        	//Suppression de toutes les données
	        	e.clear();
	        	e.commit();
	        	
				//Instantiation et affichage du Toast
	    		Toast toast = Toast.makeText(context, texteReinitialiser, duration);
	    		toast.show();
	            break;

	        //NON a été choisi
	        case DialogInterface.BUTTON_NEGATIVE:
	            break;
	        }
	    }
	};
}
