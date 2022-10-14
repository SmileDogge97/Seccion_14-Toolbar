package com.example.toolbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat

class MainActivity : AppCompatActivity() {

    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle(R.string.app_name)
        setSupportActionBar(toolbar)

        val bIr = findViewById<Button>(R.id.bIr)
        bIr.setOnClickListener {
            val intent = Intent(this, PantallaDos::class.java)
            startActivity(intent)
        }
    }

    //permite mapear elementos mientras se va creando el menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val itemBusqueda = menu?.findItem(R.id.busqueda)
        val vistaBusqueda = itemBusqueda?.actionView as SearchView
        val itemCompartir = menu.findItem(R.id.share)
        val shareActionProvider = MenuItemCompat.getActionProvider(itemCompartir) as androidx.appcompat.widget.ShareActionProvider
        compartirIntent(shareActionProvider)


        //al botón de busqueda del toolbar le agregamos un hint
        vistaBusqueda.queryHint = "Escribe tu nombre..."
        //devuelve si ingresaste al botón de busqueda del toolbar o no (true o false)
        vistaBusqueda.setOnQueryTextFocusChangeListener { v, hasFocus ->
            Log.d("LISTENERFOCUS", hasFocus.toString())
        }
        vistaBusqueda.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("onQueryTextSubmit", query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("onQueryTextChange", newText.toString())
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    //permite mapear de acuerdo al item que se dio click, que actividad se va a realizar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.bFav -> {
                Toast.makeText(this, "Elemento añadido como favorito", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //acción de compartir contenido
    private fun compartirIntent(shareActionProvider: androidx.appcompat.widget.ShareActionProvider) {
        if (shareActionProvider != null){
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Este es un mensaje compartido")
            shareActionProvider.setShareIntent(intent)
        }
    }
}