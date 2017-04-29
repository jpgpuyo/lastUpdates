package com.focusings.focusingsworld.base.renderer;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemsList<I extends ViewModel> {

  private final List<I> items;
  private final RecyclerView.Adapter adapter;

  public ItemsList(RecyclerView.Adapter adapter) {
    this.adapter = adapter;
    this.items = new ArrayList<>();
  }

  public void clear() {
    items.clear();
  }

  public void addAll(List<I> itemList) {
    items.addAll(itemList);
    adapter.notifyDataSetChanged();
  }

  public int size() {
    return items.size();
  }

  public I get(int position) {
    return items.get(position);
  }
}
