# Compose Neon

A small Jetpack Compose library that provides Compose support for different image loading libraries.


## Setup

####  Integrating with Glide:

To add Glide support, import the Neon Glide library:

```groovy
implementation 'com.husseinala.neon:glide:0.1.4'
```

Provide a Glide `RequestManager` instance to your root Compose tree:

```kotlin
class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Create a new `RequestManager` instance or re-use your existing instance
    val glide = Glide.with(applicationContext)

    setContent { GlideApp(glide) }

  }
}

@Composable
fun GlideApp(requestManager: RequestManager) {
  ProvideGlideLoader(requestManager) {
    SampleScreen()
  }
}
```

####  Integrating with Picasso:

To add Picasso support, import the Neon Picasso library:

```groovy
implementation 'com.husseinala.neon:picasso:0.1.4'
```

Provide a `Picasso` instance to your root Compose tree:

```kotlin
class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Create a new `Picasso` instance or re-use your existing instance
    val picasso = Picasso.Builder(applicationContext).build()

    setContent { PicassoApp(picasso) }

  }
}

@Composable
fun PicassoApp(picasso: Picasso) {
  ProvidePicassoLoader(picasso) {
    SampleScreen()
  }
}
```

## Usage

After providing your image loader, you can simply use the `Neon` Composable function to directly load images:

```kotlin
@Composable
fun UserListItem(user: User) {
  Column {
    Neon(
      url = user.profileImage,
      // Optional: Any Transformations to be applied to the image, such as rounded corners or scale type.
      transformation = Transformation.centerCrop(),
      // Optional: Any Modifiers to be applied to the image container, such as view size or paddings.
      modifier = Modifier.fillMaxWidth().aspectRatio(1.7f).padding(horizontal = 16.dp, vertical = 8.dp),
      onLoading = { /* Optional: the widget to be displayed while the image is being downloaded */ },
      onError = { /* Optional: the widget to be displayed if an error occurs */ }
    )
    Text(text = user.name)
  }
}
```

## License

    Copyright 2020 Hussein Aladeen

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
