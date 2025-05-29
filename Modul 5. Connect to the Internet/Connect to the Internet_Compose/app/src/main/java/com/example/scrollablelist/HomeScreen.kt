package com.example.scrollablelist.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.scrollablelist.data.FilmRepository
import com.example.scrollablelist.network.RetrofitInstance
import com.example.scrollablelist.ui.FilmViewModel
import com.example.scrollablelist.ui.FilmViewModelFactory

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current

    val repository = remember {
        FilmRepository(
            api = RetrofitInstance.api,
            apiKey = "9fad0dc9a0338ecf00596875e4cf5645"
        )
    }
    val viewModel: FilmViewModel = viewModel(factory = FilmViewModelFactory(repository))

    val films by viewModel.filmList.collectAsState()
    val event by viewModel.event.collectAsState()

    val imageBaseUrl = "https://image.tmdb.org/t/p/w500"

    LaunchedEffect(event) {
        when (event) {
            is FilmViewModel.Event.NavigateToDetail -> {
                val film = (event as FilmViewModel.Event.NavigateToDetail).film
                navController.navigate(
                    "detail/${Uri.encode(film.title)}/${Uri.encode(film.posterPath ?: "")}/${Uri.encode(film.overview)}"
                )
                viewModel.clearEvent()
            }
            is FilmViewModel.Event.OpenWebUrl -> {
                val url = (event as FilmViewModel.Event.OpenWebUrl).url
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                navController.context.startActivity(intent)
                viewModel.clearEvent()
            }
            null -> Unit
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(films) { film ->
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2C2C2C)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(imageBaseUrl + (film.posterPath ?: "")),
                        contentDescription = film.title,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = film.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = film.overview,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            maxLines = 3,
                            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row {
                            Button(
                                onClick = { viewModel.onItemClicked(film) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFB3C7F9),
                                    contentColor = Color.Black
                                ),
                                shape = RoundedCornerShape(50),
                                modifier = Modifier.height(40.dp)
                            ) {
                                Text(text = "Detail")
                            }
                        }
                    }
                }
            }
        }
    }
}
