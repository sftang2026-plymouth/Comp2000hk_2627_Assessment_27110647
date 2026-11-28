package uk.ac.plymouth.hkuspace.sosbooking;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.function.Function;

public class DashboardFragment extends Fragment {

    private DashboardViewModel mViewModel;

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SuggestionItemAdapter suggestionItemAdapter = new SuggestionItemAdapter();
        suggestionItemAdapter.setListener((adapter, item, position) -> {

        });
        ((RecyclerView)getView().findViewById(R.id.rv_suggestion)).setAdapter(suggestionItemAdapter);
        BookingItemAdapter upcomingItemAdapter = new BookingItemAdapter();
        upcomingItemAdapter.setListener((adapter, item, position) -> {

        });
        ((RecyclerView)getView().findViewById(R.id.rv_upcoming)).setAdapter(upcomingItemAdapter);
        BookingItemAdapter waitingItemAdapter = new BookingItemAdapter();
        waitingItemAdapter.setListener((adapter, item, position) -> {

        });
        ((RecyclerView)getView().findViewById(R.id.rv_waiting)).setAdapter(waitingItemAdapter);

        mViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        mViewModel.getSuggestionItems().observe(getViewLifecycleOwner(), suggestionItemAdapter::setItems);
        mViewModel.getUpcomingItems().observe(getViewLifecycleOwner(), upcomingItemAdapter::setItems);
        mViewModel.getWaitingItems().observe(getViewLifecycleOwner(), waitingItemAdapter::setItems);
    }

    protected static class SuggestionItemAdapter
            extends BaseAdapter<DashboardViewModel.SuggestionItem, SuggestionItemViewHolder> {

        @Override
        protected int getLayoutResId(int viewType) {
            return R.layout.item_suggestion;
        }

        @Override
        protected Function<View, SuggestionItemViewHolder> viewHolderFactory() {
            return SuggestionItemViewHolder::new;
        }

        @Override
        protected void setItemToViewHolder(SuggestionItemViewHolder holder, int position, DashboardViewModel.SuggestionItem item) {
            holder.tvTitle.setText(item.getTitle());
            String subtitle = item.getSubtitle();
            holder.tvSubtitle.setText(subtitle);
            holder.tvSubtitle.setVisibility(subtitle.isEmpty() ? View.GONE : View.VISIBLE);
        }
    }

    protected static class SuggestionItemViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvSubtitle;

        SuggestionItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvSubtitle = itemView.findViewById(R.id.tv_subtitle);
        }
    }

    protected static class BookingItemAdapter
            extends BaseAdapter<DashboardViewModel.BookingItem, BookingItemViewHolder> {

        @Override
        protected int getLayoutResId(int viewType) {
            return R.layout.item_request;
        }

        @Override
        protected Function<View, BookingItemViewHolder> viewHolderFactory() {
            return BookingItemViewHolder::new;
        }

        @Override
        protected void setItemToViewHolder(BookingItemViewHolder holder, int position, DashboardViewModel.BookingItem item) {
            holder.tvTitle.setText(item.getString());
        }
    }

    protected static class BookingItemViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;

        BookingItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}