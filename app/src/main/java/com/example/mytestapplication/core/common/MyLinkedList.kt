package com.example.mytestapplication.core.common

class MyLinkedList {
    private var head: Node? = null
    private var tail: Node? = null

    private data class Node(var data: Int, var next: Node? = null)

    fun add(data: Int) {
        val newNode = Node(data)
        if (head == null) {
            head = newNode
            tail = newNode
        } else {
            tail?.next = newNode
            tail = newNode
        }
    }

    fun reverse(): MyLinkedList {
        var previous: Node? = null
        var current = head
        var next: Node?

        while (current != null) {
            next = current.next
            current.next = previous
            previous = current
            current = next
        }
        head = previous
        return this
    }

    fun toList(): List<Int> {
        val result = mutableListOf<Int>()
        var current = head
        while (current != null) {
            result.add(current.data)
            current = current.next
        }
        return result
    }

    fun printList() {
        var current = head
        while (current != null) {
            println(current.data)
            current = current.next
        }
    }
}