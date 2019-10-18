package ch.larsbaertschi.lordeckcodes.model

enum class Faction(
    val id: Int,
    val shortName: String
) {
    Demacia(0, "DE"),
    Freljord(1, "FE"),
    Ionia(2, "IO"),
    Noxus(3, "NX"),
    PiltoverZaun(4, "PZ"),
    ShadowIsles(5, "SI");

    companion object {
        fun getById(id: Int): Faction {
            return values().first { faction -> faction.id == id }
        }
    }
}