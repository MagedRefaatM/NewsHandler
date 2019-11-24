package com.newshandler.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.newshandler.R;
import com.newshandler.model.entities.FeedItem;
import com.newshandler.presenter.NewsContentsPresenter;
import com.squareup.picasso.Picasso;

public class NewsContentsFragment extends Fragment implements AddingToNewsPage{

    private ImageView newsImageView;
    private TextView newsTitleTv;
    private TextView newsLinkTv;
    private TextView newsSourceTv;
    private TextView newsDescriptionTv;
    private TextView newsPubDateTv;

    private NewsContentsPresenter presenter = new NewsContentsPresenter(this);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.full_news_page , container ,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.onReceive( (FeedItem) getArguments().get("list"));

        newsImageView = view.findViewById(R.id.newsImageView);

        newsTitleTv = view.findViewById(R.id.fullTitleTv);
        newsLinkTv = view.findViewById(R.id.fullLinkTv);
        newsSourceTv = view.findViewById(R.id.fullSourceTv);
        newsDescriptionTv = view.findViewById(R.id.fullDescriptionTv);
        newsPubDateTv = view.findViewById(R.id.fullPublishDateTv);

        Picasso.get().load(presenter.getImageUrl()).into(newsImageView);

        newsTitleTv.setText(getString(R.string.head_line)+presenter.getTitle());
        newsLinkTv.setText(getString(R.string.link)+presenter.getNewsLink());
        newsSourceTv.setText(getString(R.string.source)+presenter.getSource());
        newsDescriptionTv.setText(getString(R.string.description)+presenter.getDescription());
        newsPubDateTv.setText(getString(R.string.publish_date)+presenter.getPublishDate());
    }

    @Override
    public void receivedList(FeedItem feedItem) {
        presenter.FullNewsContents(String.valueOf(feedItem.getItemImageLink()), feedItem.getItemTitle() , feedItem.getItemLink() , feedItem.getItemSource() , feedItem.getItemDescription() , feedItem.getItemPublishDate());
    }
}