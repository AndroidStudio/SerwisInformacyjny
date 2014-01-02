package androidstudio.pl.serwisinformacyjny.animation;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;

public class AnimationsClass {

    public static LayoutAnimationController createAnimationSet() {
        final AnimationSet animationSet = new AnimationSet(true);
        animationSet.setStartOffset(500);
        final AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(500);

        final Animation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, -2.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        translateAnimation.setDuration(500);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());

        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(translateAnimation);
        return new LayoutAnimationController(animationSet, 0.2f);
    }
}
