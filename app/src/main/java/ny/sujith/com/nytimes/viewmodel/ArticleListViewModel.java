package ny.sujith.com.nytimes.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import ny.sujith.com.nytimes.EspressoTestingIdlingResource;
import ny.sujith.com.nytimes.callback.IGetArticle;
import ny.sujith.com.nytimes.model.Result;
import ny.sujith.com.nytimes.callback.GetArticleImpl;

/**
 * Created by Sujith Chandran on 09-08-2018.
 */
public class ArticleListViewModel extends ViewModel {
    public ArticleListViewModel() {
    }

    /**
     * observer live data to get news list
     */
    private MutableLiveData<List<Result>> newsList;

    /**
     * set the news list from API
     * @return
     */
    public LiveData<List<Result>> getNewsList() {
        if (newsList == null) {
            newsList = new MutableLiveData<>();
            loadNewsList();
        }
        return newsList;
    }

    private void loadNewsList() {
        EspressoTestingIdlingResource.increment();


        IGetArticle articleIntractor = new GetArticleImpl();
        articleIntractor.getArticleArrayList(new IGetArticle.OnFinishedListener() {
            @Override
            public void onFinished(List<Result> resultList) {
                newsList.setValue(resultList);

                EspressoTestingIdlingResource.decrement();
            }

            @Override
            public void onFailure(Throwable t) {
                newsList.setValue(null);

                EspressoTestingIdlingResource.decrement();
            }
        });
    }

}
