package com.example.mad03_fragments_and_navigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mad03_fragments_and_navigation.fragments.QuizEndFragmentArgs
import com.example.mad03_fragments_and_navigation.fragments.QuizEndFragmentDirections
import com.example.mad03_fragments_and_navigation.R
import com.example.mad03_fragments_and_navigation.databinding.FragmentQuizEndBinding
import com.example.mad03_fragments_and_navigation.viewModels.EndFragmentViewMod
import com.example.mad03_fragments_and_navigation.viewModels.FactoryViewModelProvider


class QuizEndFragment : Fragment() {
    private lateinit var binding: FragmentQuizEndBinding
    private lateinit var viewModel: EndFragmentViewMod
    private lateinit var viewModelFactory: FactoryViewModelProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_quiz_end, container, false)


        viewModelFactory = FactoryViewModelProvider(
            QuizEndFragmentArgs.fromBundle(
                requireArguments()
            ).score)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EndFragmentViewMod::class.java)
        binding.textView7.text = QuizEndFragmentArgs.fromBundle(
            requireArguments()
        ).score.toString()
        restartQuiz()

        return binding.root
    }

    private fun restartQuiz() {
        binding.btnRestart.setOnClickListener {
            view?.findNavController()
                ?.navigate(QuizEndFragmentDirections.actionQuizEndFragmentToQuizFragment())
        }
    }
}