package edu.skku.cs.pa1

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.pa1.R

class CustomWordListAdapter(val context: Context, val text: ArrayList<Words>,
                            val yellowList: ArrayList<String>, val greenList: ArrayList<String> ):BaseAdapter(){

    override fun getCount(): Int {
        return text.size
    }
    override fun getItem(position: Int): Any {
        return text.get(position)
    }
    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(i: Int, cvtView: View?, parent: ViewGroup?): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.wordlayout,null)

        // setting the input string to variable guess
        // and secretWord to secretWord variable
        val guess = text.get(i).name
        val secretWord = text.get(i).secretWord

        val textViewIds = arrayOf(
            R.id.textView1, R.id.textView2, R.id.textView3,
            R.id.textView4, R.id.textView5
        )

        val guessArray = guess.toCharArray()
        val secretWordArray = secretWord.toCharArray()

        for (i in guessArray.indices) {
            var textView = view.findViewById<TextView>(textViewIds[i])
            textView.setText(guessArray[i].toString().capitalize())

            // flag to determine if the secretword has repeated characters
            val secondSecretCharOccurence = isCharacterRepeatedInSecretWord(guessArray[i], secretWord)
            if (guessArray[i] == secretWordArray[i]) {
                textView.setBackgroundColor(Color.parseColor("#99F691"))
                textView.setTextColor(Color.parseColor("#000000"))

                // in order to detect if the character is repeated in a guessWord
                val secondQuessCharOccurenceId = isCharacterRepeatedInGuessWord(guessArray[i], guess)

                // If the given character is not repeated in secretWord,
                // but is repeated in guessWord, the second occurance of this character
                // will be displayed as gray, instead of green
                // If the given character is the last letter in the guessWord,
                // there is no need to change anything
                if ((secondQuessCharOccurenceId > 0) and (!secondSecretCharOccurence)
                    and (!greenList.contains(guessArray[i].toString()))){
                    val secondTextView = view.findViewById<TextView>(textViewIds[secondQuessCharOccurenceId])
                    secondTextView.setBackgroundColor(Color.parseColor("#787C7E"))
                    secondTextView.setTextColor(Color.parseColor("#FFFFFF"))
                }
            } else if (secretWordArray.contains(guessArray[i])) {
                if (!greenList.contains(guessArray[i].toString())) {
                    textView.setBackgroundColor(Color.parseColor("#FFE46F"))
                    textView.setTextColor(Color.parseColor("#000000"))

                    val secondGuessCharOccurenceId =
                        isCharacterRepeatedInGuessWord(guessArray[i], guess)

                    // If the given character is not repeated in secretWord,
                    // but is repeated in guessWord, the second occurance of this character
                    // will be displayed as gray, instead of green
                    if ((secondGuessCharOccurenceId > 0) and (!secondSecretCharOccurence)) {
                        val secondTextView =
                            view.findViewById<TextView>(textViewIds[secondGuessCharOccurenceId])
                        secondTextView.setBackgroundColor(Color.parseColor("#787C7E"))
                        secondTextView.setTextColor(Color.parseColor("#FFFFFF"))
                    }
                }

            } else {
                textView.setBackgroundColor(Color.parseColor("#787C7E"))
                textView.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }
        return view
    }

    // Used to determine the secondOccurance of the character "char" in the guessWord
    // return value is the id of the second occurance of the variable "char"
    fun isCharacterRepeatedInGuessWord(char: Char, word: String): Int {
        var count = 0
        for (i in word.toCharArray().indices) {
            if (word.toCharArray()[i] == char) {
                count++
                if (count > 1){
                    return i
                }
            }
        }
        return 0
    }

    // Used to determine the secondOccurence of the character "char" in the secretWord
    // return value is true if secretWord has repeated character "char".
    // Otherwise returns false
    fun isCharacterRepeatedInSecretWord(char: Char, word: String): Boolean{
        var count = 0
        for (i in word.toCharArray().indices) {
            if (word.toCharArray()[i] == char) {
                count++
                if (count > 1)
                    return true
            }
        }
        return false
    }

}