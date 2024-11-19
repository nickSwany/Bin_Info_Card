package com.example.test_task.history.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_task.databinding.FragmentHistoryBinding
import com.example.test_task.history.HistoryState
import com.example.test_task.history.ui.adapter.HistoryAdapter
import com.example.test_task.search.domain.model.BinInfoCard
import org.koin.androidx.viewmodel.ext.android.viewModel


class HistoryFragment : Fragment() {
    companion object {
        fun newInstance() = HistoryFragment()
    }

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<HistoryViewModel>()

    private val adapter = HistoryAdapter { card ->
        println("Number $card")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            rcHistory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rcHistory.adapter = adapter
        }

        viewModel.fillData()

        viewModel.stateLiveData().observe(viewLifecycleOwner) { state ->
            render(state)
        }

        viewModel.historyList.observe(viewLifecycleOwner) { cards ->
            showContent(cards)
        }
    }

    private fun render(state: HistoryState) {
        when (state) {
            is HistoryState.Content -> {
                val cards = state.card.reversed()
                showContent(cards)
            }
            HistoryState.Empty -> showLoading()
            HistoryState.Loading -> showEmpty()
        }
    }

    private fun showLoading() {
        binding.apply {
            rcHistory.isVisible = false
        }
    }

    private fun showEmpty() {
        binding.apply {
            rcHistory.isVisible = false
        }
    }

    private fun showContent(cards: List<BinInfoCard>) {
        binding.apply {
            rcHistory.isVisible = true
        }
        adapter.historyList.clear()
        adapter.historyList.addAll(cards)
        adapter.notifyDataSetChanged()
    }
}
