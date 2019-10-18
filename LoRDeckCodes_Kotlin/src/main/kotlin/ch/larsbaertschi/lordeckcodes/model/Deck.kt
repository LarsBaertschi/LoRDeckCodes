package ch.larsbaertschi.lordeckcodes.model

data class Deck(
    val cards: List<Card>,
    val format: Int,
    val version: Int
)