package web.model

import java.util.Calendar

import web.service.Redis

class PasteContent(content: String) {
    private val _createDate = Calendar.getInstance().getTime
    println(content)
    Redis.set("dfsdf", content)
}

object PasteContent {
    def write(string: String): Unit = {
        new PasteContent(string)
    }

    def recv(string: String): String = {
        Redis.get(string)
    }
}