package com.example.tugasproyek.widget

class saveMassage {
    private var lstMessage = arrayListOf(
        "Hello Sobat Esport",
        "Yuk Cek Kami"
    )
    private var index = -1

    fun addMessage(str: String) {
        lstMessage.add(str)
    }
    fun removeMessage(str: String){
        lstMessage.remove(str)
    }
    fun backToStart() { index = -1}

    fun getMessage() : String {
        if(lstMessage.size==0)
            return "Kosong"
        if(index+1==lstMessage.size)
            backToStart()
        index+=1
        return lstMessage.get(index)
    }
}