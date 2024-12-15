package com.dam23_24.composecatalogolayout.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
Actividad 1:
Hacer que el texto del botón muestre "Cargar perfil", pero cambie a "Cancelar"
cuando se muestre la línea de progreso... Cuando pulsemos "Cancelar" vuelve al texto por defecto.
*/

@Preview(showBackground = true)
@Composable
fun Actividad1() {

    var showLoading by rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (showLoading) {
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 10.dp
            )
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Red,
                trackColor = Color.LightGray
            )
        }

        Button(
            onClick = { showLoading = !showLoading }
        ) {
            Text(text = if (showLoading) "Cancelar" else "Cargar perfil") // Cambia el texto del botón según el estado
        }
    }
}

/*
Actividad 2:
Modifica ahora también que se separe el botón de la línea de progreso 30 dp,
pero usando un estado... es decir, cuando no sea visible no quiero que tenga la separación
aunque no se vea.
*/

@Preview(showBackground = true)
@Composable
fun Actividad2() {

    var showLoading by rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showLoading) {
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 10.dp
            )
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Red,
                trackColor = Color.LightGray
            )
        }

        Button(
            onClick = { showLoading = !showLoading },
            modifier = Modifier.padding(top = if (showLoading) 30.dp else 0.dp) // Añade separación solo si el indicador es visible
        ) {
            Text(text = if (showLoading) "Cancelar" else "Cargar perfil")
        }
    }
}

/*
Actividad 3:
- Separar los botones entre ellos 10 dp, del indicador de progreso 15 dp y centrarlos horizontalmente.
- Cuando se clique el botón Incrementar, debe añadir 0.1 a la propiedad progress y quitar 0.1
  cuando se pulse el botón Decrementar.
- Evitar que nos pasemos de los márgenes de su propiedad progressStatus (0-1)
*/

@Preview(showBackground = true)
@Composable
fun Actividad3() {

    // Estado para el progreso, inicializado en 0
    var progress by rememberSaveable { mutableStateOf(0f) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally // Centrarlos horizontalmente
    ) {
        LinearProgressIndicator(
            progress = progress, // Progreso actual
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp) // Separación del indicador de 15dp
        )

        // Fila de botones separados por 10.dp
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp), // Espaciado entre botones de 10dp
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    if (progress < 1f) {
                        progress = (progress + 0.1f).coerceAtMost(1f) // Aumenta progreso sin superar 1.0
                    }
                }
            ) {
                Text(text = "Incrementar")
            }

            Button(
                onClick = {
                    if (progress > 0f) {
                        progress = (progress - 0.1f).coerceAtLeast(0f) // Reduce progreso sin bajar de 0.0
                    }
                }
            ) {
                Text(text = "Reducir")
            }
        }
    }
}

/*
Actividad 4:
Sitúa el TextField en el centro de la pantalla y haz que reemplace el valor de una coma por un punto
y que no deje escribir más de un punto decimal...
*/

@Preview(showBackground = true)
@Composable
fun Actividad4() {

    var myVal by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center // Situarlo en el centro de la pantalla
    ) {
        TextField(
            value = myVal,
            onValueChange = { input ->
                var reemplazarComas = input.replace(",", ".") // Reemplaza comas por puntos

                // Evita más de un punto decimal
                if (reemplazarComas.count { it == '.' } > 1) {
                    reemplazarComas = reemplazarComas.dropLast(1) // Elimina el carácter adicional
                }

                myVal = reemplazarComas
            },
            label = { Text(text = "Importe") },
        )
    }
}

/*
Actividad 5:
Haz lo mismo, pero elevando el estado a una función superior y usando un componente OutlinedTextField
al que debes añadir un padding alrededor de 15 dp y establecer colores diferentes en los bordes
cuando tenga el foco y no lo tenga.
A nivel funcional no permitas que se introduzcan caracteres que invaliden un número decimal.
*/

@Preview(showBackground = true)
@Composable
fun Actividad5() {
    var myVal by rememberSaveable { mutableStateOf("") }

    Actividad5Content(
        myVal = myVal,
        onValueChange = { input ->
            var reemplazarComas = input.replace(",", ".")

            // Filtrar caracteres no válidos para números decimales
            reemplazarComas = reemplazarComas.filterIndexed { index, char ->
                char.isDigit() || (char == '.' && !reemplazarComas.take(index).contains("."))
            }

            myVal = reemplazarComas
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Actividad5Content(myVal: String, onValueChange: (String) -> Unit) {

    // Gestión del foco para controlar cuando el usuario interactúa
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable {
                // Cuando se hace clic fuera del TextField, se pierde el foco
                focusManager.clearFocus()
            },
        contentAlignment = Alignment.Center
    ) {
        OutlinedTextField(
            value = myVal,
            onValueChange = onValueChange,
            label = { Text(text = "Importe") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp), // Padding alrededor de 15 dp.
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue, // Color del borde con foco
                unfocusedBorderColor = Color.Gray, // Color del borde sin foco
            ),
        )
    }
}