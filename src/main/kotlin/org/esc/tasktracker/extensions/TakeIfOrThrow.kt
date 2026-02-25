package org.esc.tasktracker.extensions

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Conditionally processes a value or throws an exception if the predicate fails.
 *
 * An inline extension function that combines conditional checking with exception throwing.
 * If the predicate returns true, returns the original value. If false, throws the
 * provided exception. Uses Kotlin contracts to help the compiler with nullability analysis.
 *
 * @param T The type of the receiver object.
 * @param predicate Condition to evaluate against the receiver object.
 *                  Called exactly once due to contract.
 * @param exception Factory function that creates the exception to throw
 *                  when predicate returns false.
 * @return The receiver object if predicate returns true.
 * @throws Throwable The exception created by [exception] if predicate returns false.
 *
 * @see kotlin.contracts.ExperimentalContracts
 * @see kotlin.contracts.InvocationKind.EXACTLY_ONCE
 *
 * @author Vladimir Fokin
 * @since 1.0
 */
@OptIn(ExperimentalContracts::class)
inline fun <T> T.takeIfOrThrow(predicate: (T) -> Boolean, exception: () -> Throwable): T {
    contract { callsInPlace(predicate, InvocationKind.EXACTLY_ONCE) }
    return if (predicate(this)) this else throw exception()
}