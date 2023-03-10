package com.example.demo

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.SurfaceView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.demo.databinding.ActivityMainBinding
import io.agora.rtc2.IRtcEngineEventHandler
import io.agora.rtc2.RtcEngine
import io.agora.rtc2.RtcEngineConfig


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Fill the App ID of your project generated on Agora Console.
    private val appId = "733b8c1281fe445992e9169d55b10873"

    // Fill the channel name.
    private val channelName = "VIDEO KYC"

    // Fill the temp token generated on Agora Console.
    private val token = "007eJxTYDi5rjFimdT/3/7xWxRsJ56+avqZ/1bL7Osfn0YmNZ97MmuuAoO5sXGSRbKhkYVhWqqJiamlpVGqpaGZZYqpaZKhgYW5cd0VrpSGQEaGSe8OsDAyQCCIz8kQ5uni6q/gHenMwAAA6hUjVQ=="

    // An integer that identifies the local user.
    private val uid = 0
    private var isJoined = false
    private var agoraEngine: RtcEngine? = null

    //SurfaceView to render local video in a Container.
    private val localSurfaceView: SurfaceView? = null

    //SurfaceView to render Remote video in a Container.
    private val remoteSurfaceView: SurfaceView? = null


    private val PERMISSION_ID = 12
    private val REQUESTED_PERMISSION =
        arrayOf(
            android.Manifest.permission.RECORD_AUDIO,
            android.Manifest.permission.CAMERA
        )

    private fun checkSelfPermission() : Boolean{
        return !(ContextCompat.checkSelfPermission(
            this,REQUESTED_PERMISSION[0]
        )!= PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    this,REQUESTED_PERMISSION[1]
                )!= PackageManager.PERMISSION_GRANTED)
    }

    private fun showMessage(message: String){
        runOnUiThread{
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpVideoSdkEngine() {
        try {
            val config = RtcEngineConfig()

            config.mContext = baseContext
            config.mAppId = appId
            config.mEventHandler = mRtcEventHandler
            agoraEngine = RtcEngine.create(config)
            agoraEngine!!.enableVideo()
        } catch (e.Exception){
            showMessage(e.message)
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.JoinButton.setOnClickListener{
            joinCall()
        }
        binding.LeaveButton.setOnClickListener {
            leaveCall()
        }
    }

    private fun leaveCall() {

    }

    private fun joinCall() {

    }

    private val mRtcEventHandler : IRtcEngineEventHandler =
        object : IRtcEngineEventHandler(){

            override fun onUserJoined(uid: Int, elapsed: Int) {
                showMessage("Remote User Joined $uid")
            }

            override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
                isJoined = true
                showMessage("Joined Meeting $channel")

            }

            override fun onUserOffline(uid: Int, reason: Int) {
                showMessage("User Offline $uid")

            }

            override fun onUserMuteAudio(uid: Int, muted: Boolean) {
                showMessage(" User is on Mute $uid")
            }

    }

}