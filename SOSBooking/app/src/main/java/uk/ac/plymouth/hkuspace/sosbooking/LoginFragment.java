package uk.ac.plymouth.hkuspace.sosbooking;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Objects;

import uk.ac.plymouth.hkuspace.sosbooking.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime());
            Insets systemBarInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            int bottomPadding = Math.max(imeInsets.bottom, systemBarInsets.bottom);

            v.setPadding(systemBarInsets.left, systemBarInsets.top, systemBarInsets.right, bottomPadding);
            return insets;
        });

        binding.btnLogin.setOnClickListener(v -> {
            EditText usernameEditText = binding.etUsername;
            EditText passwordEditText = binding.etPassword;

            String username = usernameEditText.getText() != null
                    ? usernameEditText.getText().toString()
                    : "";
            String password = passwordEditText.getText() != null
                    ? passwordEditText.getText().toString()
                    : "";

            if (username.equals("Staff") && password.equals("Staff")) {

            } else {
                new MaterialAlertDialogBuilder(getActivity())
                        .setTitle(R.string.login_fail_title)
                        .setMessage(R.string.login_fail_message)
                        .setNegativeButton("Ok", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();
            }
        });

        binding.btnForgetpassword.setOnClickListener(v ->
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_to_ForgetPasswordFragment)
        );
        return binding.getRoot();
    }
}
