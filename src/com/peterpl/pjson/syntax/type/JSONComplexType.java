package com.peterpl.pjson.syntax.type;

import com.peterpl.pjson.syntax.*;

public abstract class JSONComplexType<K, T> implements JSONValue {
    protected final T elements;

    protected JSONComplexType(T elements) {
        this.elements = elements;
    }

    public abstract int size();

    public abstract void add(K key, JSONValue elem);

    public abstract JSONValue get(K key);

    public final T getElements() {
        return elements;
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
