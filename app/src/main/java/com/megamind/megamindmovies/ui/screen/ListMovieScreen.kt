package com.megamind.megamindmovies.ui.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.megamind.megamindmovies.R
import com.megamind.megamindmovies.domain.model.Movie
import com.megamind.megamindmovies.domain.model.categories
import com.megamind.megamindmovies.domain.model.movies
import kotlinx.coroutines.launch


@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SharedTransitionScope.ListMoviesScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (String, String, imageRes: Int) -> Unit
) {


    val drawableState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(

        drawerState = drawableState,
        drawerContent = {

            ModalDrawerSheet ( modifier = Modifier.fillMaxWidth(0.7f)){

                    Column {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            painter = painterResource(R.drawable.theshi),
                            contentDescription = null,

                            contentScale = ContentScale.Crop
                        )
                        HorizontalDivider()

                        LazyColumn {

                            items(12){

                                Text("$it")
                            }
                        }
                    }

                }


        }


    ) {

        Scaffold(topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { coroutineScope.launch { drawableState.open() } }) {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = null
                        )
                    }
                },
                title = { Text("MegaMind Tech", fontWeight = FontWeight.W900) },
                actions = {
                    IconButton(onClick = {},
                        content = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Rechercher"
                            )
                        })
                })
        }) { innerPadding ->


            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 8.dp)
            ) {


                item {

                    LazyRow {

                        items(categories) {

                            ElevatedFilterChip(
                                shape = RoundedCornerShape(20.dp),
                                colors = SelectableChipColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                                    labelColor = MaterialTheme.colorScheme.onBackground,
                                    leadingIconColor = MaterialTheme.colorScheme.onBackground,
                                    trailingIconColor = MaterialTheme.colorScheme.onBackground,
                                    disabledContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                                    disabledLabelColor = MaterialTheme.colorScheme.onBackground,
                                    disabledLeadingIconColor = MaterialTheme.colorScheme.onBackground,
                                    disabledTrailingIconColor = MaterialTheme.colorScheme.onBackground,
                                    selectedContainerColor = MaterialTheme.colorScheme.onBackground,
                                    disabledSelectedContainerColor = MaterialTheme.colorScheme.onBackground,
                                    selectedLabelColor = MaterialTheme.colorScheme.onBackground,
                                    selectedLeadingIconColor = MaterialTheme.colorScheme.onBackground,
                                    selectedTrailingIconColor = MaterialTheme.colorScheme.onBackground,
                                ),
                                modifier = Modifier.padding(2.8.dp),
                                selected = false,
                                onClick = {},
                                label = {
                                    Text(
                                        it.description,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                })


                        }

                    }

                }

                items(movies) { movie ->


                    Card(modifier = Modifier.padding(top = 0.dp)) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onItemClick(movie.title, movie.description, movie.image)
                                }) {

                            Image(
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(120.dp)
                                    .sharedElement(
                                        state = rememberSharedContentState(key = "img_${movie.image}"),
                                        animatedVisibilityScope = animatedVisibilityScope,
                                        boundsTransform = { i, t ->
                                            tween(
                                                durationMillis = 500,
                                                easing = EaseOut
                                            )
                                        }

                                    ),
                                painter = painterResource(movie.image),
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )
                            Spacer(Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = movie.title,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Spacer(Modifier.height(12.dp))
                                Text(
                                    text = movie.description,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }


                            Row(modifier = Modifier.padding(start = 6.dp)) {

                                for (i in 1..5) {
                                    Icon(
                                        modifier = Modifier.size(14.dp),
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null
                                    )

                                }

                            }

                        }


                    }

                    Spacer(Modifier.height(12.dp))
                }


            }

        }

    }
}


