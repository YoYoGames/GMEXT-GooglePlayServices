@title Google Setup

# Google Play Setup

Before you can start using Google Play Services in your game and test it, there are a few things
that you first have to set up in Google Play. This page gives you an overview of what needs to be
set up, and points you at the Google Play documentation for the details of each step.

## Setting up an App

The first steps are to create an app on Google Play, to set it up for testing, and to create a
release:

1. [Create and set up an app](https://support.google.com/googleplay/android-developer/answer/113469?hl=en)
   for the game on your
   [Google Play Developer Console](https://developer.android.com/distribute/console/index.html).

2. [Set up your app on the app dashboard](https://support.google.com/googleplay/android-developer/answer/9859454)

3. [Set up your app for testing](https://support.google.com/googleplay/android-developer/answer/9845334)

4. [Create an internal release](https://support.google.com/googleplay/android-developer/answer/9859348)


[[Note: For your own development testing we recommend that you set up an internal test, as the
review times are very quick, with your app typically being ready for download through the Play Store
app within an hour or two, and getting builds to your testers is more straightforward than it is on
the other tracks.]]

[[Note: If you are sharing app bundles or APKs with testers directly through internal app sharing
then your testers need to have that enabled. See **How authorized testers turn on internal app
sharing** on the page
[Share app bundles and APKs internally](https://support.google.com/googleplay/android-developer/answer/9844679).]]

[[Note: If you get an error message during upload saying that the release is not compliant with the
Google Play 64-bit requirement then you should check the `Build for ARM64` game option under
[Android Game Options](https://manual.gamemaker.io/monthly/en/Settings/Game_Options/Android.htm)
(`Game Options` > `Android` > `Architecture` > `Build for ARM64`). This option has to be checked.]]

## Setting up Google Play Games Services

The next steps are to set up Google Play Games Services for the app, to create the credentials that
it needs, and finally to set up your leaderboards, achievements and saved games:

1. [Set up Google Play Games Services](https://developer.android.com/games/pgs/console/setup)
2. [Generate an OAuth 2.0 client ID](https://developer.android.com/games/pgs/console/setup#generate_an_oauth_20_client_id)
3. [Create Access Credentials](https://developers.google.com/workspace/guides/create-credentials).
   For the key hash, see the **Keystore** section of the
   [Android Preferences](https://manual.gamemaker.io/monthly/en/Setting_Up_And_Version_Information/Platform_Preferences/Android.htm).<br />
    - Create a Developer Keystore credential
    - Create a Play Store credential
4. [Set up Google Play games services features](https://support.google.com/googleplay/android-developer/answer/2990418)
    - [Leaderboards](https://support.google.com/googleplay/android-developer/answer/2990418#zippy=%2Cleaderboards)
    - [Achievements](https://support.google.com/googleplay/android-developer/answer/2990418#zippy=%2Cachievements)
    - [Saved Games](https://support.google.com/googleplay/android-developer/answer/2990418#zippy=%2Csaved-games)

Once all of that is in place, see ${page.extension_setup} for setting up the extension itself inside
GameMaker.
