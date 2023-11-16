package com.solvd.classes.exceptions;

import org.json.JSONException;

public class IncorrectJSONFormatException extends JSONException {
    public IncorrectJSONFormatException(String message) {
        super(message);
    }
}
