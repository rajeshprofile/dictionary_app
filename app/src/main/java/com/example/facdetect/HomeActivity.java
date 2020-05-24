package com.example.facdetect;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    String url;
    private EditText enterWord;
    private TextView showDef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        showDef = (TextView) findViewById(R.id.showDef);
        enterWord = (EditText) findViewById(R.id.enterWord);
    }

    private String dictionaryEntries() {
        final String language = "en-gb";
        final String word = enterWord.getText().toString().trim();
        final String fields = "definitions";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }

    public void sendRequestOnClick(View v) {
        DictionayRequest dR = new DictionayRequest(this, showDef);
        url = dictionaryEntries();
        dR.execute(url);
    }
}
