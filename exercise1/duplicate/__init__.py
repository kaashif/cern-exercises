from typing import Sequence
from collections import Counter
from collections.abc import Hashable


def get_duplicates(items: Sequence[Hashable]) -> list:
    """
    Returns list of items that appeared more than once,
    in the order they first appeared.
    """
    counter = Counter(items)

    # Since Python 3.7, Counter iteration order is insertion order,
    # which means this gives elements in the order they first appeared.
    return [item for item, count in counter.items() if count > 1]
