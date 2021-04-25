package com.example.mad03_fragments_and_navigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import com.example.mad03_fragments_and_navigation.R
import com.example.mad03_fragments_and_navigation.databinding.FragmentQuizBinding
import com.example.mad03_fragments_and_navigation.viewModels.QuizFragmentViewMod

class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private lateinit var viewModel: QuizFragmentViewMod

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_quiz, container, false)

        viewModel = ViewModelProvider(this).get(QuizFragmentViewMod::class.java)

        viewModel.questionsCount.observe(viewLifecycleOwner) { counter -> binding.questionsCount = counter}

        viewModel.question.observe(viewLifecycleOwner, Observer { newQuestion -> binding.question = newQuestion })

        viewModel.index.observe(viewLifecycleOwner) { newIndex -> binding.index = newIndex }

        viewModel.endGame.observe(viewLifecycleOwner, Observer<Boolean> { hasFinished ->
            if (hasFinished) gameFinished()
        })


        binding.btnNext.setOnClickListener {
            if (getClickedRadioButton() != -1) {
                if (binding.question?.answers?.get(getClickedRadioButton())?.isCorrectAnswer == true) {
                    viewModel.onCorrect()
                }


                viewModel.nextQuestion()

            }
        }

        return binding.root
    }

    private fun gameFinished() {
        val action =
            QuizFragmentDirections.actionQuizFragmentToQuizEndFragment()
        action.score = viewModel.score.value ?: 0
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.onEndGameFinish()
    }

    private fun getClickedRadioButton(): Int {
        val clickedRB = binding.answerBox.checkedRadioButtonId
        return if (clickedRB == -1) {
            Toast.makeText(requireContext(), "Select an answer to continue!", Toast.LENGTH_SHORT).show()
            clickedRB
        } else {
            val radioButton: View = binding.answerBox.findViewById(clickedRB)
            binding.answerBox.indexOfChild(radioButton)
        }

    }

}