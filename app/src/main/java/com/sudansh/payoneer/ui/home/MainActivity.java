package com.sudansh.payoneer.ui.home;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.sudansh.payoneer.R;
import com.sudansh.payoneer.data.models.Applicable;
import com.sudansh.payoneer.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements ItemClickInterface {

    private MainViewModel mainViewModel;
    private PaymentOptionsAdapter paymentOptionsAdapter;
    private ActivityMainBinding binding;
    private ItemClickInterface onItemClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onItemClick = this;

        binding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
        initialiseViewModel();
        paymentOptionsAdapter = new PaymentOptionsAdapter(onItemClick);
        binding.rvPayment.setAdapter(paymentOptionsAdapter);
    }

    private void initialiseViewModel() {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        observeViewViewModel();

        mainViewModel.getPayments();
    }

    private void observeViewViewModel() {
        mainViewModel.paymentResponse.observe(this, resource -> {
            switch (resource.status) {
                case SUCCESS:
                    paymentOptionsAdapter.submitList(resource.data);
                    binding.loadingProgress.setVisibility(View.GONE);
                    break;
                case ERROR:
                    showError(resource.message);
                    binding.loadingProgress.setVisibility(View.GONE);
                    break;
                case LOADING:
                    binding.loadingProgress.setVisibility(View.VISIBLE);
                    break;
            }
        });
    }

    private void showError(String message) {
        Snackbar snackbar = Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_INDEFINITE);

        snackbar.show();
        snackbar.setAction(R.string.retry, v -> {
            snackbar.dismiss();
            mainViewModel.getPayments();
        });
    }

    @Override
    protected void onDestroy() {
        onItemClick = null;
        super.onDestroy();
    }

    @Override
    public void onItemClick(Applicable applicable) {
        //item clicked
    }
}