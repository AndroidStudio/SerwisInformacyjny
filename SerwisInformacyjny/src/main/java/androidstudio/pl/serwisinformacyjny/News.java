package androidstudio.pl.serwisinformacyjny;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidstudio.pl.serwisinformacyjny.adapters.ListViewNewsAdapter;

public class News extends Activity {
    private final static String TAG_LOG = "News.class";
    private ListViewNewsAdapter listViewNewsAdaspter;
    public static boolean isScrolling;

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
        this.setContentView(R.layout.layout_news);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int screenWidth = displayMetrics.widthPixels;
        final TextView textViewNews = (TextView) findViewById(R.id.textViewNews);
        textViewNews.setTextSize(TypedValue.COMPLEX_UNIT_PX, screenWidth * 0.04f);
        textViewNews.setTypeface(Typeface.DEFAULT_BOLD);

        listViewNewsAdaspter = new ListViewNewsAdapter(this, getNews(), getImagesIds(), screenWidth);
        final ListView listViewNews = (ListView) findViewById(R.id.listViewNews);
        listViewNews.setAdapter(listViewNewsAdaspter);
        //listViewNews.setLayoutAnimation(AnimationsClass.createAnimationSet());
        listViewNews.setOnScrollListener(onScrollListener);
    }

    final private ListView.OnScrollListener onScrollListener = new ListView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(AbsListView absListView, int scrollState) {
            if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                isScrolling = true;
            } else {
                isScrolling = false;
                listViewNewsAdaspter.notifyDataSetChanged();
            }
        }

        @Override
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {

        }
    };

    private List<HashMap<String, String>> getNews() {
        final List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("title", "WOJCIECH KILAR wybitny kompozytor, który jeszcze za życia znalazł swe miejsce w panteonie kultury światowej.");
        hashMap.put("date", " 30.12 .2013 15:46 ");
        hashMap.put("description", "Rekrutacja osób z niepełnosprawnością do projektu „Trener pracy jako sposób na zwiększenie zatrudnienia osób niepełnosprawnych”");
        list.add(hashMap);

        hashMap = new HashMap<String, String>();
        hashMap.put("title", "Handel elektroniczny jest branżą, której funkcjonowanie jest uregulowane w wielu aktach prawnych.");
        hashMap.put("date", " 30.12 .2013 15:46 ");
        hashMap.put("description", "Ostatnie dni głosowania w Plebiscycie Miast! Które z miast znajdzie się w finałowej szesnastce?");
        list.add(hashMap);

        hashMap = new HashMap<String, String>();
        hashMap.put("title", "Państwowy Fundusz Rehabilitacji Osób Niepełnosprawnych w partnerstwie z Polską Organizacją Pracodawców Osób Niepełnosprawnych");
        hashMap.put("date", " 30.12 .2013 15:46 ");
        hashMap.put("description", "Jak być świadomym użytkownikiem Internetu?");
        list.add(hashMap);

        hashMap = new HashMap<String, String>();
        hashMap.put("title", "Państwowy Fundusz Rehabilitacji Osób Niepełnosprawnych w partnerstwie z Polską Organizacją Pracodawców Osób Niepełnosprawnych");
        hashMap.put("date", " 30.12 .2013 15:46 ");
        hashMap.put("description", "Jak być świadomym użytkownikiem Internetu?");
        list.add(hashMap);

        hashMap = new HashMap<String, String>();
        hashMap.put("title", "Handel elektroniczny jest branżą, której funkcjonowanie jest uregulowane w wielu aktach prawnych.");
        hashMap.put("date", " 30.12 .2013 15:46 ");
        hashMap.put("description", "Ostatnie dni głosowania w Plebiscycie Miast! Które z miast znajdzie się w finałowej szesnastce?");
        list.add(hashMap);

        hashMap = new HashMap<String, String>();
        hashMap.put("title", "Państwowy Fundusz Rehabilitacji Osób Niepełnosprawnych w partnerstwie z Polską Organizacją Pracodawców Osób Niepełnosprawnych");
        hashMap.put("date", " 30.12 .2013 15:46 ");
        hashMap.put("description", "Jak być świadomym użytkownikiem Internetu?");
        list.add(hashMap);

        hashMap = new HashMap<String, String>();
        hashMap.put("title", "Państwowy Fundusz Rehabilitacji Osób Niepełnosprawnych w partnerstwie z Polską Organizacją Pracodawców Osób Niepełnosprawnych");
        hashMap.put("date", " 30.12 .2013 15:46 ");
        hashMap.put("description", "Jak być świadomym użytkownikiem Internetu?");
        list.add(hashMap);
        hashMap = new HashMap<String, String>();
        hashMap.put("title", "Handel elektroniczny jest branżą, której funkcjonowanie jest uregulowane w wielu aktach prawnych.");
        hashMap.put("date", " 30.12 .2013 15:46 ");
        hashMap.put("description", "Ostatnie dni głosowania w Plebiscycie Miast! Które z miast znajdzie się w finałowej szesnastce?");
        list.add(hashMap);

        hashMap = new HashMap<String, String>();
        hashMap.put("title", "Państwowy Fundusz Rehabilitacji Osób Niepełnosprawnych w partnerstwie z Polską Organizacją Pracodawców Osób Niepełnosprawnych");
        hashMap.put("date", " 30.12 .2013 15:46 ");
        hashMap.put("description", "Jak być świadomym użytkownikiem Internetu?");
        list.add(hashMap);

        hashMap = new HashMap<String, String>();
        hashMap.put("title", "Państwowy Fundusz Rehabilitacji Osób Niepełnosprawnych w partnerstwie z Polską Organizacją Pracodawców Osób Niepełnosprawnych");
        hashMap.put("date", " 30.12 .2013 15:46 ");
        hashMap.put("description", "Jak być świadomym użytkownikiem Internetu?");
        list.add(hashMap);
        hashMap = new HashMap<String, String>();
        hashMap.put("title", "Handel elektroniczny jest branżą, której funkcjonowanie jest uregulowane w wielu aktach prawnych.");
        hashMap.put("date", " 30.12 .2013 15:46 ");
        hashMap.put("description", "Ostatnie dni głosowania w Plebiscycie Miast! Które z miast znajdzie się w finałowej szesnastce?");
        list.add(hashMap);

        hashMap = new HashMap<String, String>();
        hashMap.put("title", "Państwowy Fundusz Rehabilitacji Osób Niepełnosprawnych w partnerstwie z Polską Organizacją Pracodawców Osób Niepełnosprawnych");
        hashMap.put("date", " 30.12 .2013 15:46 ");
        hashMap.put("description", "Jak być świadomym użytkownikiem Internetu?");
        list.add(hashMap);

        hashMap = new HashMap<String, String>();
        hashMap.put("title", "Państwowy Fundusz Rehabilitacji Osób Niepełnosprawnych w partnerstwie z Polską Organizacją Pracodawców Osób Niepełnosprawnych");
        hashMap.put("date", " 30.12 .2013 15:46 ");
        hashMap.put("description", "Jak być świadomym użytkownikiem Internetu?");
        list.add(hashMap);
        hashMap = new HashMap<String, String>();
        hashMap.put("title", "Handel elektroniczny jest branżą, której funkcjonowanie jest uregulowane w wielu aktach prawnych.");
        hashMap.put("date", " 30.12 .2013 15:46 ");
        hashMap.put("description", "Ostatnie dni głosowania w Plebiscycie Miast! Które z miast znajdzie się w finałowej szesnastce?");
        list.add(hashMap);

        hashMap = new HashMap<String, String>();
        hashMap.put("title", "Państwowy Fundusz Rehabilitacji Osób Niepełnosprawnych w partnerstwie z Polską Organizacją Pracodawców Osób Niepełnosprawnych");
        hashMap.put("date", " 30.12 .2013 15:46 ");
        hashMap.put("description", "Jak być świadomym użytkownikiem Internetu?");
        list.add(hashMap);

        hashMap = new HashMap<String, String>();
        hashMap.put("title", "Państwowy Fundusz Rehabilitacji Osób Niepełnosprawnych w partnerstwie z Polską Organizacją Pracodawców Osób Niepełnosprawnych");
        hashMap.put("date", " 30.12 .2013 15:46 ");
        hashMap.put("description", "Jak być świadomym użytkownikiem Internetu?");
        list.add(hashMap);
        return list;
    }

    private Integer[] getImagesIds() {
        return new Integer[]{
                R.drawable.news11,
                R.drawable.news12,
                R.drawable.news13,
                R.drawable.news14,
                R.drawable.news15,
                R.drawable.news16,
                R.drawable.news7,
                R.drawable.news8,
                R.drawable.news3,
                R.drawable.fot_irena_galuszka4,
                R.drawable.news2,
                R.drawable.news4,
                R.drawable.news5,
                R.drawable.news6,
                R.drawable.news9,
                R.drawable.news10
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG_LOG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG_LOG, "onPause");
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
    }
}
