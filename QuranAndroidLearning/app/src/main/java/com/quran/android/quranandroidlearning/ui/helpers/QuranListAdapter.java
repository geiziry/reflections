package com.quran.android.quranandroidlearning.ui.helpers;

import android.content.Context;
import android.graphics.PorterDuff;
import android.nfc.Tag;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.quran.android.quranandroidlearning.R;
import com.quran.android.quranandroidlearning.dao.Bookmark;
import com.quran.android.quranandroidlearning.ui.fragment.BookmarksFragment;
import com.quran.android.quranandroidlearning.util.QuranUtils;
import com.quran.android.quranandroidlearning.widgets.JuzView;
import com.quran.android.quranandroidlearning.widgets.TagsViewGroup;

import org.w3c.dom.Text;

import java.net.FileNameMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by HP_Spectre on 7/28/2016.
 */
public class QuranListAdapter extends
        RecyclerView.Adapter<QuranListAdapter.HeaderHolder>
        implements View.OnLongClickListener, View.OnClickListener {

    private QuranRow[] mElements;
    private SparseBooleanArray mCheckedState;
    private boolean mSelectableHeaders;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private LayoutInflater mInflater;
    private Map<Long, Tag> mTagMap;
    private boolean mShowTags;
    private QuranTouchListener mTouchListener;

    public QuranListAdapter(Context context, RecyclerView recyclerView,
                            QuranRow[] items, boolean selectableHeaders) {
        mInflater = LayoutInflater.from(context);
        mRecyclerView = recyclerView;
        mElements = items;
        mContext = context;
        mSelectableHeaders = selectableHeaders;
        mCheckedState = new SparseBooleanArray();
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public QuranListAdapter.HeaderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            final View view = mInflater.inflate(R.layout.index_header_row, parent, false);
            return new HeaderHolder(view);
        } else {
            final View view = mInflater.inflate(R.layout.index_sura_row, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(QuranListAdapter.HeaderHolder viewHolder, int position) {
        final int type = getItemViewType(position);
        if (type == 0) {
            bindHeader(viewHolder, position);
        } else {
            bindRow(viewHolder, position);
        }

    }

    @Override
    public int getItemCount() {
        return mElements.length;
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public void onClick(View v) {

    }

    public QuranRow getQuranRow(int position) {
        return mElements[position];
    }

    public boolean isItemChecked(int position) {
        return mCheckedState.get(position);
    }

    public void setItemChecked(int position, boolean checked) {
        mCheckedState.put(position, checked);
        notifyItemChanged(position);
    }

    public List<QuranRow> getCheckedItems() {
        final List<QuranRow> result = new ArrayList<>();
        final int count = mCheckedState.size();
        final int elements = getItemCount();
        for (int i = 0; i < count; i++) {
            final int key = mCheckedState.keyAt(i);

            if (mCheckedState.get(key) && elements > key) {
                result.add(getQuranRow(key));
            }
        }
        return result;
    }

    public void uncheckAll() {
        mCheckedState.clear();
        notifyDataSetChanged();
    }

    public void setElements(QuranRow[] elements, Map<Long, Tag> tagMap) {
        mElements = elements;
        mTagMap = tagMap;
    }

    public void setShowTags(boolean showTags) {
        mShowTags = showTags;
    }

    private void bindRow(HeaderHolder vh, int position) {
        ViewHolder holder = (ViewHolder) vh;

        final QuranRow item = mElements[position];
        bindHeader(vh, position);
        holder.number.setText(
                QuranUtils.getLocalizedNumber(mContext, item.sura));

        holder.metadata.setVisibility(View.VISIBLE);
        holder.metadata.setText(item.metadata);
        holder.tags.setVisibility(View.GONE);

        if (item.juzType != null) {
            holder.image.setImageDrawable(
                    new JuzView(mContext, item.juzType, item.juzOverlayText));
            holder.image.setVisibility(View.VISIBLE);
            holder.number.setVisibility(View.GONE);
        } else if (item.imageResource == null) {
            holder.number.setVisibility(View.VISIBLE);
            holder.image.setVisibility(View.GONE);
        } else {
            holder.image.setImageResource(item.imageResource);
            if (item.imageFilterColor == null) {
                holder.image.setColorFilter(null);
            } else {
                holder.image.setColorFilter(
                        item.imageFilterColor, PorterDuff.Mode.SRC_ATOP
                );
            }
            holder.image.setVisibility(View.VISIBLE);
            holder.number.setVisibility(View.GONE);

            List<Tag> tags = new ArrayList<>();
            Bookmark bookmark = item.bookmark;
            if (bookmark != null && !bookmark.tags.isEmpty() && mShowTags) {
                for (int i = 0, bookmarkTags = bookmark.tags.size(); i < bookmarkTags; i++) {
                    Long tagId = bookmark.tags.get(i);
                    Tag tag = mTagMap.get(tagId);
                    if (tag != null) {
                        tags.add(tag);
                    }
                }
            }

            if (tags.isEmpty()) {
                holder.tags.setVisibility(View.GONE);
            } else {
                holder.tags.setTags(tags);
                holder.tags.setVisibility(View.VISIBLE);

            }
        }
    }

    private void bindHeader(HeaderHolder vh, int position) {
        final QuranRow item = mElements[position];
        vh.title.setText(item.text);
        if (item.page == 0) {


        }
    }

    public void setQuranTouchListener(BookmarksFragment quranTouchListener) {
        mTouchListener = quranTouchListener;
    }

    public interface QuranTouchListener {
        void onClick(QuranRow row, int position);

        boolean onLongClick(QuranRow row, int position);
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {


        TextView title;
        TextView pageNumber;
        View view;

        public HeaderHolder(View itemView) {
            super(itemView);
            view = itemView;
            title = (TextView) itemView.findViewById(R.id.title);
            pageNumber = (TextView) itemView.findViewById(R.id.pageNumber);

            itemView.setOnClickListener(QuranListAdapter.this);
            itemView.setOnLongClickListener(QuranListAdapter.this);
        }
    }

    class ViewHolder extends HeaderHolder {
        TagsViewGroup tags;
        ImageView image;
        TextView number;
        TextView metadata;

        public ViewHolder(View itemView) {
            super(itemView);
            metadata = (TextView) itemView.findViewById(R.id.metadata);
            number = (TextView) itemView.findViewById(R.id.suraNumber);
            image = (ImageView) itemView.findViewById(R.id.rowIcon);
            tags = (TagsViewGroup) itemView.findViewById(R.id.tags);
        }
    }


}
