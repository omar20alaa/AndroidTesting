package app.android_unit_testing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import app.android_unit_testing.model.Post
import app.android_unit_testing.repo.PostRepository
import app.android_unit_testing.view_model.PostViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PostViewModelTest {

    /*
    https://developer.android.com/kotlin/coroutines/test
    Unit testing code that uses coroutines requires some extra attention,
    as their execution can be asynchronous and happen across multiple threads.
    This guide covers how suspending functions can be tested,
    the testing constructs you need to
    be familiar with, and how to make your code that uses coroutines testable.
    */
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Mock dependencies using MockK
    private val postRepository: PostRepository = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()

    // ViewModel to be tested
    private lateinit var postViewModel: PostViewModel

    @Before
    fun setUp() {
        postViewModel = PostViewModel(postRepository)
    }

    @After
    fun tearDown() {
        unmockkAll() // Release all mocks
        testDispatcher.cancel()
    }

    @Test
    fun `fetchPosts success`() = testDispatcher.run {
        // Mock data
        val posts = listOf(Post(1, 1,
            "Title 1", "Body 1"),
            Post(2, 2, "Title 2", "Body 2"))

        // Mock repository behavior
        coEvery { postRepository.getPosts() } returns posts

        // Observer for the LiveData
        val observer: Observer<List<Post>> = mockk(relaxed = true)
        postViewModel.posts.observeForever(observer)

        // Call the function to be tested
        postViewModel.fetchPosts()

        // Verify the interactions and captured values
        coVerify { postRepository.getPosts() }
        verify { observer.onChanged(posts) }

        // Clean up
        postViewModel.posts.removeObserver(observer)
    }

    @Test
    fun `fetchPosts error`() = testDispatcher.run {
        // Mock repository behavior
        val exception = Exception("Network error")
        coEvery { postRepository.getPosts() } throws exception

        // Observer for the LiveData
        val observer: Observer<List<Post>> = mockk(relaxed = true)
        postViewModel.posts.observeForever(observer)

        // Call the function to be tested
        postViewModel.fetchPosts()

        // Verify the interactions and captured values
        coVerify { postRepository.getPosts() }

        // Clean up
        postViewModel.posts.removeObserver(observer)
    }
}