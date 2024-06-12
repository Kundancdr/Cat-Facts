package com.example.catfacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.example.catfacts.models.catfacts
import com.example.catfacts.ui.theme.CatFactsTheme


class MainActivity : ComponentActivity() {
    var fact = mutableStateOf(catfacts())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatFactsTheme {
                Scaffold(
                    modifier = Modifier

                        .fillMaxSize()
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {

//                        var fact by remember {
//                            mutableStateOf(catfacts())
//                        }
//                        val context =  LocalContext.current
//
//                        val scope = rememberCoroutineScope()
//                        LaunchedEffect(key1 = true) {
//
//                            scope.launch(Dispatchers.IO) {
//
////                                try {
////                                    val fact = RetrofitInstance.api.getRandomFact()
////                                    Log.d("TAGGG", "$fact ")
////                                } catch (e: Exception) {
////                                    Log.e("TAGGG", "Error fetching cat fact", e)
////                                }
//
//                                val response = try {
//                                    RetrofitInstance.api.getRandomFact()
//                                } catch (e: HttpException) {
//                                    Toast.makeText(context, "http error : ${e.message}", Toast.LENGTH_SHORT).show()
//                                    return@launch
//                                } catch (e: IOException) {
//                                    Toast.makeText(context," app error : ${e.message}",Toast.LENGTH_SHORT).show()
//                                    return@launch
//                                }
//                                if (response.isSuccessful && response.body() != null){
//                                    withContext(Dispatchers.Main){
//                                        fact = response.body()!!
//                                    }
//                                }
//                            }
//                        }
                        CatFactUi(fact = fact)
                    }

                }
            }
        }
    }

}

