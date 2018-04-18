package com.example.gabri.taskorganizer

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.*
import com.example.gabri.taskorganizer.Models.DiaDaSemana
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView
import android.content.Intent
import com.example.gabri.taskorganizer.Models.ProgramDataStore


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        lateinit var adapter:DiaListAdapter

        fun RealoadListData(){
            adapter.notifyDataSetChanged()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var listView: ListView = findViewById(R.id.diasListView)
        adapter = DiaListAdapter(this, generateData())
        listView?.adapter = adapter
        adapter.notifyDataSetChanged()

        listView.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val intent = Intent(this@MainActivity, EditarDiaActivity::class.java)
                intent.putExtra("dayOfWeek", position)
                startActivity(intent)
            }
        }

        fab.setOnClickListener { view ->
            val intent = Intent(this@MainActivity, AdicionarAtividadeActivity::class.java)
            startActivity(intent)
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }



    private fun generateData(): ArrayList<DiaDaSemana> {
        var result = ArrayList<DiaDaSemana>()

        result.addAll(ProgramDataStore.diasNaSemana)

        return result
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_jornada -> {
                val intent = Intent(this@MainActivity, AlterarJornada::class.java)
                startActivity(intent)
            }
            R.id.nav_relatorio -> {
                val intent = Intent(this@MainActivity, Relatorio::class.java)
                startActivity(intent)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    class DiaListAdapter(private var activity: Activity, private var items: ArrayList<DiaDaSemana>) : BaseAdapter() {

        private class ViewHolder(row: View?) {
            var txtName: TextView? = null
            var txtComment: TextView? = null
            var img: ImageView? = null

            init {
                this.txtName = row?.findViewById(R.id.nameTextViewID)
                this.txtComment = row?.findViewById(R.id.infoTextViewID)
                this.img = row?.findViewById(R.id.imageView1ID)
            }
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view: View?
            val viewHolder: ViewHolder
            if (convertView == null) {
                val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                view = inflater.inflate(R.layout.lista_linha, null)
                viewHolder = ViewHolder(view)
                view?.tag = viewHolder
            } else {
                view = convertView
                viewHolder = view.tag as ViewHolder
            }

            val userDto = items[position]
            viewHolder.txtName?.text = userDto.nome
            viewHolder.txtComment?.text = userDto.descricao
            viewHolder.img?.setImageResource(userDto.imagem)

            return view as View
        }

        override fun getItem(i: Int): DiaDaSemana {
            return items[i]
        }

        override fun getItemId(i: Int): Long {
            return i.toLong()
        }

        override fun getCount(): Int {
            return items.size
        }
    }
}