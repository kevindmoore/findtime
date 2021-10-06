package com.raywenderlich.findtime

import kotlin.test.Test
import kotlin.test.assertTrue


class TimeZoneTest {
    val timeZoneHelperImpl = TimeZoneHelperImpl()

    @Test
    fun testSearch1() {
        val zonesToTest = mutableListOf<String>()
        zonesToTest.add("America/Los_Angeles")
        zonesToTest.add("America/New_York")
        val result = timeZoneHelperImpl.search(8, 17, zonesToTest)
        assertTrue(result.size == 7)
    }

    @Test
    fun testSearch2() {
        val zonesToTest = mutableListOf<String>()
        zonesToTest.add("America/Los_Angeles")
        zonesToTest.add("Europe/Berlin")
        val result = timeZoneHelperImpl.search(8, 17, zonesToTest)
        assertTrue(result.size == 1)
    }

    @Test
    fun testSearch3() {
        val zonesToTest = mutableListOf<String>()
        zonesToTest.add("America/Los_Angeles")
        zonesToTest.add("Europe/Berlin")
        val result = timeZoneHelperImpl.search(8, 20, zonesToTest)
        assertTrue(result.size == 4)
    }

    @Test
    fun testSearch4() {
        val zonesToTest = mutableListOf<String>()
        zonesToTest.add("America/Los_Angeles")
        zonesToTest.add("Europe/Berlin")
        zonesToTest.add("Asia/Tehran")
        val result = timeZoneHelperImpl.search(8, 20, zonesToTest)
        assertTrue("Result equals ${result.size}") { result.size == 2 }
    }
}