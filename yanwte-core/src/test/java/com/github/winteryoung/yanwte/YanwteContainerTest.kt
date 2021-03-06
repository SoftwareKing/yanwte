package com.github.winteryoung.yanwte

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * @author Winter Young
 * @since 2016/1/25
 */
class YanwteContainerTest {
    @Before
    fun before() {
        YanwteContainer.clear()
    }

    @After
    fun after() {
        YanwteContainer.clear()
    }

    @Test
    fun testDataExtensionRegistration() {
        val testData = TestData()
        val extSpaceName = TestData::class.java.`package`.name
        YanwteContainer.cacheDataExtension(testData, extSpaceName, TestDataExt(5))
        val (i) = YanwteContainer.getDataExtension(testData, extSpaceName) as TestDataExt

        Assert.assertEquals(5, i)
    }

    @Test
    fun testGetExtensionPointByClass() {
        val extPoint = YanwteContainer.getExtensionPointByClass(TestExtensionPoint::class.java)
        Assert.assertNotNull(extPoint)

        YanwteContainer.getExtensionPointByClass(TestExtensionPoint::class.java).let { extPoint2 ->
            Assert.assertEquals(extPoint, extPoint2)
        }
    }
}

interface TestExtensionPoint {
    fun foo()
}

class TestExtensionPointProvider : ExtensionPointProvider() {
    override fun tree(): Combinator {
        return empty()
    }
}

class TestData : DataExtensionPoint

data class TestDataExt(val i: Int)