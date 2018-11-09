package ny.sujith.com.nytimes.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

import ny.sujith.com.nytimes.R;
import ny.sujith.com.nytimes.databinding.ActivityItemListBinding;
import ny.sujith.com.nytimes.view.adapter.ArticleListAdapter;
import ny.sujith.com.nytimes.model.Result;
import ny.sujith.com.nytimes.view.utils.CustomProgressDialog;
import ny.sujith.com.nytimes.viewmodel.ArticleListViewModel;

/**
 * Created by Sujith Chandran on 09-08-2018.
 */
public class HomeActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {


    private CustomProgressDialog mCustomProgressDialog;
    private ActivityItemListBinding mBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinder = DataBindingUtil.setContentView(this, R.layout.activity_item_list);


        setupToolbar();

        setupNavigationView();



        setupViewModel();


    }

    private void setupViewModel() {
        ArticleListViewModel articleListViewModel = ViewModelProviders.of(this).get
                (ArticleListViewModel.class);

        observeNewsData(articleListViewModel);


    }

    /**
     * observe news data.
     * @param articleListViewModel
     */
    private void observeNewsData(ArticleListViewModel articleListViewModel) {
        createProgressDialog();
        showProgressDialog();
        articleListViewModel.getNewsList().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> results) {
                hideProgressDialog();
                setupRecyclerView(results);
            }
        });
    }

    private void setupNavigationView() {
        mBinder.navView.setNavigationItemSelectedListener(this);
    }

    private void setupToolbar() {
        setSupportActionBar(mBinder.appBarMain.toolbar);
        mBinder.appBarMain.toolbar.setTitle(getTitle());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mBinder.drawerLayout, mBinder.appBarMain.toolbar, R.string
                .navigation_drawer_open, R.string
                .navigation_drawer_close);
        mBinder.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupRecyclerView(List<Result> resultList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinder.appBarMain.includedList.itemList.setLayoutManager(layoutManager);

        ArticleListAdapter articleListAdapter = new ArticleListAdapter(resultList, this,
                getFragmentManager());
        mBinder.appBarMain.includedList.itemList.setAdapter(articleListAdapter);
    }


    private void showProgressDialog() {
        if (!mCustomProgressDialog.isShowing())
            mCustomProgressDialog.showDialog();
    }

    private void createProgressDialog() {
        try {
            if (null == mCustomProgressDialog)
                mCustomProgressDialog = new CustomProgressDialog(this);
        } catch (Exception er) {

        }
    }


    private void hideProgressDialog() {
        try {
            if (null != mCustomProgressDialog)
                mCustomProgressDialog.dismissDialog();
        } catch (Exception er) {

        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

}
