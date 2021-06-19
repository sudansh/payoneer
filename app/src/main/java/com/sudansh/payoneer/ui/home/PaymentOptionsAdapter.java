package com.sudansh.payoneer.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sudansh.payoneer.R;
import com.sudansh.payoneer.data.models.Applicable;
import com.sudansh.payoneer.databinding.ItemPaymentOptionBinding;

public class PaymentOptionsAdapter extends ListAdapter<Applicable, PaymentOptionsAdapter.PaymentOptionViewHolder> {

    private final ItemClickInterface onItemClick;

    public PaymentOptionsAdapter(ItemClickInterface onItemClick) {
        super(new DiffUtil.ItemCallback<Applicable>() {
            @Override
            public boolean areItemsTheSame(@NonNull Applicable oldItem, @NonNull Applicable newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areContentsTheSame(@NonNull Applicable oldItem, @NonNull Applicable newItem) {
                return oldItem.getLabel().equals(newItem.getLabel());
            }
        });
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public PaymentOptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPaymentOptionBinding binding = ItemPaymentOptionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PaymentOptionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentOptionViewHolder holder, int position) {
        holder.bind(getItem(position));
        holder.binding.getRoot().setOnClickListener(v -> onItemClick.onItemClick(getItem(position)));
    }


    static class PaymentOptionViewHolder extends RecyclerView.ViewHolder {
        ItemPaymentOptionBinding binding;

        public PaymentOptionViewHolder(ItemPaymentOptionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Applicable applicable) {
            binding.name.setText(applicable.getLabel());

            Glide.with(binding.getRoot().getContext())
                    .load(applicable.getLinks().getLogo())
                    .placeholder(R.drawable.ic_card_placeholder)
                    .into(binding.logo);

        }
    }

}




