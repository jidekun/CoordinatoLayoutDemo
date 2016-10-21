package com.dgh.dgh.coordinatolayoutdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = new RecyclerView(getActivity());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(new PinDaoAdapter());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!canScrollDown(recyclerView)) {

                    if (!mIsLoading) {
                        mIsLoading = true;
                        Toast.makeText(getActivity(), "start load more!", Toast.LENGTH_LONG).show();
//                        if (mOnLoadingListener != null) {
//                            mOnLoadingListener.loading();
//                        }
                    } else {
                        Toast.makeText(getActivity(), "is loading more! please wait", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        return recyclerView;
    }

    boolean mIsLoading;

    /**
     * @return Whether it is possible for the child view of this layout to
     * scroll up. Override this if the child view is a custom view.
     */
    private boolean canScrollDown(RecyclerView recyclerView) {
        return ViewCompat.canScrollVertically(recyclerView, 1);
    }

    private final class PinDaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        public PinDaoAdapter() {
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ContentView(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pindao, parent, false));
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ContentView content = (ContentView) holder;
        }


        @Override
        public int getItemCount() {
            return 20;
        }

    }

    class ContentView extends RecyclerView.ViewHolder {
        public ContentView(View itemView) {
            super(itemView);
        }
    }
}
