package jp.ac.dendai.im.cps.iwitutorial;

import android.content.Context;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.BindingHolder> {
    private static final String TAG = RecyclerViewAdapter.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private Context mContext;
    private SortedList<LogItem> mSortedList;
    private LayoutInflater mInflater;

    public RecyclerViewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        mSortedList = new SortedList<>(LogItem.class, new SortedListCallback(this));
        mContext = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView = null;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.item_recyclerview, parent, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        holder.text.setText(mSortedList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mSortedList.size();
    }

    public RecyclerViewAdapter getAdapter() {
        return this;
    }

    public void addDataOf(List<LogItem> dataList) {
        mSortedList.addAll(dataList);
    }

    public void add(LogItem item) {
        mSortedList.add(item);
    }

    public void add(int timing, String name) {
        LogItem item = new LogItem();
        item.setName(name);
        item.setTiming(timing);
        mSortedList.add(item);















    }

    public void removeDataOf(List<LogItem> dataList) {
        mSortedList.beginBatchedUpdates();
        for (LogItem item : dataList) {
            mSortedList.remove(item);
        }
        mSortedList.endBatchedUpdates();
    }

    public void clearData() {
        mSortedList.clear();
    }

    public SortedList<LogItem> getList() {
        return this.mSortedList;
    }


    static class BindingHolder extends RecyclerView.ViewHolder {
        TextView text;

        public BindingHolder(View itemView) {
            super(itemView);

            text = (TextView) itemView.findViewById(R.id.item_text);
        }
    }

    private static class SortedListCallback extends SortedList.Callback<LogItem> {
        private RecyclerViewAdapter adapter;

        public SortedListCallback(RecyclerViewAdapter recyclerViewAdapter) {
            adapter = recyclerViewAdapter;
        }

        @Override
        public int compare(LogItem o1, LogItem o2) {
            if (o1.getTiming() < o2.getTiming()) {
                return -1;
            }

            if (o1.getTiming() == o2.getTiming()) {
                return 0;
            }

            return 1;
        }

        @Override
        public void onInserted(int position, int count) {
            adapter.notifyItemRangeInserted(position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
            adapter.notifyItemRangeRemoved(position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            adapter.notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onChanged(int position, int count) {
            adapter.notifyItemChanged(position, count);
        }

        @Override
        public boolean areContentsTheSame(LogItem oldItem, LogItem newItem) {
            return newItem.getName().equals(oldItem.getName());
        }

        @Override
        public boolean areItemsTheSame(LogItem item1, LogItem item2) {
            return item1.getTiming() == item2.getTiming();
        }
    }
}
