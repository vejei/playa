package io.github.zeleven.playa.ui.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<D, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    protected List<D> data = new ArrayList<>();

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public void setData(List<D> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public List<D> getData() {
        return data;
    }

    public void appendItems(List<D> items) {
        if (items != null && (items.size() != 0) && data != null) {
            data.addAll(items);
            notifyDataSetChanged();
        }
    }

    public void removeItem(D item) {
        data.remove(item);
        notifyDataSetChanged();
    }

    public void clear() {
        if (data != null) {
            data.clear();
            notifyDataSetChanged();
        }
    }
}
