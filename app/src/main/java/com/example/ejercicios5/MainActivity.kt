package com.example.ejercicios5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dam23_24.composecatalogolayout.screens.Actividad1
import com.dam23_24.composecatalogolayout.screens.Actividad2
import com.dam23_24.composecatalogolayout.screens.Actividad3
import com.dam23_24.composecatalogolayout.screens.Actividad4
import com.dam23_24.composecatalogolayout.screens.Actividad5
import com.example.ejercicios5.ui.theme.Ejercicios5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicios5Theme {
                Actividad5() // Cambiar el nombre con la actividad que corresponda
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ejercicios5Theme {
        Actividad5() // Cambiar el nombre con la actividad que corresponda
    }
}