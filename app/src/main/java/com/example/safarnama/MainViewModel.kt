package com.example.safarnama

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import org.json.JSONObject

class MainViewModel(var context:Context):ViewModel() {
    private var quoteList:ArrayList<Quote> = ArrayList()
    private var index=0
    var quoteLive = MutableLiveData<Quote>(Quote("hey","yash"))
    init{
        quoteList=loadQuoteFromAssets()
        quoteLive.value=getQuote()
    }

    private fun loadQuoteFromAssets(): ArrayList<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val jsonString = String(buffer, Charsets.UTF_8)
        val jsonArrayQuotes=JSONObject(jsonString).getJSONArray("AllQuotes") as JSONArray
        val quotesArray:ArrayList<Quote> = ArrayList()
        for(i in 0 until jsonArrayQuotes.length()){
            var currentJsonObject=jsonArrayQuotes.getJSONObject(i)
            var currentQuote:Quote=Quote(
                currentJsonObject.getString("text"),
                currentJsonObject.getString("author")
            )
            quotesArray.add(currentQuote)
        }
        return quotesArray
    }

    fun getQuote():Quote{
        return quoteList[index]
    }

    fun nextQuote(){
        index=(++index)%quoteList.size
        quoteLive.value=quoteList[index]
    }

    fun previousQuote(){
        index=(--index+quoteList.size)%quoteList.size
        quoteLive.value=quoteList[index]
    }
}