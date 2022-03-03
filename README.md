# FetchRewardsExercise
Coding Exercise for Android Internship Position 

##Solution 1 

- This is a simple solution where all the logic rests in the MainActivity and views are implemented using RecyclerView.
- Used Retrofit for network call and Gson for parsing Json Array to Object Arraylist.

### App Walkthough GIF

<img src="walkthrough.gif" width=250><br>

### Notes

Started with Moshi but it parses Json Array as generic List. Tried to workaround using custom adapters but to no avail. Swtiched to Gson then.

## Open-source libraries used
- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android
