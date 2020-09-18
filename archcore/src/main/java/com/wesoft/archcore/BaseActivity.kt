package com.wesoft.archcore

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * Create by james.li on 2020/9/9
 * Description:
 */

abstract class BaseActivity: AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun setupViews()

    abstract fun observeData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        setupViews()
        observeData()
    }
}