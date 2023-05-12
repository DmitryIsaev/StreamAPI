import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        int[] input = {50, 60, 70, 80, 90, 100};
        List<Integer> result = new ArrayList<>();
        int count = 0;
        for (int x : input) {
            if (x > 90) {
                continue;
            }
            x += 10;
            count++;
            if (count > 3) break;
            result.add(x);
        }
        System.out.println(result);

        //Использование Stream
        int[] input1 = {50, 60, 70, 80, 90, 100};
        List<Integer> result1 =
                Arrays.stream(input1)
                        .filter(x -> x > 90)
                        .map(x -> x + 10)
                        .limit(3)
                        .boxed()
                        .collect(Collectors.toList());
        System.out.println(result1);

        List<String> list =
                Arrays.asList("My", "Pen", "Is", "Black");
        list.stream()
                .filter(x -> x.length() == 3) //Ищем слово из трёх букв
                .map(String::toUpperCase)
                .forEach(System.out::println);

        //ПРОМЕЖУТОЧНЫЕ ОПЕРАТОРЫ:
        //filter
        List<String> list1 = Arrays.asList("Moscow", "NY", "Tokyo");
        list1.stream()
                .filter(value -> value.length() >= 3)
                .filter(value -> value.contains("M"))
                .forEach(System.out::println);
        //Moscow

        //map задаёт функцию преобразования одного объекта в другой
        List<String> list2 = Arrays.asList("Moscow", "NY", "Tokyo");
        list2.stream()
                .map(String::toUpperCase) //преобразуем список городов в слова заглавными буквами
                .map(value -> value + " <3") //добавим строку
                .forEach(System.out::println);
        // MOSCOW <3
        // NY <3
        // TOKYO <3

        //flatMap отображает один элемент в виде нескольких элементов
        //требуется вывести в консоль количество слов в четверостишии
        List<String> list3 = new ArrayList<>();
        list3.add("И долго буду тем любезен я народу");
        list3.add("Что чувства добрые я лирой пробуждал");
        list3.add("Что в мой жестокий век восславил я Свободу");
        list3.add("И милость к падшим призывал");
        long count2 = list3.stream()
                .flatMap(value -> Arrays.stream(value.split(" ")))
                .count();
        System.out.println(count2);
        // 26

        //distinct применяется для удаления дубликатов
        List<String> list4 = Arrays.asList("one", "two", "three", "one", "two");
        list4 = list4.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list4);
        // [one, two, three]

        //limit применяется для ограничения количества элементов в стриме
        List<String> list5 = Arrays.asList("one", "two", "three", "one", "two");
        list5 = list5.stream()
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(list5);
        // [one, two]

        //sorted применяется для сортировки элементов источника стрима

        List<String> list6 = Arrays.asList("9", "A", "Z", "1", "B", "Y", "4", "a", "c");
        //naturalOrder — сортировка элементов в естественном порядке
        list6.stream()
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::print); // 149ABYZac
        System.out.println();
        //reverseOrder — сортировка элементов в обратном порядке
        list6.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::print); // caZYBA941
        System.out.println();

        //ТЕРМИНАЛЬНЫЕ ОПЕРАТОРЫ:
        //match применяются для проверки наличия совпадающего объекта в источнике стрима.
        //В качестве аргумента используется предикат
        // - Метод anyMatch возвращает true, если предикат возвращает true для любого из элементов
        // - Метод allMatch возвращает true, если предикат возвращает true для всех элементов
        // - Метод noneMath возвращает true, если предикат возвращает false для всех элементов
        List<String> list7 = Arrays.asList("one", "two", "three");
        boolean anyMatch = list7.stream()
                .anyMatch(value -> value.startsWith("o")); //проверку элементов листа на наличие элемента «о»
        System.out.println(anyMatch); // true

        //collect — терминальная операция, которая запускает внутреннюю итерацию элементов
        //и собирает элементы стрима в коллекцию
        List<String> list8 = Arrays.asList("one", "two", "three");
        List<String> uppercaseList = list8.stream()
                .map(String::toUpperCase) //преобразуем элементы листа
                .collect(Collectors.toList()); //занесём их в отдельный лист
        System.out.println(uppercaseList);

        //count — терминальная операция, которая запускает внутреннюю итерацию элементов
        //и определяет количество элементов
        List<String> list9 = new ArrayList<>();
        list9.add("И долго буду тем любезен я народу");
        list9.add("Что чувства добрые я лирой пробуждал");
        list9.add("Что в мой жестокий век восславил я Свободу");
        list9.add("И милость к падшим призывал");
        long count1 = list9.stream()
                .flatMap(value -> Arrays.stream(value.split(" ")))
                .count(); //подсчёт слов
        System.out.println(count1); // 26

        //find — терминальная операция, которая производит поиск элементов в стриме
        //findAny может найти один элемент из стрима. Найденный элемент может быть из любой точки стрима
        //findFirst вернёт первый элемент
        List<String> strings = Arrays.asList("Java Script" , "Java 8" , "Java 11" , "Android" , "Spring" );

        Optional<String> result2 = strings.parallelStream()
                .filter(s -> s.contains( "Java"))
                .findFirst();

        result2.ifPresentOrElse(System.out::println, () -> System.out.println( "There is no Java " )); // Java Script

        //forEach используется для перебора всех элементов потока и выполнения функции для каждого элемента.
        //Это действует как альтернатива операторам цикла, таким как for, while и другим

        Stream<Integer> numStream = Stream.of(43, 65, 1, 98, 63);
        numStream.forEach(n -> System.out.print(n + " ")); // 43 65 1 98 63
        System.out.println();

        //min и max — это терминальные операции, которые возвращают наименьший и наибольший элемент стрима
        List<Integer> intList = Arrays. asList(1, 2, 5, 10);
        Optional<Integer> min = intList.stream().min(Integer::compareTo);
        if (min.isPresent()) {
            Integer minString = min.get();
            System. out.println(minString); // 1
        }

        //reduce — это терминальная операция, которая может свести все элементы в стриме к одному элементу
        List<String> stringList = new ArrayList<String>();
        stringList.add("one");
        stringList.add("two");
        stringList.add("three");
        Optional<String> reduced = stringList.stream()
                .reduce((value, combinedValue) -> combinedValue + " + " + value);
        reduced.ifPresent(System.out::println); // one + three + two

        //toArray — это терминальная операция, которая запускает внутреннюю итерацию элементов в стриме
        //и возвращает массив Object, содержащий все элементы
        List<String> stringList1 = new ArrayList<String>();
        stringList1.add("One");
        stringList1.add("Two");
        stringList1.add("Three");
        Stream<String> stream = stringList1.stream();
        Object[] objects = stream.toArray();
        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i]);
        }
    }
}