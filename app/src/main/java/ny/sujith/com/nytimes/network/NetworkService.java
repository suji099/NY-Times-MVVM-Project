package ny.sujith.com.nytimes.network;

import ny.sujith.com.nytimes.model.Response;
import retrofit2.Call;
import retrofit2.http.GET;
/**
 * Created by Sujith Chandran on 09-08-2018.
 */
public interface NetworkService {

    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key" +
            "=aea8b7c7a56e4bdd994a1325a61869ec")
    Call<Response> getArticles();
}
