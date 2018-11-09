package ny.sujith.com.nytimes.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;


import ny.sujith.com.nytimes.R;
import ny.sujith.com.nytimes.databinding.ActivityItemDetailBinding;

/**
 * Created by Sujith Chandran on 09-08-2018.
 */
public class NYArticleDetailActivity extends AppCompatActivity {

    private String mUrl;
    private String mTitle;
    private ActivityItemDetailBinding mBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinder = DataBindingUtil.setContentView(this, R.layout.activity_item_detail);
        setSupportActionBar(mBinder.detailToolbar);

        mBinder.fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareTextUrl();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initBundle(savedInstanceState);
    }

    private void initBundle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();

            mUrl = getIntent().getStringExtra(NYArticleDetailFragment.ARG_ITEM_URL);
            mTitle = getIntent().getStringExtra(NYArticleDetailFragment.ARG_ITEM_TITLE);
            arguments.putString(NYArticleDetailFragment.ARG_ITEM_URL, mUrl
            );
            arguments.putString(NYArticleDetailFragment.ARG_ITEM_TITLE, mTitle
            );
            NYArticleDetailFragment fragment = new NYArticleDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    /**
     * share the news link
     */
    private void shareTextUrl() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, mUrl);
        share.putExtra(Intent.EXTRA_TEXT, mTitle);

        startActivity(Intent.createChooser(share, "Share link!"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, HomeActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
