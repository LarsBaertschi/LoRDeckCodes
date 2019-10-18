package ch.larsbaertschi.lordeckcodes.model

data class Card(
    val set: Int,
    val card: Int,
    val faction: Faction,
    val count: Int
) {
    fun getSetAsString(): String = set.toString().padStart(2, '0')
    fun getCardAsString(): String = card.toString().padStart(3, '0')
    fun getCardCode(): String = getSetAsString() + faction.shortName + getCardAsString()
    override fun toString(): String = "${getCardCode()} (count: $count)"
}