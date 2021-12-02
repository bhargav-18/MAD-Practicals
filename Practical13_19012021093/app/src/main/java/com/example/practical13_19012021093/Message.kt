package com.example.practical13_19012021093

class Message(var address: String, var body: String) {
    companion object {
        var messageArray: ArrayList<Message> = arrayListOf()
    }
}