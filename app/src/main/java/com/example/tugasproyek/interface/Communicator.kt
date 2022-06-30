package com.example.tugasproyek.`interface`

interface Communicator {
    fun passDataPosting(images: Int, titles: String, dates: String,authors:String, bodies: String)

    fun passDataTurnamen(images: Int,  titles: String,  dates: String, categories: String)

    fun passDataSchedule(dates: String,  team1: String,  team2: String, categories: String)

    fun overviewTurnamen()

}