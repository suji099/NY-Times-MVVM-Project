package ny.sujith.com.nytimes.callback;

import android.util.Log;

import ny.sujith.com.nytimes.network.APIClient;
import ny.sujith.com.nytimes.network.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sujith Chandran on 09-08-2018.
 */

public class GetArticleImpl implements IGetArticle {

    @Override
    public void getArticleArrayList(final OnFinishedListener onFinishedListener) {
        /** Create handle for the APIClient interface*/
        NetworkService service = APIClient.getRetrofitInstance().create(NetworkService.class);

        /** Call the method with parameter in the interface to get the notice data*/
        Call<ny.sujith.com.nytimes.model.Response> call = service.getArticles();

        /**Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<ny.sujith.com.nytimes.model.Response>() {
            @Override
            public void onResponse(Call<ny.sujith.com.nytimes.model.Response> call, Response<ny.sujith.com.nytimes.model.Response> response) {
                onFinishedListener.onFinished(response.body().getResults());

            }

            @Override
            public void onFailure(Call<ny.sujith.com.nytimes.model.Response> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });

    }

}
