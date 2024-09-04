package com.example.readit.models

class Product {
    var name:String = ""
    var price:String = ""
    var imageUrl:String = ""
    var id:String = ""

    constructor(name: String, price: String, imageUrl: String, id: String) {
        this.name = name
        this.price = price
        this.imageUrl = imageUrl
        this.id = id
    }

    constructor()
}