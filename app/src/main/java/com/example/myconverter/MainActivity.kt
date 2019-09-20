package com.example.myconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private fun showMessageBox(message:String?, title:String?="Ошибка") {
        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(title)
            setMessage(message)
            setPositiveButton(
                android.R.string.yes
            ) { dialog, _ -> dialog.cancel()}
            alertDialogBuilder.show()
        }
    }

    @ExperimentalUnsignedTypes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnEnter.setOnClickListener {
            val numberDec = etDecimal?.text?.toString()
            if(numberDec.isNullOrEmpty()){
                showMessageBox("Ты забыл ввести число, дружок")
            }
            else{
                etBinary.setText(numberDec.toUInt().toString(radix = 2))
                tvResult.text = "Конвертация успешна"
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {

        outState.run {
            putString("KEY", tvResult.text.toString())
        }

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        tvResult.text = savedInstanceState.getString("KEY")
    }
}
