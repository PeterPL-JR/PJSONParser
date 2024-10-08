package com.peterpl.pjson.syntax.handler.simpleType;

import com.peterpl.pjson.syntax.handler.*;
import com.peterpl.pjson.syntax.type.simpleType.*;

public class NullHandler extends SimpleTypeHandler<JSONNull> {
    public JSONNull parse() {
        return new JSONNull();
    }
    @Override
    public JSONNull parse(String json) {
        return null;
    }

    @Override
    public boolean is(String json) {
        return json.equals("null");
    }
}
