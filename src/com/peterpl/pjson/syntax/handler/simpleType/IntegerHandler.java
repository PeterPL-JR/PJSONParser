package com.peterpl.pjson.syntax.handler.simpleType;

import com.peterpl.pjson.syntax.handler.*;
import com.peterpl.pjson.syntax.type.simpleType.*;

public class IntegerHandler extends SimpleTypeHandler<JSONInteger> {
    @Override
    public JSONInteger parse(String json) {
        return new JSONInteger(Integer.parseInt(json));
    }

    @Override
    public boolean is(String json) {
        try {
            parse(json);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
