package io.github.zeleven.playa.ui.module.browser;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import io.github.zeleven.playa.R;
import io.github.zeleven.playa.ui.base.BaseActivity;

public class BrowserActivity extends BaseActivity<BrowserPresenter> implements BrowserContract.View {
    ActionBar actionBar;

    private String url;
    private String title;

    @BindView(R.id.progress_bar) ProgressBar progressBar;
    @BindView(R.id.web_view) WebView webView;

    @Override
    public int getLayout() {
        return R.layout.activity_browser;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        url = intent.getStringExtra("URL");
        title = intent.getStringExtra("TITLE");

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(title != null ? title : "");
        }

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
            }
        });

        webView.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.browser, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.star:
                break;
            case R.id.share:
                openShareDialog();
                break;
            case R.id.reload:
                webView.reload();
                break;
            case R.id.copy_link:
                copyTextToClipboard();
                break;
            case R.id.open_in_browser:
                openBrowser();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void openShareDialog() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String dialogTitle = getString(R.string.share_dialog_title);
        String shareContent = "【" + title + "】：" + url
                + " （" + getString(R.string.share_source) + "）";
        intent.putExtra(Intent.EXTRA_SUBJECT, dialogTitle);
        intent.putExtra(Intent.EXTRA_TEXT, shareContent);
        startActivity(intent);
    }

    @Override
    public void copyTextToClipboard() {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("link", url);
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(this, R.string.text_copied, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openBrowser() {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}
