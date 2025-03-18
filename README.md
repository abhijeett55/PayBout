# PayBout

PayBout is an Android application designed for betting on various fighting events, including trending fights, UFC, ONE Championship, and Bellator events. The app integrates Firebase for user authentication and real-time database management, ensuring a seamless and secure betting experience.

## Installation

### Open in Android Studio
1. Launch **Android Studio**.
2. Select **"Open an existing project"**.
3. Navigate to the cloned repository folder and open the project.

### Configure Firebase
1. Create a new project in the [Firebase Console](https://console.firebase.google.com/).
2. Add an **Android app** to your Firebase project using your app's package name.
3. Download the **google-services.json** file provided by Firebase.
4. Place the **google-services.json** file in the `app/` directory of your Android project.
5. Ensure that **Firebase Authentication** and **Realtime Database** are properly configured in the Firebase Console.

### Build the Project
- Sync the project with **Gradle files** and build the project to ensure all dependencies are resolved.

### Run the Application
- Connect an **Android device** or use an **emulator** to run the application.

## Usage

- **Sign Up / Log In**: Users can register a new account or log in using existing credentials.
- **Browse Events**: Navigate through different categories to view upcoming fights.
- **View Fight Details**: Select a fight to view detailed information, including fighter statistics and event date.
- **Place a Bet**: Enter a betting amount and confirm to place a bet on a selected fight.
- **Manage Bets**: Access the **"My Bets"** section to view and manage placed bets.

## Contributing

Contributions to PayBout are welcome! To contribute:

1. **Fork the repository**.
2. **Create a new branch**:

   ```bash
   git checkout -b feature-name
