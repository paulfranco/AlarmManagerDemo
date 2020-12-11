package co.paulfran.alarmmanagerdemo

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import co.paulfran.alarmmanagerdemo.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var context: Context
    lateinit var alarmManager: AlarmManager
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        context = this
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        binding.btnCreate.setOnClickListener {
            val seconds = binding.edtTimer.text.toString().toInt() * 1000
            val intent = Intent(context, Receiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            Log.d("MainActivity", "Create " + Date().toString())
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + seconds, pendingIntent)
        }

        binding.btnUpdate.setOnClickListener {
            val seconds = binding.edtTimer.text.toString().toInt() * 1000
            val intent = Intent(context, Receiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            Log.d("MainActivity", "Update " + Date().toString())
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + seconds, pendingIntent)
        }

        binding.btnCancel.setOnClickListener {
            val intent = Intent(context, Receiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            Log.d("MainActivity", "Cancel " + Date().toString())
            alarmManager.cancel(pendingIntent)
        }

    }

    class Receiver: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("MainActivity", "Receiver : " + Date().toString())
        }
    }
}

