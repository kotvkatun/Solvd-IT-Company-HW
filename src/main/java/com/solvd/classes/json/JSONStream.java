package com.solvd.classes.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JSONStream {
    static Stream<JSONObject> of(JSONArray jsonArray) {
        return IntStream.range(0, jsonArray.length()).mapToObj(jsonArray::getJSONObject);
    }
}
