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
import androidx.ui.layout.Column
import androidx.ui.layout.aspectRatio
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.Scaffold
import androidx.ui.material.TopAppBar
import androidx.ui.unit.dp
import com.husseinala.neon.core.Neon
import com.husseinala.neon.core.Transformation
import com.husseinala.neon.core.centerCrop
import com.husseinala.neon.picasso.ProvidePicassoLoader
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val picasso = Picasso.Builder(applicationContext).build()

        setContent { PicassoSample(picasso) }
    }
}

@Composable
fun PicassoSample(picasso: Picasso) {
    ProvidePicassoLoader(picasso = picasso) {
        SampleScreen(title = "Picasso Sample")
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
                transformation = Transformation.centerCrop(),
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

@Composable
fun UserListItem(user: User) {
    Column {
        Neon(
            url = user.profileImage,
            // Optional: Any Transformations to be applied to the image, such as rounded corners or scale type.
            transformation = Transformation.centerCrop(),
            // Optional: Any Modifiers to be applied to the image container, such as view size or paddings.
            modifier = Modifier.fillMaxWidth().aspectRatio(1.7f)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            onLoading = { /* Optional: the widget to be displayed while the image is being downloaded */ },
            onError = { /* Optional: the widget to be displayed if an error occurs */ }
        )
        Text(text = user.name)
    }
}
