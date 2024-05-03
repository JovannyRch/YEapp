package com.example.yeapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberAsyncImagePainter
import com.example.yeapp.ui.theme.YEappTheme

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val disponibilidad: String,
    val imageUrl: String,
    val price: String
)

class CartViewModel : ViewModel() {
    // Lista observable de productos en el carrito
    var cartItems = mutableStateListOf<Product>()
        private set

    // Método para agregar productos al carrito
    fun addToCart(product: Product) {
        cartItems.add(product)
    }

    // Método para remover productos del carrito
    fun removeFromCart(product: Product) {
        cartItems.remove(product)
    }
}

val allProducts =  listOf(
    Product(
        id= 1,
        title = "MacBook Pro",
        disponibilidad= "On Deplay",
        description = "Pantalla de 14\" Procesador M2 de 8 núcleos 8GB de RAM Hasta 12 horas de batería",
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/4/4c/Apple_MacBook_Pro%2C_model_A1278-8112.jpg",
        price= "$99.9"
    ),
    Product(
        id=2,
        title = "Huawei D14",
        disponibilidad= "On Deplay",
        description = "Procesador Ryzen 5 4550G 16 GB de RAM Pantalla de 12.2\" SSD de 512 GB",
        imageUrl = "https://www.trustedreviews.com/wp-content/uploads/sites/54/2023/06/Huawei-MateBook-X-Pro-2023-23-1300x731.jpg",
        price= "$99.9"
    ),
    Product(
        id = 3,
        title = "HP Omen 16",
        disponibilidad= "En exhibición",
        description = "Procesador Core i7-12th 16 GB de RAM Pantalla de 14\" SSD de 512 GB",
        imageUrl = "https://http2.mlstatic.com/D_NQ_NP_657721-MLU70380642107_072023-O.webp",
        price= "$99.9"
    ),
    Product(
        id= 4,
        title = "MacBook Pro",
        disponibilidad= "On Deplay",
        description = "Pantalla de 14\" Procesador M2 de 8 núcleos 8GB de RAM Hasta 12 horas de batería",
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/4/4c/Apple_MacBook_Pro%2C_model_A1278-8112.jpg",
        price= "$99.9"
    ),
    Product(
        id=5,
        title = "Huawei D14",
        disponibilidad= "On Deplay",
        description = "Procesador Ryzen 5 4550G 16 GB de RAM Pantalla de 12.2\" SSD de 512 GB",
        imageUrl = "https://www.trustedreviews.com/wp-content/uploads/sites/54/2023/06/Huawei-MateBook-X-Pro-2023-23-1300x731.jpg",
        price= "$99.9"
    ),
    Product(
        id = 6,
        title = "HP Omen 16",
        disponibilidad= "En exhibición",
        description = "Procesador Core i7-12th 16 GB de RAM Pantalla de 14\" SSD de 512 GB",
        imageUrl = "https://http2.mlstatic.com/D_NQ_NP_657721-MLU70380642107_072023-O.webp",
        price= "$99.9"
    ),
    Product(
        id= 1,
        title = "MacBook Pro",
        disponibilidad= "On Deplay",
        description = "Pantalla de 14\" Procesador M2 de 8 núcleos 8GB de RAM Hasta 12 horas de batería",
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/4/4c/Apple_MacBook_Pro%2C_model_A1278-8112.jpg",
        price= "$99.9"
    ),
    Product(
        id=7,
        title = "Huawei D14",
        disponibilidad= "On Deplay",
        description = "Procesador Ryzen 5 4550G 16 GB de RAM Pantalla de 12.2\" SSD de 512 GB",
        imageUrl = "https://www.trustedreviews.com/wp-content/uploads/sites/54/2023/06/Huawei-MateBook-X-Pro-2023-23-1300x731.jpg",
        price= "$99.9"
    ),
    Product(
        id = 8,
        title = "HP Omen 16",
        disponibilidad= "En exhibición",
        description = "Procesador Core i7-12th 16 GB de RAM Pantalla de 14\" SSD de 512 GB",
        imageUrl = "https://http2.mlstatic.com/D_NQ_NP_657721-MLU70380642107_072023-O.webp",
        price= "$99.9"
    ),
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
    val cartViewModel = viewModel<CartViewModel>()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(onNavigateToRegisterScreen = { navController.navigate("register") }, onNavigateToMainScreen = { navController.navigate("home") }) }
        composable("register") { RegisterScreen(onNavigateToLogin = { navController.navigate("login") }) }
        composable("home") { MainScreen(navController) }
        composable("detailsScreen/{productId}", arguments = listOf(navArgument("productId") { type = NavType.StringType })) { backStackEntry ->
            DetailsScreen(productId = backStackEntry.arguments?.getString("productId") ?: "", navController, cartViewModel)
        }

        composable("buyNowScreen/{productId}") { backStackEntry ->
            BuyNowScreen(
                productId = backStackEntry.arguments?.getString("productId") ?: "",
                products = allProducts,
                onPurchaseCompleted = {
                    navController.navigate("purchaseConfirmationScreen")
                }
            )
        }
        composable("purchaseConfirmationScreen") {
            PurchaseConfirmationScreen(navController)
        }
        composable("searchScreen") {
            SearchScreen(navController)
        }
        composable("cartScreen") { CartScreen(cartViewModel, navController) }

    }
}

