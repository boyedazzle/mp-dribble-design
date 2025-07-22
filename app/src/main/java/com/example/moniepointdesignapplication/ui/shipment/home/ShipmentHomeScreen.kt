package com.example.moniepointdesignapplication.ui.shipment.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moniepointdesignapplication.R

@Composable
fun ShipmentHomeScreen(
    onCalculateClicked: () -> Unit,
    onShipmentClicked: () -> Unit,
    onSearchItemClick: () -> Unit
) {
    val scrollState = rememberLazyListState()

    Scaffold(
        topBar = { TopSection() },
        bottomBar = {
            BottomNavBar(
                onCalculateClicked = {
                    onCalculateClicked()
                },
                onShipmentClicked = {
                    onShipmentClicked()
                }
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column {
                SearchBar(
                    onSearchItemClick = {
                        onSearchItemClick()
                    }
                )

                LazyColumn(
                    state = scrollState,
                ) {
                    item {
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn() + slideInVertically(),
                            exit = fadeOut()
                        ) {
                            TrackingCard()
                        }
                    }
                    item { AvailableVehiclesSection() }
                }
            }
        }
    }
}

@Composable
fun TopSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF5E2E91))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.profile), // change drawable back to profile
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color.LightGray,
                        modifier = Modifier.size(16.dp)
                    )
                    Text("Your location", color = Color.White, fontSize = 12.sp)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Wertheimer, Illinois", color = Color.White, fontWeight = FontWeight.Bold)
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Notifications", tint = Color.White)
                }
            }
        }
        Surface(
            shape = CircleShape,
            color = Color.White
        ) {
            Icon(
                imageVector = Icons.Default.Notifications, // Bell icon
                contentDescription = "Bell Icon",
                modifier = Modifier
                    .size(36.dp)
                    .padding(8.dp),
                tint = Color.Black
            )
        }
    }
}

@Composable
fun SearchBar(
    onSearchItemClick: () -> Unit
) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(animationSpec = tween(1000)) + expandVertically(),
        exit = fadeOut(),
        modifier = Modifier.background(Color(0xFF5E2E91))
    ) {
        Surface(
            color = Color.Transparent,
            modifier = Modifier.fillMaxWidth()
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
                            // navigate to custom search item screen
                            onSearchItemClick()
                        }
                ) {
                    Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Enter the receipt number …",
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.weight(1f))
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
fun TrackingCard() {
    Column {
        Text(
            "Tracking",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFFF7F7F7), RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Shipment Number", color = Color.Gray)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("NEJ20089934122231", fontWeight = FontWeight.Bold)
                }

                Image(
                    painter = painterResource(R.drawable.shipment),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f) // Take half the width
                        .padding(end = 8.dp) // Add padding to avoid crowding
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.sender),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Column {
                            Text("Sender", color = Color.Gray)
                            Text("Atlanta, 5243", fontWeight = FontWeight.Bold)
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.receiver),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Column {
                            Text("Receiver", color = Color.Gray)
                            Text("Chicago, 6342", fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(1f) // Take half the width
                        .padding(start = 8.dp) // Add padding for balance
                ) {
                    Text("Time", color = Color.Gray)
                    Row(
                        horizontalArrangement = Arrangement.Start, // Align content to start
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("• ", color = Color.Green, style = TextStyle(fontSize = 24.sp))
                        Text("2 day - 3 days", fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Status", color = Color.Gray)
                    Text("Waiting to collect", fontWeight = FontWeight.Bold)
                }
            }
            // inconsistent across screens
            /*Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.sender),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Column {
                            Text("Sender", color = Color.Gray)
                            Text("Atlanta, 5243", fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.receiver),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Column {
                            Text("Receiver", color = Color.Gray)
                            Text("Chicago, 6342", fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Column {
                    Text("Time", color = Color.Gray)
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("• ", color = Color.Green, style = TextStyle(fontSize = 24.sp))
                        Text("2 day - 3 days", fontWeight = FontWeight.Bold)
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Status", color = Color.Gray)
                    Text("Waiting to collect", fontWeight = FontWeight.Bold)
                }
            }*/

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))

            Text("+ Add Stop", color = Color(0xFFFF6A00), modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun AvailableVehiclesSection() {
    Column(modifier = Modifier.padding(start = 16.dp, top = 8.dp)) {
        Text("Available vehicles", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(end = 16.dp)
        ) {
            items(listOf("Ocean freight", "Cargo freight", "Air freight")) { type ->
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn(animationSpec = tween(500)) + expandHorizontally(),
                    exit = fadeOut()
                ) {
                    VehicleCard(title = type, subtitle = if (type == "Cargo freight") "Reliable" else "International")
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun VehicleCard(title: String, subtitle: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .size(width = 170.dp, height = 200.dp).padding(bottom = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(title, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(subtitle, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.ShoppingCart, contentDescription = null, modifier = Modifier.size(48.dp)) // change icon back to DirectionsBoat
            }
        }
    }
}

@Composable
fun BottomNavBar(
    onCalculateClicked: () -> Unit,
    onShipmentClicked: () -> Unit
) {
    var isCalculateSelected by remember { mutableStateOf(false) }
    var isShipmentSelected by remember { mutableStateOf(false) }

    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            icon = { Image(
                painter = painterResource(R.drawable.home),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(20.dp),
                contentScale = ContentScale.Fit
            ) },
            selected = false,
            onClick = {  }
        )
        NavigationBarItem(
            icon = { Image(
                painter = if (isCalculateSelected) painterResource(R.drawable.ic_calculate_selected) else painterResource(R.drawable.calculate),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(16.dp)
            ) },
            selected = isCalculateSelected,
            onClick = {
                isCalculateSelected = !isCalculateSelected
                onCalculateClicked()
            }
        )
        NavigationBarItem(
            icon = { Image(
                painter = if (isShipmentSelected) painterResource(R.drawable.ic_shipment_selected) else painterResource(R.drawable.ic_shipment),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(16.dp)
            ) },
            selected = isShipmentSelected,
            onClick = {
                isShipmentSelected = !isShipmentSelected
                onShipmentClicked()
            }
        )
        NavigationBarItem(
            icon = { Image(
                painter = painterResource(R.drawable.ic_profile),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(20.dp)
            ) },
            selected = false,
            onClick = { }
        )
    }
}

@Preview
@Preview(device = Devices.PIXEL_4)
@Preview(device = Devices.NEXUS_7)
@Preview(device = Devices.PIXEL_TABLET)
@Composable
fun HomeScreenPreview() {
    ShipmentHomeScreen(
        onCalculateClicked = {},
        onShipmentClicked = {},
        onSearchItemClick = {}
    )
}