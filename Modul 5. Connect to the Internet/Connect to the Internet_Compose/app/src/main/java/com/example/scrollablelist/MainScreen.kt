package com.example.scrollablelist.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.scrollablelist.model.Film

@Composable
fun MainScreen(viewModel: FilmViewModel) {
    // Ganti films ke filmList, dan pastikan collectAsState dengan tipe eksplisit
    val films = viewModel.filmList.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            SmallTopAppBar(title = { Text("Popular Movies") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(films.value) { film ->
                FilmListItem(film)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopAppBar(title: @Composable () -> Unit) {
    TopAppBar(title = title)
}

@Composable
fun FilmListItem(film: Film) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Navigate to details if needed */ }
            .padding(8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w500${film.posterPath}"),
            contentDescription = film.title,
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = film.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = film.overview,
                maxLines = 3,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
