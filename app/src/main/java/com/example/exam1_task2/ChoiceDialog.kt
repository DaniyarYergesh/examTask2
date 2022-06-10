package com.example.exam1_task2

import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment

class ChoiceDialog:DialogFragment(R.layout.dialog_custom) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent()
        with(view){
            findViewById<ImageView>(R.id.rock).setOnClickListener {
                (activity as ChooseOne).chosenOne()
                dismiss()
            }
            findViewById<ImageView>(R.id.paper).setOnClickListener {
                (activity as ChooseOne).chooseTwo()
                dismiss()
            }
            findViewById<ImageView>(R.id.scissor).setOnClickListener {
                (activity as ChooseOne).chooseThree()
                dismiss()
            }
        }
    }

    private fun setWidthPercent(percentage: Int = 90) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}

interface ChooseOne{
    fun chosenOne()
    fun chooseTwo()
    fun chooseThree()
}

