package utils

object extFn {
    implicit class KtStd[T](a: T) {
        def let[R](f: T => R): R = f(a)
    }
}
