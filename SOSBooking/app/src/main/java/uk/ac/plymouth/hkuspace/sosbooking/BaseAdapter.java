package uk.ac.plymouth.hkuspace.sosbooking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class BaseAdapter<T, U extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<U>{

    public interface OnItemClickListener<T> {
        void onItemClick(BaseAdapter adapter, T item, int position);
    }

    private List<T> items = new ArrayList<T>();
    private OnItemClickListener<T> listener;

    public void setItems(List<T> newItems) {
        this.items = newItems != null ? newItems : new ArrayList<>();
        notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener<T> listener) {
        this.listener = listener;
    }

    protected abstract int getLayoutResId(int viewType);
    protected abstract Function<View, U> viewHolderFactory();
    protected abstract void setItemToViewHolder(U holder, int position, T item);

    @NonNull
    @Override
    public U onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = getLayoutResId(viewType);
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        U viewHolder = viewHolderFactory().apply(view);
        BaseAdapter self = this;
        if (listener != null) {
            view.setOnClickListener(v -> {
                //int position = viewHolder.getBindingAdapterPosition();
                int position = viewHolder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null) {
                    listener.onItemClick(self, items.get(position), position);
                }
            });
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull U holder, int position) {
        setItemToViewHolder(holder, position, items.get(position));
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
