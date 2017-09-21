package io.github.zane.recyclerview_frame;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Zane on 2017/9/21.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private Context mContext;
    private List<String> mList;
    private List<Integer> mHeights;

    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public HomeAdapter(Context context, List<String> list, List<Integer> heightList) {
        this.mContext = context;
        this.mList = list;
        this.mHeights = heightList;
    }

    public void removeData(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 加载条目布局
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        HomeViewHolder holder = new HomeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false));

        //瀑布流
        HomeViewHolder holder = new HomeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_grid_recycler, parent, false));
        return holder;
    }

    /**
     * 将试图与数据进行绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final HomeAdapter.HomeViewHolder holder, int position) {
        //瀑布流
        ViewGroup.LayoutParams lp = holder.mTvItem.getLayoutParams();
        lp.height = mHeights.get(position);
        holder.mTvItem.setLayoutParams(lp);

        holder.mTvItem.setText(mList.get(position));
        if(mOnItemClickListener != null) {
            holder.mTvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.mTvItem, pos);
                }
            });

            holder.mTvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.mTvItem, pos);
                    return false;
                }
            });
        }
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