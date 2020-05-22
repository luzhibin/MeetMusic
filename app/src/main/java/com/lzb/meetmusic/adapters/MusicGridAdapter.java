package com.lzb.meetmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lzb.meetmusic.R;
import com.lzb.meetmusic.activitys.AlbumListActivity;

/**
 * 网格适配器
 */
public class MusicGridAdapter extends RecyclerView.Adapter<MusicGridAdapter.ViewHolder> {

    private Context mContext;

    public MusicGridAdapter(Context context){
        this.mContext = context;
    }

    /** onCreateViewHolder(ViewGroup parent, int viewType)创建Holder
     *  这个方法就是用来创建出一个新的ViewHolder，可以根据需求的itemType，创建出多个ViewHolder。创建多个itemType时，需要getItemViewType(int position)方法配合
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param viewGroup 可以简单理解为item的根ViewGroup，item的子控件加载在其中
     * @param viewType  item的类型，可以根据viewType来创建不同的ViewHolder，来加载不同的类型的item
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_grid_music,viewGroup,false));
    }

    /** 绑定ViewHolder
     * @param viewHolder 就是在onCreateViewHolder()方法中，创建的ViewHolder
     * @param position   item对应的DataList数据源集合的postion
     *                   postion就是adapter position，RecycelrView中item的数量，就是根据DataList数据源集合的数量来创建的
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Glide.with(mContext)
                .load("http://res.lgdsunday.club/poster-1.png")
                .into(viewHolder.ivIcon);

        //添加点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AlbumListActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    /** getItemCount()获取Item的数目
     * @return
     */
    @Override
    public int getItemCount() {
        return 6;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivIcon;
        View itemView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            this.itemView = itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon);
        }
    }

}
