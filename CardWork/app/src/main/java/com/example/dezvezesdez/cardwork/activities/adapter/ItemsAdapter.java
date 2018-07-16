package com.example.dezvezesdez.cardwork.activities.adapter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dezvezesdez.cardwork.R;
import com.example.dezvezesdez.cardwork.activities.util.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dezvezesdez on 04/08/16.
 */
public class ItemsAdapter extends RecyclerView.Adapter {

    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    private int visibleThreshold = 2;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;

    private Activity a;
    private final ArrayList<Card> arrayList;

    public ItemsAdapter(Activity a, RecyclerView recyclerView, ArrayList<Card> arrayList) {
        this.a = a;
        this.arrayList = arrayList;

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        // End has been reached
                        // Do something
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return arrayList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == VIEW_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_card, parent, false);
            vh = new ItemsViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_item, parent, false);
            vh = new ProgressViewHolder(itemView);
        }
        return vh;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemsViewHolder) {

            Card card_item = arrayList.get(position);


            String id_item = card_item.getId_card();
            String name_item = card_item.getName();
            String number_item = card_item.getNumber();
            String link = card_item.getLink_icon();

            int icon_item = a.getResources().getIdentifier("com.example.dezvezesdez.cardwork:drawable/" + link, null, null);

            ((ItemsViewHolder) holder).tx_id_card.setText("ID CARD: " + id_item);
            ((ItemsViewHolder) holder).tx_name.setText(name_item);
            ((ItemsViewHolder) holder).tx_number.setText(number_item);

            ((ItemsViewHolder) holder).im_icon.setImageResource(icon_item);


        } else {

            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setLoaded() {
        loading = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        }
    }

    public static class ItemsViewHolder extends RecyclerView.ViewHolder {
        //public ImageButton imageButton;
        public TextView tx_id_card;
        public TextView tx_name;
        public TextView tx_number;
        public ImageView im_icon;

        public ItemsViewHolder(View view) {
            super(view);

            //imageButton = (ImageButton) view.findViewById(R.id.imageButton);
            tx_id_card = (TextView) view.findViewById(R.id.tx_id_card);
            tx_name = (TextView) view.findViewById(R.id.tx_name);
            tx_number = (TextView) view.findViewById(R.id.tx_number);
            im_icon = (ImageView) view.findViewById(R.id.im_icon);
        }

    }

    public Card removeItem(int position) {
        final Card model = arrayList.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Card model) {
        arrayList.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Card model = arrayList.remove(fromPosition);
        arrayList.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void animateTo(List<Card> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<Card> newModels) {
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            final Card model = arrayList.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }


    private void applyAndAnimateAdditions(List<Card> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Card model = newModels.get(i);
            if (!arrayList.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Card> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Card model = newModels.get(toPosition);
            final int fromPosition = arrayList.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }
}
