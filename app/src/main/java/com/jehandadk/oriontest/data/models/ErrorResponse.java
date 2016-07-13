package com.jehandadk.oriontest.data.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jehandad.kamal on 6/28/2016.
 */
public class ErrorResponse {

    List<ErrorDetail> errors = new ArrayList<>();

    public List<ErrorDetail> getResponse() {
        return errors;
    }

    public void setResponse(List<ErrorDetail> response) {
        this.errors = response;
    }
}
