package com.husseinala.neon.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.emptyContent
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.foundation.lazy.LazyColumnItems
import androidx.ui.layout.aspectRatio
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.unit.dp
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
        LazyColumnItems(items = Samples.images) { url ->
            Neon(
                url = url,
                transformation = Transformation.centerCrop().roundedCorners(radius = 16.dp),
                modifier = Modifier.fillMaxWidth().aspectRatio(1.7f)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                onLoading = { Center { CircularProgressIndicator() } }
            )
        }
    }
}

@Composable
fun Center(children: @Composable () -> Unit = emptyContent()) {
    Box(modifier = Modifier.fillMaxSize(), gravity = Alignment.Center, children = children)
}
