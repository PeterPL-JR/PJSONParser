package com.peterpl.pjson.syntax.handler.simpleType;

import com.peterpl.pjson.*;
import com.peterpl.pjson.syntax.handler.*;
import com.peterpl.pjson.syntax.type.simpleType.*;

import java.util.*;

public class StringHandler extends SimpleTypeHandler<JSONString> {
    public static final HashMap<String, Character> SPECIAL_CHARS = new HashMap<>();

    static {
        SPECIAL_CHARS.put("\"", '\"');
        SPECIAL_CHARS.put("n", '\n');
        SPECIAL_CHARS.put("t", '\t');
        SPECIAL_CHARS.put("\\", '\\');
        SPECIAL_CHARS.put("r", '\r');
        SPECIAL_CHARS.put("b", '\b');
        SPECIAL_CHARS.put("f", '\f');
    }

    private JSONUtils utils = new JSONUtils();

    @Override
    public JSONString parse(String json) {
        return new JSONString(json.substring(1, json.length() - 1));
    }

    @Override
    public String build(JSONString value) {
        return '\"' + value.getValue() + '\"';
    }

    @Override
    public boolean is(String json) {
        if(!(json.charAt(0) == '"' && json.charAt(json.length() - 1) == '"')) {
            return false;
        }
        String content = json.substring(1, json.length() - 1);
        ArrayList<Character> chars = new ArrayList<>();

        for(int i = 0; i < content.length(); i++) {
            char ch = content.charAt(i);
            if(ch == '\\') {
                if(i + 1 >= content.length() || !isSpecialChar(content.charAt(i + 1))) {
                    return false;
                } else {
                    i++;
                }
            } else {
                chars.add(ch);
            }
        }
        return !chars.contains('\"') && !chars.contains('\\');
    }

    private static boolean isSpecialChar(Character ch) {
        return SPECIAL_CHARS.containsKey(ch + "");
    }
}
