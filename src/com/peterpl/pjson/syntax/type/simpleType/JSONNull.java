package com.peterpl.pjson.syntax.type.simpleType;

import com.peterpl.pjson.syntax.handler.*;
import com.peterpl.pjson.syntax.handler.simpleType.*;
import com.peterpl.pjson.syntax.type.*;

public class JSONNull extends JSONSimpleType<Object> {
    public JSONNull() {
        super(null);
    }

    @Override
    public DataTypeHandler<JSONNull> getHandler() {
        return new NullHandler();
    }

    @Override
    public String stringify() {
        return getHandler().build(this);
    }
}
