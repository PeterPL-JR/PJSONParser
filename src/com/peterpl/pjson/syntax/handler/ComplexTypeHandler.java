package com.peterpl.pjson.syntax.handler;

import com.peterpl.pjson.*;
import com.peterpl.pjson.syntax.type.*;

public abstract class ComplexTypeHandler<T extends JSONComplexType<?, ?>> implements DataTypeHandler<T> {
    protected JSONParser jsonParser;

    public ComplexTypeHandler(JSONParser jsonParser) {
        this.jsonParser = jsonParser;
    }
}
