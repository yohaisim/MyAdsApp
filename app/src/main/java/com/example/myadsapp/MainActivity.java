package com.example.myadsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adslibrary.models.Ad;
import com.example.adslibrary.models.ClickRequest;
import com.example.adslibrary.models.ViewRequest;
import com.example.adslibrary.network.ApiService;
import com.example.adslibrary.network.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView adImage;
    private TextView adTitle, adDescription, adTimer;
    private List<Ad> adList;
    private Handler handler = new Handler();
    private int currentIndex = 0;   // The current ad index
    private String currentClickUrl = "";
    private final int ROTATION_INTERVAL = 10; // Interval in seconds between ad rotations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link UI components
        adImage = findViewById(R.id.adImage);
        adTitle = findViewById(R.id.adTitle);
        adDescription = findViewById(R.id.adDescription);
        adTimer = findViewById(R.id.adTimer);

        // Ad image click -> open URL + click reporting
        adImage.setOnClickListener(v -> {
            if (!currentClickUrl.isEmpty()) {
                registerClick(adTitle.getText().toString());
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentClickUrl));
                startActivity(intent);
            }
        });

        // Load the list of ads from the library
        fetchAdsFromLibrary();
    }

    private void fetchAdsFromLibrary() {
        // Using the classes from your library
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        // GET request to fetch ads (like in old code, but from the com.example.adslibrary package)
        Call<List<Ad>> call = apiService.getAds();
        call.enqueue(new Callback<List<Ad>>() {
            @Override
            public void onResponse(Call<List<Ad>> call, Response<List<Ad>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adList = response.body();
                    if (!adList.isEmpty()) {
                        startAdRotation();
                    } else {
                        Toast.makeText(MainActivity.this, "No ads returned", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load ads", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Ad>> call, Throwable t) {
                Log.e("API_ERROR", "Error fetching ads", t);
                Toast.makeText(MainActivity.this, "Error connecting to server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startAdRotation() {
        // Display the first ad immediately
        updateAd();

        // Start a timer that runs every second, counting 10 seconds, then moves to the next ad
        handler.postDelayed(new Runnable() {
            int secondsRemaining = ROTATION_INTERVAL;

            @Override
            public void run() {
                if (secondsRemaining > 0) {
                    adTimer.setText(secondsRemaining + "s");
                    secondsRemaining--;
                    handler.postDelayed(this, 1000);
                } else {
                    // Time's up -> move to the next ad
                    updateAd();
                    secondsRemaining = ROTATION_INTERVAL; // reset the counter
                    handler.postDelayed(this, 1000);
                }
            }
        }, 0);
    }

    private void updateAd() {
        if (adList == null || adList.isEmpty()) return;

        // Choose the current ad based on currentIndex
        Ad ad = adList.get(currentIndex);

        // Report a view
        registerView(ad.getTitle());

        // Short fadeOut animation before switching to the next ad
        AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(500);
        fadeOut.setFillAfter(true);

        adImage.startAnimation(fadeOut);
        adTitle.startAnimation(fadeOut);
        adDescription.startAnimation(fadeOut);

        // After half a second of fadeOut, change the content and do fadeIn
        handler.postDelayed(() -> {
            // Update the ad content
            adTitle.setText(ad.getTitle());
            adDescription.setText(ad.getDescription());
            currentClickUrl = ad.getClickUrl();

            // Load the image with Picasso (from your library)
            Picasso.get().load(ad.getImageUrl()).into(adImage);

            // FadeIn animation
            AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
            fadeIn.setDuration(500);
            fadeIn.setFillAfter(true);

            adImage.startAnimation(fadeIn);
            adTitle.startAnimation(fadeIn);
            adDescription.startAnimation(fadeIn);

        }, 500);

        // Move to the next index (ad loop)
        currentIndex = (currentIndex + 1) % adList.size();
    }

    private void registerView(String adTitle) {
        // Report a view through the library API
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<Void> call = apiService.updateViewCount(new ViewRequest(adTitle));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("VIEWS", "View count updated for: " + adTitle);
                } else {
                    Log.e("VIEWS", "Failed to update view count");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("VIEWS", "Error updating view count", t);
            }
        });
    }

    private void registerClick(String adTitle) {
        // Report a click through the library API
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<Void> call = apiService.updateClickCount(new ClickRequest(adTitle));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("CLICK", "Click registered for: " + adTitle);
                } else {
                    Log.e("CLICK", "Failed to register click");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("CLICK", "Error: " + t.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop the handler to prevent memory leaks
        handler.removeCallbacksAndMessages(null);
    }
}
