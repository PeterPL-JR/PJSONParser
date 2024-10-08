package com.peterpl.pjson.syntax.type.simpleType;

import com.peterpl.pjson.syntax.handler.*;
import com.peterpl.pjson.syntax.handler.simpleType.*;

public class JSONFloat extends JSONNumber<Float> {
    public JSONFloat(float value) {
        super(value);
    }

    @Override
    public DataTypeHandler<JSONFloat> getHandler() {
        return new FloatHandler();
    }

    @Override
    public String stringify() {
        return getHandler().build(this);
    }
}
