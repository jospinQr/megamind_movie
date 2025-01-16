package com.megamind.megamindmovies.ui.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SharedTransitionScope.DetailScreen(
    title: String,
    details: String,
    image: Int,
    animatedVisibilityScope: AnimatedVisibilityScope
) {




    Scaffold { innerPadding ->


        Column(
            modifier = Modifier.fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Box {

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp)
                        .sharedElement(
                            state = rememberSharedContentState(key = "img_${image}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { i, t -> tween(durationMillis = 800) })
                        .clip(RectangleShape),
                    painter = painterResource(image),
                    contentDescription = null, contentScale = ContentScale.Crop
                )
                IconButton(
                    modifier = Modifier.padding(vertical = 36.dp, horizontal = 12.dp),
                    onClick = {},
                    colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.background)
                ) { Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null) }
            }

            Text(title)
            Text(details)

        }


    }
}