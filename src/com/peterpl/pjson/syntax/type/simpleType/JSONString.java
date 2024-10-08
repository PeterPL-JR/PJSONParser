package com.peterpl.pjson.syntax.type.simpleType;

import com.peterpl.pjson.syntax.handler.*;
import com.peterpl.pjson.syntax.handler.simpleType.*;
import com.peterpl.pjson.syntax.type.*;

public class JSONString extends JSONSimpleType<String> {
    public JSONString(String value) {
        super(value);
    }

    @Override
    public DataTypeHandler<JSONString> getHandler() {
        return new StringHandler();
    }

    @Override
    public String stringify() {
        return getHandler().build(this);
    }
}
