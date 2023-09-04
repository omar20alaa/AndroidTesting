package app.android_unit_testing.network

import app.android_unit_testing.model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<Post>

}