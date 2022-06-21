package br.com.umvini.soccercoach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import br.com.umvini.simulationengine.Engine

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val teste = findViewById<AppCompatButton>(R.id.bt_play)

        teste.setOnClickListener {
            Engine.main()
        }

    }
}