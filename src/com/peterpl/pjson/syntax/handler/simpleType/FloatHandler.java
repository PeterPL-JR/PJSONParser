package com.peterpl.pjson.syntax.handler.simpleType;

import com.peterpl.pjson.syntax.handler.*;
import com.peterpl.pjson.syntax.type.simpleType.*;

public class FloatHandler extends SimpleTypeHandler<JSONFloat> {
    @Override
    public JSONFloat parse(String json) {
        return new JSONFloat(Float.parseFloat(json));
    }

    @Override
    public boolean is(String json) {
        int separatorIndex = json.indexOf('.');
        try {
            parse(json);
            return separatorIndex != 0 && separatorIndex != json.length() - 1;
        } catch (Exception e) {
            return false;
        }
    }
}
