package ny.sujith.com.nytimes.callback;

import java.util.List;

import ny.sujith.com.nytimes.model.Result;

public interface IGetArticle {
    interface OnFinishedListener {
        void onFinished(List<Result> resultList);
        void onFailure(Throwable t);
    }

    void getArticleArrayList(OnFinishedListener onFinishedListener);
}
