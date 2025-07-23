package com.example.moniepointdesignapplication.ui.shipment.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moniepointdesignapplication.R
import com.example.moniepointdesignapplication.modal.SearchHistory

/**
 * This file is a composable file for creating the delivery application
 * custom search interface
 */
@Composable
fun CustomSearchItemScreen(
    navController: NavController,
    searchQuery: String = "",
    isSearchExpanded: Boolean = true,
    onSearchQueryChange: (String) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf(searchQuery) }
    var isSearchExpanded by remember { mutableStateOf(isSearchExpanded) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        CustomSearchItemTopBar(
            navController = navController,
            isSearchExpanded = isSearchExpanded,
            searchQuery = searchQuery,
            onSearchQueryChange = { newQuery ->
                searchQuery = newQuery
                onSearchQueryChange(newQuery)
            },
            onExpandChange = { isSearchExpanded = it }
        )
        CustomSearchItem(
            isSearchExpanded = isSearchExpanded,
            searchQuery = searchQuery,
            onSearchQueryChange = { newQuery ->
                searchQuery = newQuery
                onSearchQueryChange(newQuery)
            }
        )
    }
}

@Composable
fun CustomSearchItemTopBar(
    navController: NavController,
    isSearchExpanded: Boolean,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onExpandChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF5E2E91)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }

        AnimatedVisibility(
            visible = true,
            enter = fadeIn(animationSpec = tween(1000)) + expandVertically(),
            exit = fadeOut(),
            modifier = Modifier.background(Color(0xFF5E2E91))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White, RoundedCornerShape(24.dp))
                    .shadow(2.dp, RoundedCornerShape(24.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            onExpandChange(!isSearchExpanded)
                        }
                ) {
                    Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(8.dp))
                    BasicTextField(
                        value = searchQuery,
                        onValueChange = onSearchQueryChange,
                        textStyle = TextStyle(color = Color(0xFF2D3748), fontSize = 16.sp),
                        modifier = Modifier.weight(1f),
                        decorationBox = { innerTextField ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.weight(1f)) {
                                    if (searchQuery.isEmpty()) {
                                        Text(
                                            text = " ",
                                            color = Color.Gray,
                                            fontSize = 16.sp
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        painter = painterResource(R.drawable.trailing_search_icon),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CustomSearchItem(
    isSearchExpanded: Boolean,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
) {
    val searchHistory = listOf(
        SearchHistory(
            title = "Summer linen jacket",
            desc = "#NEJ20089934122231",
            location = "Barcelona → Paris",
            imageResId = R.drawable.search_history
        ),
        SearchHistory(
            title = "Tapered-fit jeans AW",
            desc = "#NEJ35870264978659",
            location = "Colombia → Paris",
            imageResId = R.drawable.search_history
        ),
        SearchHistory(
            title = "Macbook pro M2",
            desc = "#NE43857340857904",
            location = "Paris → Morocco",
            imageResId = R.drawable.search_history
        ),
        SearchHistory(
            title = "Slim fit jeans AW",
            desc = "#NEJ35870264978659",
            location = "France → German",
            imageResId = R.drawable.search_history
        ),
        SearchHistory(
            title = "Office setup desk",
            desc = "#NEJ23481570754963",
            location = "France → German",
            imageResId = R.drawable.search_history
        )
    )

    // Filter search history based on search query
    val filteredHistory = remember(searchQuery) {
        if (searchQuery.isEmpty()) searchHistory
        else searchHistory.filter {
            it.title.contains(searchQuery, ignoreCase = true) ||
                    it.desc.contains(searchQuery, ignoreCase = true) ||
                    it.location.contains(searchQuery, ignoreCase = true)
        }
    }

    if (isSearchExpanded && filteredHistory.isNotEmpty()) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Use a key to force animation trigger on filteredHistory change
                key(filteredHistory) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(16.dp)
                    ) {
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn(animationSpec = tween(500)) + slideInVertically(
                                animationSpec = tween(500),
                                initialOffsetY = { it / 2 }
                            ),
                            exit = fadeOut(animationSpec = tween(500))
                        ) {
                            LazyColumn {
                                items(filteredHistory, key = { "${it.desc}-${it.title}" }) { item ->
                                    SearchItem(
                                        searchHistory = item,
                                    ) {
                                        onSearchQueryChange(item.desc)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    } else if (searchQuery.isNotEmpty()) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "No results for \"$searchQuery\"",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun SearchItem(
    searchHistory: SearchHistory,
    onSearchedItemClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onSearchedItemClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 600.dp), // Prevent excessive stretching on large screens
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .sizeIn(minWidth = 36.dp, maxWidth = 48.dp, minHeight = 36.dp, maxHeight = 48.dp) // Adaptive image size
                    .clip(CircleShape)
                    .background(Color.LightGray.copy(alpha = 0.1f)) // Optional: subtle background for empty images
            ) {
                Image(
                    painter = painterResource(searchHistory.imageResId),
                    contentDescription = searchHistory.title,
                    modifier = Modifier.fillMaxSize(), // Ensure image fills the Box
                    contentScale = ContentScale.Crop // Prevent distortion
                )
            }

            Spacer(modifier = Modifier.width(8.dp)) // Consistent spacing

            Column(
                modifier = Modifier
                    .weight(1f) // Take remaining space
                    .padding(end = 8.dp) // Prevent text from touching edge
            ) {
                Text(
                    text = searchHistory.title,
                    fontSize = 18.sp,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis // Prevent wrapping
                )
                Row(
                    horizontalArrangement = Arrangement.Start, // Align to start for consistency
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = searchHistory.desc,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f, fill = false) // Allow text to shrink
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = " • ",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = searchHistory.location,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f, fill = false) // Allow text to shrink
                    )
                }
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 8.dp),
            thickness = 0.5.dp,
            color = Color.Gray
        )
    }
}

@Preview
@Preview(device = Devices.PIXEL_4)
@Preview(device = Devices.NEXUS_7)
@Preview(device = Devices.PIXEL_TABLET)
@Composable
fun CustomSearchItemPreview() {
    CustomSearchItemScreen(
        navController = rememberNavController()
    )
}