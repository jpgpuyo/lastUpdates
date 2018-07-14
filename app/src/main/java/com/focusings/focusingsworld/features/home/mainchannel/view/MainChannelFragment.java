package com.focusings.focusingsworld.features.home.mainchannel.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.features.home.view.HomeActivity;
import com.focusings.focusingsworld.features.home.mainchannel.presenter.MainChannelPresenter;
import com.focusings.focusingsworld.data.youtube.models.YoutubeVideo;
import com.focusings.focusingsworld.core.RootFragment;
import com.focusings.focusingsworld.features.home.view.HomeView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainChannelFragment extends RootFragment implements MainChannelView {

    private MainChannelAdapter mainChannelAdapter;

    @Inject
    MainChannelPresenter mainChannelPresenter;

    @BindView(R.id.rv_youtube_videos)
    RecyclerView recyclerView;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private HomeView homeView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_channel_fragment, container, false);
        ButterKnife.bind(this, view);
        setupPullToRefresh();
        setupRecyclerView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        if (activity instanceof HomeActivity) {
            homeView = (HomeView) activity;
        }
    }

    private void setupPullToRefresh() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            mainChannelPresenter.getLastVideosFromYoutubeChannel(true);
        });
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        mainChannelAdapter = new MainChannelAdapter();
        mainChannelAdapter.setHasStableIds(true);
        recyclerView.setAdapter(mainChannelAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializePresenter();
    }

    private void initializePresenter() {
        mainChannelPresenter.setView(this);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void renderYoutubeVideoList(List<YoutubeVideo> youtubeVideoList) {
        homeView.hideErrorMessage();
        mainChannelAdapter.clear();
        mainChannelAdapter.addAll(youtubeVideoList);
    }

    @Override
    public void showNetworkError() {
        homeView.showErrorMessage(R.string.networkError);
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

    @Override
    public void onDetach() {
        super.onDetach();
        homeView.hideErrorMessage();
        homeView = null;
    }
}
