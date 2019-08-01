package com.helpathand.helpathand
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.helpathand.helpathand.R
import com.auth0.android.Auth0
import com.auth0.android.lock.utils.LockException
import com.auth0.android.lock.LockCallback
import com.auth0.android.lock.AuthenticationCallback
import com.auth0.android.result.Credentials
//import android.R
import com.auth0.android.lock.Lock


class SignupActivity : AppCompatActivity() {

    // auth0 lock login widget
    private var lock: Lock? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        var auth0_account = Auth0(Constants.AUTH0_CLIENT_ID, Constants.AUTH0_DOMAIN)
        //Configure the account in OIDC conformant mode
        //Resolves login legacy endpoints
        auth0_account.isOIDCConformant = true
        //Use the account to launch Lock
        lock = Lock.newBuilder(auth0_account, callback)
            .withAudience("https://${Constants.AUTH0_DOMAIN}/userinfo")
            .build(this);
        startActivity(lock!!.newIntent((this)))

    }

    public override fun onDestroy() {
        super.onDestroy()
        lock!!.onDestroy(this)
        lock = null
    }

    // Callback handle from auth0 after client authentication
    private val callback = object : AuthenticationCallback() {
        override fun onAuthentication(credentials: Credentials?) {
            //Authenticated
        }

        override fun onCanceled() {
            //User pressed back
        }


        override fun onError(error: LockException?) {
            //Exception occurred

        }

    }

    companion object {
        private val TAG = "SignupActivity"

    }
}