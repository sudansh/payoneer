
package com.sudansh.payoneer.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Link {

    @SerializedName("logo")
    @Expose
    private String logo;

    public String getLogo() {
        return logo;
    }
}