package ch.larsbaertschi.lordeckcodes

import kotlin.experimental.and

const val ALL_BUT_MSB: Byte = 0x7f.toByte()
const val ONLY_MSB = 0x80.toByte()

fun MutableList<Byte>.popVarInt(): Int {
    var result = 0
    var currentShift = 0
    var bytesPopped = 0

    for (byte in this) {
        bytesPopped++;
        val current = byte and ALL_BUT_MSB
        result = result or (current.toInt() shl currentShift)

        if ((byte and ONLY_MSB) != ONLY_MSB) {
            this.removeRange(0, bytesPopped)
            return result
        }

        currentShift += 7;
    }

    throw IllegalStateException("Cannot parse varints.")
}

private fun MutableList<Byte>.removeRange(startIndex: Int, count: Int) {
    for (i in 0 until count) {
        this.removeAt(startIndex + i)
    }
}
