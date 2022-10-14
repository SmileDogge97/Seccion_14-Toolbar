package com.example.toolbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import androidx.appcompat.widget.Toolbar

class PantallaDos : AppCompatActivity() {

    var toolbar: Toolbar?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_dos)

        //configuración del toolbar
        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle(R.string.app_name)
        setSupportActionBar(toolbar)

        //botón de volver en el toolbar, revisa el parent activity de esta activity dentro del manifest
        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    //permite mapear elementos mientras se va creando el menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_pantalla_dos, menu)
        return super.onCreateOptionsMenu(menu)
    }
}