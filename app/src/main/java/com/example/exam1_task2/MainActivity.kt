package com.example.exam1_task2

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), ChooseOne {

    private lateinit var youChoseThisGun: TextView
    private lateinit var myChoice: ImageView
    private lateinit var randomChoice: ImageView
    private lateinit var chooseOne: Button
    private lateinit var duelGo: Button
    private lateinit var againButton: Button
    private lateinit var imageQuestion: ImageView
    private lateinit var listOfChoices: List<Drawable?>

    private var rock: Drawable? = null
    private var paper: Drawable? = null
    private var scissor: Drawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rock = AppCompatResources.getDrawable(this, R.drawable.rock)
        paper = AppCompatResources.getDrawable(this, R.drawable.paper)
        scissor = AppCompatResources.getDrawable(this, R.drawable.scissor)
        listOfChoices = listOf(rock, paper, scissor)
        val recordings = listOf(setOf(0, 1), setOf(1, 2), setOf(2, 0))


        chooseOne = findViewById(R.id.button)
        duelGo = findViewById(R.id.duelGo)
        againButton = findViewById(R.id.againButton)
        imageQuestion = findViewById(R.id.imageQuestion)
        myChoice = findViewById(R.id.myChoice)
        randomChoice = findViewById(R.id.randomChoice)
        youChoseThisGun = findViewById(R.id.youChoseThisGun)


        chooseOne.setOnClickListener { ChoiceDialog().show(supportFragmentManager, null) }
        duelGo.setOnClickListener {
            imageQuestion.visibility = View.INVISIBLE
            randomChoice.background = listOfChoices.random()
            randomChoice.visibility = View.VISIBLE
            duelGo.visibility = View.INVISIBLE
            againButton.visibility = View.VISIBLE
            if (myChoice.background == randomChoice.background) {
                youChoseThisGun.text = "Ничья!"
                youChoseThisGun.setTextColor(ContextCompat.getColor(this, R.color.draw))
            } else if (recordings.contains(
                    setOf(
                        listOfChoices.indexOf(randomChoice.background),
                        listOfChoices.indexOf(myChoice.background)
                    )
                )
            ) {
                youChoseThisGun.text = "Вы выиграли!"
                youChoseThisGun.setTextColor(ContextCompat.getColor(this, R.color.winColor))
            } else {
                youChoseThisGun.text = "Вы проиграли!"
                youChoseThisGun.setTextColor(ContextCompat.getColor(this, R.color.loseColor))
            }
        }
        againButton.setOnClickListener {
            imageQuestion.visibility = View.VISIBLE
            chooseOne.visibility = View.VISIBLE
            againButton.visibility = View.INVISIBLE
            myChoice.visibility = View.INVISIBLE
            randomChoice.visibility = View.INVISIBLE
            youChoseThisGun.text = "Выберите оружие"
            youChoseThisGun.setTextColor(ContextCompat.getColor(this, R.color.black))
        }
    }

    override fun chosenOne() {
        chooseOne.visibility = View.INVISIBLE
        myChoice.background = rock
        myChoice.visibility = View.VISIBLE
        duelGo.visibility = View.VISIBLE
        youChoseThisGun.text = "Вы выбрали камень!"
    }

    override fun chooseTwo() {
        chooseOne.visibility = View.INVISIBLE
        myChoice.background = paper
        myChoice.visibility = View.VISIBLE
        duelGo.visibility = View.VISIBLE
        youChoseThisGun.text = "Вы выбрали бумагу!"
    }

    override fun chooseThree() {
        chooseOne.visibility = View.INVISIBLE
        myChoice.background = scissor
        myChoice.visibility = View.VISIBLE
        duelGo.visibility = View.VISIBLE
        youChoseThisGun.text = "Вы выбрали ножницы!"
    }
}