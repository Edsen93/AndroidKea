package com.example.bigd.gameengine;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;

import java.util.Random;

public class SimpleScreen extends Screen
{
    Bitmap bitmap;
    int x = 0;
    int y = 0;
    Random rand = new Random();
    int clearColor = Color.YELLOW;


    public SimpleScreen(Game game)
    {
        super(game);
        bitmap = game.loadBitmap("bob.png");
    }

    public void update(float deltaTime) {

        game.clearFramebuffer(clearColor);

        for (int pointer = 0; pointer < 5; pointer++)
        {
            if (game.isTouchDown(pointer)){
                game.drawBitmap(bitmap, game.getTouchX(pointer), game.getTouchY(pointer));

            }
        }

        /*
        game.drawBitmap(bitmap, 10, 10);
        game.drawBitmap(bitmap, 100, 140, 0, 0, 64, 64);
        if (game.isKeyPressed(KeyEvent.KEYCODE_MENU))
        {
            clearColor = rand.nextInt();
        }*/
        float x = game.getAccelerometer()[0];
        float y = - game.getAccelerometer()[1];
        x = (x/100) * game.getFramebufferWidth()/2 + game.getOffScreenWidth()/2;
        y = (y/100) * game.getFramebufferHeight()/2 + game.getOffScreenHeight()/2;
        game.drawBitmap(bitmap, (int)x-64, (int)y-64);


    }
    public void pause(){
        Log.d("SimpleScreen" , " we ar pausing");
    }
    public void resume(){

        Log.d("SimpleScreen" , " we ar resuming");
    }
    public void dispose(){
        Log.d("SimpleScreen" , " we ar disposing the game");
    }
}


