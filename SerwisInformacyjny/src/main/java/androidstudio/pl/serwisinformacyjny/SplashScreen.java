package androidstudio.pl.serwisinformacyjny;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SplashScreen extends Activity {

    private final String TAG_LOG = "SplashScreen";
    private ImageView imageView;
    private int screenWidth;

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

        final RelativeLayout mainLayout = new RelativeLayout(this);
        final RelativeLayout.LayoutParams layoutParamsImageLogo = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsImageLogo.addRule(RelativeLayout.CENTER_IN_PARENT);

        imageView = new ImageView(this);
        imageView.setImageBitmap(decodeBitmap());
        imageView.setAnimation(createAnimationSet());
        mainLayout.addView(imageView, layoutParamsImageLogo);
        this.setContentView(mainLayout);
    }

    private Bitmap decodeBitmap() {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        final float logoSize = 0.7f;
        return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                this.getResources(), R.drawable.logo, options), (int) (logoSize * screenWidth),
                (int) (logoSize * screenWidth * options.outHeight / options.outWidth), true);
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

    private AnimationSet createAnimationSet() {
        final AnimationSet animationSet = new AnimationSet(false);
        animationSet.setStartOffset(500);
        final AlphaAnimation startAlphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        startAlphaAnimation.setDuration(500);

        final ScaleAnimation startScaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        startScaleAnimation.setDuration(500);
        startScaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        final AlphaAnimation endAlphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        endAlphaAnimation.setDuration(500);
        endAlphaAnimation.setStartOffset(3000);

        final ScaleAnimation endScaleAnimation = new ScaleAnimation(1.0f, 5.0f, 1.0f, 5.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        endScaleAnimation.setDuration(500);
        endScaleAnimation.setStartOffset(3000);
        endScaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        animationSet.addAnimation(startAlphaAnimation);
        animationSet.addAnimation(startScaleAnimation);
        animationSet.addAnimation(endAlphaAnimation);
        animationSet.addAnimation(endScaleAnimation);
        animationSet.setAnimationListener(animationListenr);
        return animationSet;
    }

    final private AnimationSet.AnimationListener animationListenr = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            imageView.setVisibility(View.INVISIBLE);
            SplashScreen.this.startActivity(new Intent(SplashScreen.this, MainActivity.class));
            SplashScreen.this.finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
}
