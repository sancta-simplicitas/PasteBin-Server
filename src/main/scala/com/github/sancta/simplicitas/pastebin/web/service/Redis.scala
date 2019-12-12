package com.github.sancta.simplicitas.pastebin.web.service

import java.nio.charset.StandardCharsets
import org.redisson.Redisson
import org.redisson.api.{RMap, RedissonClient}
import org.redisson.config.Config
import com.github.sancta.simplicitas.pastebin.ext.Fn.KtStd

class Redis {
    def create(): RedissonClient = {
        new Config().let { config =>
            config.useSingleServer().setAddress("redis://127.0.0.1:6379")
            Redisson.create(config)
        }
    }
}

object Redis {
    println("Redis Instance created.")
    val client: RedissonClient = new Redis().create()

    def set(k: String, v: String): Unit = {
        val test: RMap[String, String] = client.getMap("test")
        test.put("da", "abcdef")
        println(test.get("da"))
        client.getBinaryStream(k).set(v.getBytes(StandardCharsets.UTF_8))
    }

    def get(k: String): String = {
        new String(client.getBinaryStream(k).get(), StandardCharsets.UTF_8)
    }
}
