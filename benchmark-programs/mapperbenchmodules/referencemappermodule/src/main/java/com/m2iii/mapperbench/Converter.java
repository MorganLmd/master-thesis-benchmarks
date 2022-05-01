package com.m2iii.mapperbench;

public interface Converter<T, U> {
    U convert(T t);
}