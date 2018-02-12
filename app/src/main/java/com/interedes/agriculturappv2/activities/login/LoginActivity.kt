package com.interedes.agriculturappv2.activities.login

import android.animation.Animator
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.interedes.agriculturappv2.R
import kotlinx.android.synthetic.main.content_login.*

class LoginActivity : AppCompatActivity(), LoginView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loadAnimation()
    }

    //region Métodos Adicionales


    //region Métodos Interfaz
    override fun loadAnimation() {
        fabLogin?.scaleX = 0f
        fabLogin?.scaleY = 0f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val interpolador = AnimationUtils.loadInterpolator(baseContext,
                    android.R.interpolator.fast_out_slow_in)
            fabLogin!!.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setInterpolator(interpolador)
                    .setDuration(600)
                    .setStartDelay(1000)
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator) {}

                        override fun onAnimationEnd(animation: Animator) {
                            fabLogin!!.animate()
                                    .scaleY(1f)
                                    .scaleX(1f)
                                    .setInterpolator(interpolador)
                                    .setDuration(600)
                                    .start()

                        }

                        override fun onAnimationCancel(animation: Animator) {

                        }

                        override fun onAnimationRepeat(animation: Animator) {}
                    })
        }


    }
    //endregion


    //endregion
}
