# Adventure Sharer - RND Project

This is an application we have developed for a client for our final year project of University. The idea of the app is for users to be able to map out different walking/hikig tracks just
by going for a walk while the application tracks the journey for them. Along the way the user is able to mark out points of interest for other users to stop and look at. Users will also 
be able to view a list of thier own walks, as well as a list of all the other walks other user have uploaded along the way. This application is still heavily under development. 

_README.md still requires updates_

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

You will need:
1. Firebase account
2. Google Maps API Key

### Installing

#### Find you SHA Code!

#### 1. Clone the project
 Clone the repository to your computer via Android Studio. To do this, navigate to 'File > New > Project from Verison Control > Git' and in the the 'Git Repository URL' field, enter:

```
https://github.com/AdventureSharer/AS_application
```

#### 2. Create a Firebase project
1. Go to the [Firebase Console](https://console.firebase.google.com/) and create a project from here. If you already have a project you want to use, skip to **Firebase Application Setup** 
2. Select the **Authentication** panel and navigate to the **Sign in Method** tab.
3. Click **Email/Password** enable it by selecting the **Enable** switch, this select **Save**.
4. Select the **Database** panel (underneath the **Authentication** panel)
5. Navigate to the **Create a Database** button and select it.
6. Select the **Storage** panel (underneath the **Database** panel)
7. Navigate to the **Get Start** button and accept the prompts


#### 3. Firebase Application Setup
1. In your Firebase Project within the Firebase Console, click the **Gear Icon** and navigate to **Project Settings**.
2. Click **Add App** and choose **Add Firebase to your Android app**. Here, you will need to follow the steps and fill in the required fields.
3. Download the **google-services.json** file and copy it into the 'app' folder, covered in the instructions prompted by Firebase.


#### 4. Google Maps API
1. Using the same account as the **Firebase**, go to the [Google Cloud Platform console](https://console.cloud.google.com/).
2. On the panel located on the left, hover you mouse other the **APIs & Services** panel and select **Dashboard**
3. From here, Near the top, to the right of the **Dashboard** title, select the **Enable APIs and Services** option
4. In the search field provided, search for **Maps SDK for Android** and select it
5. Select the **Enable** button located to the right of the logo, and navigate back to **APIs & Services Dashboard**
6. Select the **Credentials** option located on the left panel, underneath **Dashboard** and ** Library**
7. Select the **Create Credentials** dropdown and select the **API Key** option
8. When the prompt comes up, select the **Restrict Key** option
9. Here, copy the code down somewhere safe - it should start with ```AIza```
10. While here, navigate your way down to the **Application Restrictions** area, and select **Android apps**
11. With your copied code, paste it into the "google_maps_key" string object located in the file
    ```
    Application > app > src > debug > res > values > google_maps_api.xml
    ```

**INFORMATION TO BE ADDED**

## Built With

* [Firebase](https://firebase.google.com/) - Authentication, Realtime Database, and Storage

## License

**INFORMATION TO BE ADDED**
