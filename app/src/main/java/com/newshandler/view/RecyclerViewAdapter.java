package com.newshandler.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.newshandler.R;
import com.newshandler.model.entities.FeedItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.FeedModelViewHolder> {

    private List<FeedItem> mFeedItems;

    private Context mContext;

    private Bundle bundle = new Bundle();

    static class FeedModelViewHolder extends RecyclerView.ViewHolder {
        TextView headLineTitleTv;
        TextView headLineDescriptionTv;

        Button showMoreBtn;

        ImageView headLineImageView;

        FeedModelViewHolder(View v) {
            super(v);
            headLineImageView = v.findViewById(R.id.newsIv);

            headLineTitleTv = v.findViewById(R.id.itemTitleTv);
            headLineDescriptionTv = v.findViewById(R.id.itemDescriptionTv);

            showMoreBtn = v.findViewById(R.id.showMoreBtn);
        }
    }

    RecyclerViewAdapter(List<FeedItem> feedItems, Context mContext) {
        mFeedItems = feedItems;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FeedModelViewHolder onCreateViewHolder(ViewGroup parent, int type) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_item, parent, false);
        return new FeedModelViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final FeedModelViewHolder holder, final int position) {
        final FeedItem feedItem = mFeedItems.get(position);

        holder.headLineTitleTv.setText(feedItem.getItemTitle());
        holder.headLineDescriptionTv.setText(feedItem.getItemDescription());

        Picasso.get().load(String.valueOf(feedItem.getItemImageLink())).into(holder.headLineImageView);

        holder.showMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedItem feedItem1 = mFeedItems.get(position);
                bundle.putSerializable("list", feedItem1);
                NewsContentsFragment newsContentsFragment = new NewsContentsFragment();
                newsContentsFragment.setArguments(bundle);
                ((Activity)mContext).getFragmentManager().beginTransaction().replace(R.id.container1, newsContentsFragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFeedItems != null ? mFeedItems.size() : 0;
    }
}