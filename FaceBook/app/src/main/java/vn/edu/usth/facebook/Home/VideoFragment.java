package vn.edu.usth.facebook.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import androidx.fragment.app.Fragment;
import vn.edu.usth.facebook.R;

public class VideoFragment extends Fragment {
    // URL of the Facebook video to be played
    private String videoUrl = "https://www.facebook.com/plugins/video.php?href=https://www.facebook.com/video.php?v=1531448284152067";// Replace with your Facebook video URL

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);

        // Set up the search button to launch Video_Search_Activity
        ImageButton searchButton = v.findViewById(R.id.search_video_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Video_Search_Activity.class);
                startActivity(i);
            }
        });

        // Set up the save button to launch Video_Save_Activity
        ImageButton saveButton = v.findViewById(R.id.save_video_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireContext(), vn.edu.usth.facebook.More.Video_Save_Activity.class);
                startActivity(i);
            }
        });

        // Set up the WebView
        WebView webView = v.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient()); // Ensure links open in the WebView

        // Enable JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Load the Facebook video
        String html = "<html><body style=\"margin:0;padding:0;\"><iframe width=\"100%\" height=\"100%\" src=\""
                + videoUrl + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

        // Load the HTML into the WebView
        webView.loadData(html, "text/html", "utf-8");

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        // Pause WebView playback if needed
    }

    @Override
    public void onResume() {
        super.onResume();
        // You can reload the video or handle it here if needed
    }
}
