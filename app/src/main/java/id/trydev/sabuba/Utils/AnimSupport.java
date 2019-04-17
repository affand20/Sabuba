package id.trydev.sabuba.Utils;

import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class AnimSupport {

    public void slideLeftHideAnim(View view, AnimatorListenerAdapter adapter){
        view.animate()
                .translationX(-750f)
                .alpha(0f)
                .setListener(adapter);
    }

    public void slideLeftHideAnim(View view){
        view.animate()
                .translationX(-750f)
                .alpha(0f)
                .setListener(null);
    }

    public void slideLeftShowAnim(View view, AnimatorListenerAdapter adapter){
        view.animate()
                .translationX(0f)
                .alpha(1f)
                .setListener(adapter);
    }

    public void slideLeftShowAnim(View view){
        view.animate()
                .translationX(0f)
                .alpha(1f)
                .setListener(null);
    }

    public void slideRightShowAnim(View view, AnimatorListenerAdapter adapter){
        view.animate()
                .translationX(0f)
                .alpha(1f)
                .setListener(adapter);
    }

    public void slideRightShowAnim(View view){
        view.animate()
                .translationX(0f)
                .alpha(1f)
                .setListener(null);
    }

    public void slideRightHideAnim(View view, AnimatorListenerAdapter adapter){
        view.animate()
                .translationX(500f)
                .alpha(0f)
                .setListener(adapter);
    }

    public void slideRightHideAnim(View view){
        view.animate()
                .translationX(500f)
                .alpha(0f)
                .setListener(null);
    }
}
