package app.android_unit_testing.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.android_unit_testing.repo.PostRepository
import app.android_unit_testing.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject
constructor(private val postRepository: PostRepository) : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                val fetchedPosts = postRepository.getPosts()
                _posts.value = fetchedPosts
            } catch (e: Exception) {
               e.localizedMessage
            }
        }
    }
}