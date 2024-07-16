package com.generalknowledge.quizyapp.util

import com.generalknowledge.quizyapp.R
import com.generalknowledge.quizyapp.model.Questions

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestion(): ArrayList<Questions> {
        val questionList: ArrayList<Questions> = ArrayList()

        questionList.add(
            Questions(
                1, "What Country does this flag belong to?",
                R.drawable.ic_flag_of_india, "India", "Argentina",
                "Armenia", "Cuba", 1
            )
        )

        questionList.add(
            Questions(
                2, "What Country does this flag belong to?",
                R.drawable.ic_flag_of_argentina, "Bangladesh", "Argentina",
                "Iran", "Cuba", 2
            )
        )

        questionList.add(
            Questions(
                3, "What Country does this flag belong to?",
                R.drawable.ic_flag_of_australia, "Belarus", "Belize",
                "Qatar", "Australia", 4
            )
        )

        questionList.add(
            Questions(
                4, "What Country does this flag belong to?",
                R.drawable.ic_flag_of_belgium, "Belgium", "Russia",
                "Serbia", "Denmark", 1
            )
        )

        questionList.add(
            Questions(
                5, "What Country does this flag belong to?",
                R.drawable.ic_flag_of_brazil, "Fiji", "France",
                "Brazil", "Germany", 3
            )
        )

        questionList.add(
            Questions(
                6, "What Country does this flag belong to?",
                R.drawable.ic_flag_of_denmark, "Ghana", "Turkey",
                "Spain", "Denmark", 4
            )
        )

        questionList.add(
            Questions(
                7, "What Country does this flag belong to?",
                R.drawable.ic_flag_of_fiji, "Italy", "Fiji",
                "Kuwait", "Uganda", 2
            )
        )

        questionList.add(
            Questions(
                8, "What Country does this flag belong to?",
                R.drawable.ic_flag_of_germany, "Laos", "Germany",
                "Armenia", "Algeria", 2
            )
        )

        questionList.add(
            Questions(
                9, "What Country does this flag belong to?",
                R.drawable.ic_flag_of_kuwait, "Namibia", "Oman",
                "Bahrain", "Kuwait", 4
            )
        )

        questionList.add(
            Questions(
                10, "What Country does this flag belong to?",
                R.drawable.ic_flag_of_new_zealand, "Egypt", "Singapore",
                "NewZealand", "Serbia", 3
            )
        )

        return questionList
    }
}