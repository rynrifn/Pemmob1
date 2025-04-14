package com.example.inputcontrolapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inputcontrolapp.ui.theme.InputControlAppTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InputControlAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    var phoneNumber by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    var showAlert by remember { mutableStateOf(false) }

    if (showDatePicker) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            context,
            { _, year, month, day ->
                val selectedDate = "$day/${month + 1}/$year"
                Toast.makeText(context, "Tanggal dipilih: $selectedDate", Toast.LENGTH_LONG).show()
                showDatePicker = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            title = { Text("Alert Dialog") },
            text = { Text("Ini adalah contoh Alert Dialog.") },
            confirmButton = {
                TextButton(onClick = { showAlert = false }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAlert = false }) {
                    Text("Batal")
                }
            }
        )
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Nomor HP") },
                placeholder = { Text("08xxxxxxxxxx") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = { showDatePicker = true }) {
                Text("Pilih Tanggal")
            }

            Button(onClick = {
                Toast.makeText(context, "Ini adalah Toast!", Toast.LENGTH_SHORT).show()
            }) {
                Text("Tampilkan Toast")
            }

            Button(onClick = { showAlert = true }) {
                Text("Tampilkan Alert")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    InputControlAppTheme {
        MainScreen()
    }
}
