from deduplicate import deduplicate_list


def test_deduplicates_strings() -> None:
    assert deduplicate_list(["b", "a", "c", "c", "e", "a", "c", "d", "c", "d"]) == [
        "a",
        "c",
        "d",
    ]
