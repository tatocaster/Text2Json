package me.tatocaster.text2json

import junit.framework.Assert
import me.tatocaster.text2json.utils.parseEmojis
import me.tatocaster.text2json.utils.parseLinks
import me.tatocaster.text2json.utils.parseMentions
import org.junit.Test

/**
 * Created by tatocaster on 12/1/17.
 */
class ParserUtilsUnitTest {

    @Test
    fun parseEmoji_Found() {
        Assert.assertFalse("Lorem Ipsum is simply dummy text of the printing :poop: :party:Lorem is ".parseEmojis().isEmpty())
        Assert.assertFalse("Lorem :po0p:".parseEmojis().isEmpty())
        Assert.assertFalse("Lorem :po-0p:".parseEmojis().isEmpty())
    }

    @Test
    fun parseEmoji_allResultsFound() {
        Assert.assertEquals("Lorem Ipsum is simply dummy text of the printing :poop: :p4rty:Lorem is ".parseEmojis().size, 2)
        Assert.assertEquals("Lorem Ipsum is simply dummy text of the printing :po#op: :par@ty:Lorem is ".parseEmojis().size, 0)
    }

    @Test
    fun parseEmoji_isCorrectResult() {
        Assert.assertEquals("Lorem Ipsum is simply dummy text of the printing :poop: Lorem is".parseEmojis()[0].value, ":poop:")
    }

    @Test
    fun parseEmoji_notFound() {
        Assert.assertTrue("Lorem Ipsum is simply dummy text of the printing".parseEmojis().isEmpty())
        Assert.assertTrue("Lorem :poo".parseEmojis().isEmpty())
        Assert.assertTrue("Lorem poop:".parseEmojis().isEmpty())
        Assert.assertTrue("Lorem :p@:".parseEmojis().isEmpty())
    }

    @Test
    fun parseMentions_Found() {
        Assert.assertFalse("Lorem Ipsum is simply dummy text of the printing @Tato :party:Lorem is ".parseMentions().isEmpty())
        Assert.assertFalse("Lorem @Danny".parseMentions().isEmpty())
        Assert.assertFalse("Lorem @0xd43:".parseMentions().isEmpty())
        Assert.assertFalse("Lorem @po^o".parseMentions().isEmpty())
    }

    @Test
    fun parseMentions_allResultsFound() {
        Assert.assertEquals("Lorem Ipsum is simply dummy text of the printing @Tato @DanLorem is ".parseMentions().size, 2)
    }

    @Test
    fun parseMentions_isCorrectResult() {
        Assert.assertEquals("Lorem Ipsum is simply dummy text of the printing @Tato Lorem is".parseMentions()[0].value, "@Tato")
    }

    @Test
    fun parseMentions_notFound() {
        Assert.assertTrue("Lorem Ipsum is simply dummy text of the printing".parseMentions().isEmpty())
        Assert.assertTrue("Lorem poop@".parseMentions().isEmpty())
        Assert.assertTrue("Lorem das#asd".parseMentions().isEmpty())
    }

    @Test
    fun parseLinks_Found() {
        Assert.assertFalse("http://google.com".parseLinks().isEmpty())
        Assert.assertFalse("https://google.com".parseLinks().isEmpty())
        Assert.assertFalse("http://google-test.com".parseLinks().isEmpty())
        Assert.assertFalse("http://test.google.com".parseLinks().isEmpty())
    }

    @Test
    fun parseLinks_allResultsFound() {
        Assert.assertEquals("see: http://google.com and http://google.ge".parseLinks().size, 2)
    }

    @Test
    fun parseLinks_isCorrectResult() {
        Assert.assertEquals("http://google.com".parseLinks()[0].url, "http://google.com")
    }

    @Test
    fun parseLinks_notFound() {
        Assert.assertTrue("http://go@ogle.com".parseLinks().isEmpty())
        Assert.assertTrue("http//google.com".parseLinks().isEmpty())
        Assert.assertTrue("http:google.com".parseLinks().isEmpty())
    }

}