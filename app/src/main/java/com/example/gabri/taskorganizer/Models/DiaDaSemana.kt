package com.example.gabri.taskorganizer.Models

class DiaDaSemana {
    var nome:String = ""
    var descricao:String = ""
    var imagem:Int = -1

    constructor(nome: String, descricao: String, imagem:Int) {
        this.nome = nome
        this.descricao = descricao
        this.imagem = imagem
    }
}