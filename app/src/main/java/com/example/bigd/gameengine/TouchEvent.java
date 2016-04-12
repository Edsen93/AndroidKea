package com.example.bigd.gameengine;

/**
 * Created by BigD on 29/02/16.
 */
public class TouchEvent
{
    public enum TouchEventType
    {

        Down,
        Up,
        Dragged

    }
    public TouchEventType type;
    public int x;
    public int y;
    public int pointer;
}
