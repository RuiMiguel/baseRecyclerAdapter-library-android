package com.gigigo.baserecycleradapter_demoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.gigigo.baserecycleradapter.adapter.BaseRecyclerAdapter;
import com.gigigo.baserecycleradapter_demoapp.entities.ImageCell;
import com.gigigo.baserecycleradapter_demoapp.entities.TextCell;
import com.gigigo.baserecycleradapter_demoapp.viewholder.ImageViewHolder;
import com.gigigo.baserecycleradapter_demoapp.viewholder.TextViewHolder;
import com.gigigo.ui.imageloader.ImageLoader;
import com.gigigo.ui.imageloader_glide.GlideImageLoaderImp;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private RecyclerView recyclerView;
  private BaseRecyclerAdapter adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initView();
  }

  private void initView() {
    Button buttonFill = (Button) findViewById(R.id.button_fill);
    Button buttonClear = (Button) findViewById(R.id.button_clear);

    buttonFill.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        fillData(DataGenerator.generateRandomDataList(25));
      }
    });

    buttonClear.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        clearData();
      }
    });

    initAdapter();
    initRecyclerView();
  }

  private void initAdapter() {
    RequestManager requestManager = Glide.with(this);
    ImageLoader imageLoader = new GlideImageLoaderImp(requestManager);

    adapter = new BaseRecyclerAdapter(this, imageLoader);
    adapter.bind(ImageCell.class, ImageViewHolder.class);
    adapter.bind(TextCell.class, TextViewHolder.class);
  }

  private void initRecyclerView() {
    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
  }

  public void clearData() {
    adapter.clear();
  }

  private void fillData(List<?> data) {
    adapter.append(data);
  }
}