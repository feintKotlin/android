package com.example.feint.feintkotlin

import android.app.FragmentManager
import android.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.util.Log
import android.view.MenuItem
import android.view.Window
import com.example.feint.feintkotlin.frag.IndexFragment
import com.example.feint.feintkotlin.frag.TodayFragment
import org.jetbrains.anko.find

class IndexActivity : AppCompatActivity() {
    val TAG=IndexActivity::class.simpleName
    val fragMag:FragmentManager=fragmentManager

    lateinit var fragTran:FragmentTransaction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_index)

        fragTran=fragMag.beginTransaction()

        fragTran.replace(R.id.content,TodayFragment())
        fragTran.commit()

        val navigation=find<BottomNavigationView>(R.id.navigation)

        navigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            fragTran=fragMag.beginTransaction()
            when(item.itemId){
                R.id.navigation_home->{
                    fragTran.replace(R.id.content,IndexFragment())
                }
                R.id.navigation_dashboard->{
                    fragTran.replace(R.id.content,TodayFragment())
                }
            }
            fragTran.commit()
            return@setOnNavigationItemSelectedListener true
        }
    }
}
