package com.github.sancta.simplicitas.pastebin.ext

object Fn {

    implicit class KtStd[T](a: T) {
        def also(f: T => Unit): T = {
            f(a)
            a
        }

        def let[R](f: T => R): R = f(a)
    }

}
