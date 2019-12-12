package com.github.sancta.simplicitas.pastebin.web.model

import java.util.Calendar

import com.github.sancta.simplicitas.pastebin.web.service.Redis
import io.circe._, io.circe.parser._

class PasteContent() {
    var content: String = _
    private val _createDate = Calendar.getInstance().getTime
}

object PasteContent {
    def write(string: String): String = {
        val short = BigInt(30, scala.util.Random).toString(36)
        Redis.set(short, string)
        val foo = new PasteContent()
        foo.content = string
        val s = parse(foo.content).getOrElse(Json.Null)
        println(s)
        short
    }

    def recv(string: String): String = {
        Redis.get(string)
    }
}