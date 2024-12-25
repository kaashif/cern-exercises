from duplicate import get_duplicates
from hypothesis import given, strategies as st
from collections import Counter


def test_no_dups_in_empty_list() -> None:
    assert get_duplicates([]) == []


def test_no_dups_in_nonempty_list() -> None:
    assert get_duplicates([1, 2, 3, 4]) == []


def test_dups_from_given_example() -> None:
    assert get_duplicates(["b", "a", "c", "c", "e", "a", "c", "d", "c", "d"]) == [
        "a",
        "c",
        "d",
    ]


@given(st.lists(st.integers(max_value=10)))
def test_gets_duplicates_for_ints(ints: list[int]) -> None:
    dups = get_duplicates(ints)

    ints_counter = Counter(ints)
    dups_counter = Counter(dups)

    # "contains only those that are duplicated"
    for dup in dups:
        assert ints_counter[dup] > 1
        assert dups_counter[dup] == 1

    # "in the order they appeared for the first time in the original list"

    # This gives the first place each duped element appears in the original list
    dup_indices = [ints.index(dup) for dup in dups]

    # Assert the order hasn't changed
    assert dup_indices == sorted(dup_indices)
