package com.sudansh.payoneer.ui.home;

import static org.mockito.ArgumentMatchers.any;

import com.sudansh.payoneer.data.models.PaymentResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RunWith(MockitoJUnitRunner.class)
public class MainViewModelTest {

    private final PaymentRepository repo = Mockito.mock(PaymentRepository.class);
    private MainViewModel viewModel;

    @Before
    public void setup() {
        viewModel = new MainViewModel(repo);
    }

    @Test
    public void testSuccessResponse() {
        Call<PaymentResponse> mockedCall = Mockito.mock(Call.class);
        PaymentResponse response = new PaymentResponse();
        Mockito.when(repo.getPaymentOptions()).thenReturn(mockedCall);

        Mockito.doAnswer(invocation -> {
            Callback<PaymentResponse> callback = invocation.getArgument(0, Callback.class);

            callback.onResponse(mockedCall, Response.success(response));
            return null;
        }).when(mockedCall).enqueue(any(Callback.class));

        // inject mocked ApiInterface to your presenter
        // and then mock view and verify calls (and eventually use ArgumentCaptor to access call parameters)

        viewModel.getPayments();

        Mockito.verify(viewModel.paymentResponse.getValue().data, mockedCall);
    }
}