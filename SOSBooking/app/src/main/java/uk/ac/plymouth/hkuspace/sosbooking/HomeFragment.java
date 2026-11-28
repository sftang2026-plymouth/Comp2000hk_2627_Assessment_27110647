package uk.ac.plymouth.hkuspace.sosbooking;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        NavController navController = navHostFragment.getNavController();

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_graph, R.id.HomeFragment).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavHostFragment navHostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.nav_host_home_fragment);
        BottomNavigationView bottomView = view.findViewById(R.id.bottom_navigation);
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(bottomView, navController);

        bottomView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if (id == R.id.DashboardFragment) {
                selectedFragment = new DashboardFragment();
            } else if (id == R.id.CalendarFragment) {
                selectedFragment = new CalendarFragment();
            } else if (id == R.id.MyBookingFragment) {
                selectedFragment = new MyBookingFragment();
            } else if (id == R.id.ApprovalFragment) {
                selectedFragment = new ApprovalFragment();
            } else if (id == R.id.ProfileFragment) {
                selectedFragment = new ProfileFragment();
            }
            if (selectedFragment != null) {
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_home_fragment, selectedFragment)
                        .commit();
                return true;
            }
            return false;
        });
    }

}