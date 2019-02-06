package org.olaven.tictacktoe.game

/**
 * Something tha can be copied.
 * This is necessary, as generics cannot be
 * assured to be data classes.
 */
interface CanCopy<T> {
    fun copy(): T
}
