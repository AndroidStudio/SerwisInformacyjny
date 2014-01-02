package androidstudio.pl.serwisinformacyjny.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import androidstudio.pl.serwisinformacyjny.R;

public class CustomGalleryAdapter extends BaseAdapter {
    private final List<Bitmap> baners;
    private final LayoutInflater layoutInflater;

    public CustomGalleryAdapter(Context context, List<Bitmap> baners) {
        this.baners = baners;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return baners.size();
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
        private ImageView imageView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.baner, null, false);
            if (view != null) {
                viewHolder.imageView = (ImageView) view.findViewById(R.id.baner);
                view.setTag(viewHolder);
            }
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageBitmap(baners.get(i));
        return view;
    }
}
