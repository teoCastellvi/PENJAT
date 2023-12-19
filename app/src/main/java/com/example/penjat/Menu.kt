package com.example.penjat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController


@Composable
fun Menu(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Option 1") }
    var show by remember { mutableStateOf(false) }
    var dificultat by remember { mutableStateOf("") }
    Column(
        modifier= Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .weight(2f)
                .background(Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo2),
                contentDescription = "logo2",
                modifier = Modifier
                    .size(400.dp)

            )

            dificultat = MyDropDownMenu()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            ElevatedButton(
                onClick = {navController.navigate(Routes.GameScreen.createRoute(dificultat))},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.LightGray
                )
            ) {
                Text(text = "Juega",fontSize = 25.sp)
            }

            ElevatedButton(
                onClick = { show = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.LightGray
                )
            ) {
                Text(text = "Ayuda",fontSize = 25.sp)
            }
            MyDialog(show, { show = false })
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropDownMenu(): String {
    var selectedText by remember { mutableStateOf("Escull la dificultat") }
    var expanded by remember { mutableStateOf(false) }
    val dificultades = listOf("Facil", "Normal", "Difícil")

    Column(modifier = Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            enabled = false,
            readOnly = true,
            modifier = Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
                .background(Color.White)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            dificultades.forEach {
                DropdownMenuItem(text = { Text(text = it) }, onClick = {
                    expanded = false
                    selectedText = it
                })

            }
        }

    }
return selectedText
}


@Composable
fun MyDialog(show: Boolean, onDismiss: () -> Unit){
    if(show){
        Dialog(onDismissRequest = { onDismiss() }) {
            Column(
                Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()) {
                Text(text = "¡Bienvenido al juego del Colgado! Tienes tres niveles de dificultad:\n" +
                        "\n" +
                        "Fácil: Palabras cortas para comenzar.\n" +
                        "Normal: Palabras moderadas, un buen desafío.\n" +
                        "Difícil: Palabras largas, ¡prepara tu habilidad!\n" +
                        "Selecciona tu nivel y disfruta del juego. ¡Buena suerte!")
            }
        }
    }
}
