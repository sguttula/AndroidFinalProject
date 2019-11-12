package com.example.myfinalproject.Model

class Provider {

    private var username: String = ""
    private var fullname: String = ""
    private var email: String = ""
    private var washType: String = ""

    constructor(){}

    constructor(username: String, fullname: String, email: String){

        this.username = username
        this.fullname = fullname
        this.email = email
        this.washType = washType
    }

    fun  getProvidername(): String{

        return  username

    }
    fun setProvidername(username: String){
        this.username = username
    }

    fun  getProviderfullname(): String{

        return  fullname

    }
    fun setProviderfullname(fullname: String){
        this.fullname = fullname
    }

    fun  getProvideremail(): String{

        return  email

    }
    fun setProvideremail(email: String){
        this.email = email
    }

    fun  getwashType(): String{

        return washType

    }
    fun setwashType(washType: String){
        this.washType = washType
    }

}