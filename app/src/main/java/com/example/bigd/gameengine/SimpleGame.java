package com.example.bigd.gameengine;

import android.util.Log;

public class SimpleGame extends Game
{
    @Override
    public Screen createStartScreen()
    {
        Log.d("SimpleGame","*****************************");
        return new SimpleScreen(this);
    }


}
