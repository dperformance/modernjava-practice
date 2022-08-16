package com.study.modernjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


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

        // 방법 4
        Function<String, Integer> toInt3 = Integer::parseInt;

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


        // ----------------------------------------- Supplier Function Study
        final Supplier<String> helloSupplier = () -> "Hello ";
        System.out.println(helloSupplier.get() + "world");

        System.out.println(getVeryExpensiveValue());
        Long start = System.currentTimeMillis();                    // number >= 0보다 크면 처리되기때문에 1번만 동작하면 되는데 모두 동작한다.
        printIfValidIndex(1, getVeryExpensiveValue());       // 순서대로 처리하기 때문에 6초나 걸린다.
        printIfValidIndex(-3, getVeryExpensiveValue());
        System.out.println((System.currentTimeMillis() - start) / 1000 + "초 걸렸습니다.");

        start = System.currentTimeMillis();
        printIfValidIndex(1, () -> getVeryExpensiveValue());   // 병렬처리가 되어 3초만 걸린다.
        printIfValidIndex(-3, () -> getVeryExpensiveValue());
        System.out.println((System.currentTimeMillis() - start) / 1000 + "초 걸렸습니다.");



    }

    private static String getVeryExpensiveValue() {
        // DB 갔다오고 뭐 처리하고 3초 걸리는 함수
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Dyson";
    }

    private static void printIfValidIndex(int number, String value) {
        if (number >= 0) {
            System.out.println("The value is " + value + ".");
        } else {
            System.out.println("Invalid");
        }
    }

    private static void printIfValidIndex(int number, Supplier<String> value) {
        if (number >= 0) {
            System.out.println("The value is " + value.get() + ".");
        } else {
            System.out.println("Invalid");
        }
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
