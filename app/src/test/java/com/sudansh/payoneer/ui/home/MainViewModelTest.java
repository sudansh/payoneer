package com.sudansh.payoneer.ui.home;

import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.sudansh.payoneer.data.models.PaymentResponse;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import retrofit2.Call;
import retrofit2.Callback;

@RunWith(MockitoJUnitRunner.class)
public class MainViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private final PaymentRepository repo = Mockito.mock(PaymentRepository.class);
    private MainViewModel viewModel;

    @Mock
    Call<PaymentResponse> apiCall;

    @Before
    public void setup() {
        viewModel = new MainViewModel(repo);
        when(repo.getPaymentOptions()).thenReturn(apiCall);
    }

    @Test
    public void testSuccessResponse() {
        viewModel.getPayments();
        ArgumentCaptor<Callback<PaymentResponse>> argumentCaptor = ArgumentCaptor.forClass(Callback.class);

        Mockito.verify(apiCall).enqueue(argumentCaptor.capture());

    }
}