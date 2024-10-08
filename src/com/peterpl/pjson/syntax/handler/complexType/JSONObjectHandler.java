package com.peterpl.pjson.syntax.handler.complexType;

import com.peterpl.pjson.*;
import com.peterpl.pjson.syntax.handler.*;
import com.peterpl.pjson.syntax.type.*;

public class JSONObjectHandler extends ComplexTypeHandler<JSONObject> {
    public JSONObjectHandler() {
        super(null);
    }
    public JSONObjectHandler(JSONParser jsonParser) {
        super(jsonParser);
    }

    @Override
    public JSONObject parse(String json) {
        json = json.substring(1, json.length() - 1);

        JSONObject obj = new JSONObject();

        while(!json.isEmpty()) {
            jsonParser.updateBlocks(json);

            int firstColonIndex = jsonParser.findNext(json, ':');
            int fieldBegin = firstColonIndex + 1;

            char nextChar = json.charAt(fieldBegin);

            String key = json.substring(1, firstColonIndex - 1);
            String value;

            if(nextChar == '{' || nextChar == '[') {
                int blockEnd = jsonParser.findBlock(fieldBegin, nextChar) + 1;
                value = json.substring(fieldBegin, blockEnd);
                json = fieldBegin + 1 < json.length() && blockEnd < json.length() ? json.substring(blockEnd + 1) : "";
            } else {
                int simpleValueEnd = jsonParser.findNext(json, ',');
                if(simpleValueEnd != -1) {
                    value = json.substring(fieldBegin, simpleValueEnd);
                    json = json.substring(simpleValueEnd + 1);
                } else {
                    value = json.substring(fieldBegin);
                    json = "";
                }
            }
            obj.add(key, jsonParser.parseJSONValue(value));
        }
        return obj;
    }

    @Override
    public String build(JSONObject obj) {
        String[] keys = obj.getKeys();
        String str = "{";

        for(int i = 0; i < keys.length; i++) {
            String key = keys[i];
            str += '\"' + key + '\"' + ":" + obj.get(key).stringify();
            if(i < obj.size() - 1) {
                str += ",";
            }
        }
        return str + "}";
    }
    @Override
    public boolean is(String json) {
        return json.charAt(0) == '{' && json.charAt(json.length() - 1) == '}';
    }
}
