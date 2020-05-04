package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    RecyclerView rel;
    LinearLayoutManager linearLayoutManager;
    Adapter adapter;
    TextView tvquestion;
    public static Button next;
    int pos = 0;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rel = findViewById(R.id.rel);
        tvquestion = findViewById(R.id.question);
        next = findViewById(R.id.next);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rel.setLayoutManager(linearLayoutManager);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos++;

                if (pos == jsonArray.length()) {
                    pos = 0;
                }
                findjson();

            }
        });

        findjson();
    }

    private void findjson() {
        String jsonresponse = "{\n" +
                "\"status\":true,\n" +
                "\"response\":[\n" +
                "{\n" +
                "\"id\":\"1\",\n" +
                "\"question\":\"Which of the following make-up products do you currently use? (Select all that apply)\",\n" +
                "\"qtype\":\"2\",\n" +
                "\"options\":\"Lipstick~^Lip color~^Lip gloss~^Lip liner~^Other lip color products~^Liquid foundation~^Cream foundation~^Compact foundation~^Mineral powder foundation~^Other foundation~^Face powder~^Concealer~^Blusher~^Bronzer~^Eye shadow~^Mascara~^Eyeliner~^Other~^None~^Eyebrow pencil~^Eyebrow powder~^Not Applicable\",\n" +
                "\"answer\":\", Lipstick\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":\"2\",\n" +
                "\"question\":\"Which of the following facial skin care products do you currently use? Please select all that apply.\",\n" +
                "\"qtype\":\"2\",\n" +
                "\"options\":\"Facial cleansers or wash~^Facial scrub, exfoliator or dermabrasion~^Makeup remover~^Toners or astringents~^Anti-acne products~^Oil blotting paper~^Liquid moisturizer~^Cream moisturizer~^Gel moisturizer~^Beauty essence or serum~^Eye treatment including creams, lotions and gels~^Facial massage cream~^Facial mask or pack, such as sheet, cream or gel type~^Facial sunscreen\\/UV protection~^Other~^None\",\n" +
                "\"answer\":\", Facial cleansers or wash\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":\"3\",\n" +
                "\"question\":\"Do you regularly see a dermatologist for skin care maintainance?\",\n" +
                "\"qtype\":\"1\",\n" +
                "\"options\":\"Yes~^No\",\n" +
                "\"answer\":\"No\"\n" +
                "}\n" +
                "]\n" +
                "}";


        try {
            JSONObject jsonObject = new JSONObject(jsonresponse);
            jsonArray = jsonObject.getJSONArray("response");


            JSONObject jsonObject1 = jsonArray.getJSONObject(pos);
            String question = jsonObject1.getString("question");
            String answer = jsonObject1.getString("answer").replace(", ", "");
            tvquestion.setText("1." + question);

            String options = jsonObject1.getString("options").replace("~^", "#");
            Log.d("question", question);
            String[] arrays = options.split("#");
            Log.d("questionans", "" + arrays.length);
            for (int j = 0; j < arrays.length; j++) {
                Log.d("questionans", arrays[j]);

            }

            adapter = new Adapter(MainActivity.this, arrays, answer,question);
            rel.setAdapter(adapter);
          //  helper.hashMaplist.put(question,answer);


            String status = jsonObject.getString("status");
        } catch (Exception e) {

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (pos > 0) {
            pos--;
            findjson();
        }


    }
}
