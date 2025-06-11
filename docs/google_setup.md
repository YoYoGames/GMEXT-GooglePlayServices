@title Google Setup

# Google Play Setup

Before you can start using Google Play Services in your game and test it, you first need to set up a few things in Google Play. This page gives an overview of what needs to be set up and refers to the Google Play documentation for more information.

## Setting up an App

* [Create and set up an app](https://support.google.com/googleplay/android-developer/answer/113469?hl=en) on your [Google Play Developer Console](https://developer.android.com/distribute/console/index.html) for the game.

* [Set up your app on the app dashboard](https://support.google.com/googleplay/android-developer/answer/9859454)

* [Set up your app for testing](https://support.google.com/googleplay/android-developer/answer/9845334)

* [Create an internal release](https://support.google.com/googleplay/android-developer/answer/9859348)


[[Tip: For your own dev testing we recommend you set up an internal test, as the review times are very quick (typically your app is ready for download via the Play Store app within an hour or two) and the process of getting builds to your testers is more straightforward than the other tracks.]]

[[Note: If you are sharing app bundles or APKs with testers directly through internal app sharing, testers need to turn this on. See **How authorized testers turn on internal app sharing** on the page [Share app bundles and APKs internally](https://support.google.com/googleplay/android-developer/answer/9844679).

[[Note: If, during upload, you get an error message saying the release is not compliant with the Google Play 64-bit requirement you should check the `Build for ARM64` game option under [Android Game Options](https://manual.gamemaker.io/monthly/en/Settings/Game_Options/Android.htm) (`Game Options` > `Android` > `Architecture` > `Build for ARM64`). This needs to be checked.]]

## Setting up Google Play Games Services

* [Set up Google Play Games Services](https://developer.android.com/games/pgs/console/setup)
* [Generate an OAuth 2.0 client ID](https://developer.android.com/games/pgs/console/setup#generate_an_oauth_20_client_id)
* [Create Access Credentials](https://developers.google.com/workspace/guides/create-credentials) (See the **Keystore** section in the [Android Preferences](https://manual.gamemaker.io/monthly/en/Setting_Up_And_Version_Information/Platform_Preferences/Android.htm))
  * Create a Developer Keystore credential
  * Create a Play Store credential
* [Set up Google Play games services features](https://support.google.com/googleplay/android-developer/answer/2990418)
  * [Leaderboards](https://support.google.com/googleplay/android-developer/answer/2990418#zippy=%2Cleaderboards)
  * [Achievements](https://support.google.com/googleplay/android-developer/answer/2990418#zippy=%2Cachievements)
  * [Saved Games](https://support.google.com/googleplay/android-developer/answer/2990418#zippy=%2Csaved-games)