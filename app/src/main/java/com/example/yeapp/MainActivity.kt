package com.example.yeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.yeapp.ui.theme.YEappTheme

data class Product(
    val title: String,
    val description: String,
    val imageUrl: String
)

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
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(onNavigateToRegisterScreen = { navController.navigate("register") }, onNavigateToMainScreen = { navController.navigate("home") }) }
        composable("register") { RegisterScreen(onNavigateToLogin = { navController.navigate("login") }) }
        composable("home") { MainScreen() }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("YEapp") },
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(Icons.Filled.Search, contentDescription = "Buscar")
                    }
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Carrito")
                    }
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Black,
                contentColor = Color.White
            ) {
                IconButton(onClick = { /* TODO */ }) {
                    Icons.Filled.Home
                }
                IconButton(onClick = { /* TODO */ }) {
                    Icons.Filled.ShoppingCart
                }
            }
        }
    ) { padding ->
        val products =  remember {
            listOf(
                Product(
                    title = "MacBook Pro",
                    description = "Pantalla de 14\" Procesador M2 de 8 núcleos 8GB de RAM Hasta 12 horas de batería",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/4/4c/Apple_MacBook_Pro%2C_model_A1278-8112.jpg"
                ),
                Product(
                    title = "Huawei D14",
                    description = "Procesador Ryzen 5 4550G 16 GB de RAM Pantalla de 12.2\" SSD de 512 GB",
                    imageUrl = "https://www.trustedreviews.com/wp-content/uploads/sites/54/2023/06/Huawei-MateBook-X-Pro-2023-23-1300x731.jpg"
                ),
            )
        }

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .background(Color.White)
        ) {
            items(products) { product ->
                ProductCard(product)
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberAsyncImagePainter(model = product.imageUrl),
                contentDescription = product.title,
                modifier = Modifier
                    .size(100.dp)
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .weight(2f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(product.title, style = MaterialTheme.typography.titleMedium)
                Text(product.description, style = MaterialTheme.typography.bodySmall)
                TextButton(onClick = { /* TODO */ }) {
                    Text("Conoce más")
                }
            }
        }
    }
}

@Composable
fun LoginScreen(onNavigateToRegisterScreen: () -> Unit, onNavigateToMainScreen: () -> Unit) {
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
        Button(onClick = {
            onNavigateToMainScreen()
        }) {
            Text("Iniciar sesión")
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Registro
        Row {
            Text(text = "No tengo cuenta")
            Text(
                text = " Registrarme",
                modifier = Modifier.clickable {
                    onNavigateToRegisterScreen()
                },
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

@Composable
fun RegisterScreen(onNavigateToLogin: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color.Blue, Color.LightGray)))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo y título
        Text(
            "YEapp",
            color = Color.Red,
            fontSize = 24.sp
        )
        Text(
            "Registro de Usuario",
            color = Color.White,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campos de Registro
        val username = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val confirmPassword = remember { mutableStateOf("") }

        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("User") },
            leadingIcon = {
                Icons.Filled.Person
            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            leadingIcon = {
                Icons.Filled.Email
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            leadingIcon = {
                Icons.Filled.Lock
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = confirmPassword.value,
            onValueChange = { confirmPassword.value = it },
            label = { Text("Confirm Password") },
            leadingIcon = {
                Icons.Filled.Lock
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Botón Registrar
        Button(onClick = { /* acción */ }) {
            Text("Registrar")
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Ir a Iniciar Sesión
        Row {
            Text(text = "¿Ya tienes una cuenta?")
            Text(
                text = " Inicia sesión aquí",
                modifier = Modifier.clickable { onNavigateToLogin() },
                color = Color.Red,
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}