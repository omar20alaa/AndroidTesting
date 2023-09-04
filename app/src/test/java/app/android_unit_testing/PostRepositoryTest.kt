package app.android_unit_testing

import app.android_unit_testing.model.Post
import app.android_unit_testing.network.ApiService
import app.android_unit_testing.repo.PostRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.fail
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class PostRepositoryTest {

    // Mock ApiService
    private val apiService: ApiService = mock(ApiService::class.java)

    // Class under test
    private lateinit var postRepository: PostRepository

    @Before
    fun setUp() {
        postRepository = PostRepository(apiService)
    }

    @Test
    fun `getPosts success`() = runBlocking {
        // Mock response data
        val posts = listOf(Post(1,1, "Title 1", "Body 1"),
            Post(2,2, "Title 2", "Body 2"))

        // Mock ApiService behavior
        `when`(apiService.getPosts()).thenReturn(posts)

        // Call the function to be tested
        val result = postRepository.getPosts()

        // Verify the interactions and result
        verify(apiService).getPosts()
        assert(result == posts)
    }

    @Test
    fun `getPosts failure`() = runBlocking {
        // Mock ApiService behavior to throw an exception
        `when`(apiService.getPosts()).thenAnswer {
            throw Exception("Network error")
        }
        try {
            // Call the function to be tested
            postRepository.getPosts()

            // If an exception is not thrown, fail the test
            fail("Expected an exception to be thrown")
        } catch (e: Exception) {
            // Optionally, you can assert the properties of the exception
            assertEquals("Network error", e.message)
        }
    }

}