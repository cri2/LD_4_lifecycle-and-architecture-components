
package com.example.mad03_fragments_and_navigation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mad03_fragments_and_navigation.models.Question
import com.example.mad03_fragments_and_navigation.models.QuestionCatalogue

class QuizFragmentViewMod : ViewModel() {

    private val _endGame = MutableLiveData<Boolean>()
    val endGame: LiveData<Boolean>
        get() = _endGame

    private val _questions = MutableLiveData<MutableList<Question>>()
    val questions: LiveData<MutableList<Question>>
        get() = _questions

    private val _questionsCount = MutableLiveData<Int>()
    val questionsCount: LiveData<Int>
        get() = _questionsCount

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _index = MutableLiveData<Int>()
    val index: LiveData<Int>
        get() = _index

    init {
        _score.value = 0
        _index.value = 0
        _question.value = index.value?.let { questions.value?.get(it) }
        resetQuestions()
        _questionsCount.value = _questions.value?.size
        nextQuestion()
    }

    private fun resetQuestions() {
        _questions.value = QuestionCatalogue().defaultQuestions.toMutableList()
    }

    fun nextQuestion() {
        if (_questions.value?.isEmpty() == true) {
            onEndGame()
        } else {
            _question.value = (_questions.value)?.removeAt(0)
            _index.value = (index.value)?.plus(1)
        }
    }
    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
    }

    private fun onEndGame() {
        _endGame.value = true
    }

    fun onEndGameFinish() {
        _endGame.value = false
    }

}