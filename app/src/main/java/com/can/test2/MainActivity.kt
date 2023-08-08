package com.can.test2

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.renderscript.ScriptGroup.Binding
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.can.test2.databinding.ActivityMainBinding
import kotlinx.coroutines.Runnable
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var dizi=ArrayList<ImageView>()
    var x=0
    var runnable= Runnable {  }
    var Handler =Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dizi.add(binding.imageView1)
        dizi.add(binding.imageView2)
        dizi.add(binding.imageView3)
        dizi.add(binding.imageView4)
        dizi.add(binding.imageView5)
        dizi.add(binding.imageView6)
        dizi.add(binding.imageView7)
        dizi.add(binding.imageView8)
        dizi.add(binding.imageView9)
        for (i in dizi){
            i.visibility=View.INVISIBLE
        }
    }
    fun tikla(view: View){
        x=x+1
        binding.textView2.text="Skor:${x}"
    }
    fun basla(View:View){
        gizle()

        object :CountDownTimer(10000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.textView.text="Süre:${millisUntilFinished/1000}"
            }
            override fun onFinish() {
                Handler.removeCallbacks(runnable)
                for (i in dizi){
                    i.visibility= android.view.View.INVISIBLE
                }
                val alert=AlertDialog.Builder(this@MainActivity)
                alert.setTitle("SÜREN BİTTİ KARDEŞŞ")
                alert.setMessage("Tekrar başlamak ister misin?")
                alert.setPositiveButton("Evet",DialogInterface.OnClickListener { dialog, which ->
                    gizle()
                })
                alert.setNegativeButton("Nayırrr",DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this@MainActivity, "Peki...", Toast.LENGTH_SHORT).show()
                })
                alert.show()
            }
        }.start()

    }
    fun gizle(){
       runnable= object :Runnable{
           override fun run() {
               for (i in dizi){
                   i.visibility=View.INVISIBLE
               }
               var random = Random()
               var randomIndex=random.nextInt(9)
               dizi[randomIndex].visibility=View.VISIBLE
               Handler.postDelayed(runnable,500)
           }
       }
       Handler.post(runnable)

    }
}