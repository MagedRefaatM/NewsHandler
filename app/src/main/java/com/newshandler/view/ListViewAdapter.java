package com.newshandler.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.newshandler.R;
import com.newshandler.model.entities.UITalker;

import java.util.List;

public class ListViewAdapter extends BaseAdapter{
    private Context context;

    private List<UITalker> sitesList;

    private ItemClick itemClick;

    private DeleteClick deleteclick;

    ListViewAdapter(Context context, List<UITalker> sites, ItemClick itemClick , DeleteClick deleteclick) {
        this.context = context;
        this.sitesList = sites;
        this.itemClick = itemClick;
        this.deleteclick = deleteclick;
    }

    @Override
    public int getCount() {
        return sitesList != null ? sitesList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return sitesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.single_source, parent, false);
        }

        final UITalker currentItem = (UITalker) getItem(position);

        TextView srcNameTv = convertView.findViewById(R.id.srcNameTv);
        TextView srcUrlTv = convertView.findViewById(R.id.srcUrlTv);

        Button btnRemove = convertView.findViewById(R.id.btn_remove);

        srcNameTv.setText(currentItem.getSrcName());
        srcUrlTv.setText(currentItem.getSrcURL());

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteclick.deleteNews(currentItem.getId() , position);
                notifyDataSetChanged();
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.onItemClick(position);
            }
        });

        return convertView;
    }
}