from typing import TypeVar
from collections import Counter

T = TypeVar("T")


def get_duplicates(items: list[T]) -> list[T]:
    """
    Returns list of items that appeared more than once,
    in the order they first appeared.
    """
    counter = Counter(items)

    # Since Python 3.7, Counter iteration order is insertion order,
    # which means this gives elements in the order they first appeared.
    return [item for item, count in counter.items() if count > 1]
