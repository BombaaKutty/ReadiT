package com.example.readit.models


class Book {
    var name:String = ""
    var author:String = ""
    var title:String = ""
    var description:String = ""
    var userId:String = ""

    constructor(name: String,author: String,title: String, description: String, userId: String) {
        this.name = name
        this.author = author
        this.title = title
        this.description = description
        this.userId = userId
    }

    constructor()
}