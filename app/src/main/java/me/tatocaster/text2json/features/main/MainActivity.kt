package me.tatocaster.text2json.features.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.tatocaster.text2json.App
import me.tatocaster.text2json.AppComponent
import me.tatocaster.text2json.R
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {
    @Inject
    lateinit var mainPresenter: MainContract.Presenter

    private lateinit var scopeGraph: MainComponent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupScopeGraph(App.getAppContext(this).appComponent)

        action_parse.setOnClickListener({
            main_result.text = mainPresenter.parseText(main_input.text.toString())
        })
    }


    override fun showMessage(s: String) {
        Snackbar.make(main_wrapper, s, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        mainPresenter.onDestroy()
        super.onDestroy()
    }

    private fun setupScopeGraph(appComponent: AppComponent) {
        scopeGraph = DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(MainModule(this))
                .build()
        scopeGraph.inject(this)
    }

    companion object {
        val TAG = "MainActivity"
    }
}
