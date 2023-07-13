package ru.otus.network.finish.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import ru.otus.network.databinding.FragmentCharactersBinding
import ru.otus.network.InjectorProvider

class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharactersViewModel by viewModels(
        factoryProducer = {
            (requireContext().applicationContext as InjectorProvider)
                .injector
                .provideViewModelFactory()
        }
    )

    private val adapter = CharactersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.uiRecyclerView.adapter = adapter
        binding.uiRecyclerView.layoutManager = LinearLayoutManager(context)

        subscribeUI()

        binding.uiSwipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(State.STARTED) {
                viewModel.state.collect { state: CharactersState ->
                    when {
                        state.isError -> Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                        state.isLoading -> showUILoading()
                        else -> {
                            adapter.submitList(state.items)
                            showUIContent()
                        }
                    }
                }
            }
        }
    }

    private fun showUILoading() {
        binding.uiRecyclerView.visibility = View.GONE
        binding.uiProgressBar.visibility = View.VISIBLE
        binding.uiMessage.visibility = View.GONE
        binding.uiSwipeRefreshLayout.isRefreshing = false
    }

    private fun showUIContent() {
        binding.uiRecyclerView.visibility = View.VISIBLE
        binding.uiProgressBar.visibility = View.GONE
        binding.uiMessage.visibility = View.GONE
        binding.uiSwipeRefreshLayout.isRefreshing = false
    }
}