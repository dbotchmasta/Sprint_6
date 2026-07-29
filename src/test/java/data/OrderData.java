package data;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;

public class OrderData {
    public static Stream<Arguments> orderData() {
        return Stream.of(
                Arguments.of("Иван", "Иванов", "Москва, ул. Ленина 1", "Черкизовская", "89998887766", "20.07.2026", "сутки", true, "Позвоните заранее", true),
                Arguments.of("Петр", "Петров", "Москва, ул. Гагарина 15", "Сокольники", "89991112233", "22.07.2026", "двое суток", false, "Домофон не работает", false)
        );
    }
}