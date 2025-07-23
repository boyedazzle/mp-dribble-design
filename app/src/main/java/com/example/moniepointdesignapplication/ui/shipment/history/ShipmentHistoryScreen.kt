package com.example.moniepointdesignapplication.ui.shipment.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moniepointdesignapplication.R
import com.example.moniepointdesignapplication.modal.CreateShipment
import com.example.moniepointdesignapplication.modal.Shipment

/**
 * This is a composable file to display the record of delivery conducted
 * by the user of the application ranging through several categories including
 * completed, all, pending, in-progress and cancelled
 */
@Composable
fun ShipmentHistoryScreen(
    navController: NavController
) {
        var selectedCategory by remember { mutableStateOf("All") }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .background(Color(0xFF5E2E91))
            ) {
                ShipmentHistoryTitle(navController = navController)
                ShipmentHistoryCategories(
                    selectedCategory = selectedCategory,
                    onCategorySelected = { category -> selectedCategory = category }
                )
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Shipments",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                ShipmentInfo(selectedCategory = selectedCategory)
            }

        }
    }
}

@Composable
fun ShipmentHistoryTitle(
    navController: NavController
) {
    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth(),
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

        Text(
            text = "Shipment history",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}

@Composable
fun ShipmentHistoryCategories(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {
    val items = listOf(
        "All" to 12,
        "Completed" to 5,
        "In progress" to 3,
        "Pending" to 4,
        "Cancelled" to 0
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 0.dp)
    ) {
        items(items) { (text, count) ->
            Box(
                modifier = Modifier
                    .clickable { onCategorySelected(text) }
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 0.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = text,
                            color = if (selectedCategory == text) Color.White else Color.LightGray,
                            fontSize = 16.sp,
                            fontWeight = if (selectedCategory == text) FontWeight.Bold else FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Surface(
                            color = Color(0xFF6B46C1),
                            contentColor = Color.LightGray,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.padding(start = 4.dp)
                        ) {
                            if (count > 0) {
                                Text(
                                    text = count.toString(),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    // Divider Image for selected category only
                    if (selectedCategory == text) {
                        Image(
                            painter = painterResource(R.drawable.ic_selected_category),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp) // Adjust height to match divider thickness
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ShipmentInfo(selectedCategory: String) {
    val shipments = CreateShipment.sampleShipmentList()

    // Filter shipments based on selected category
    val filteredShipments = remember(selectedCategory) {
        if (selectedCategory == "All") shipments.shuffled()
        else shipments.filter { it.category.equals(selectedCategory, ignoreCase = true) }
    }

    if (filteredShipments.isEmpty()) {
        Text(
            text = "No shipments found for $selectedCategory",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            textAlign = TextAlign.Center
        )
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(bottom = 16.dp)
        ) {
            items(filteredShipments) { shipment ->
                ShipmentDetailsCard(shipment)
            }
        }
    }
}

@Composable
fun ShipmentDetailsCard(shipment: Shipment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 8.dp)
            .background(Color.Transparent),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Surface(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = shipment.status,
                                tint = Color(0xFF48BB78),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = shipment.status,
                                color = Color(0xFF48BB78),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = shipment.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2D3748)
                )
                Text(
                    text = shipment.description,
                    fontSize = 14.sp,
                    color = Color(0xFF4A5568),
                    modifier = Modifier.padding(top = 4.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(
                        text = shipment.cost,
                        fontSize = 12.sp,
                        color = Color(0xFF6B46C1),
                    )
                    Text(
                        text = " · ",
                        fontSize = 16.sp,
                        color = Color(0xFF4A5568),
                    )
                    Text(
                        text = shipment.date,
                        fontSize = 12.sp,
                        color = Color(0xFF4A5568),
                    )
                }
            }
            Image(
                painter = painterResource(id = shipment.imageResId),
                contentDescription = "Package Icon",
                modifier = Modifier
                    .size(48.dp)
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(4.dp))
                    .padding(start = 8.dp, end = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun ShipmentHistoryScreenPreview() {
    val navController = rememberNavController()
    ShipmentHistoryScreen(navController = navController)
}