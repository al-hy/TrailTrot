package com.example.ahy.myapplication;

/**
 * Created by ahy on 5/2/17.
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;

import java.io.Serializable;

/**
 * Created by ahy on 5/1/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class YelpInfo extends SerializableSerializer {
    @JsonProperty("access_token")
    public  String accessToken;
    @JsonProperty("token_type")
    private  String tokenType;
    @JsonProperty("expires_in")
    private  int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String at) {
        this.accessToken = at;
    }

    public  String getTokenType() {
        return tokenType;
    }

    public  void setTokenType(String tt) {
       this.tokenType = tt;
    }

    public  int getExpiresIn() {
        return expiresIn;
    }

    public  void setExpiresIn(int e) {
        this.expiresIn = e;
    }
}
