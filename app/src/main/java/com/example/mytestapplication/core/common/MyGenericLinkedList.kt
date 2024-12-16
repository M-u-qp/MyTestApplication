package com.example.mytestapplication.core.common

class MyGenericLinkedList<T>(private val head: Node<T>? = null) {
    data class Node<T>(val data: T, val next: Node<T>? = null)

    fun add(data: T): MyGenericLinkedList<T> {
        val newNode = Node(data)
        return if (head == null) {
            MyGenericLinkedList(newNode, newNode)
        } else {
            val newTail = Node(data)
            val newHead = appendToTail(head, newTail)
            MyGenericLinkedList(newHead, newTail)
        }
    }

    private fun appendToTail(current: Node<T>, newTail: Node<T>): Node<T> {
        return if (current.next == null) {
            current.copy(next = newTail)
        } else {
            current.copy(next = appendToTail(current.next, newTail))
        }
    }

    private constructor(oldHead: Node<T>?, newTail: Node<T>) : this(oldHead ?: newTail)

    fun reverse(): MyGenericLinkedList<T> {
        var previous: Node<T>? = null
        var current = head
        var next: Node<T>?

        while (current != null) {
            next = current.next
            previous = Node(current.data, previous)
            current = next
        }
        return MyGenericLinkedList(previous)
    }

    fun toList(): List<T> {
        val result = mutableListOf<T>()
        var current = head
        while (current != null) {
            result.add(current.data)
            current = current.next
        }
        return result
    }

    fun forEach1(action: (T) -> Unit) {
        var current = head
        while (current != null) {
            action(current.data)
            current = current.next
        }
    }
}