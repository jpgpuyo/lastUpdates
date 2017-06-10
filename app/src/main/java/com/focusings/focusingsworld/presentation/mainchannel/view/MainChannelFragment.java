package com.focusings.focusingsworld.presentation.mainchannel.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.infrastructure.BaseFragment;
import com.focusings.focusingsworld.infrastructure.dagger.peractivity.components.ActivityComponent;
import com.focusings.focusingsworld.presentation.mainchannel.model.YoutubeVideoModel;
import com.focusings.focusingsworld.presentation.mainchannel.presenter.MainChannelPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainChannelFragment extends BaseFragment implements MainChannelView {

    private MainChannelAdapter mainChannelAdapter;

    @Inject
    MainChannelPresenter mainChannelPresenter;

    @InjectView(R.id.rv_youtube_videos)
    RecyclerView recyclerView;

    @InjectView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_channel_fragment, container, false);
        ButterKnife.inject(this, view);
        setupPullToRefresh();
        setupRecyclerView();
        return view;
    }

    private void setupPullToRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainChannelPresenter.getLastVideosFromYoutubeChannel(true);
            }
        });
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
    }

    @Override
    protected void initializeInjector() {
        this.getComponent(ActivityComponent.class).inject(this);
    }

    private void initializePresenter() {
        mainChannelPresenter.setView(this);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void renderYoutubeVideoList(List<YoutubeVideoModel> youtubeVideoModelCollection) {
        mainChannelAdapter.clear();
        mainChannelAdapter.addAll(youtubeVideoModelCollection);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainChannelPresenter.getLastVideosFromYoutubeChannel(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        mainChannelPresenter.destroy();
    }
}
