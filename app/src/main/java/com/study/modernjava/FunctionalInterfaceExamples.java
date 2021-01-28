package com.study.modernjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


/*
    Identity Function (매개변수로 받은 타입과 값을 그대로 리턴해주는 함수를 Identity Function 이라 부른다.)

    public String identity(String value) {
        return value;
    }
*/
public class FunctionalInterfaceExamples {
    public static void main(String[] args) {
        // ----------------------------------------- Identity Function Study

        // 방법 1
        Function<String, Integer> toInt = new Function<String, Integer>() {
            @Override
            public Integer apply(String value) {
                return Integer.parseInt(value);
            }
        };
        final Integer number = toInt.apply("1000");
        System.out.println(number);

        // 방법 2
        Function<String, Integer> toInt1 = (final String value) -> {
            return Integer.parseInt(value);
        };
        // 방법 3
        Function<String, Integer> toInt2 = (value) -> Integer.parseInt(value);

        final Integer number1 = toInt1.apply("123");
        final Integer number2 = toInt2.apply("444");
        System.out.println(number1);
        System.out.println(number2);

        // Identity function
        // static <T> Function<T, T> identity() {
        //      return t -> t;
        // }
        final Function<Integer, Integer>  identity = Function.identity();
        System.out.println(identity.apply(9876));

        final Function<Integer, Integer>  identity1 = t -> t;
        System.out.println(identity1.apply(5678));


        // ----------------------------------------- Consumer Function Study
        // Consumer 는 return 타입이 없는(void) 녀석이다.
        final Consumer<String> print = new Consumer<String>() {
            @Override
            public void accept(String value) {
                System.out.println(value);
            }
        };
        print.accept("Hello");

        // Lambda Style
        final Consumer<String> print1 = (value) -> System.out.println(value);
        final Consumer<String> greetings = (value) -> System.out.println("Hello " + value);

        print1.accept("zzz");
        greetings.accept("Dyson");


        // ----------------------------------------- Predicate Function Study
        // Predicate의 return type은 boolean이다.
        // Function<Integer, Boolean>으로 써도 같은데
        Predicate<Integer> isPositive = i -> i > 0;
        System.out.println(isPositive.test(3));
        System.out.println(isPositive.test(0));
        System.out.println(isPositive.test(-5));

        List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);

        List<Integer> positiveNumbers = new ArrayList<>();
        for (Integer num : numbers) {
            if (isPositive.test(num)) {
                positiveNumbers.add(num);
            }
        }
        System.out.println("positiveNumbers : " + positiveNumbers);
        System.out.println("filter : " + filter(numbers, isPositive));
    }

    private static <T> List<T> filter(List<T> list, Predicate<T> filter) {
        List<T> result = new ArrayList<>();
        for (T input : list) {
            if (filter.test(input)) {
                result.add(input);
            }
        }
        return result;
    }
}
