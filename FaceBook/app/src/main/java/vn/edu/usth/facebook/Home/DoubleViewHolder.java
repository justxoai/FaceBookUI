package vn.edu.usth.facebook.Home;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.Video.VideoItem;

public class DoubleViewHolder extends RecyclerView.ViewHolder{

    ImageView avatarView,  postView;
    TextView nameView, timeView, contentView;
    WebView url;

    public DoubleViewHolder(@NonNull View itemView){
        super(itemView);
        avatarView = itemView.findViewById(R.id.image_avatar);
        postView = itemView.findViewById(R.id.content_imageview);
        url = itemView.findViewById(R.id.webView);
        nameView = itemView.findViewById(R.id.user_name);
        timeView = itemView.findViewById(R.id.time);
        contentView = itemView.findViewById(R.id.post_content);
    }

    public void bind(DoubleItem videoUrl) {
        if (videoUrl == null || videoUrl.getUrl() == null) {
            return;
        }

        url.setWebViewClient(new WebViewClient());
        WebSettings webSettings = url.getSettings();
        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptEnabled(true);

        String videoUrlString = videoUrl.getUrl();

        String html = "<html><body style=\"margin:0;padding:0;\"><iframe width=\"100%\" height=\"100%\" src=\""
                + videoUrlString + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

        url.loadData(html, "text/html", "utf-8");
    }
}