@Composable
fun BuyNowScreen(productId: String, products: List<Product>, onPurchaseCompleted: () -> Unit) {
    val product = products.find { it.id == productId.toInt() }

    if (product == null) {
        Text("Producto no encontrado", style = MaterialTheme.typography.titleMedium, color = Color.Red)
        return
    }

    Scaffold(
        containerColor = Color.Black,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = product.title, style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
                Text(text = "Precio: ${product.price}", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(16.dp))
                Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
            }
            Button(
                onClick = {
                    onPurchaseCompleted()
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
            ) {
                Text("Comprar Ahora", color = Color.Black)
            }
        }
    }

}

@Composable
fun DetailsScreen(productId: String, navController: NavController, cartViewModel: CartViewModel) {

    // Encuentra el producto en la lista global basado en el ID
    val product = allProducts.find { it.id == productId.toInt() }

    // Si el producto no se encuentra, muestra un mensaje
    if (product == null) {
        Text("Producto no encontrado", style = MaterialTheme.typography.titleSmall, color = Color.Red)
        return
    }

    Scaffold(
        containerColor = Color.Black,
    ) { padding ->
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(modifier = Modifier.clickable {
                navController.navigate("home")
            }){
                AppLogo()
            }
            Spacer(modifier = Modifier.width(8.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)

                ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                    Text(text = product.title, style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(text = product.disponibilidad, style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold, color = Color.Black)
                    Box(modifier = Modifier.height(275.dp)){
                        Image(
                            painter = rememberAsyncImagePainter(model = product.imageUrl),
                            contentDescription = product.title,
                            modifier = Modifier
                                .size(275.dp)
                        )
                    }
                    Text(text = product.description, style = MaterialTheme.typography.bodyLarge, color= Color.DarkGray)

                    TextButton(onClick = { cartViewModel.addToCart(product) }, colors = ButtonDefaults.textButtonColors(containerColor = Color(0xFFFCD816))) {
                        Text(text = "Agregar al carrito", color = Color.Black)
                    }
                    TextButton(onClick = { navController.navigate("buyNowScreen/${product.id}") }, colors = ButtonDefaults.textButtonColors(containerColor = Color(0xFFF9A41C))) {
                        Text(text = "Comprar ahora", color = Color.Black)
                    }
                }
            }
        }
    }



}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(cartViewModel: CartViewModel, navController: NavController) {
    val cartItems = cartViewModel.cartItems

    Scaffold(
        containerColor = Color.Black,
        topBar = { TopAppBar(title = { Text("Carrito de Compras") },  colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black),) },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                if (cartItems.isEmpty()) {
                    ZeroStateView()
                } else {
                    LazyColumn {
                        items(cartItems) { product ->
                            CartItemView(product = product, navController = navController)
                        }
                    }
                }
            }

        }
    )
}

@Composable
fun ZeroStateView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Tu carrito está vacío", style = MaterialTheme.typography.titleMedium)

    }
}

@Composable
fun CartItemView(product: Product, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("detailsScreen/${product.id}") }
            .padding(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = product.imageUrl),
            contentDescription = product.title,
            modifier = Modifier
                .size(60.dp)
        )
        Spacer(Modifier.width(16.dp))
        Column {
            Text(product.title, fontWeight = FontWeight.Bold, color = Color.White)
            Text("Precio: ${product.price}", color = Color.White)
            Text("Toque para más detalles", color = Color.Gray)
        }
    }
}
@Composable
fun CartItemView(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(product.title, style = MaterialTheme.typography.titleMedium)
            Text("Precio: ${product.price}", style = MaterialTheme.typography.bodySmall)
        }
    }
}


