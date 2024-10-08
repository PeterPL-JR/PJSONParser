package com.peterpl.pjson.syntax.handler;

import com.peterpl.pjson.syntax.*;

public interface DataTypeHandler<T extends JSONValue> {
    T parse(String json);
    String build(T value);
    boolean is(String json);
}
