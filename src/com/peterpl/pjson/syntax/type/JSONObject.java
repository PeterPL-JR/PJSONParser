package com.peterpl.pjson.syntax.type;

import com.peterpl.pjson.syntax.*;
import com.peterpl.pjson.syntax.handler.*;
import com.peterpl.pjson.syntax.handler.complexType.*;

import java.util.*;

public class JSONObject extends JSONComplexType<String, HashMap<String, JSONValue>> implements JSONValue {
    private ArrayList<String> keys = new ArrayList<>();

    public JSONObject() {
        super(new HashMap<>());
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void add(String key, JSONValue elem) {
        keys.add(key);
        elements.put(key, elem);
    }

    @Override
    public JSONValue get(String key) {
        return elements.get(key);
    }

    public String[] getKeys() {
        String[] keys = new String[elements.size()];

        for(int i = 0; i < keys.length; i++) {
            keys[i] = this.keys.get(i);
        }
        return keys;
    }

    @Override
    public DataTypeHandler<JSONObject> getHandler() {
        return new JSONObjectHandler();
    }

    @Override
    public String stringify() {
        return getHandler().build(this);
    }
}
