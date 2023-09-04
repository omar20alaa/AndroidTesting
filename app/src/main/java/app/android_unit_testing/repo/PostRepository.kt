package app.android_unit_testing.repo

import app.android_unit_testing.model.Post
import app.android_unit_testing.network.ApiService
import javax.inject.Inject

class PostRepository @Inject
constructor(private val apiService: ApiService) {

    suspend fun getPosts(): List<Post> {
        return apiService.getPosts()
    }
}