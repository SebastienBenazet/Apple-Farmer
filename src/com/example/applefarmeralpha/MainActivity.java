package com.example.applefarmeralpha;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGHT = 2000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        
      //Instanciation du MediaPlayer avec le son de la pomme croquée
      final MediaPlayer mPlayer = MediaPlayer.create(this,  R.raw.sonpomme);
      mPlayer.start();

        /* New Handler to start the Menu-Activity 
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* On créer un intent qui lancera l'activité principale après le splash screen */
                Intent mainIntent = new Intent(MainActivity.this,MenuDemarrer.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}