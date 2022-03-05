# FetchRewardsExercise
Coding Exercise for Android Internship Position 

## Solution Final

- Implemented MVVM architecture (My first attempt at implementing this design pattern so it's not the perfect. Lacks data persistence for one, which I believe can be implemented using SharedPreferences or Room Db etc.)
- Used Retrofit for network call and Gson for parsing Json Array to Object Arraylist.
- Handled sorting logic when mapping data object from network layer to domain layer.
- Implemented simple cache
- Data is shown user using Recycler View

### App Walkthough GIF

<img src="walkthrough1.gif" width=250><br>
<img src="walkthrough2.gif" width=250><br>

### Notes

The MVVM is very interesting, to say the least, and, very powerful. When mapping network layer to domain layer, I observed how little I had to change in the upper levels of the architecture. I thoroughly enjoyed working on this exercise. I am now excited more so learning about android app development.

## Solution 1 (Commit at 'Solution 1') 

- This is a simple solution where all the logic rests in the MainActivity and views are implemented using RecyclerView.
- Used Retrofit for network call and Gson for parsing Json Array to Object Arraylist.

### App Walkthough GIF

<img src="walkthrough.gif" width=250><br>

### Notes

Started with Moshi but it parses Json Array as generic List. Tried to workaround using custom adapters but to no avail. Swtiched to Gson then.

## Open-source libraries used
- [Retrofit](https://github.com/square/retrofit) - A type-safe HTTP client for Android and the JVM
- [Gson](https://github.com/google/gson) - A Java serialization/deserialization library to convert Java Objects into JSON and back
