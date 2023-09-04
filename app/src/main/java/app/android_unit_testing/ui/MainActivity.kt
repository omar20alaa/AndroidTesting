package app.android_unit_testing.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import app.android_unit_testing.adapter.PostAdapter
import app.android_unit_testing.databinding.ActivityMainBinding
import app.android_unit_testing.view_model.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: PostViewModel  by viewModels()
    private lateinit var postAdapter: PostAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        postAdapter = PostAdapter(emptyList())
        binding.recyclerView.adapter = postAdapter

        viewModel.posts.observe(this) { posts ->
            postAdapter.setData(posts)
        }

        lifecycleScope.launch {
            try {
                viewModel.fetchPosts()
            } catch (e: Exception) {
              e.localizedMessage
            }
        }
    }
}