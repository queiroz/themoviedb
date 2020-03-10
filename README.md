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

Please note that gradle version `4.0.0-alpha09` is required as well as `gradle-6.1-rc-1-all` as Android Studio Preview 4 was used to write the app, because why not! 

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

* Dagger2/dagger-android
* Android-extensions
* Picasso
* Retrofit
* Retrofit Gson
* OkHttpInterceptor
* Timber
* PlainPie

## Extra Implementation Details

**PagedList/DataSource**
The data source has the capability of retrying from the point it failed, thanks to the use of the executor.
If 20 items are being displayed and Wifi is turned off or lack of network connectivity or server timeout, the executor will try to fetch data from that point on so that the entire list does not have to be reloaded.

**RecyclerView Adapter/ListAdapter**
Use of DiffUtil for background list diff computation.
Custom views.
Use of recycler view and view pager 2 without Fragments

**Shared Element Transition with Jetpack Navigation**
Cover<ImageView> is being shared between `List` item and `MovieDetailFragment`.

**Jetpack Navigation**
`NavHostFragment` with the latest recommendation of `FragmentContainerView`.

Also in order to get the NavController we have to find the Fragment first then look for NavController as there are issues getting the NavController directly with `findNavController(R.id.container)` because of Fragment lifecycle issues.
```kotlin
private val navController: NavController by lazy {
    (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment).navController
}
```
**Unit Test**

Testing view model interactions with `LiveData` and `PagedList`.

**UI Test**

Uses Fragment Scenario and espresso to test recyclerview item navigation with jetpack Navigation library.

Implemented Fragment Factory with Dagger 2 and Androidx Navigation library so Fragments can take constructor arguments from daggers graph.

Now Fragment constructor arguments can easily be mocked or switched with fakes for testing. 
