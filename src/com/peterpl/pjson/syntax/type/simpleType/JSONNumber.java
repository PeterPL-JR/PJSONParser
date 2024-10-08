package com.peterpl.pjson.syntax.type.simpleType;

import com.peterpl.pjson.syntax.type.*;

public abstract class JSONNumber<T extends Number> extends JSONSimpleType<T> {
    public JSONNumber(T value) {
        super(value);
    }
}
