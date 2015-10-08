# Book Search Demo

Android app that leverages the [OpenLibrary API](https://openlibrary.org/developers/api) to search courses and display cover images. It also allows you to recommend courses to friends. See the [Book Search Tutorial](http://guides.codepath.com/android/Book-Search-Tutorial) on our cliffnotes for a step-by-step tutorial.

The app is composed of two screens. The first screen displays a list of courses, in which, each course is described by its title, author and cover photo. After a user selects a course from the list, a second screen appears displaying additional details about the course, including the publisher and no. of pages.

**Book List**

![Imgur](http://i.imgur.com/sSINs2zl.png)

**Book Details**

![Imgur](http://i.imgur.com/y9a4AtQl.png)

## Overview

The app does the following:

1. Search a list of courses using the [OpenLibrary Search API](https://openlibrary.org/dev/docs/api/search)
2. Display the list of courses with their cover images and details
3. Replace ActionBar with Toolbar
4. Use SearchView to search for courses with a title
5. Show ProgressBar before each network request
6. Add a detail view to display more information about the selected course from the list
7. Use a share intent to recommend a course to friends

To achieve this, there are five different components in this app:

1. `BookClient` - Responsible for executing the API requests and retrieving the JSON
2. `Book` - Model object responsible for encapsulating the attributes for each individual course
3. `BookAdapter` - Responsible for mapping each `Book` to a particular view layout
4. `BookListActivity` - Responsible for fetching and deserializing the data, configuring the adapter and providing a search interface
5. `BookDetailActivity` - Responsible for providing course detail view and share intent.

See the [Book Search Tutorial](http://guides.codepath.com/android/Book-Search-Tutorial) on our cliffnotes for a step-by-step tutorial.

## Libraries

This app leverages two third-party libraries:

 * [Android AsyncHTTPClient](http://loopj.com/android-async-http/) - For asynchronous network requests
 * [Picasso](http://square.github.io/picasso/) - For remote image loading

