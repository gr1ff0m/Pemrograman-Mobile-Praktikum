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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.scrollablelist.model.Book
import com.example.scrollablelist.viewmodel.BookViewModel
import com.example.scrollablelist.viewmodel.BookViewModelFactory
import coil.compose.rememberAsyncImagePainter
import timber.log.Timber

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: BookViewModel = viewModel(factory = BookViewModelFactory("some query"))
    val books by viewModel.bookList.collectAsState()
    val event by viewModel.event.collectAsState()

    LaunchedEffect(event) {
        when (event) {
            is BookViewModel.Event.NavigateToDetail -> {
                val book = (event as BookViewModel.Event.NavigateToDetail).book
                navController.navigate("detail/${Uri.encode(book.title)}/${Uri.encode(book.imageUrl)}/${Uri.encode(book.fullDescription)}")
            }
            is BookViewModel.Event.OpenWebUrl -> {
                val url = (event as BookViewModel.Event.OpenWebUrl).url
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                navController.context.startActivity(intent)
            }
            null -> Unit
        }
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(books) { book ->
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
                        painter = rememberAsyncImagePainter(book.imageUrl),
                        contentDescription = book.title,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = book.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = book.shortDescription,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            maxLines = 2
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row {
                            Button(
                                onClick = {
                                    viewModel.onWebButtonClicked(book)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFB3C7F9),
                                    contentColor = Color.Black
                                ),
                                shape = RoundedCornerShape(50),
                                modifier = Modifier.height(40.dp)
                            ) {
                                Text(text = "Website")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = {
                                    viewModel.onItemClicked(book)
                                },
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
