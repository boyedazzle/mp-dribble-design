package com.example.moniepointdesignapplication.ui.shipment.calculate

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moniepointdesignapplication.R

@Composable
fun CalculateShipment(
    navController: NavController,
    onCalculateButtonClick: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize().background(Color.White)
                .padding(innerPadding)
        ) {
            CalculateShipmentTitle(navController)
            Spacer(modifier = Modifier.height(8.dp))
            DestinationInfo()
            PackagingInfo()
            CategoriesInfo()
            Spacer(modifier = Modifier.weight(1f))
            CalculateButton(
                onCalculateButtonClick = {
                    onCalculateButtonClick()
                }
            )
        }
    }
}

@Composable
fun CalculateShipmentTitle(navController: NavController) {
    Row(
        modifier = Modifier
            .height(50.dp)
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

        Text(
            text = "Calculate",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = Color.White
        )
    }
}

@Composable
fun DestinationInfo() {
    var senderLocation by remember { mutableStateOf("") }
    var receiverLocation by remember { mutableStateOf("") }
    var approxWeight by remember { mutableStateOf("") }

    
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Destination",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier.background(color = Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF7FAFC), shape = RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    BasicTextField(
                        value = senderLocation,
                        onValueChange = { senderLocation = it },
                        textStyle = TextStyle(color = Color(0xFF2D3748), fontSize = 16.sp),
                        modifier = Modifier.fillMaxWidth(),
                        decorationBox = { innerTextField ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(R.drawable.sender_location),
                                    contentDescription = "Location Icon",
                                    modifier = Modifier.size(20.dp).padding(end = 4.dp)
                                )
                                VerticalDivider(modifier = Modifier.size(20.dp))
                                Box(modifier = Modifier.weight(1f)) {
                                    if (senderLocation.isEmpty()) {
                                        Text(
                                            text = "Sender location",
                                            color = Color.Gray,
                                            fontSize = 16.sp
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF7FAFC), shape = RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    BasicTextField(
                        value = receiverLocation,
                        onValueChange = { receiverLocation = it },
                        textStyle = TextStyle(color = Color(0xFF2D3748), fontSize = 16.sp),
                        modifier = Modifier.fillMaxWidth(),
                        decorationBox = { innerTextField ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(R.drawable.receiver_location),
                                    contentDescription = "Receiver Location",
                                    modifier = Modifier.size(20.dp).padding(end = 4.dp)
                                )
                                VerticalDivider(modifier = Modifier.size(20.dp))
                                Box(modifier = Modifier.weight(1f)) {
                                    if (receiverLocation.isEmpty()) {
                                        Text(
                                            text = "Receiver location",
                                            color = Color.Gray,
                                            fontSize = 16.sp
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF7FAFC), shape = RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    BasicTextField(
                        value = approxWeight,
                        onValueChange = { approxWeight = it },
                        textStyle = TextStyle(color = Color(0xFF2D3748), fontSize = 16.sp),
                        modifier = Modifier.fillMaxWidth(),
                        decorationBox = { innerTextField ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(R.drawable.approx_weight),
                                    contentDescription = "Approx Weight",
                                    modifier = Modifier.size(20.dp).padding(end = 4.dp)
                                )
                                VerticalDivider(modifier = Modifier.size(20.dp))
                                Box(modifier = Modifier.weight(1f)) {
                                    if (approxWeight.isEmpty()) {
                                        Text(
                                            text = "Approx weight",
                                            color = Color.Gray,
                                            fontSize = 16.sp
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PackagingInfo() {
    var packaging by remember { mutableStateOf("Box") }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Packaging",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "What are you sending?",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF9FAFB), shape = RoundedCornerShape(8.dp))
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_box), // Replace with your box icon resource
                    contentDescription = "Box Icon",
                    modifier = Modifier.size(40.dp).padding(end = 12.dp)
                )
                VerticalDivider(
                    modifier = Modifier
                        .size(width = 1.dp, height = 24.dp) // Adjust height to match text
                        .background(Color.Gray)
                )
                Text(
                    text = packaging,
                    color = Color(0xFF1A202C),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 8.dp) // Adjust padding to control spacing
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { expanded = !expanded },
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Dropdown Arrow",
                        tint = Color.Gray
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    DropdownMenuItem(
                        text = { Text("Box") },
                        onClick = {
                            packaging = "Box"
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Envelope") },
                        onClick = {
                            packaging = "Envelope"
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CategoriesInfo() {
    var selectedCategory by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Categories", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = "What are you sending?", fontSize = 14.sp)

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp), // Space between buttons
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(
                onClick = { selectedCategory = "Documents" },
                colors = if (selectedCategory == "Documents") {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFF2D3748), contentColor = Color.White)
                } else {
                    ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF1A202C))
                },
                shape = RoundedCornerShape(8.dp),
                border = if (selectedCategory != "Documents") BorderStroke(1.dp, Color.LightGray) else null,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (selectedCategory == "Documents") {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    Text("Documents")
                }
            }
            Button(
                onClick = { selectedCategory = "Glass" },
                colors = if (selectedCategory == "Glass") {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFF2D3748), contentColor = Color.White)
                } else {
                    ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF1A202C))
                },
                shape = RoundedCornerShape(8.dp),
                border = if (selectedCategory != "Glass") BorderStroke(1.dp, Color.LightGray) else null,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (selectedCategory == "Glass") {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    Text("Glass")
                }
            }
            Button(
                onClick = { selectedCategory = "Liquid" },
                colors = if (selectedCategory == "Liquid") {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFF2D3748), contentColor = Color.White)
                } else {
                    ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF1A202C))
                },
                shape = RoundedCornerShape(8.dp),
                border = if (selectedCategory != "Liquid") BorderStroke(1.dp, Color.LightGray) else null,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (selectedCategory == "Liquid") {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    Text("Liquid")
                }
            }
            Button(
                onClick = { selectedCategory = "Food" },
                colors = if (selectedCategory == "Food") {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFF2D3748), contentColor = Color.White)
                } else {
                    ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF1A202C))
                },
                shape = RoundedCornerShape(8.dp),
                border = if (selectedCategory != "Food") BorderStroke(1.dp, Color.LightGray) else null,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (selectedCategory == "Food") {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    Text("Food")
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(
                onClick = { selectedCategory = "Electronic" },
                colors = if (selectedCategory == "Electronic") {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFF2D3748), contentColor = Color.White)
                } else {
                    ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF1A202C))
                },
                shape = RoundedCornerShape(8.dp),
                border = if (selectedCategory != "Electronic") BorderStroke(1.dp, Color.LightGray) else null,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (selectedCategory == "Electronic") {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    Text("Electronic")
                }
            }
            Button(
                onClick = { selectedCategory = "Product" },
                colors = if (selectedCategory == "Product") {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFF2D3748), contentColor = Color.White)
                } else {
                    ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF1A202C))
                },
                shape = RoundedCornerShape(8.dp),
                border = if (selectedCategory != "Product") BorderStroke(1.dp, Color.LightGray) else null,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (selectedCategory == "Product") {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    Text("Product")
                }
            }
            Button(
                onClick = { selectedCategory = "Others" },
                colors = if (selectedCategory == "Others") {
                    ButtonDefaults.buttonColors(containerColor = Color(0xFF2D3748), contentColor = Color.White)
                } else {
                    ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF1A202C))
                },
                shape = RoundedCornerShape(8.dp),
                border = if (selectedCategory != "Others") BorderStroke(1.dp, Color.LightGray) else null,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (selectedCategory == "Others") {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    Text("Others")
                }
            }
        }
    }
}

@Composable
fun CalculateButton(
    onCalculateButtonClick: () -> Unit
) {
    Button(
        onClick = {  onCalculateButtonClick() },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF6AD55)),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp).padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
        shape = RoundedCornerShape(25.dp)
    ) {
        Text("Calculate", color = Color.White, fontSize = 16.sp)
    }
}

@Preview
@Composable
fun CalculateShipmentPreview() {
    val navController = rememberNavController()
    CalculateShipment(
        navController = navController,
        onCalculateButtonClick = {}
    )
}