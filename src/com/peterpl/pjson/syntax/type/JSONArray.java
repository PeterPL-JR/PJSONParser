package com.peterpl.pjson.syntax.type;

import com.peterpl.pjson.syntax.*;
import com.peterpl.pjson.syntax.handler.*;
import com.peterpl.pjson.syntax.handler.complexType.*;

import java.util.*;

public class JSONArray extends JSONComplexType<Integer, ArrayList<JSONValue>> implements JSONValue {
    public JSONArray(JSONValue...elements) {
        super(new ArrayList<>());

        for(JSONValue elem : elements) {
            add(elem);
        }
    }

    @Override
    public int size() {
        return elements.size();
    }

    public void add(JSONValue elem) {
        elements.add(elem);
    }
    @Override
    public void add(Integer key, JSONValue elem) {
        elements.add(key, elem);
    }

    @Override
    public JSONValue get(Integer key) {
        return elements.get(key);
    }

    @Override
    public DataTypeHandler<JSONArray> getHandler() {
        return new JSONArrayHandler();
    }

    @Override
    public String stringify() {
        return getHandler().build(this);
    }
}
