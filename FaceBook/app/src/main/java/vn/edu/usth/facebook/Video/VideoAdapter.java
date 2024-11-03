package vn.edu.usth.facebook.Video;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import vn.edu.usth.facebook.R;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final List<String> videoUrls;
    private final Context context;

    public VideoAdapter(Context context, List<String> videoUrls) {
        this.context = context;
        this.videoUrls = videoUrls;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        String videoUrl = videoUrls.get(position);
        holder.bind(videoUrl);
    }

    @Override
    public int getItemCount() {
        return videoUrls.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        private final WebView videoWebView;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoWebView = itemView.findViewById(R.id.videoWebView);
        }

        public void bind(String videoUrl) {
            videoWebView.setWebViewClient(new WebViewClient());
            WebSettings webSettings = videoWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            String html = "<html><body style=\"margin:0;padding:0;\"><iframe width=\"100%\" height=\"100%\" src=\""
                    + videoUrl + "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
            videoWebView.loadData(html, "text/html", "utf-8");
        }
    }
}
