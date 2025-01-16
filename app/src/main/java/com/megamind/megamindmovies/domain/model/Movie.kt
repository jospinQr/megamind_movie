package com.megamind.megamindmovies.domain.model

import androidx.compose.ui.graphics.painter.Painter
import com.megamind.megamindmovies.R

data class Movie(

    val id : Long,
    val title: String,
    val image : Int,
    val description: String

)


val movies = listOf(

    Movie(
        id = 1L,
        title = "The shi",
        image = R.drawable.theshi,
        "Une serie de dileurs"
    ),
    Movie(
        id = 2L,
        title = "Viking",
        image = R.drawable.viking,
        "Une serie de viking"
    ),
    Movie(
        id = 3L,
        title = "Game of throne",
        image = R.drawable.gameofthrone,
        "Une serie de royaumes"
    ),
)
