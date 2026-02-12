package org.esc.tasktracker.extensions

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun <T> T.takeIfOrThrow(predicate: (T) -> Boolean, exception: () -> Throwable): T {
    contract { callsInPlace(predicate, InvocationKind.EXACTLY_ONCE) }
    return if (predicate(this)) this else throw exception()
}