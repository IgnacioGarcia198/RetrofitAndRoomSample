# Android Basic Retrofit and Room Sample 

This is a simple implementation of Android Architecture Components: https://developer.android.com/topic/libraries/architecture/guide.html
<br/>It is based on the one of <b>Philippe Boisney:</b> https://github.com/PhilippeBoisney/GithubArchitectureComponents
although my approach is simpler without using Dagger nor ButterKnife.

## Getting Started

This simple app has onle one screen. When it starts, it will fetch photos of a given album from the API https://jsonplaceholder.typicode.com/photos
and it will save this info into a database. The photos are shown in a RecyclerView. Only when the photos in the database are not fresh enough they will be downloaded again.
In order to browse photos there is a Spinner to choose the album and an EditText to enter a photo title.
When restarting the activity or rotating the device the screen keeps the same information.(One of the good things of using ViewModel and LiveData).

## Preview
<p align="center">
 <img src ="https://raw.githubusercontent.com/IgnacioGarcia198/RetrofitAndRoomSample/retrofitAndRoomSample/Screenshot_1562137202.png", height=500/>
</p>


### Architecture Components

This application implements the following concepts :
- ViewModel
- LiveData
- Room


### Libraries used

* [Android Support Library][support-lib]
* [CardView][cardview]
* [RecyclerView][recyclerview]
* [Android Architecture Components][arch]
* [Retrofit][retrofit] for REST api communication
* [Glide][glide] for image loading

[support-lib]: https://developer.android.com/topic/libraries/support-library/index.html
[cardview]: https://developer.android.com/guide/topics/ui/layout/cardview
[recyclerview]: https://developer.android.com/reference/android/support/v7/widget/RecyclerView
[arch]: https://developer.android.com/arch
[retrofit]: http://square.github.io/retrofit
[glide]: https://github.com/bumptech/glide


## Author
Ignacio Garcia - (ignaciogarcia198@gmail.com)


## Acknowledgments
Philippe Boisney https://github.com/PhilippeBoisney/
