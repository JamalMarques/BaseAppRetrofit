package com.basesoft.baseappretro.RestAPI;

import com.basesoft.baseappretro.Models.APIModels.Responses.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by yamil.marques on 26/12/2016.
 */

public interface APIInterface {

    @GET(APIConstants.URL_TOP_RATED)
    Call<MoviesResponse> getTopRatedMovies(@Query(APIConstants.PARAMETER_API_KEY) String apiKey);

    @GET(APIConstants.URL_GET_MOVIE)
    Call<MoviesResponse> getMovieDetails(@Path(APIConstants.PARAMETER_API_ID) int id,
                                         @Query(APIConstants.PARAMETER_API_KEY) String apiKey);

}
