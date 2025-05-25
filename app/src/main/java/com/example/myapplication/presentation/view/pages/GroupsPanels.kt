package com.example.myapplication.presentation.view.pages

import android.widget.Toast
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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class SolarPanelGroup(
    val id: Int,
    val name: String,
    val panelCount: Int,
    val totalCapacityKw: Double,
    val location: String,
    val status: String,
    val panels: List<SolarPanel>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupsPanelsScreen() {
    val context = LocalContext.current
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
                    SolarPanel(2, "Connected", 24.3, 3.1, 34.9, "123 Solar St, City A")
                    // ... يمكنك إضافة باقي اللوحات هنا ...
                )
            ),
            // ... باقي المجموعات ...
        )
    }
    val newPanels = SolarPanelRepository.newPanels

    val darkColorScheme = darkColorScheme(
        primary = Color.White,
        onPrimary = Color.Black,
        secondary = Color.White,
        onSecondary = Color.Black,
        error = Color(0xFFCF6679),
        onError = Color.Black,
        background = Color(0xFF121212),
        onBackground = Color.White,
        surface = Color(0xFF121212),
        onSurface = Color.White,
        surfaceVariant = Color(0xFF2C2C2C),
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
                        containerColor = MaterialTheme.colorScheme.background
                    )
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues)
                    .padding(horizontal = 0.dp, vertical = 0.dp),
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                if (newPanels.isNotEmpty()) {
                    item {
                        SolarPanelGroupItem(
                            group = SolarPanelGroup(
                                id = -1,
                                name = "New Panels",
                                panelCount = newPanels.size,
                                totalCapacityKw = newPanels.sumOf { it.voltage },
                                location = "User Added",
                                status =


                        "Connected",



                        panels = newPanels
                        ),
                        isEditable = true
                        )
                    }
                }
                items(solarPanelGroups) { group ->
                    SolarPanelGroupItem(group = group)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolarPanelGroupItem(group: SolarPanelGroup, isEditable: Boolean = false) {
    var expanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(targetValue = if (expanded) 180f else 0f, label = "rotation")
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
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
                    color = MaterialTheme.colorScheme.onSurface
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    modifier = Modifier.rotate(rotationAngle),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(expandFrom = Alignment.Top),
                exit = shrinkVertically(shrinkTowards = Alignment.Top)
            ) {
                Column(
                    modifier = Modifier.padding(top = 12.dp)
                ) {
                    Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
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
                        group.panels.forEachIndexed { index, panel ->
                            PanelItem(
                                panel = panel,
                                isEditable = isEditable,
                                onDelete = {
                                    SolarPanelRepository.newPanels.removeAt(index)
                                    Toast.makeText(context, "تم حذف اللوحة!", Toast.LENGTH_SHORT).show()
                                },
                                onEdit = { updatedPanel ->
                                    SolarPanelRepository.newPanels[index] = updatedPanel
                                    Toast.makeText(context, "تم تعديل المعلومات!", Toast.LENGTH_SHORT).show()
                                }
                            )
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
                    "connected" -> Color(0xFF4CAF50)
                    "disconnected" -> Color(0xFFF44336)
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
fun PanelItem(
    panel: SolarPanel,
    isEditable: Boolean = false,
    onDelete: (() -> Unit)? = null,
    onEdit: ((SolarPanel) -> Unit)? = null
) {
    var showEditDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
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
                "connected" -> Color(0xFF4CAF50)
                "disconnected" -> Color(0xFFF44336)
                else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            }
        )
        if (isEditable) {
            Row {
                Button(
                    onClick = { showEditDialog = true },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text("Edit")
                }
                Button(
                    onClick = { onDelete?.invoke() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Delete", color = Color.White)
                }
            }
        }
        if (showEditDialog) {
            EditPanelDialog(panel = panel, onDismiss = { showEditDialog = false }, onSave = {
                onEdit?.invoke(it)
                showEditDialog = false
            })
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}

@Composable
fun EditPanelDialog(panel: SolarPanel, onDismiss: () -> Unit, onSave: (SolarPanel) -> Unit) {
    var voltage by remember { mutableStateOf(panel.voltage.toString()) }
    var current by remember { mutableStateOf(panel.current.toString()) }
    var temperature by remember { mutableStateOf(panel.temperature.toString()) }
    var address by remember { mutableStateOf(panel.address) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("تعديل اللوحة") },
        text = {
            Column {
                OutlinedTextField(value = voltage, onValueChange = { voltage = it }, label = { Text("V") })


                OutlinedTextField(value = current, onValueChange = { current = it }, label = { Text("I") })
                OutlinedTextField(value = temperature, onValueChange = { temperature = it }, label = { Text("T") })
                OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Address") })
            }
        },
        confirmButton = {
            Button(onClick = {
                onSave(panel.copy(
                    voltage = voltage.toDoubleOrNull() ?: panel.voltage,
                    current = current.toDoubleOrNull() ?: panel.current,
                    temperature = temperature.toDoubleOrNull() ?: panel.temperature,
                    address = address
                ))
            }) { Text("حفظ") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("إلغاء") }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewGroupsPanelsScreen() {
    GroupsPanelsScreen()
}