package io.github.zane.recyclerview_frame;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.id_recyclerview)
    RecyclerView mRecyclerView;

    private HomeAdapter mHomeAdapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mList = new ArrayList<>();
        for(int i = 0; i <= 10; i++) {
            mList.add(i+"");
        }
        //设置布局管理器
        //垂直排列
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //水平排列
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        mRecyclerView.setLayoutManager(linearLayoutManager);

        //设置item增加和删除是的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mHomeAdapter = new HomeAdapter(this, mList);
        mRecyclerView.setAdapter(mHomeAdapter);
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

        private Context mContext;
        private List<String> mList;

        public HomeAdapter(Context context, List<String> list) {
            this.mContext = context;
            this.mList = list;
        }

        /**
         * 加载条目布局
         * @param parent
         * @param viewType
         * @return
         */
        @Override
        public HomeAdapter.HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            HomeViewHolder holder = new HomeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false));
            return holder;
        }

        /**
         * 将试图与数据进行绑定
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(HomeAdapter.HomeViewHolder holder, int position) {
            holder.mTvItem.setText(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList != null ? mList.size() : 0;
        }

        class HomeViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.tv_item)
            TextView mTvItem;

            public HomeViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
