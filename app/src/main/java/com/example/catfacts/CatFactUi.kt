package com.example.catfacts

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catfacts.data.RetrofitInstance
import com.example.catfacts.models.catfacts
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

@SuppressLint("UnrememberedMutableState")
@Composable
fun CatFactUi(modifier: Modifier = Modifier, fact: MutableState<catfacts>) {
    Column(modifier = Modifier


        .clickable {
            sendRequest(fact)
        }
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 45.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(R.drawable.kitmemecat),
            contentDescription = "cats",
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(20.dp))
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Cat Facts:", modifier = Modifier.padding(bottom = 25.dp), fontSize = 26.sp)
        Text(
            text = fact.value.fact,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 40.sp
        )

    }

}

@OptIn(DelicateCoroutinesApi::class)
fun sendRequest(fact: MutableState<catfacts>) {
    GlobalScope.launch(Dispatchers.IO) {


        val response = try {
            RetrofitInstance.api.getRandomFact()
        } catch (e: HttpException) {
            // Toast.makeText(, "http error : ${e.message}", Toast.LENGTH_SHORT).show()
            return@launch
        } catch (e: IOException) {
            // Toast.makeText(applicationContext," app error : ${e.message}",Toast.LENGTH_SHORT).show()
            return@launch
        }
        if (response.isSuccessful && response.body() != null) {
            withContext(Dispatchers.Main) {
                fact.value = response.body()!!
            }
        }

    }

}
