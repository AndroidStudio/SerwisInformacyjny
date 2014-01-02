package androidstudio.pl.serwisinformacyjny.tasks;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import androidstudio.pl.serwisinformacyjny.R;
import androidstudio.pl.serwisinformacyjny.adapters.ListViewNewsAdapter;

public class Cache {

    private static final String TAG_LOG = "Cache";
    private final LruCache<Integer, Bitmap> mBitmapCache;
    private final ArrayList<Integer> mCurrentTasks;
    private final Resources resources;
    private final ListViewNewsAdapter listViewNewsAdapter;

    public Cache(int size, Context context, ListViewNewsAdapter listViewNewsAdapter) {
        this.listViewNewsAdapter = listViewNewsAdapter;
        this.resources = context.getResources();
        this.mCurrentTasks = new ArrayList<Integer>();
        this.mBitmapCache = new LruCache<Integer, Bitmap>(size) {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
            @Override
            protected int sizeOf(Integer key, Bitmap bitmap) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
                    return bitmap.getHeight() * bitmap.getWidth() * 2;
                }
                return bitmap.getByteCount();
            }
        };
    }

    private void addBitmapToCache(Integer key, Bitmap bitmap) {
        synchronized (this) {
            if (getBitmapFromCache(key) == null) {
                mBitmapCache.put(key, bitmap);
            }
        }
    }

    private Bitmap getBitmapFromCache(Integer key) {
        Log.d(TAG_LOG, "MaxSize: " + mBitmapCache.maxSize() + " bytes");
        Log.d(TAG_LOG, "Size:    " + mBitmapCache.size() + " bytes");
        return mBitmapCache.get(key);
    }

    public void loadBitmap(Integer imagesId, ImageView imageView, ProgressBar progressBar, boolean mIsScrolling) {
        final Bitmap bitmap = getBitmapFromCache(imagesId);
        if (bitmap != null && !mIsScrolling) {
            imageView.setImageBitmap(bitmap);
            progressBar.setVisibility(View.GONE);
        } else {
            if (!mCurrentTasks.contains(imagesId) && !mIsScrolling) {
                final DecodeImageTask decodeImageTask = new DecodeImageTask(imagesId);
                decodeImageTask.execute();
            }
        }
    }

    public class DecodeImageTask extends AsyncTask<Integer, Integer, Bitmap> {
        private final Integer imagesId;

        public DecodeImageTask(Integer imagesId) {
            this.imagesId = imagesId;
        }

        @Override
        protected void onPreExecute() {
            mCurrentTasks.add(imagesId);
        }

        @Override
        protected Bitmap doInBackground(Integer... id) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            final Bitmap bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, imagesId, options),
                    (int) (resources.getDimension(R.dimen.news_list_image_size)), (int) (resources.getDimension(R.dimen.news_list_image_size)), true);
            addBitmapToCache(imagesId, bitmap);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mCurrentTasks.remove(imagesId);
            listViewNewsAdapter.notifyDataSetChanged();
        }
    }
}