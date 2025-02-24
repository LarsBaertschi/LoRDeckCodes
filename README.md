LoRDeckCodes
============

The LorDeckCodes library can be used to encode/decode Legends of Runeterra decks to/from simple strings. Below is an example code for a Demacia/Freljord deck.
```
CEAAECABAQJRWHBIFU2DOOYIAEBAMCIMCINCILJZAICACBANE4VCYBABAILR2HRL
```
These strings can be used to share decks across Legends of Runeterra clients. Just remember: you can't netdeck skill.

## Cards & Decks

Every Legends of Runeterra card has a corresponding card code. Card codes are seven character strings comprised of two characters for card set, two characters for faction identifier, and three characters for card number. 

```
01DE123
│ │ └ card number - 123
│ └ faction - DE
└ set - 01
```

The deck code library accepts a Legends of Runeterra deck as a list of `CardCodeAndCount` objects. This is simply the code and an associated integer for the number of occurences of the card in the deck.

## Process
Decks are encoding via arranging VarInts into an array and then base 32 encoding into a string.

All encodings begin with 4 bits for format and 4 bits for version. Currently this is only 00010001.

The list of cards are then encoded according to the following scheme:

1. Cards are grouped together based on how many copies of the card are in the deck (e.g., cards with three copies are grouped together, cards with two copies are grouped together, and cards with a single copy are grouped together).
1. Within those groups, lists of cards are created which share the same set AND faction.
1. The set/faction lists are ordered by increasing length. The contents of the set/faction lists are ordered alphanumerically.
1. Variable length integer ([varints](https://en.wikipedia.org/wiki/Variable-length_quantity)) bytes for each ordered group of cards are written into the byte array according to the following convention:
    * [how many lists of set/faction combination have three copies of a card]
      * [how many cards within this set/faction combination follow]
      * [set]
      * [faction]
        * [card number]
        * [card number]
        * ...
      * [how many cards in this next set/faction combination follow]
      * [set]
      * [faction]
        * [card number]
        * [card number]
        * ...
    * [repeat for the groups of two copies of a card]
    * [repeat for the groups of a single copy of a card]
1. The resulting byte array is base32 encoded into a string.


### Faction Identifiers
Factions are mapped as follows:

| Integer Identifier | Faction Identifier | Faction Name |
| ------------------ | ------------------ | ------------ |
| 0 | DE | Demacia |
| 1 | FR | Frejlord |
| 2 | IO | Ionia |
| 3 | NX | Noxus |
| 4 | PZ | Piltover & Zaun |
| 5 | SI | Shadow Isles |

## License
Apache 2 (see [LICENSE](/LICENSE.txt) for details)