@Composable
fun PurchaseConfirmationScreen(navController: NavController) {

    Scaffold(
        containerColor = Color.Black,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppLogo()
            Spacer(modifier = Modifier.height(24.dp))
            Text("¡Gracias por tu compra!", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(12.dp))
            TextButton(onClick = { navController.navigate("home") }) {
                Text(text = "Regrear al inicio", color = Color.White)
            }

        }
    }
}

@Composable
fun SearchScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }
    val filteredProducts = allProducts.filter {
        it.title.contains(searchText, ignoreCase = true)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        SearchBar(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            onSearch =  { text ->
                searchText = text
            }
        )

        LazyColumn {
            items(filteredProducts) { product ->
                ProductCard(product, navController)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier, onSearch: (text: String) -> Unit) {
    val searchText = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column{
        Box(
            modifier = modifier.height(56.dp),
            contentAlignment = Alignment.Center
        ) {
            OutlinedTextField(
                value = searchText.value,
                onValueChange = { searchText.value = it },
                textStyle = TextStyle(color = Color.Black),
                placeholder = { Text("Buscar...", color = Color.Black ) },
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Search),
                trailingIcon = {
                    IconButton(onClick = {
                        onSearch(searchText.value)
                        keyboardController?.hide()
                    }) {
                        Icon(Icons.Filled.Search, contentDescription = "Buscar", tint = Color.Black)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = MaterialTheme.shapes.small)
            )
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black),
                title = { AppLogo() },
                actions = {
                    IconButton(onClick = { navController.navigate("searchScreen") }) {
                        Icon(Icons.Filled.Search, contentDescription = "Buscar", tint = Color.White)
                    }
                    IconButton(onClick = { navController.navigate("cartScreen") }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Carrito", tint = Color.White)
                    }
                },
            )
        },

        ) { padding ->
        val products =  remember {
            allProducts
        }
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .padding(padding)
                .background(Color.Black)
        ) {
            Row {
                Text("¿Dónde nos encontramos?", color = Color.White, modifier = Modifier.padding(15.dp))
                Text(
                    "Ver ubicación",
                    color = Color.White,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://www.youtube.com/watch?v=PZ3inHrWvG0")
                            )
                            context.startActivity(intent)
                        }
                )
            }
            LazyColumn {
                items(products) { product ->
                    ProductCard(product, navController)
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product, navController: NavController) {
    Card( modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
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
                TextButton(onClick = { navController.navigate("detailsScreen/${product.id}") }) {
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
            .background(Brush.verticalGradient(listOf(Color.Blue, Color.DarkGray)))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo y Bienvenida
        AppLogo()
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
            label = { Text("Usuario", color = Color.White) },
            leadingIcon = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "User Icon",
                    tint = Color.White
                )
            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Contraseña", color = Color.White) },
            leadingIcon = {
                Icon(
                    Icons.Filled.Lock,
                    contentDescription = "Lock Icon",
                    tint = Color.White
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
                onCheckedChange = {},
                colors = CheckboxDefaults.colors(checkmarkColor = Color.White, uncheckedColor = Color.White)
            )
            Text(text = "Recordarme",color = Color.White)

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
            Text(text = "No tengo cuenta", color = Color.White)
            Spacer(modifier = Modifier.width(4.dp))
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
/*Row {
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
        }*/
    }
}

@Composable
fun RegisterScreen(onNavigateToLogin: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color.Blue, Color.DarkGray)))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo y título
        AppLogo()
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
            label = { Text("Nombre de usuario") },
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
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Correo electrónico") },
            leadingIcon = {
                Icon(
                    Icons.Filled.Email,
                    contentDescription = "Email"
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Contraseña") },
            leadingIcon = {
                Icon(
                    Icons.Filled.Lock,
                    contentDescription = "Password"
                )
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = confirmPassword.value,
            onValueChange = { confirmPassword.value = it },
            label = { Text("Confirmación de contraseña") },
            leadingIcon = {
                Icon(
                    Icons.Filled.Lock,
                    contentDescription = "Confirm pass"
                )
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


@Composable
fun AppLogo(){
    Row {
        Text("YE", color = Color.Red,  fontSize = 24.sp, style = TextStyle(fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.width(2.dp))
        Text("app", color = Color.White,  fontSize = 24.sp, style = TextStyle(fontWeight = FontWeight.Bold))
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}