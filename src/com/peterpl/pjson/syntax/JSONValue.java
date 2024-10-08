package com.peterpl.pjson.syntax;

import com.peterpl.pjson.syntax.handler.*;

public interface JSONValue {
    DataTypeHandler<?> getHandler();
    String stringify();
}
