/// @description Show video overlay

// Early exit on locked
if (locked) exit;

// This method will enable the video recording overlay.
// With this feature the user will be able to record his screen
// and save the recordings to the phone memory.
// Front camera and microphone can also be recorded.
GooglePlayServices_VideoRecording_ShowVideoOverlay();
