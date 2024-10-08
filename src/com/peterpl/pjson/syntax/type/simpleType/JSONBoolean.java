package com.peterpl.pjson.syntax.type.simpleType;

import com.peterpl.pjson.syntax.handler.*;
import com.peterpl.pjson.syntax.handler.simpleType.*;
import com.peterpl.pjson.syntax.type.*;

public class JSONBoolean extends JSONSimpleType<Boolean> {
    public JSONBoolean(Boolean value) {
        super(value);
    }

    @Override
    public DataTypeHandler<JSONBoolean> getHandler() {
        return new BooleanHandler();
    }

    @Override
    public String stringify() {
        return getHandler().build(this);
    }
}
