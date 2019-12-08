package web.service

import java.nio.charset.StandardCharsets
import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config

class Redis {
    def create(): RedissonClient = {
        val config = new Config
        config.useSingleServer().setAddress("redis://127.0.0.1:6379")
        Redisson.create(config)
    }
}

object Redis {
    println("Redis Instance created.")
    val client: RedissonClient = new Redis().create()

    def set(k: String, v: String): Unit = {
        client.getBinaryStream(k).set(v.getBytes(StandardCharsets.UTF_8))
    }

    def get(k: String): String = {
        new String(client.getBinaryStream(k).get(), StandardCharsets.UTF_8)
    }
}
