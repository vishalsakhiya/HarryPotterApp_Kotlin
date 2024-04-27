package com.example.harrypotterapp

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.harrypotterapp.api.HowardViewModel
import com.google.gson.Gson

class CharacterDetailActivity : AppCompatActivity() {
    private lateinit var bar: Toolbar
    private lateinit var imgProfile: ImageView
    private lateinit var txtSpecies: TextView
    private lateinit var txtGender: TextView
    private lateinit var txtHouse: TextView
    private lateinit var txtDateOfBirth: TextView
    private lateinit var txtWizard: TextView
    private lateinit var txtAncestry: TextView
    private lateinit var txtEyeColour: TextView
    private lateinit var txtHairColour: TextView
    private lateinit var txtWood: TextView
    private lateinit var txtCore: TextView
    private lateinit var txtLength: TextView
    private lateinit var txtPatronus: TextView
    private lateinit var txtHogwartsStudent: TextView
    private lateinit var txtHogwartsStaff: TextView
    private lateinit var txtActor: TextView
    private lateinit var txtAlive: TextView
    private var howardViewModel: HowardViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_detail)
        bar = findViewById(R.id.toolbar)
        setSupportActionBar(bar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val characterId = intent.getStringExtra("characterId");
        imgProfile = findViewById(R.id.imgProfile)
        txtSpecies = findViewById(R.id.txtSpecies)
        txtGender = findViewById(R.id.txtGender)
        txtHouse = findViewById(R.id.txtHouse)
        txtDateOfBirth = findViewById(R.id.txtDateOfBirth)
        txtWizard = findViewById(R.id.txtWizard)
        txtAncestry = findViewById(R.id.txtAncestry)
        txtEyeColour = findViewById(R.id.txtEyeColour)
        txtHairColour = findViewById(R.id.txtHairColour)
        txtWood = findViewById(R.id.txtWood)
        txtCore = findViewById(R.id.txtCore)
        txtLength = findViewById(R.id.txtLength)
        txtPatronus = findViewById(R.id.txtPatronus)
        txtHogwartsStudent = findViewById(R.id.txtHogwartsStudent)
        txtHogwartsStaff = findViewById(R.id.txtHogwartsStaff)
        txtActor = findViewById(R.id.txtActor)
        txtAlive = findViewById(R.id.txtAlive)
        howardViewModel = ViewModelProvider(this)[HowardViewModel::class.java]
        howardViewModel!!.fetchCharacterDetail(characterId!!);
        howardViewModel!!.characterDetail?.observe(this, Observer {
            if (it!=null){
                Log.v("TAG", "Got Data!!!!: " + Gson().toJson(it))
//                var requestOptions = RequestOptions()
//                requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(1))
//                Glide.with(this!!)
//                    .load(it[0]?.image)
//                    .placeholder(R.drawable.user)
//                    .apply(requestOptions)
//                    .into(imgProfile)
                Glide.with(this!!)
                    .asBitmap()
                    .load(it[0]?.image)
                    .into(imgProfile)
                supportActionBar?.title = if (it[0].name != "") it[0].name else "-"
                txtSpecies.text = if (it[0].species != "") it[0].species else "-"
                txtGender.text = if (it[0].gender != "") it[0].gender else "-"
                txtHouse.text = if (it[0].house != "") it[0].house else "-"
                txtDateOfBirth.text = if (it[0].dateOfBirth != null) it[0].dateOfBirth else "-"
                txtWizard.text = it[0].wizard.toString()
                txtAncestry.text = if (it[0].ancestry != "") it[0].ancestry else "-"
                txtEyeColour.text = if (it[0].eyeColour != "") it[0].eyeColour else "-"
                txtHairColour.text = if (it[0].hairColour != "") it[0].hairColour else "-"
                txtWood.text = if (it[0].wand.wood != "") it[0].wand.wood else "-"
                txtCore.text = if (it[0].wand.core != "") it[0].wand.core else "-"
                txtLength.text = it[0].wand.length.toString()
                txtPatronus.text = if (it[0].patronus != "") it[0].patronus else "-"
                txtHogwartsStudent.text = it[0].hogwartsStudent.toString()
                txtHogwartsStaff.text = it[0].hogwartsStaff.toString();
                txtActor.text = if (it[0].actor != "") it[0].actor else "-"
                txtAlive.text = it[0].alive.toString()
            } else {
                Log.v("TAg", "Something went wrong")
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}