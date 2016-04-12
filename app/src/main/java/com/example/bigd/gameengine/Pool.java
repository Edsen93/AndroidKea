package com.example.bigd.gameengine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BigD on 29/02/16.
 */
public abstract class Pool<T>
{
    private List<T> items = new ArrayList<>();

    protected abstract T newItem();

    public T obtain()
    {
        int last = items.size() - 1;
        if (last == -1) return newItem();
        return items.remove(last);

    }

    public void free(T item)
    {
        items.add(item);
    }

}
