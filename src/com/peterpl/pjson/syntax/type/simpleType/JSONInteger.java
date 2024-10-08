package com.peterpl.pjson.syntax.type.simpleType;

import com.peterpl.pjson.syntax.handler.*;
import com.peterpl.pjson.syntax.handler.simpleType.*;

public class JSONInteger extends JSONNumber<Integer> {
    public JSONInteger(Integer value) {
        super(value);
    }

    @Override
    public DataTypeHandler<JSONInteger> getHandler() {
        return new IntegerHandler();
    }

    @Override
    public String stringify() {
        return getHandler().build(this);
    }
}
