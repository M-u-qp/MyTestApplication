package com.example.mytestapplication.screen.common

class MyGenericLinkedList<T> {
    private var head: Node<T>? = null
    private data class Node<T>(var data: T, var next: Node<T>? = null)

    fun add(data: T) {
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
        var previous: Node<T>? = null
        var current = head
        var next: Node<T>?

        while (current != null) {
            next = current.next
            current.next = previous
            previous = current
            current = next
        }
        head = previous
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
}