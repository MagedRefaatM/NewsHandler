package com.newshandler.view;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.newshandler.R;
import com.newshandler.model.entities.FeedItem;
import com.newshandler.model.entities.Rss;
import com.newshandler.model.network.ApiService;
import com.newshandler.presenter.RecyclerViewPresenter;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RecyclerViewFragment extends Fragment implements AddingToRecyclerView {

    private String Link;

    private RecyclerViewAdapter recyclerViewAdapter;

    private RecyclerViewPresenter presenter;

    private ArrayList<FeedItem> feedsList;

    private ProgressDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = ProgressDialog.show(getActivity(), getString(R.string.dialog_title), getString(R.string.dialog_message), true);
        presenter = new RecyclerViewPresenter(Link, this);
        feedsList = (ArrayList<FeedItem>) presenter.getList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rss_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        Link = bundle.get("Link").toString();
        presenter.setSrcLink(Link);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(feedsList, getActivity());
        RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("list", (Serializable) presenter.getList());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        load();
    }

    @Override
    public void IsListReceived(List<FeedItem> list) {
        feedsList.clear();
        feedsList.addAll(list);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    private void load() {

        if (!Link.startsWith("https://")) {

            String baseUrl = "https://" + Link;
            Pattern pattern = Pattern.compile("/.*/(.*)/");
            Matcher matcher = pattern.matcher(baseUrl);

            if (matcher.find()) {

                if (feedsList.size() == 0) {

                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                    httpClient.addInterceptor(logging);

                    ApiService api = new Retrofit.Builder()
                            .baseUrl("https:" + matcher.group())
                            .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(new Persister(new AnnotationStrategy())))
                            .client(httpClient.build())
                            .build().create(ApiService.class);

                    Pattern pattern2 = Pattern.compile("/(.*)$");
                    Matcher matcher2 = pattern2.matcher(Link);

                    if (matcher2.find()) {

                        String functionLink = matcher2.group();
                        Call<Rss> call = api.getNews(functionLink);

                        call.enqueue(new Callback<Rss>() {
                            @Override
                            public void onResponse(Call<Rss> call, Response<Rss> response) {
                                dialog.dismiss();
                                Rss rss = response.body();
                                presenter.OnReceived(rss.getChannel().getChannelItemList());
                            }

                            @Override
                            public void onFailure(Call<Rss> call, Throwable t) {
                                dialog.dismiss();
                                Log.e("Rss Error", t.getLocalizedMessage());
                                Toast.makeText(getActivity() , "Error" , Toast.LENGTH_SHORT).show();
                                RecyclerViewFragment.this.getActivity().getFragmentManager().beginTransaction().replace(R.id.container1 , new MainFragment()).commit();
                            }
                        });
                    }
                }
            }
        }
    }
}