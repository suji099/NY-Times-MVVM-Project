package ny.sujith.com.nytimes.view.adapter;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import ny.sujith.com.nytimes.view.utils.GlideApp;

/**
 * Created by Sujith Chandran on 09-08-2018.
 */

public class CustomBindingAdapter {

    @BindingAdapter("bind:imageUrl")

    public static void loadImage(ImageView imageView, String url) {

        GlideApp.with(imageView.getContext()).load(url).centerCrop().listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable
                    > target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable>
                    target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).apply(RequestOptions.circleCropTransform()).into(imageView);


    }

}
