package com.peterpl.pjson.syntax.handler.simpleType;

import com.peterpl.pjson.syntax.handler.*;
import com.peterpl.pjson.syntax.type.simpleType.*;

public class BooleanHandler extends SimpleTypeHandler<JSONBoolean> {
    @Override
    public JSONBoolean parse(String json) {
        return new JSONBoolean(Boolean.parseBoolean(json));
    }

    @Override
    public boolean is(String json) {
        return json.equals("true") || json.equals("false");
    }
}
