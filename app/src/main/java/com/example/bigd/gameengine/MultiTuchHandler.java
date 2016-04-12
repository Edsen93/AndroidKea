package com.example.bigd.gameengine;

import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import java.util.List;

/**
 * Created by BigD on 29/02/16.
 */
public class MultiTuchHandler implements TouchHandler, View.OnTouchListener
{
    private boolean[] isTouched = new boolean[20];
    private int[] touchX = new int[20];
    private int[] touchY = new int[20];

    private List<TouchEvent> touchEventsBuffer;
    private TouchEventPool touchEventPool;

    public MultiTuchHandler(View view, List<TouchEvent> touchEventsBuffer, TouchEventPool touchEventPool)
    {
        view.setOnTouchListener(this);
        this.touchEventsBuffer = touchEventsBuffer;
        this.touchEventPool = touchEventPool;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        synchronized (touchEventsBuffer)
        {


            TouchEvent touchEvent = null;
            int action = event.getAction() & MotionEvent.ACTION_MASK;
            int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_MASK;
            int poointerId = event.getPointerId(pointerIndex);

            switch (action)
            {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    touchEvent = touchEventPool.obtain();
                    touchEvent.type = TouchEvent.TouchEventType.Down;
                    touchEvent.pointer = poointerId;
                    touchEvent.x = touchX[poointerId] = (int) (event.getX(pointerIndex));
                    touchEvent.y = touchY[poointerId] = (int) (event.getY(pointerIndex));
                    isTouched[poointerId] = true;
                    touchEventsBuffer.add(touchEvent);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_CANCEL:
                    touchEvent = touchEventPool.obtain();
                    touchEvent.type = TouchEvent.TouchEventType.Up;
                    touchEvent.pointer = poointerId;
                    touchEvent.x = touchX[poointerId] = (int) (event.getX(pointerIndex));
                    touchEvent.y = touchY[poointerId] = (int) (event.getY(pointerIndex));
                    isTouched[poointerId] = false;
                    touchEventsBuffer.add(touchEvent);
                    break;
                case MotionEvent.ACTION_MOVE:
                    int pointerCount = event.getPointerCount();
                    for (int i = 0; i < pointerCount; i++)
                    {
                        touchEvent = touchEventPool.obtain();
                        touchEvent.type = TouchEvent.TouchEventType.Dragged;
                        pointerIndex = i;
                        poointerId = event.getPointerId(pointerIndex);
                        touchEvent.pointer = poointerId;
                        touchEvent.x = touchX[poointerId] = (int) (event.getX(pointerIndex));
                        touchEvent.y = touchY[poointerId] = (int) (event.getY(pointerIndex));
                        touchEventsBuffer.add(touchEvent);

                    }
                    break;

            }
        }
        return true;
    }

    @Override
    public synchronized boolean isTouchDown(int pointer)
    {
        if (pointer < 0 || pointer > 21)
            return false;
        else
            return isTouched[pointer];
    }

    @Override
    public int getTouchX(int pointer)

    {
        if (pointer < 0 || pointer > 21) return -1;
        else return touchX[pointer];

    }

    @Override
    public int getTouchY(int pointer)
    {
        if (pointer < 0 || pointer > 21) return -1;
        else return touchY[pointer];
    }
}
