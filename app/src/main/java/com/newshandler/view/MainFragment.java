package com.newshandler.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.newshandler.R;
import com.newshandler.model.entities.UITalker;
import com.newshandler.presenter.MainViewPresenter;

import java.util.List;

public class MainFragment extends Fragment implements ItemClick, AddingMainView , DeleteClick {

    private EditText srcNameEdt;
    private EditText srcURLEdt;

    private List<UITalker> uiTalkerList;
    private ListViewAdapter listViewAdapter;

    private MainViewPresenter mainViewPresenter;

    private Bundle bundle = new Bundle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment , container , false); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        mainViewPresenter = new MainViewPresenter( getActivity() ,this);

        if (savedInstanceState != null) {
            mainViewPresenter.OnStart((List<UITalker>) savedInstanceState.getSerializable("list"));
        }else {
            mainViewPresenter.onRequired();
            uiTalkerList = mainViewPresenter.getList();
        }

        ListView sourcesListView = view.findViewById(R.id.sourcesListView);
        listViewAdapter = new ListViewAdapter(getActivity(), uiTalkerList, this , this);
        sourcesListView.setAdapter(listViewAdapter);

        srcNameEdt = view.findViewById(R.id.srcNameEdt);
        srcURLEdt = view.findViewById(R.id.srcUrlEdt);

        Button addSrcBtn = view.findViewById(R.id.addBtn);
        addSrcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewPresenter.onAddition(srcNameEdt.getText().toString() , srcURLEdt.getText().toString()); }
        });
    }

    @Override
    public void onItemClick(int position) {
        mainViewPresenter.OnSelect(position);
        RecyclerViewFragment fragmentTwo = new RecyclerViewFragment();
        bundle.putString("Link" , mainViewPresenter.getSrcLink());
        fragmentTwo.setArguments(bundle);
        (getActivity()).getFragmentManager().beginTransaction().replace(R.id.container1, fragmentTwo).addToBackStack(null).commit();
    }

    @Override
    public void isNameValid(boolean status) {
        if (!status)
            srcNameEdt.setError(getString(R.string.name_require));
    }

    @Override
    public void isLinkValid(boolean status) {
        if (!status)
            srcURLEdt.setError(getString(R.string.link_require));
    }

    @Override
    public void isAddingCorrect(boolean name, boolean link) {

        if (name && link){

            uiTalkerList.add(new UITalker(mainViewPresenter.getId(), mainViewPresenter.getSrcName(), mainViewPresenter.getSrcLink()));

            listViewAdapter.notifyDataSetChanged();

            srcNameEdt.setText("");
            srcURLEdt.setText("");

        } else if (!name && !link){
            Toast.makeText(getActivity() , getString(R.string.case_error) , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void isLinkExist(boolean link) {
        if (link)
            srcURLEdt.setError(getString(R.string.link_exists));
    }

    @Override
    public void deleteNews(int id, int position) {
        uiTalkerList.remove(position);
        mainViewPresenter.delete(id);
    }
}