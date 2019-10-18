package ch.larsbaertschi.lordeckcodes

import ch.larsbaertschi.lordeckcodes.model.Card
import ch.larsbaertschi.lordeckcodes.model.Deck
import ch.larsbaertschi.lordeckcodes.model.Faction
import com.google.common.io.BaseEncoding
import kotlin.experimental.and

class LoRDeckDecoder {
    fun decode(code: String): Deck {
        val bytes = BaseEncoding.base32().decode(code).toList() as MutableList<Byte>

        check(bytes.isNotEmpty()) { "Code cannot be decoded." }

        val format = bytes[0].toInt() shr 4
        val version = (bytes[0] and 0xF).toInt()
        bytes.removeAt(0)

        check(version <= MAX_KNOWN_VERSION) { "Version of code ($version) is not supported by this library. This library only supports up to $MAX_KNOWN_VERSION." }

        val cards = mutableListOf<Card>()

        for (i in 3 downTo 1) {
            val numGroupOfs = bytes.popVarInt()

            for (j in 0 until numGroupOfs) {
                val numOfsInThisGroup = bytes.popVarInt()
                val set = bytes.popVarInt()
                val faction = bytes.popVarInt()

                for (k in 0 until numOfsInThisGroup) {
                    val card = bytes.popVarInt()
                    val factionType = Faction.getById(faction)

                    cards.add(
                        Card(
                            set = set,
                            faction = factionType,
                            card = card,
                            count = i
                        )
                    )
                }
            }
        }

        while (bytes.size > 0) {
            val fourPlusCount = bytes.popVarInt()
            val fourPlusSet = bytes.popVarInt()
            val fourPlusFaction = bytes.popVarInt()
            val fourPlusNumber = bytes.popVarInt()

            val factionType = Faction.getById(fourPlusFaction)

            val card = Card(
                set = fourPlusSet,
                faction = factionType,
                card = fourPlusNumber,
                count = fourPlusCount
            )

            cards.add(card)
        }

        return Deck(
            cards = cards,
            format = format,
            version = version
        )
    }

    companion object {
        const val MAX_KNOWN_VERSION = 1
    }
}