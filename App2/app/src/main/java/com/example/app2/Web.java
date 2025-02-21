package com.example.app2;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Web extends AppCompatActivity {
    private WebView wView;
    private enum Browser {
        BRAVE("https://search.brave.com"),
        GOOGLE("https://google.com"),
        BING("https://bing.com");

        private final String browserURL;
        Browser(String url) {
            this.browserURL = url;
        }

        public String getBrowserURL () {
            return this.browserURL;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_web);

        // Get radio-button group and WebView
        RadioGroup rg = this.findViewById(R.id.rbGroup);
        this.wView = this.findViewById(R.id.seeWeb);

        // Put the initial checked button and the url to webView
        this.compareAndPut(rg, 0, this.wView);

        // Put listener to change checked button
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                group.clearCheck();
                RadioButton button = (RadioButton) group.getChildAt(checkedId);
                button.setChecked(true);

                for (Browser browser: Browser.values()) {
                    if (browser.name().equalsIgnoreCase(button.getText().toString())) {
                        wView.loadUrl(browser.getBrowserURL());
                        break;
                    }
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void compareAndPut (RadioGroup rg, int index, WebView webView) {
        RadioButton[] buttons = this.getRadioButtons(rg);

        for (Browser browser: Browser.values()) {
            if (browser.name().equalsIgnoreCase(buttons[index].getText().toString())) {
                rg.clearCheck();
                rg.check(index);
                webView.loadUrl(browser.getBrowserURL());
                break;
            }
        }
    }

    private RadioButton[] getRadioButtons (RadioGroup rg) {
        RadioButton[] radioButtons = new RadioButton[rg.getChildCount()];

        for (int i = 0; i < rg.getChildCount(); i++) {
            View view = rg.getChildAt(i);

            if (view instanceof RadioButton) radioButtons[i] = (RadioButton) view;
        }

        return radioButtons;
    }
}