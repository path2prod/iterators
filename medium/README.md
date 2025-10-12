# Understanding Medium

1. Create a `MultiList<T>` class that stores elements in multiple internal lists. Implement different iterators: sequential (all elements), odd-indexed, even-indexed. Use factory methods like `getSequentialIterator(), getOddIterator()`
2. Create a generic `FilteredIterable<T>` that wraps another Iterable<T>. Takes a Predicate<T> to filter elements during iteration. Should chain with other FilteredIterables using `and`, `or` and `not`.
3. Create an iterator that generates elements on-demand using a supplier function. Should not pre-compute values, only generate when next() is called
Implement infinite sequences with proper hasNext() logic.
4. Implement an iterator that cycles through elements infinitely. Include methods to reset, limit cycles, or change directionHandle empty collections gracefully.
5. Create a `PeekableIterable<T>` wrapper that adds peek() functionality. Allow looking at the next element without consuming it. Implement proper state management for peek operations
6. Create an iterator that maintains a buffer of previously seen elements. Support previous() operations for limited backtracking. !hImplement configurable buffer size with memory management
7. Build an iterator where multiple transformation functions can be chained. Each iterator applies a transformation to the previous iterator's output