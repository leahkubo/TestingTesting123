package com.example.testingtesting123

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class UniqueCollectionTest {

    lateinit var collection: UniqueCollection

    @Before
    fun setUp() {
        collection = UniqueCollection()
    }

    @Test
    fun getAnItem(){
        val item1 = Item("Item1")
        val item2 = Item("Item2")
        val item3 = Item("Item3")
        collection.addItem(item1)
        collection.addItem(item2)
        collection.addItem(item3)

        assert(collection.get(0).equals(item1) && collection.get(1).equals(item2) && collection.get(2).equals(item3))
    }

    @Test
    fun addAnItem() {
        collection.addItem(Item("Item1"))
        val item = collection.get(0)
        assert(item.name == "Item1")
    }

    @Test
    fun addUniqueItem() {
        collection.addItem(Item("Item1"))
        collection.addItem(Item("item1"))
        collection.addItem(Item("Item2"))

        assert(collection.size() == 2)
    }

    @Test
    fun removeAnItem(){
        val item1 = Item("Item1")
        val item2 = Item("Item2")
        val item3 = Item("Item3")
        collection.addItem(item1)
        collection.addItem(item2)
        collection.addItem(item3)

        var originalNum = collection.size()
        collection.remove(item2)
        var newNum = collection.size()
        assert(originalNum == 3 && newNum == 2) {"Original: ${originalNum}(expected 3) New: ${newNum}(expected 2)"}
        assert(collection.get(0).equals(item1) && collection.get(1).equals(item3)) {"Wrong values in items"}

        originalNum = collection.size()
        collection.remove(item1)
        newNum = collection.size()
        assert(originalNum == 2 && newNum == 1) {"Original: ${originalNum}(expected 2) New: ${newNum}(expected 1)"}
        assert(collection.get(0).equals(item3)) {"Wrong values in items"}
    }

    @Test
    fun removeMissingItem(){
        val item1 = Item("Item1")
        val item2 = Item("Item2")
        val item3 = Item("Item3")
        val item4 = Item("Item4")
        collection.addItem(item1)
        collection.addItem(item2)
        collection.addItem(item3)

        val originalNum = collection.size()
        collection.remove(item4)
        assert(collection.size() == originalNum) {"Item removed despite never added"}
        assert(collection.get(0).equals(item1) && collection.get(1).equals(item2) && collection.get(2).equals(item3)) {"Items changed incorrectly"}
    }

    @Test
    fun removeFromEmpty(){
        collection.remove(Item("item"))
        assert(collection.size() == 0)
    }
}