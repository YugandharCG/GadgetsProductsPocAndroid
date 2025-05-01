package com.example.pocandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.example.pocandroid.ui.theme.PocAndroidTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    val TAG = MainActivity::class.java.simpleName
    val mainviewModel: MainViewModel by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PocAndroidTheme {
                val objects = mainviewModel.objects.collectAsState()
                Scaffold(modifier = Modifier.fillMaxSize(), topBar ={TopAppBar(title = {
                    Text(text = stringResource(R.string.title_gadgets))}
                ) } ) { innerPadding ->

                    objects.let {
                        if (!it.value.isNullOrEmpty()) {
                            ProductList(it.value!!)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PocAndroidTheme {
        Greeting("Android")
    }
}

@Composable
fun ProductList(products: List<Product>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(products.size) { index ->
            ProductItem(products.get(index))
            Divider()

        }
    }


}


@Composable
fun ProductItem(product: Product) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Name: ${product.name}", style = MaterialTheme.typography.titleMedium)
        product.data?.forEach { (key, value) ->
            Text(text = "$key: $value", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

