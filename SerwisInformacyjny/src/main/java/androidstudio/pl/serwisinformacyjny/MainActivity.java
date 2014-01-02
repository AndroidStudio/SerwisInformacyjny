package androidstudio.pl.serwisinformacyjny;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidstudio.pl.serwisinformacyjny.adapters.CustomExpandableListAdapter;
import androidstudio.pl.serwisinformacyjny.adapters.CustomGalleryAdapter;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {
    private final String TAG_LOG = "MainActivity";
    private final List<String> headerGroupList = new ArrayList<String>();
    private int screenWidth;
    private Resources resources;
    private final Handler handler = new Handler();
    private Gallery gallery;
    private List<Bitmap> bitmaps;

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG_LOG, "onStart");
        //this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG_LOG, "onCreate");

        final DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.screenWidth = displayMetrics.widthPixels;

        this.resources = getResources();

        final RelativeLayout mainLayout = new RelativeLayout(this);
        final RelativeLayout headerView = new RelativeLayout(this);

        final ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(decodeBitmap(R.drawable.mainactivitylogo));
        imageView.setId(2398476);

        final int marginlayoutParamsExpandableList = screenWidth / 100;

        final CustomGalleryAdapter customGalleryAdapter = new CustomGalleryAdapter(this, getBaners());
        final RelativeLayout.LayoutParams galleryLayoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        galleryLayoutParams.addRule(RelativeLayout.BELOW, imageView.getId());
        galleryLayoutParams.setMargins(marginlayoutParamsExpandableList, 0, marginlayoutParamsExpandableList, 0);
        //noinspection deprecation,deprecation,deprecation,deprecation,deprecation,deprecation,deprecation,deprecation
        gallery = new Gallery(this);
        gallery.setLayoutParams(galleryLayoutParams);
        gallery.setId(23893436);
        gallery.setSpacing(marginlayoutParamsExpandableList * 2);
        gallery.setAdapter(customGalleryAdapter);

        headerView.addView(imageView);
        headerView.addView(gallery, galleryLayoutParams);


        final ImageView imageViewFooter = new ImageView(this);
        imageViewFooter.setImageBitmap(decodeBitmap(R.drawable.footerview));
        imageViewFooter.setId(23111476);

        final RelativeLayout.LayoutParams layoutParamsExpandableList = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParamsExpandableList.addRule(RelativeLayout.BELOW, gallery.getId());

        layoutParamsExpandableList.setMargins(marginlayoutParamsExpandableList, marginlayoutParamsExpandableList,
                marginlayoutParamsExpandableList, marginlayoutParamsExpandableList);

        final StateListDrawable states = new StateListDrawable();
        states.addState(new int[]{android.R.attr.state_empty}, resources.getDrawable(R.drawable.arrow_expanded));
        states.addState(new int[]{android.R.attr.state_expanded}, resources.getDrawable(R.drawable.arrow));

        final CustomExpandableListAdapter expandableListAdapter = new CustomExpandableListAdapter(this, getHeaderListGroup(), getChildList());
        final ExpandableListView expandableListView = new ExpandableListView(this);
        expandableListView.addHeaderView(headerView, null, false);
        expandableListView.addFooterView(imageViewFooter, null, false);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setSelector(new StateListDrawable());
        expandableListView.setGroupIndicator(states);
        expandableListView.setChildDivider(resources.getDrawable(R.color.dividerGroup));
        expandableListView.setDivider(resources.getDrawable(R.color.dividerGroup));
        expandableListView.setDividerHeight((int) (resources.getDimension(R.dimen.explistdividerheight)));
        expandableListView.setOnGroupClickListener(onGroupClickListener);
        expandableListView.setOnChildClickListener(onChildClickListener);
        //expandableListView.setLayoutAnimation(AnimationsClass.createAnimationSet());

        mainLayout.addView(expandableListView, layoutParamsExpandableList);
        this.setContentView(mainLayout);
    }

    private final ExpandableListView.OnChildClickListener onChildClickListener = new ExpandableListView.OnChildClickListener() {
        @Override
        public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long l) {
            MainActivity.this.startActivity(new Intent(MainActivity.this, News.class));
            return true;
        }
    };

    private final ExpandableListView.OnGroupClickListener onGroupClickListener = new ExpandableListView.OnGroupClickListener() {
        private int lastClickedPosition = -1;

        @Override
        public boolean onGroupClick(ExpandableListView expandableListView, View view, int pos, long l) {
            final boolean shouldExpand = !expandableListView.isGroupExpanded(pos);
            expandableListView.collapseGroup(lastClickedPosition);
            if (shouldExpand) {
                expandableListView.expandGroup(pos);
                expandableListView.setSelectionFromTop(pos, 0);
            }
            lastClickedPosition = pos;
            return true;
        }
    };

    private List<Bitmap> getBaners() {
        bitmaps = new ArrayList<Bitmap>(2);
        bitmaps.add(decodeBitmap(R.drawable.baner2));
        bitmaps.add(decodeBitmap(R.drawable.baner1));
        return bitmaps;
    }

    private List<String> getHeaderListGroup() {
        headerGroupList.add("Miasto Rzeszów");
        headerGroupList.add("Biznes");
        headerGroupList.add("Kultura I Sport");
        headerGroupList.add("Turystyka");
        headerGroupList.add("Mieszkańcy");
        headerGroupList.add("Promocja");
        headerGroupList.add("Clap Opportunities");
        headerGroupList.add("Kontakt");
        return headerGroupList;
    }

    private HashMap<String, List<String>> getChildList() {
        final HashMap<String, List<String>> childList = new HashMap<String, List<String>>();

        final List<String> childListRzeszowCity = new ArrayList<String>(6);
        childListRzeszowCity.add("Wizytówka Miasta");
        childListRzeszowCity.add("Aktualności");
        childListRzeszowCity.add("Urząd Miasta Rzeszowa");
        childListRzeszowCity.add("Rankingi");
        childListRzeszowCity.add("Rozszerzenie Granic Miasta");
        childListRzeszowCity.add("Dane Statystyczne");

        final List<String> childListBiznes = new ArrayList<String>(2);
        childListBiznes.add("Podkarpacki Portal Pracy");
        childListBiznes.add("Polska Wschodnia");

        childList.put(headerGroupList.get(0), childListRzeszowCity);
        childList.put(headerGroupList.get(1), childListBiznes);
        childList.put(headerGroupList.get(2), childListRzeszowCity);
        childList.put(headerGroupList.get(3), childListBiznes);
        childList.put(headerGroupList.get(4), childListRzeszowCity);
        childList.put(headerGroupList.get(5), childListBiznes);
        childList.put(headerGroupList.get(6), childListBiznes);
        childList.put(headerGroupList.get(7), childListBiznes);
        return childList;
    }

    private Bitmap decodeBitmap(int id) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                resources, id, options), screenWidth, screenWidth
                * options.outHeight / options.outWidth, true);
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            final int selectedItem = gallery.getSelectedItemPosition();
            if (selectedItem == bitmaps.size() - 1) {
                gallery.setSelection(0);
            } else {
                gallery.setSelection(selectedItem + 1);
            }
            handler.postDelayed(runnable, 4000);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG_LOG, "onResume");
        handler.postDelayed(runnable, 4000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG_LOG, "onPause");
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG_LOG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG_LOG, "onDestroy");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i(TAG_LOG, "onBackPressed");
        //System.exit(0);
    }
}
