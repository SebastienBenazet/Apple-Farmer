package com.example.applefarmeralpha;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuAmeliorationCarte extends Activity {
	
	//Nom du fichier de preferences des ressources
	public static final String RESSOURCES = "Ressources";
	
	public static final String NB_POMMES = "nbPommes";
	public static final String NB_SUCRE = "nbSucre";
	public static final String NB_FUT = "nbFut";
	public static final String PRIX_USINE_POMMES = "prixUsinePommes";
	public static final String PRIX_USINE_SUCRE = "prixUsineSucre";
	
	
	Timer _t,timer;
	TextView _tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_amelioration_carte);
		
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
		
		//Instantiation du GridView
	    GridView gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(new ImageAdapter(this));
		
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
		
		gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            //Lorsque le bouton "Sucre" est cliqué
	        	if(position == 0) {
		        		//methode qui permet d'afficher un OUI/NON pour quitter l'application
		        		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		        		    @Override
		        		    public void onClick(DialogInterface dialog, int which) {
		        		        switch (which){
		        		        //OUI a été choisi
		        		        case DialogInterface.BUTTON_POSITIVE:
		        		        	//SI le joueur ne possède pas l'usine
		        		        	if(ressources.getInt(MenuJeu.POSSEDE_USINE, 0) == 0) {
		        		    	    //Si le joueur possède assez de pommes pour acheter l'amélioration
		        		        		if(ressources.getInt(MenuJeu.NB_POMMES, 0) >= ressources.getInt(PRIX_USINE_POMMES, 0) && (ressources.getInt(MenuJeu.NB_SUCRE, 0) >= ressources.getInt(PRIX_USINE_SUCRE, 0))) {
		        		        			SharedPreferences.Editor e = ressources.edit();
		        		        			//On retire le nombre de pommes et de sucre de ses ressources
		        		        			e.putInt(MenuJeu.NB_POMMES, ressources.getInt(MenuJeu.NB_POMMES, 0)-ressources.getInt(PRIX_USINE_POMMES, 0));
		        		        			e.putInt(MenuJeu.NB_SUCRE, ressources.getInt(MenuJeu.NB_SUCRE, 0)-ressources.getInt(PRIX_USINE_SUCRE, 0));
		        		        			//On passe l'usine a 1 afin de prouver qu'elle a été achetée
		        		        			e.putInt(MenuJeu.POSSEDE_USINE, ressources.getInt(MenuJeu.POSSEDE_USINE, 0) + 1);
		        		        			e.commit();
		        		        		} else {
		        		        			//Nécessaires au Toast
		        		        			Context context = getApplicationContext();
		        		        			int duration = Toast.LENGTH_SHORT;
		        		        			if(ressources.getInt(MenuJeu.NB_POMMES, 0) <= ressources.getInt(PRIX_USINE_POMMES, 0)) {
		        		        				//On indique au joueur qu'il n'a pas assez de pommes
		        		        				Toast toast = Toast.makeText(context, "Pas assez de pommes", duration);
		        		        				toast.show();
		        		        			} else if(ressources.getInt(MenuJeu.NB_SUCRE, 0) <= ressources.getInt(PRIX_USINE_SUCRE, 0)) {
		        		        				//On indique au joueur qu'il n'a pas assez de sucre
		        		        				Toast toast = Toast.makeText(context, "Pas assez de sucre", duration);
		        		        				toast.show();
			        		    		    	}
		        		    		//On indique au joueur qu'il n'a ni assez de pommes ni assez de sucre
		        		        	Toast toast = Toast.makeText(context, "Pas assez de pommes et de sucre", duration);
		        		        	toast.show();
			        		    	break;
		        		    		}
		        		        	//Si le joueur possède déjà l'usine
		        		        	} else {
		        		        		//Nécessaires au Toast
	        		        			Context context = getApplicationContext();
	        		        			int duration = Toast.LENGTH_SHORT;
		        		        		Toast toast = Toast.makeText(context, "Vous possédez déjà l'usine", duration);
	    		        				toast.show();
	        		    		    	}
		        		        //NON a été choisi
		        		        case DialogInterface.BUTTON_NEGATIVE:
		        		            break;
		        		        }

		        		        //Affichage du nombre de pommes quand l'activité commence
		        				TextView textPommes = (TextView) findViewById(R.id.nbPommes);
		        				textPommes.setText(Integer.toString(ressources.getInt(MenuJeu.NB_POMMES, 0)));
		        				
	        		    		//Affichage du nombre de sucre quand l'activité commence
	    		    			TextView textSucre = (TextView) findViewById(R.id.nbSucre);
	    		    			textSucre.setText(Integer.toString(ressources.getInt(MenuJeu.NB_SUCRE, 0)));
		        		    }
		        		};
		        		
		        		AlertDialog.Builder builder = new AlertDialog.Builder(MenuAmeliorationCarte.this);
		        		builder.setMessage("Acheter l'usine ?\n Usine déverouillée\n-"+ ressources.getInt(PRIX_USINE_POMMES, 0) + " pommes\n" + "-" + ressources.getInt(PRIX_USINE_SUCRE, 0) + " sucre").setPositiveButton("Oui", dialogClickListener).setNegativeButton("Non", dialogClickListener).show();
		        	}
	        	}
		});
	}
	
	public class ImageAdapter extends BaseAdapter {
	    private Context mContext;

	    public ImageAdapter(Context c) {
	        mContext = c;
	    }

	    public int getCount() {
	        return mThumbIds.length;
	    }

	    public Object getItem(int position) {
	        return null;
	    }

	    public long getItemId(int position) {
	        return 0;
	    }

	    // On créer une nouvelle ImageView pour chaque référence à un objet grâce à l'Adaptateur
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView;
	        if (convertView == null) {
	            imageView = new ImageView(mContext);
	            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
	            imageView.setPadding(8, 8, 8, 8);
	        } else {
	            imageView = (ImageView) convertView;
	        }
	        imageView.setImageResource(mThumbIds[position]);
	        return imageView;
	    }

	    // Référence et intégration de nos images
	    private Integer[] mThumbIds = {
	            R.drawable.ameliorationusine
	    };
	}
}