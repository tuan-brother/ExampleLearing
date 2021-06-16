package me.threebecause.learning.ui.base

import android.app.ProgressDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import me.threebecause.learning.untils.Const

abstract class BaseActivity : AppCompatActivity() {
    var mProgressDialog: ProgressDialog? = null
    var isNetworkState: Boolean = false

    abstract fun getLayoutId(): Int
    abstract fun onCreateActivity(saveInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(getLayoutId())
        onCreateActivity(savedInstanceState)
    }

    private var onNetworkConnectedListener: OnNetworkConnectedListener? = null
    fun setOnNetworkConnectedListener(onNetworkConnectedListener: OnNetworkConnectedListener) {
        this.onNetworkConnectedListener = onNetworkConnectedListener
    }

    override fun onPause() {
        super.onPause()
        mProgressDialog = null
    }

    fun showProgressLoadding() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
            mProgressDialog = null
        }
        mProgressDialog = ProgressDialog(this)
        mProgressDialog!!.setIndeterminate(true)
        mProgressDialog!!.setMessage("I dang show Loading")
        mProgressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        mProgressDialog!!.show()
    }

    fun updateMessageProgressDialog(message: String) {
        if (mProgressDialog != null && mProgressDialog!!.isShowing()) {
            mProgressDialog!!.setMessage(message)
        }
    }

    fun dismisProgressLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing()) {
            mProgressDialog!!.dismiss()
        }
    }

    //Đăng ký broadcast lắng nghe sự kiện thay đổi network
    private fun registerBroadcastReciver() {
        val filter = IntentFilter()
        filter.addAction(Const.ACTION_NETWORK_CHANGE)
        registerReceiver(receiver, filter)
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (action != null) {
                when (action) {
                    Const.ACTION_NETWORK_CHANGE -> {
                        isNetworkState = isNetworkConnected()
                        if (isNetworkState) {
                            onNetworkConnectedListener?.let {
                                it.onNetworkConnected()
                            }
                        } else {
                            onNetworkConnectedListener?.let {
                                it.onNetworkDisconnect()
                            }
                        }
                    }
                }
            }
        }
    }

    fun isNetworkConnected(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetInfor = connectivityManager.getActiveNetworkInfo()
        isNetworkState = activeNetInfor != null && activeNetInfor.isConnected
        return isNetworkState
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}