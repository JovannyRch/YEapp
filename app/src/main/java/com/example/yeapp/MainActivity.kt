package com.example.yeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yeapp.ui.theme.YEappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YEappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}




@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color.Blue, Color.LightGray)))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo y Bienvenida
        Text(
            "YEapp",
            color = Color.Red,
            fontSize = 24.sp
        )
        Text(
            "¡Bienvenido!",
            color = Color.White,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campos de Usuario y Contraseña
        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("Usuario") },
            leadingIcon = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "User Icon"
                )
            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Contraseña") },
            leadingIcon = {
                Icon(
                    Icons.Filled.Lock,
                    contentDescription = "Lock Icon"
                )
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Checkbox y Olvidé mi contraseña
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Checkbox(
                checked = false,
                onCheckedChange = {}
            )
            Text(text = "Recordarme")

            Text(
                text = "Olvidé mi contraseña",
                modifier = Modifier.clickable { /* acción */ },
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Botón Iniciar Sesión
        Button(onClick = { /* acción */ }) {
            Text("Iniciar sesión")
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Registro
        Row {
            Text(text = "No tengo cuenta")
            Text(
                text = " Registrarme",
                modifier = Modifier.clickable { /* acción */ },
                color = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Iconos de redes sociales
        Row {
            Icon(
                Icons.Filled.Info,
                contentDescription = "Instagram Icon",
                modifier = Modifier.clickable { /* acción */ }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                Icons.Filled.Info,
                contentDescription = "Facebook Icon",
                modifier = Modifier.clickable { /* acción */ }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                Icons.Filled.Info,
                contentDescription = "Twitter Icon",
                modifier = Modifier.clickable { /* acción */ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    YEappTheme {
        LoginScreen()
    }
}