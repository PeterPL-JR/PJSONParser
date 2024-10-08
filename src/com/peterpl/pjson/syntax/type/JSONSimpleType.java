package com.peterpl.pjson.syntax.type;

import com.peterpl.pjson.syntax.*;

public abstract class JSONSimpleType<T> implements JSONValue {
    private final T value;

    protected JSONSimpleType(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
