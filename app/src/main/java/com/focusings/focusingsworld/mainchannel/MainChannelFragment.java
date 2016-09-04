package com.focusings.focusingsworld.mainchannel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.focusings.focusingsworld.R;
import com.focusings.focusingsworld.UIThread;
import com.focusings.focusingsworld.executor.JobExecutor;
import com.focusings.focusingsworld.executor.PostExecutionThread;
import com.focusings.focusingsworld.executor.ThreadExecutor;
import com.focusings.focusingsworld.interactor.GetYoutubeVideosFromChannelUseCase;
import com.focusings.focusingsworld.mainchannel.model.YoutubeVideoModel;
import com.focusings.focusingsworld.repository.YoutubeRepository;
import com.focusings.focusingsworld.repository.YoutubeRepositoryImpl;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainChannelFragment extends Fragment implements MainChannelView{

    private MainChannelAdapter mainChannelAdapter;
    private MainChannelPresenter mainChannelPresenter;

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializePresenter();
        mainChannelPresenter.getLastVideosFromYoutubeChannel();
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        mainChannelAdapter = new MainChannelAdapter();
        recyclerView.setAdapter(mainChannelAdapter);
    }

    private void initializePresenter() {
        ThreadExecutor threadExecutor = JobExecutor.getInstance();
        PostExecutionThread postExecutionThread = UIThread.getInstance();
        YoutubeRepository youtubeRepository = new YoutubeRepositoryImpl();
        GetYoutubeVideosFromChannelUseCase getYoutubeVideosFromChannelUseCase = new GetYoutubeVideosFromChannelUseCase(threadExecutor,postExecutionThread,youtubeRepository);

        mainChannelPresenter = new MainChannelPresenter(getYoutubeVideosFromChannelUseCase);
        mainChannelPresenter.setView(this);
    }

    @Override
    public void renderYoutubeVideoList(List<YoutubeVideoModel> youtubeVideoModelCollection) {
        mainChannelAdapter.setYoutubeVideoCollection(youtubeVideoModelCollection);
    }
}
