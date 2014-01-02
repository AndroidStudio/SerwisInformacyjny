package androidstudio.pl.serwisinformacyjny.adapters;

import android.app.ActivityManager;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import androidstudio.pl.serwisinformacyjny.News;
import androidstudio.pl.serwisinformacyjny.R;
import androidstudio.pl.serwisinformacyjny.RetainCache;
import androidstudio.pl.serwisinformacyjny.tasks.Cache;

public class ListViewNewsAdapter extends BaseAdapter {

    private final List<HashMap<String, String>> news;
    private final LayoutInflater layoutInflater;
    private final Integer[] imagesIds;
    private final int screenWidth;
    private Cache cache;

    public ListViewNewsAdapter(Context context, List<HashMap<String, String>> news, Integer[] imagesIds, int screenWidth) {
        this.imagesIds = imagesIds;
        this.screenWidth = screenWidth;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.news = news;

        final int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        final int cacheSize = 1024 * 1024 * memClass;

        final RetainCache c = RetainCache.getOrCreateRetainableCache();
        this.cache = c.mRetainedCache;
        if (this.cache == null) {
            this.cache = new Cache(cacheSize, context, this);
            c.mRetainedCache = this.cache;
        }
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDate;
        private TextView textViewDescription;
        private ImageView imageView;
        private ProgressBar progressBar;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.row_list_news, null, false);
            if (view != null) {
                viewHolder.textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
                viewHolder.textViewTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (0.04 * screenWidth));
                viewHolder.textViewDate = (TextView) view.findViewById(R.id.textViewDate);
                viewHolder.textViewDate.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (0.03 * screenWidth));
                viewHolder.textViewDescription = (TextView) view.findViewById(R.id.textViewDescription);
                viewHolder.textViewDescription.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (0.035 * screenWidth));
                viewHolder.imageView = (ImageView) view.findViewById(R.id.imageView);
                viewHolder.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
                view.setTag(viewHolder);
            }
        } else {
            viewHolder = (ViewHolder) view.getTag();
            viewHolder.imageView.setImageBitmap(null);
            viewHolder.progressBar.setVisibility(View.VISIBLE);
        }

        final HashMap<String, String> row_news = news.get(position);
        viewHolder.textViewTitle.setText(row_news.get("title"));
        viewHolder.textViewDate.setText(row_news.get("date"));
        viewHolder.textViewDescription.setText(row_news.get("description"));
        cache.loadBitmap(imagesIds[position], viewHolder.imageView, viewHolder.progressBar, News.isScrolling);
        return view;
    }
}
