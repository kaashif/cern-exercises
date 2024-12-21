from typing import TypeVar

T = TypeVar("T")


def deduplicate_list(items: list[T]) -> list[T]:
    """
    Returns list of items in item that appeared more than once,
    in the order they first appeared.
    """
    item_to_count: dict[T, int] = {}

    for item in items:
        item_to_count[item] = item_to_count.get(item, 0) + 1

    # Since Python 3.7, dict iteration order is insertion order,
    # which means this gives elements in the order they first appeared.
    return [item for item, count in item_to_count.items() if count > 1]
