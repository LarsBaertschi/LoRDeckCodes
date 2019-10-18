# LoRDeckCodes Kotlin
This is a Kotlin library to encode and decode Legends of Runeterra deck codes. It can also be used
in Java of course!

## Usage
### To decode a code to a deck object:

Kotlin:
```kotlin
val deck = LoRDeckDecoder().decode("CEAAECABAQJRWHBIFU2DOOYIAEBAMCIMCINCILJZAICACBANE4VCYBABAILR2HRL")
```

Java:
```java
Deck deck = new LoRDeckDecoder().decode("CEAAECABAQJRWHBIFU2DOOYIAEBAMCIMCINCILJZAICACBANE4VCYBABAILR2HRL");
```

### To encode a deck object to a code:

Kotlin:
```kotlin
val code = LoRDeckEncoder().encode(myDeck)
```

Java:

```java
String code = new LoRDeckEncoder().encode(myDeck)
```