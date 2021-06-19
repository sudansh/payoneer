
package com.sudansh.payoneer.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentResponse {
    @SerializedName("networks")
    @Expose
    private Networks networks;

    public Networks getNetworks() {
        return networks;
    }
}

