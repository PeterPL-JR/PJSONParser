package com.peterpl.pjson.syntax.handler.complexType;

import com.peterpl.pjson.*;
import com.peterpl.pjson.syntax.handler.*;
import com.peterpl.pjson.syntax.type.*;

public class JSONArrayHandler extends ComplexTypeHandler<JSONArray> {
    public JSONArrayHandler() {
        super(null);
    }
    public JSONArrayHandler(JSONParser jsonParser) {
        super(jsonParser);
    }

    @Override
    public JSONArray parse(String json) {
        json = json.substring(1, json.length() - 1);

        JSONArray arr = new JSONArray();

        while(!json.isEmpty()) {
            jsonParser.updateBlocks(json);

            int fieldBegin = 0;
            char nextChar = json.charAt(fieldBegin);

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
            arr.add(jsonParser.parseJSONValue(value));
        }
        return arr;
    }

    @Override
    public String build(JSONArray arr) {
        String str = "[";
        for(int i = 0; i < arr.size(); i++) {
            str += arr.get(i).stringify();
            if(i < arr.size() - 1) {
                str += ',';
            }
        }
        return str + "]";
    }

    @Override
    public boolean is(String json) {
        return json.charAt(0) == '[' && json.charAt(json.length() - 1) == ']';
    }
}
