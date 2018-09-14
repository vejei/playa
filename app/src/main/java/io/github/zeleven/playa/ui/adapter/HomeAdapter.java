package io.github.zeleven.playa.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.github.zeleven.playa.R;
import io.github.zeleven.playa.data.model.Article;
import io.github.zeleven.playa.data.model.Banner;
import io.github.zeleven.playa.ui.base.BaseRecyclerViewAdapter;
import io.github.zeleven.playa.ui.base.BaseViewHolder;
import io.github.zeleven.playa.ui.module.browser.BrowserActivity;

public class HomeAdapter extends BaseRecyclerViewAdapter<Article, RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;
    private static final int VIEW_TYPE_FOOTER = 2;

    private FooterViewHolder footerViewHolder;
    private List<Banner> bannerData = new ArrayList<>();

    public void setBannerData(List<Banner> bannerData) {
        this.bannerData = bannerData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                viewHolder = new HeaderViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.home_header, parent, false));
                break;
            case VIEW_TYPE_ITEM:
                viewHolder = new ItemViewHolder(LayoutInflater.from(
                        parent.getContext()).inflate(R.layout.article_list_item, parent, false));
                break;
            case VIEW_TYPE_FOOTER:
                viewHolder = new FooterViewHolder(LayoutInflater.from(
                        parent.getContext()).inflate(R.layout.list_footer, parent, false));
                footerViewHolder = (FooterViewHolder) viewHolder;
                break;
            default:
                viewHolder = new ItemViewHolder(LayoutInflater.from(
                        parent.getContext()).inflate(0, parent, false));
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).bind(data.get(position - 1));
        } else if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_HEADER;
        } else if (position == (getItemCount() - 1)) {
            return VIEW_TYPE_FOOTER;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    public void setLoading() {
        footerViewHolder.progressBar.setVisibility(View.VISIBLE);
        footerViewHolder.noMoreText.setVisibility(View.GONE);
    }

    public void setNoMore() {
        footerViewHolder.noMoreText.setVisibility(View.VISIBLE);
        footerViewHolder.progressBar.setVisibility(View.GONE);
    }

    public void resetFooterState() {
        footerViewHolder.footerLayout.setVisibility(View.GONE);
    }

    class HeaderViewHolder extends BaseViewHolder<Banner> {
        @BindView(R.id.banner) com.youth.banner.Banner bannerView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind() {
            bannerView.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            bannerView.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            });
            List<String> bannerImageList = new ArrayList<>();
            final List<String> bannerTitleList = new ArrayList<>();
            final List<String> bannerUrlList = new ArrayList<>();
            for (Banner banner : bannerData) {
                bannerImageList.add(banner.getImagePath());
                bannerTitleList.add(banner.getTitle());
                bannerUrlList.add(banner.getUrl());
            }
            bannerView.setImages(bannerImageList);
            bannerView.setBannerTitles(bannerTitleList);
            bannerView.isAutoPlay(true);
            bannerView.setIndicatorGravity(BannerConfig.RIGHT);
            bannerView.setDelayTime(5000);
            bannerView.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(itemView.getContext(), BrowserActivity.class);
                    intent.putExtra("URL", bannerUrlList.get(position));
                    intent.putExtra("TITLE", bannerTitleList.get(position));
                    itemView.getContext().startActivity(intent);
                }
            });
            bannerView.start();
        }
    }

    class ItemViewHolder extends BaseViewHolder<Article> {
        @BindView(R.id.super_chapter_name)
        TextView superChapterName;
        @BindView(R.id.chapter_name) TextView chapterName;
        @BindView(R.id.title) TextView title;
        @BindView(R.id.envelope_pic)
        ImageView envelopePic;
        @BindView(R.id.desc) TextView desc;
        @BindView(R.id.author) TextView author;
        @BindView(R.id.pub_date) TextView pubDate;

        public ItemViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(final Article data) {
            superChapterName.setText(data.getSuperChapterName());
            chapterName.setText(data.getChapterName());

            title.setText(data.getTitle());
            String imagePath = data.getEnvelopePic();
            if (imagePath != null && !imagePath.equals("")) {
                envelopePic.setVisibility(View.VISIBLE);
                Glide.with(itemView.getContext()).load(imagePath).into(envelopePic);
            } else {
                envelopePic.setVisibility(View.GONE);
            }

            String descContent = data.getDesc();
            if (descContent != null && !descContent.equals("")) {
                desc.setVisibility(View.VISIBLE);
                desc.setText(descContent);
            } else {
                desc.setVisibility(View.GONE);
            }

            author.setText(data.getAuthor());
            pubDate.setText(data.getNiceDate());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), BrowserActivity.class);
                    intent.putExtra("URL", data.getLink());
                    intent.putExtra("TITLE", data.getTitle());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    class FooterViewHolder extends BaseViewHolder {
        @BindView(R.id.footer_layout) RelativeLayout footerLayout;
        @BindView(R.id.progress_bar) ProgressBar progressBar;
        @BindView(R.id.no_more_text) TextView noMoreText;

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
