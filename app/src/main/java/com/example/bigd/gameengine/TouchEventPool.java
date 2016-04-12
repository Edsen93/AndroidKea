package com.example.bigd.gameengine;

/**
 * Created by BigD on 29/02/16.
 */
public class TouchEventPool extends Pool<TouchEvent>
{
    protected TouchEvent newItem()

    {
        return new TouchEvent();
    }
}
