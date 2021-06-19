package com.sudansh.payoneer.ui.home;

import com.sudansh.payoneer.ApiService;
import com.sudansh.payoneer.data.models.PaymentResponse;

import javax.inject.Inject;

import retrofit2.Call;

public class PaymentRepository {
    private final ApiService apiService;

    @Inject
    public PaymentRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Call<PaymentResponse> getPaymentOptions() {
        return apiService.getPayments();
    }
}
