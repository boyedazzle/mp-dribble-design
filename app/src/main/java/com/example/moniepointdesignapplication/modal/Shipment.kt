package com.example.moniepointdesignapplication.modal

import com.example.moniepointdesignapplication.R

// Data class to hold shipment details
data class Shipment(
    val status: String,
    val title: String,
    val description: String,
    val cost: String,
    val date: String,
    val category: String,
    val imageResId: Int // Resource ID for the image
)

// Create list place holder for the shipment history view
object CreateShipment {
    fun sampleShipmentList(): List<Shipment> {
        return listOf(
            Shipment(
                status = "in-progress",
                title = "Arriving today!",
                description = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
                cost = "$1400 USD",
                date = "Sep 20, 2023",
                category = "In progress",
                imageResId = R.drawable.ic_box
            ),
            Shipment(
                status = "completed",
                title = "Delivered yesterday!",
                description = "Your delivery, #XYZ123456789 from New York, was delivered yesterday!",
                cost = "$900 USD",
                date = "Sep 19, 2023",
                category = "Completed",
                imageResId = R.drawable.ic_box
            ),
            Shipment(
                status = "in-progress",
                title = "Arriving today!",
                description = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
                cost = "$370 USD",
                date = "Sep 20, 2023",
                category = "In progress",
                imageResId = R.drawable.ic_box
            ),
            Shipment(
                status = "completed",
                title = "Delivered yesterday!",
                description = "Your delivery, #XYZ123456789 from New York, was delivered yesterday!",
                cost = "$900 USD",
                date = "Sep 19, 2023",
                category = "Completed",
                imageResId = R.drawable.ic_box
            ),
            Shipment(
                status = "completed",
                title = "Delivered yesterday!",
                description = "Your delivery, #XYZ123456789 from New York, was delivered yesterday!",
                cost = "$900 USD",
                date = "Sep 19, 2023",
                category = "Completed",
                imageResId = R.drawable.ic_box
            ),
            Shipment(
                status = "in-progress",
                title = "Arriving today!",
                description = "Your delivery, #NEJ20089934122231 from Atlanta, is arriving today!",
                cost = "$3570 USD",
                date = "Sep 20, 2023",
                category = "In progress",
                imageResId = R.drawable.ic_box
            ),
            Shipment(
                status = "pending",
                title = "Arriving today!",
                description = "Your delivery, #XYZ123456789 from New York, was delivered yesterday!",
                cost = "$650 USD",
                date = "Sep 20, 2023",
                category = "Pending",
                imageResId = R.drawable.ic_box
            ),
            Shipment(
                status = "completed",
                title = "Delivered yesterday!",
                description = "Your delivery, #XYZ123456789 from New York, was delivered yesterday!",
                cost = "$900 USD",
                date = "Sep 19, 2023",
                category = "Completed",
                imageResId = R.drawable.ic_box
            ),
            Shipment(
                status = "completed",
                title = "Delivered yesterday!",
                description = "Your delivery, #XYZ123456789 from New York, was delivered yesterday!",
                cost = "$900 USD",
                date = "Sep 19, 2023",
                category = "Completed",
                imageResId = R.drawable.ic_box
            ),
            Shipment(
                status = "pending",
                title = "Arriving today!",
                description = "Your delivery, #XYZ123456789 from New York, was delivered yesterday!",
                cost = "$650 USD",
                date = "Sep 20, 2023",
                category = "Pending",
                imageResId = R.drawable.ic_box
            ),
            Shipment(
                status = "pending",
                title = "Arriving today!",
                description = "Your delivery, #XYZ123456789 from New York, was delivered yesterday!",
                cost = "$230 USD",
                date = "Sep 20, 2023",
                category = "Pending",
                imageResId = R.drawable.ic_box
            ),
            Shipment(
                status = "pending",
                title = "Arriving today!",
                description = "Your delivery, #XYZ123456789 from New York, was delivered yesterday!",
                cost = "$230 USD",
                date = "Sep 20, 2023",
                category = "Pending",
                imageResId = R.drawable.ic_box
            )

            // Add more shipments as needed
        )
    }
}
