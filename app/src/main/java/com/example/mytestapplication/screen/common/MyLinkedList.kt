package com.example.mytestapplication.screen.common

class MyLinkedList {
    private var head: Node? = null
    private data class Node(var data: Int, var next: Node? = null)

    fun add(data: Int) {
        val newNode = Node(data)
        if (head == null) {
            head = newNode
        } else {
            var current = head
            while (current?.next != null) {
                current = current.next
            }
            current?.next = newNode
        }
    }
    fun reverse() {
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
}