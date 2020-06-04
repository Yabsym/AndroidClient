package com.riskm.androidclient.ui.admin.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.riskm.androidclient.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final WebView webViewIndex = root.findViewById(R.id.web_Index);
        webViewIndex.getSettings().setJavaScriptEnabled(true); // 开启javascript支持
        webViewIndex.getSettings().setSupportZoom(false);
        webViewIndex.getSettings().setAppCacheEnabled(false);
        webViewIndex.getSettings().setAllowFileAccess(true);
        webViewIndex.addJavascriptInterface(this, "changeVersionJs");
        if (getResources().getConfiguration().locale.getLanguage().equals("zh")) {
            webViewIndex.loadUrl("file:///android_asset/index.html");
        } else {
            webViewIndex.loadUrl("file:///android_asset/index.html");
        }
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
