package com.focusings.focusingsworld.mainchannel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.dagger.peractivity.components.ActivityComponent;
import com.focusings.focusingsworld.BaseFragment;
import com.focusings.focusingsworld.mainchannel.model.YoutubeVideoModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainChannelFragment extends BaseFragment implements MainChannelView{

    private MainChannelAdapter mainChannelAdapter;

    @Inject
    MainChannelPresenter mainChannelPresenter;

    @InjectView(R.id.fragment_list_rv)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        ButterKnife.inject(this, view);
        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        mainChannelAdapter = new MainChannelAdapter();
        recyclerView.setAdapter(mainChannelAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializePresenter();
        mainChannelPresenter.getLastVideosFromYoutubeChannel();
    }

    @Override
    protected void initializeInjector() {
        this.getComponent(ActivityComponent.class).inject(this);
    }

    private void initializePresenter() {
        mainChannelPresenter.setView(this);
    }

    @Override
    public void renderYoutubeVideoList(List<YoutubeVideoModel> youtubeVideoModelCollection) {
        mainChannelAdapter.setYoutubeVideoCollection(youtubeVideoModelCollection);
    }
}
