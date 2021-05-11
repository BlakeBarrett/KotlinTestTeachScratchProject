package com.example.scratches

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ScratchTests {

    @Serializable
    data class Chair(var numberOfArms: Int, val serialNumber: String? = null) {}

    class ChairClass(var numberOfArms: Int, val serialNumber: String? = null) {}

    @Test
    fun testChairDataClassEquality() {
        val chair1 = Chair(2)
        val chair2 = Chair(2)

        // data classes (aka "structs") are value based
        assertEquals(chair1, chair2)
    }

    @Test
    fun testChairClassEquality() {
        val chair1 = ChairClass(2)
        val chair2 = ChairClass(2)

        // classes are reference based
        assertEquals(chair1, chair2)
    }

    @Test
    fun testChairClassAndDataClassEquality() {
        val chair1 = Chair(2)
        val chair2 = ChairClass(2)

        // can't compare the two directly
        assertEquals(chair1, chair2)

        // How would you fix this test?
        //  - only compare the attribute(s) you care about
        //  - override `fun equals(other: Any?): Boolean`
        //    • https://kotlinlang.org/docs/equality.html#referential-equality
    }

    @Test
    fun testSerializeChair() {
        val chair = Chair(0, "123abc")
        val jsonString = Json.encodeToString(chair)

        assertNotNull(jsonString)

        val expected = "{\"numberOfArms\":0,\"serialNumber\":\"123abc\"}"

        assertEquals(expected, jsonString)
    }

    @Test
    fun testDeserializeChair() {
        val jsonString = "{\"numberOfArms\":0,\"serialNumber\":\"123abc\"}"
        val actual = Json.decodeFromString<Chair>(jsonString)

        assertEquals("123abc", actual.serialNumber)
    }

    @Test
    fun testDeserializeChairWithMissingRequiredAttribute() {
        val jsonString = "{\"serialNumber\":\"123abc\"}"
        val actual = Json.decodeFromString<Chair>(jsonString)

        assertEquals("123abc", actual.serialNumber)
    }

    @Test
    fun testDeserializeChairWithMissingOptionalAttribute() {
        val jsonString = "{\"numberOfArms\":0}"
        val actual = Json.decodeFromString<Chair>(jsonString)

        assertEquals(0, actual.numberOfArms)

        assertNull(actual.serialNumber)
    }

    @Test
    fun testDeserializeChairWithExtraAttribute() {
        val jsonString = "{\"numberOfArms\":1,\"serialNumber\":\"123abc\",\"name\":\"Chaise lounge\"}"
        val actual = Json.decodeFromString<Chair>(jsonString)

        assertEquals("123abc", actual.serialNumber)
    }
}
