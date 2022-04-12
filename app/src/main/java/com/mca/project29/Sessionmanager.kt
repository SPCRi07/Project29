package com.mca.project29


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.mca.project29.loginRegister.LoginActivity


class Sessionmanager(  // Context
    private var _context: Context?
)


{
    companion object {

        private const val PREF_NAME = "Pref"
        const val Name = "name"
        const val fName = "fname"
        const val lName = "lname"
        const val Address = "address"
        const val City = "city"
        const val Veg = "veg"

        private const val Uid = "Uid"
        const val ID = "ID"
        const val Image = "image"

        // Sharedpref file name


        // All Shared Preferences Keys
        private const val IS_LOGIN = "IsLoggedIn"
        private const val Iswaiting = "Iswaiting"

        private const val Issplash = "Isspash"
        private const val Member = "Member"

        // User name (make variable public to access from outside)
        const val KEY_NAME = "name"

        // Email address (make variable public to access from outside)
        const val KEY_EMAIL = "email"
        // Shared Preferences


    }
    var pref: SharedPreferences

    // Editor for Shared preferences
    var editor: SharedPreferences.Editor

    // Shared pref mode
    var PRIVATE_MODE = 0

    // Constructor
    init {
        pref = _context!!.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }


    /**
     * Create login session
     */
    fun createLoginSession(
        id: String?,
        name: String?,
        image: String?
    ) {
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true)
        // Storing name in pref
        editor.putString(ID, id)
        editor.putString(Name, name)
        // Storing email in pref
        editor.putString(Image, image)
        // commit changes
        editor.commit()
    }

    fun setUid(id: String?) {
        editor.putString(Uid, id)
        editor.putBoolean(IS_LOGIN, true)
        editor.commit()
    }

    fun setsplashcomplete() {
        editor.putBoolean(Issplash, true)
        editor.commit()
    }
    fun isplashcomplete(): Boolean {
        return pref.getBoolean(Issplash, false)
    }



    fun setIswaiting() {
        editor.putBoolean(Iswaiting, true)
        editor.commit()
    }

    fun setMember() {
        editor.putBoolean(Member, true)
        editor.commit()
    }

    fun RemoveMember() {
        editor.putBoolean(Member, false)
        editor.commit()
    }

    fun disablewait() {
        editor.putBoolean(Iswaiting, false)
        editor.commit()
    }

    fun iswaiting(): Boolean {
        return pref.getBoolean(Iswaiting, false)
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    fun checkLogin() {
        // Check login status
        if (!isLoggedIn) {
            // user is not logged in redirect him to Login Activity
            val i = Intent(_context, LoginActivity::class.java)
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            // Add new Flag to start new Activity
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            // Staring Login Activity
            _context!!.startActivity(i)
        }
    }// user name
    // user email id
    // return user
    /**
     * Get stored session data
     */
    val userDetails: HashMap<String, String?>
        get() {
            val user = HashMap<String, String?>()
            // user name
            user[KEY_NAME] = pref.getString(KEY_NAME, null)
            // user email id
            user[KEY_EMAIL] =pref.getString(KEY_EMAIL, null)
            user[ID] = pref.getString(ID, null)
            user[Image] = pref.getString(Image, null)
            user[lName] =pref.getString(lName, null)
            user[fName] = pref.getString(fName, null)
            user[Address] = pref.getString(Address, null)
            user[City] = pref.getString(City, null)
            // return user
            return user
        }

    val getUid: HashMap<String, String?>
    get() {

        val user = HashMap<String, String?>()
        user[Uid]=pref.getString(Uid,null)
        return user
    }


    /**
     * Signup Parts
     *
     */
    fun signup_credentials(
        fname: String?,
        lname: String?,
        address:String?,
        city:String?,
        veg:Boolean?
    ) {
        editor.putString(fName, fname)
        editor.putString(lName, lname)
        editor.putString(Address, address)
        editor.putString(City, city)
        if (veg != null) {
            editor.putBoolean(Veg,veg)
        }
        editor.commit()
    }


    /**
     * Clear session details
     */
    fun logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear()
        editor.commit()

        // After logout redirect user to Loing Activity
        val i = Intent(_context, LoginActivity::class.java)
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        // Add new Flag to start new Activity
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        // Staring Login Activity
        _context!!.startActivity(i)
    }

    fun ClearData() {
        editor.clear()
        editor.commit()
    }

    /**
     * Quick check for login
     */
    // Get Login State
    val isLoggedIn: Boolean
        get() = pref.getBoolean(IS_LOGIN, false)

    val isMember: Boolean
        get() = pref.getBoolean(Member, false)


}