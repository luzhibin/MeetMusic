package com.lzb.meetmusic.activitys;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.Utils;
import com.lzb.meetmusic.R;
import com.lzb.meetmusic.adapters.MusicGridAdapter;
import com.lzb.meetmusic.adapters.MusicListAdapter;
import com.lzb.meetmusic.views.GridSpaceItemDecoration;

public class MainActivity extends BaseActivity {

    /**  RecyclerView是Android一个更强大的控件,其不仅可以实现和ListView同样的效果,还有优化了ListView中的各种不足。
     *   其可以实现数据纵向滚动,也可以实现横向滚动(ListView做不到横向滚动)。
     *
     * @param mRvGrid “推荐歌单”下的 RecyclerView
     * @param mRvList “最热音乐”下的 RecyclerView
     */
    private RecyclerView mRvGrid,mRvList;
    private MusicGridAdapter mGridAdapter;
    private MusicListAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化Android util code
        Utils.init(this);
        initView();
    }

    private void initView(){
        initNavigationBar(false,"邂逅音乐",true);

        mRvGrid = findViewById(R.id.rv_grid);
        /*  GridLayoutManager可以实现标准的网格布局    Grid:网格
         *  spanCount:3  同一行中展示的item的数量
         *  可以写成mRvGrid.setLayoutManager(new GridLayoutManager(this,3));
         */
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRvGrid.setLayoutManager(gridLayoutManager);
        //网格适配器的分割线 【原理：让item向右偏移4dp，就产生了分割线效果】
        mRvGrid.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelOffset(R.dimen.albumMarginSize),mRvGrid));
        //取消滑动
        mRvGrid.setNestedScrollingEnabled(false);
        //网格适配器
        mGridAdapter = new MusicGridAdapter(this);
        mRvGrid.setAdapter(mGridAdapter);

        /** RecyclerView线性布局定义高度
         * 1、在已经明确的知道列表高度的情况下，可以直接在布局中把RecyclerView的高度定义好
         * 2、在不知道需要展示多少条数据的情况下，需要去手动计算RecyclerView的高度
         */

        mRvList = findViewById(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //分割线
        mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        //取消滑动
        mRvList.setNestedScrollingEnabled(false);
        mListAdapter = new MusicListAdapter(MainActivity.this,mRvList);
        mRvList.setAdapter(mListAdapter);
    }
}
