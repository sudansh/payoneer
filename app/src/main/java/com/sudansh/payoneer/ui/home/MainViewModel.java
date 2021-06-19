package com.sudansh.payoneer.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sudansh.payoneer.data.Resource;
import com.sudansh.payoneer.data.models.Applicable;
import com.sudansh.payoneer.data.models.PaymentResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class MainViewModel extends ViewModel {
    private final PaymentRepository paymentRepository;

    private final MutableLiveData<Resource<List<Applicable>>> _paymentResponse = new MutableLiveData<>();
    public LiveData<Resource<List<Applicable>>> paymentResponse = _paymentResponse;


    @Inject
    public MainViewModel(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void getPayments() {
        _paymentResponse.setValue(Resource.loading(null));
        paymentRepository.getPaymentOptions().enqueue(new Callback<PaymentResponse>() {
            @Override
            public void onResponse(@NonNull Call<PaymentResponse> call, @NonNull Response<PaymentResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    _paymentResponse.setValue(Resource.success(response.body().getNetworks().getApplicable()));
                } else {
                    String errorMessage;

                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());

                        if (jsonObject.has("message")) {
                            errorMessage = jsonObject.getString("message");
                        } else {
                            errorMessage = "Unable to complete your request";
                        }
                        _paymentResponse.setValue(Resource.error(errorMessage, null));
                    } catch (JSONException | IOException e) {
                        errorMessage = "Unable to complete your request";
                        _paymentResponse.setValue(Resource.error(errorMessage, null));
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<PaymentResponse> call, @NonNull Throwable t) {
                _paymentResponse.setValue(Resource.error("Unable to connect, check your connection", null));
            }
        });
    }
}
