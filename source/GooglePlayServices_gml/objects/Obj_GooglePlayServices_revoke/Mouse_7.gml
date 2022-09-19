/// @description Revoke access

// Early exit if locked
if (locked) exit;

// This function call will revoke the access right from this phone.
// It will not auto login on startup and will require to sign in again.
// Generates a Social Async callback 'GooglePlayServices_RevokeAccess'
GooglePlayServices_RevokeAccess();