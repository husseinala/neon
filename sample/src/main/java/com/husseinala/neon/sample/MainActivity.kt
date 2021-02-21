package com.husseinala.neon.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.RequestManager
import com.husseinala.neon.core.Neon
import com.husseinala.neon.core.Transformation
import com.husseinala.neon.core.centerCrop
import com.husseinala.neon.core.roundedCorners
import com.husseinala.neon.glide.ProvideGlideLoader
import com.husseinala.neon.picasso.ProvidePicassoLoader
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val picasso = Picasso.Builder(applicationContext).build()

        setContent { PicassoSample(picasso = picasso) }
    }
}

@Composable
fun PicassoSample(picasso: Picasso) {
    ProvidePicassoLoader(picasso = picasso) {
        SampleScreen(title = "Picasso Sample")
    }
}

@Composable
fun GlideSample(requestManager: RequestManager) {
    ProvideGlideLoader(requestManager = requestManager) {
        SampleScreen(title = "Glide Sample")
    }
}

@Composable
fun SampleScreen(title: String) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = title) }) }
    ) {
        LazyColumn {
            items(Samples.images) { url ->
                Neon(
                    url = url,
                    transformation = Transformation.centerCrop().roundedCorners(radius = 16.dp),
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .aspectRatio(1.7f)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    onLoading = { Center { CircularProgressIndicator() } }
                )
            }
        }
    }
}

@Composable
fun Center(children: @Composable () -> Unit = { }) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.align(Alignment.Center)) {
            children()
        }
    }
}
