
package com.sudansh.payoneer.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Networks {

    @SerializedName("applicable")
    @Expose
    private List<Applicable> applicable;

    public List<Applicable> getApplicable() {
        return applicable;
    }

}