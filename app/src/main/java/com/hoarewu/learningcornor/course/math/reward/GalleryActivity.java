package com.hoarewu.learningcornor.course.math.reward;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hoarewu.learningcornor.R;
import java.io.IOException;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private ImageAdapter imageAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        imageAdapter = new ImageAdapter(getApplicationContext());

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(imageAdapter);
    }

    static class ImageAdapter extends PagerAdapter {

        private final LayoutInflater layoutInflater;
        private List<Drawable> rewards;

        public ImageAdapter(Context context) {
            RewardManager rewardManager = new RewardManager(context);
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            try {
                rewards = rewardManager.loadUnlockedRewards(context.getAssets());
            } catch (IOException e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public int getCount() {
            return rewards.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = layoutInflater.inflate(R.layout.pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.galleryItem);
            imageView.setImageDrawable(rewards.get(position));

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }
}
