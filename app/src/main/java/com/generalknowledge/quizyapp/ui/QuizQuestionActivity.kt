package com.generalknowledge.quizyapp.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.generalknowledge.quizyapp.R
import com.generalknowledge.quizyapp.databinding.ActivityQuizQuestionBinding
import com.generalknowledge.quizyapp.model.Questions
import com.generalknowledge.quizyapp.util.Constants

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {
    private var mCurrentPosition = 1
    private var mQuestionsList: ArrayList<Questions> = ArrayList()
    private var mSelectedOptionPosition = 0
    private var mCorrectAnswer = 0
    private var mUserName: String? = null

    private lateinit var binding: ActivityQuizQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the title text with color
        setUpToolbarTextColor()
        mUserName = intent.getStringExtra(Constants.USER_NAME)
        mQuestionsList = Constants.getQuestion()
        addOnClickListener()
        setQuestions()

        
    }

    private fun addOnClickListener() {
        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)
    }

    private fun setUpToolbarTextColor() {
        val actionBar = supportActionBar
        val titleText = SpannableString("Quizy App")
        titleText.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.white)),
            0,
            titleText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        actionBar?.title = titleText
    }

    private fun setQuestions() {
        val question: Questions = mQuestionsList[mCurrentPosition - 1]
        defaultOptionView()

        binding.progressBar.progress = mCurrentPosition
        binding.tvProgressBar.text = "$mCurrentPosition/${binding.progressBar.max}"
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour
        binding.tvQuestion.text = question.question
        binding.imgFlag.setImageResource(question.image)

        if (mCurrentPosition == mQuestionsList.size)
            binding.btnSubmit.text = "FINISH"
        else
            binding.btnSubmit.text = "SUBMIT"
    }

    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)
        options.add(2, binding.tvOptionThree)
        options.add(3, binding.tvOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this, R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionView()

        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tvOptionOne -> {
                selectedOptionView(binding.tvOptionOne, 1)
            }

            R.id.tvOptionTwo -> {
                selectedOptionView(binding.tvOptionTwo, 2)
            }

            R.id.tvOptionThree -> {
                selectedOptionView(binding.tvOptionThree, 3)
            }

            R.id.tvOptionFour -> {
                selectedOptionView(binding.tvOptionFour, 4)
            }

            R.id.btnSubmit -> {
                if(mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList.size -> setQuestions()
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswer)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = mQuestionsList[mCurrentPosition - 1]
                    if (question.correctAnswer != mSelectedOptionPosition)
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    else
                        mCorrectAnswer++

                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList.size)
                        binding.btnSubmit.text = "FINISH"
                    else
                        binding.btnSubmit.text = "GO TO NEXT QUESTION"

                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> binding.tvOptionOne.background = ContextCompat.getDrawable(this, drawableView)
            2 -> binding.tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            3 -> binding.tvOptionThree.background = ContextCompat.getDrawable(this, drawableView)
            4 -> binding.tvOptionFour.background = ContextCompat.getDrawable(this, drawableView)
        }
    }
}