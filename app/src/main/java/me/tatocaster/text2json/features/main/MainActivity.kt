package me.tatocaster.text2json.features.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.tatocaster.text2json.R

class MainActivity : AppCompatActivity(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun showMessage(s: String) {
        Snackbar.make(main_wrapper, s, Snackbar.LENGTH_SHORT).show()
    }
}
