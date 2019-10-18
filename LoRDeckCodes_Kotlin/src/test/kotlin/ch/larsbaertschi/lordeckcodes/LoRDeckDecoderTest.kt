package ch.larsbaertschi.lordeckcodes

import org.junit.Test

class LoRDeckDecoderTest {

    @Test
    fun testDecoder() {
        val code = "CEAAECABAQJRWHBIFU2DOOYIAEBAMCIMCINCILJZAICACBANE4VCYBABAILR2HRL"
        val deck = LoRDeckDecoder().decode(code)
    }

}