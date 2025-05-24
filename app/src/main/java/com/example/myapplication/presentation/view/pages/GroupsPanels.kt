package com.example.myapplication.presentation.view.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
// تم إزالة Icons.Filled.Add, Icons.Filled.Home, Icons.Filled.Person لأنها لم تعد تستخدم في هذا الملف
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data class for an individual solar panel
data class SolarPanel(
    val id: Int,
    val status: String, // "Connected" or "Disconnected"
    val voltage: Double, // Voltage (V)
    val current: Double, // Current (I)
    val temperature: Double, // Temperature (T)
    val address: String // Address
)

// Data class for a group of solar panels
data class SolarPanelGroup(
    val id: Int,
    val name: String, // Group name
    val panelCount: Int, // Number of solar panels
    val totalCapacityKw: Double, // Total capacity in kW
    val location: String, // Location
    val status: String, // Group status (e.g., "Connected")
    val panels: List<SolarPanel> // List of individual panels in the group
)

/**
 * Screen displaying a list of expandable solar panel groups.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupsPanelsScreen() {
    // Sample data for the groups and panels
    val solarPanelGroups = remember {
        listOf(
            SolarPanelGroup(
                id = 1,
                name = "GROUP 1",
                panelCount = 16,
                totalCapacityKw = 8.2,
                location = "Location A",
                status = "Connected",
                panels = listOf(
                    SolarPanel(1, "Connected", 24.5, 3.2, 35.1, "123 Solar St, City A"),
                    SolarPanel(2, "Connected", 24.3, 3.1, 34.9, "123 Solar St, City A"),
                    SolarPanel(3, "Connected", 24.6, 3.3, 35.5, "123 Solar St, City A"),
                    SolarPanel(4, "Connected", 24.4, 3.2, 35.0, "123 Solar St, City A"),
                    SolarPanel(5, "Connected", 24.7, 3.4, 36.0, "123 Solar St, City A"),
                    SolarPanel(6, "Connected", 24.2, 3.0, 34.5, "123 Solar St, City A"),
                    SolarPanel(7, "Connected", 24.5, 3.2, 35.2, "123 Solar St, City A"),
                    SolarPanel(8, "Connected", 24.3, 3.1, 34.8, "123 Solar St, City A"),
                    SolarPanel(9, "Connected", 24.6, 3.3, 35.3, "123 Solar St, City A"),
                    SolarPanel(10, "Connected", 24.4, 3.2, 35.0, "123 Solar St, City A"),
                    SolarPanel(11, "Disconnected", 0.0, 0.0, 25.0, "123 Solar St, City A"),
                    SolarPanel(12, "Connected", 24.5, 3.2, 35.1, "123 Solar St, City A"),
                    SolarPanel(13, "Connected", 24.3, 3.1, 34.9, "123 Solar St, City A"),
                    SolarPanel(14, "Connected", 24.6, 3.3, 35.5, "123 Solar St, City A"),
                    SolarPanel(15, "Connected", 24.4, 3.2, 35.0, "123 Solar St, City A"),
                    SolarPanel(16, "Connected", 24.7, 3.4, 36.0, "123 Solar St, City A")
                )
            ),
            SolarPanelGroup(
                id = 2,
                name = "GROUP 2",
                panelCount = 12,
                totalCapacityKw = 6.0,
                location = "Location B",
                status = "Connected",
                panels = listOf(
                    SolarPanel(1, "Connected", 23.8, 2.9, 33.0, "456 Sun Ave, City B"),
                    SolarPanel(2, "Connected", 23.9, 3.0, 33.2, "456 Sun Ave, City B"),
                    SolarPanel(3, "Connected", 23.7, 2.8, 32.8, "456 Sun Ave, City B"),
                    SolarPanel(4, "Connected", 24.0, 3.1, 33.5, "456 Sun Ave, City B"),
                    SolarPanel(5, "Connected", 23.8, 2.9, 33.1, "456 Sun Ave, City B"),
                    SolarPanel(6, "Connected", 23.9, 3.0, 33.3, "456 Sun Ave, City B"),
                    SolarPanel(7, "Connected", 23.7, 2.8, 32.9, "456 Sun Ave, City B"),
                    SolarPanel(8, "Connected", 24.0, 3.1, 33.6, "456 Sun Ave, City B"),
                    SolarPanel(9, "Disconnected", 0.0, 0.0, 25.0, "456 Sun Ave, City B"),
                    SolarPanel(10, "Connected", 23.8, 2.9, 33.0, "456 Sun Ave, City B"),
                    SolarPanel(11, "Connected", 23.9, 3.0, 33.2, "456 Sun Ave, City B"),
                    SolarPanel(12, "Connected", 23.7, 2.8, 32.8, "456 Sun Ave, City B")
                )
            ),
            SolarPanelGroup(
                id = 3,
                name = "GROUP 3",
                panelCount = 8,
                totalCapacityKw = 4.0,
                location = "Location C",
                status = "Disconnected",
                panels = listOf(
                    SolarPanel(1, "Disconnected", 0.0, 0.0, 25.0, "789 Power Rd, City C"),
                    SolarPanel(2, "Connected", 22.5, 2.5, 30.0, "789 Power Rd, City C"),
                    SolarPanel(3, "Connected", 22.6, 2.6, 30.2, "789 Power Rd, City C"),
                    SolarPanel(4, "Connected", 22.4, 2.4, 29.8, "789 Power Rd, City C"),
                    SolarPanel(5, "Connected", 22.7, 2.7, 30.5, "789 Power Rd, City C"),
                    SolarPanel(6, "Connected", 22.5, 2.5, 30.1, "789 Power Rd, City C"),
                    SolarPanel(7, "Connected", 22.6, 2.6, 30.3, "789 Power Rd, City C"),
                    SolarPanel(8, "Connected", 22.4, 2.4, 29.9, "789 Power Rd, City C")
                )
            ),
            SolarPanelGroup(
                id = 4,
                name = "GROUP 4",
                panelCount = 10,
                totalCapacityKw = 5.0,
                location = "Location D",
                status = "Connected",
                panels = emptyList() // Example with empty panels list
            ),
            SolarPanelGroup(
                id = 5,
                name = "GROUP 5",
                panelCount = 20,
                totalCapacityKw = 10.0,
                location = "Location E",
                status = "Connected",
                panels = emptyList()
            ),
            SolarPanelGroup(
                id = 6,
                name = "GROUP 6",
                panelCount = 5,
                totalCapacityKw = 2.5,
                location = "Location F",
                status = "Disconnected",
                panels = emptyList()
            ),
            SolarPanelGroup(
                id = 7,
                name = "GROUP 7",
                panelCount = 14,
                totalCapacityKw = 7.0,
                location = "Location G",
                status = "Connected",
                panels = emptyList()
            ),
            SolarPanelGroup(
                id = 8,
                name = "GROUP 8",
                panelCount = 9,
                totalCapacityKw = 4.5,
                location = "Location H",
                status = "Connected",
                panels = emptyList()
            ),
            SolarPanelGroup(
                id = 9,
                name = "GROUP 9",
                panelCount = 11,
                totalCapacityKw = 5.5,
                location = "Location I",
                status = "Disconnected",
                panels = emptyList()
            ),
            SolarPanelGroup(
                id = 10,
                name = "GROUP 10",
                panelCount = 18,
                totalCapacityKw = 9.0,
                location = "Location J",
                status = "Connected",
                panels = emptyList()
            ),
            SolarPanelGroup(
                id = 11,
                name = "GROUP 11",
                panelCount = 7,
                totalCapacityKw = 3.5,
                location = "Location K",
                status = "Connected",
                panels = emptyList()
            ),
            SolarPanelGroup(
                id = 12,
                name = "GROUP 12",
                panelCount = 13,
                totalCapacityKw = 6.5,
                location = "Location L",
                status = "Disconnected",
                panels = emptyList()
            )
        )
    }

    // Define a dark color scheme to match png.png
    val darkColorScheme = darkColorScheme(
        primary = Color.White, // White for main elements like text on dark background
        onPrimary = Color.Black,
        secondary = Color.White,
        onSecondary = Color.Black,
        error = Color(0xFFCF6679), // Red for errors
        onError = Color.Black,
        background = Color(0xFF121212), // Very dark grey/black for the overall background
        onBackground = Color.White,
        surface = Color(0xFF121212), // Same as background for cards to blend in
        onSurface = Color.White,
        surfaceVariant = Color(0xFF2C2C2C), // Not explicitly used for cards now, but kept for consistency
        onSurfaceVariant = Color.White
    )

    MaterialTheme(colorScheme = darkColorScheme) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Solar Panel Groups",
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background // Match overall background
                    )
                )
            },
            // تم حذف 'bottomBar' بالكامل من هنا
            // bottomBar = { ... }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background) // Apply overall background
                    .padding(paddingValues)
                    .padding(horizontal = 0.dp, vertical = 0.dp), // Remove horizontal padding
                verticalArrangement = Arrangement.spacedBy(1.dp) // Minimal spacing between cards
            ) {
                items(solarPanelGroups) { group ->
                    SolarPanelGroupItem(group = group)
                }
            }
        }
    }
}

/**
 * Composable for a single expandable solar panel group item.
 *
 * @param group The solar panel group data to display.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolarPanelGroupItem(group: SolarPanelGroup) {
    var expanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(targetValue = if (expanded) 180f else 0f, label = "rotation")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(0.dp), // No rounded corners for cards
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface // Same as background for seamless look
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // No shadow for cards
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp) // Adjust padding for a more compact look
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = group.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface // White text
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    modifier = Modifier.rotate(rotationAngle),
                    tint = MaterialTheme.colorScheme.onSurface // White icon
                )
            }

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(expandFrom = Alignment.Top),
                exit = shrinkVertically(shrinkTowards = Alignment.Top)
            ) {
                Column(
                    modifier = Modifier.padding(top = 12.dp) // Adjust padding for expanded content
                ) {
                    Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)) // Lighter divider
                    Spacer(modifier = Modifier.height(8.dp))
                    GroupDetailRow(label = "Number of Panels", value = "${group.panelCount}")
                    GroupDetailRow(label = "Total Capacity", value = "${group.totalCapacityKw} kW")
                    GroupDetailRow(label = "Location", value = group.location)
                    GroupDetailRow(label = "Status", value = group.status, isStatus = true)
                    if (group.panels.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Individual Panels:",
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        group.panels.forEach { panel ->
                            PanelItem(panel = panel)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GroupDetailRow(label: String, value: String, isStatus: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f), fontSize = 14.sp)
        Text(
            text = value,
            fontWeight = if (isStatus) FontWeight.Bold else FontWeight.Normal,
            color = if (isStatus) {
                when (value.lowercase()) {
                    "connected" -> Color(0xFF4CAF50) // Green
                    "disconnected" -> Color(0xFFF44336) // Red
                    else -> MaterialTheme.colorScheme.onSurface
                }
            } else {
                MaterialTheme.colorScheme.onSurface
            },
            fontSize = 14.sp
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
fun PanelItem(panel: SolarPanel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp) // Indent individual panel details
    ) {
        Text(text = "Panel ${panel.id}", fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface)
        Spacer(modifier = Modifier.height(2.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "V: ${panel.voltage} V", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f))
            Text(text = "I: ${panel.current} A", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f))
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "T: ${panel.temperature}°C", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f))
            Text(text = "Address: ${panel.address}", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f))
        }
        Text(
            text = "Status: ${panel.status}",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = when (panel.status.lowercase()) {
                "connected" -> Color(0xFF4CAF50) // Green
                "disconnected" -> Color(0xFFF44336) // Red
                else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            }
        )
    }
    Spacer(modifier = Modifier.height(4.dp))
}

@Preview(showBackground = true)
@Composable
fun PreviewGroupsPanelsScreen() {
    GroupsPanelsScreen()
}