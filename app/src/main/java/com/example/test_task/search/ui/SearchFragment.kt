package com.example.test_task.search.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.test_task.databinding.FragmentSearchBinding
import com.example.test_task.util.debounce
import com.example.test_task.search.domain.model.BinInfoCard
import com.example.test_task.history.ui.adapter.HistoryClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<SearchViewModel>()

    private var textWatcher: TextWatcher? = null
    private var clickDebounce: ((Boolean) -> Unit)? = null
    private var isClickAllowed = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getScreenState().observe(viewLifecycleOwner) {
            render(it)
        }

        clickDebounce = debounce(
            CLICK_DEBOUNCE_DELAY,
            viewLifecycleOwner.lifecycleScope, false
        ) {
            isClickAllowed = true
        }

        binding.clearBt.setOnClickListener {
            viewModel.clear()
            binding.editText.text.clear()
            binding.cardContainer.isVisible = false
        }

        textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val input = s?.toString()?.trim() ?: ""

                if (input.length == 8) {
                    viewModel.searchDebounce(s?.toString() ?: "")


                } else {
                    binding.apply {
                        cardContainer.isVisible = false
                        tvError.isVisible = false
                        progressbar.isVisible = false
                    }
                }

            }
        }

        textWatcher.let { binding.editText.addTextChangedListener(it) }

    }

    @SuppressLint("SetTextI18n")
    private fun render(screenState: SearchScreenState) {
        when (screenState) {
            is SearchScreenState.Success -> {
                val info = screenState.binInfo.firstOrNull() ?: return
                binding.apply {
                    nameTv.text = info.bank.name
                    emailTv.text = info.bank.url
                    numberTv.text = info.bank.phone
                    brandTv.text = info.brand
                    tvSchemeNetwork.text = info.scheme
                    lengthTv.text = info.number.length.toString()
                    luhnTv.text = if (info.number.luhn == true) "Yes" else "No"
                    typeTv.text = info.type
                    preparidTv.text = if (info.prepaid) "Yes" else "No"
                    countryTv.text = info.country.name

                    emailTv.setOnClickListener {
                        viewModel.emailTo(info.bank.url)
                    }

                    numberTv.setOnClickListener {
                        viewModel.callTo(info.bank.phone)
                    }

                    progressbar.isVisible = false
                    cardContainer.isVisible = true
                    tvError.isVisible = false
                }
            }

            is SearchScreenState.Error -> {
                binding.apply {
                    progressbar.isVisible = false
                    cardContainer.isVisible = false
                    tvError.isVisible = true
                }
            }

            is SearchScreenState.Loading -> {
                binding.apply {
                    progressbar.isVisible = true
                    cardContainer.isVisible = false
                    tvError.isVisible = false
                }
            }

            is SearchScreenState.Default -> {
                binding.apply {
                    progressbar.isVisible = false
                    cardContainer.isVisible = false
                    tvError.isVisible = false
                }
            }
        }
    }

    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 1000L
        fun newInstance() = SearchFragment()
    }
}