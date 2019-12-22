package ru.nsu.itboard.util;

import lombok.Data;

@Data
public class Pair<T> {
    private T first;

    private T second;
}
