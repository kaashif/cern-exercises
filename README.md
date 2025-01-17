# Solutions for BE-OP-LHC-2024-174-GRAP & TE-MPE-CB-2024-166-GRAP Coding Exercises

The solution to exercise 1 is written in Python, all other solutions are
written in Java.

## Exercise 1

This solution is written in Python. It depends on `pytest` for running the
tests and `mypy` for type checking.

Install dependencies:

```
$ cd exercise1
$ python3.13 -m venv venv
$ . venv/bin/activate
$ pip install -e .
```

Run the test suite:

```
$ pytest
```

Check the types:

```
$ mypy .
```

Both of these steps run as part of CI.

## Exercises 2 and 3

Ensure you have Java >=21 installed. Run the tests on Linux with:

```
$ ./gradlew test
```

Code for Exercise 2 is in `src/main/java/org/cern/exercise2/`, code for Exercise 3 in `src/main/java/org/cern/exercise3/`.
