
package com.sudansh.payoneer.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Applicable {
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("links")
    @Expose
    private Link links;

    public String getLabel() {
        return label;
    }

    public Link getLinks() {
        return links;
    }

}
