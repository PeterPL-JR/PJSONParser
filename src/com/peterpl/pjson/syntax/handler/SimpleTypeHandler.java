package com.peterpl.pjson.syntax.handler;

import com.peterpl.pjson.syntax.type.*;

public abstract class SimpleTypeHandler<T extends JSONSimpleType<?>> implements DataTypeHandler<T> {
    @Override
    public String build(T value) {
        return value.getValue() + "";
    }
}
