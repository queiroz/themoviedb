# Popular Movies (The Movie Database)


<img src="https://user-images.githubusercontent.com/670685/73623045-734e2900-4633-11ea-8e3e-f689fcc764ff.gif" width="320" height="569" />

## Pre-conditions

Grab an API key from [https://www.themoviedb.org/settings/api](https://www.themoviedb.org/settings/api).
Then add the key into the gradle global properties `gradle.properties` at `~/.gradle/gradle.properties`:

```bash
vim ~/.gradle/gradle.properties
```

then paste the key:

```vim
# The Movie DB API Key
theMovieDbApiKey="API_KEY"
```

## App stack

**Kotlin**

* Kotlin gradle DSL
* Coroutines
* Coroutines-android

**Jetpack**

* Test
* Android KTX
* ViewModel
* Navigation
* Paging
* LiveData
* Fragment
* ViewPager2
* Data Binding
* Animation & transitions

**Other**

* Android-extensions
* Picasso
* Retrofit
* Retrofit Gson
* OkHttpInterceptor
* Timber
* PlainPie
