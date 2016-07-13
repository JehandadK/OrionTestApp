package com.jehandadk.oriontest.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jehandad.kamal on 4/25/2016.
 */
public class ErrorDetail {
    @SerializedName("errorParameters")
    @Expose
    private String errorParameters;
    @SerializedName("errorCode")
    @Expose
    private String errorCode;
    @SerializedName("errorKey")
    @Expose
    private String errorKey;
    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    public String getErrorParameters() {
        return errorParameters;
    }

    public void setErrorParameters(String errorParameters) {
        this.errorParameters = errorParameters;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
