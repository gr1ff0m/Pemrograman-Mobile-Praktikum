package com.example.scrollablelist.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.scrollablelist.model.Book

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current

    val books = remember {
        listOf(
            Book(
                1,
                "Atomic Habits",
                "https://m.media-amazon.com/images/I/91bYsX41DVL.jpg",
                "https://www.goodreads.com/book/show/40121378-atomic-habits",
                "Build good habits and break bad ones.",
                "Atomic Habits by James Clear offers a proven framework for improving everyday life. Learn how tiny changes add up to remarkable results."
            ),
            Book(
                2,
                "Deep Work",
                "https://m.media-amazon.com/images/I/91nujEwIpYL._SL1500_.jpg",
                "https://www.goodreads.com/book/show/25744928-deep-work",
                "Focused success in a distracted world.",
                "Deep Work is a book about the benefits of intense focus and how to systematically train your mind and habits to achieve it."
            ),
            Book(
                3,
                "The Subtle Art of Not Giving a F*ck",
                "https://m.media-amazon.com/images/I/71QKQ9mwV7L.jpg",
                "https://www.goodreads.com/book/show/28257707-the-subtle-art-of-not-giving-a-f-ck",
                "Counterintuitive approach to living a good life.",
                "Mark Manson's book shows that life's struggles give it meaning, and teaches how to stop trying to be positive all the time."
            ),
            Book(
                4,
                "Thinking, Fast and Slow",
                "https://m.media-amazon.com/images/I/61fdrEuPJwL._SL1500_.jpg",
                "https://www.goodreads.com/book/show/11468377-thinking-fast-and-slow",
                "Explores how we think, make decisions, and act.",
                "Nobel laureate Daniel Kahneman explores two modes of thought: 'fast', intuitive thinking, and 'slow', deliberate thinking."
            ),
            Book(
                5,
                "Start With Why",
                "https://m.media-amazon.com/images/I/71NBZIExBCL._SY466_.jpg",
                "https://www.goodreads.com/book/show/7108725-start-with-why",
                "Find your why and inspire others.",
                "Simon Sinek explains how leaders can inspire cooperation, trust and change by focusing on the WHY behind their mission."
            ),
            Book(
                6,
                "The Power of Now",
                "https://m.media-amazon.com/images/I/61Ij8nLooNL._SL1500_.jpg",
                "https://www.amazon.com/Power-Now-Guide-Spiritual-Enlightenment/dp/1577314808",
                "A guide to spiritual enlightenment.",
                "Eckhart Tolle’s guide helps readers discover the importance of living in the present moment and letting go of the past and future."
            ),
            Book(
                7,
                "Can't Hurt Me",
                "https://m.media-amazon.com/images/I/81gTRv2HXrL._SL1500_.jpg",
                "https://www.goodreads.com/book/show/41721428-can-t-hurt-me",
                "Master your mind and defy the odds.",
                "David Goggins shares his incredible life story and teaches readers how to overcome pain, fear, and self-doubt to reach their full potential."
            ),
            Book(
                8,
                "Educated",
                "https://m.media-amazon.com/images/I/81WojUxbbFL.jpg",
                "https://www.goodreads.com/book/show/35133922-educated",
                "A memoir of transformation through education.",
                "Tara Westover tells her story of growing up in a strict and abusive household in rural Idaho and how she escaped through learning and education."
            )
        )
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
                                    try {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(book.webUrl))
                                        context.startActivity(intent)
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                    }
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
                                    navController.navigate(
                                        "detail/${Uri.encode(book.title)}/${Uri.encode(book.imageUrl)}/${Uri.encode(book.fullDescription)}"
                                    )
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
