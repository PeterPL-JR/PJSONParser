package com.peterpl.pjson;

import com.peterpl.pjson.syntax.*;

public class JSONBuilder {
    public String build(JSONValue value) {
        return value.stringify();
    }
}
