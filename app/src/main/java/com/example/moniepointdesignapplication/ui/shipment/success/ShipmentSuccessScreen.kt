package com.example.moniepointdesignapplication.ui.shipment.success

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moniepointdesignapplication.R

/**
 * This composable file is to display a completion of activity status
 * for the calculate activity of the application
 */
@Composable
fun ShipmentSuccessScreen(
    onBackToHomeClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(60.dp), // Uniform padding around the content
        contentAlignment = Alignment.Center // Center the entire content
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp), // Equal spacing between items
            horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
        ) {
            Image(
                painter = painterResource(R.drawable.movemate),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
            )

            // Centered Package Image
            Box(
                modifier = Modifier
                    .weight(1f) // Allows the box to take available space
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_box),
                    contentDescription = "Package",
                    modifier = Modifier
                        .size(150.dp) // Adjust size as needed
                )
            }

            // Total Estimated Amount Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Total Estimated Amount",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2D3748)
                )
                Text(
                    text = "$1460 USD",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF48BB78),
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = "This amount is estimated this will vary if you change your location or weight",
                    fontSize = 14.sp,
                    color = Color(0xFF718096),
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp)
                )
            }

            // Back to Home Button
            Button(
                onClick = { onBackToHomeClick() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF6AD55)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("Back to home", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Preview
@Composable
fun ShipmentSuccessScreenPreview() {
    ShipmentSuccessScreen(
        onBackToHomeClick = {}
    )
}