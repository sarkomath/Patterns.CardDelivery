import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;


import java.time.*;

import static com.codeborne.selenide.Selectors.byText;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class PlanningTests {

    Person person = DataGen.Generator.personGenerator("ru");

    String getDate(int currentDate) {
        LocalDate nowDate = LocalDate.now().plusDays(2);
        return String.valueOf(nowDate);
    }

    @BeforeEach
    void openTestPage() {
        open("http://localhost:9999");
    }

    @Test
    void allSuccessTest() {
        $("[placeholder='Город']").setValue(person.getCity()); // Устанавливаем правильный город
        $("[placeholder='Дата встречи']").setValue(getDate(2)); // вводим правильный формат даты
        $("[name='name']").setValue(person.getName()); // ВВодим правильные ИФ
        $("[name='phone']").setValue(person.getPhone()); // Вводим правильным номер телефона
        $("[data-test-id=agreement] .checkbox__box").click();
        $(byText("Запланировать")).click();
        $(byText("Успешно!")).shouldBe(visible);
        $(byText("Запланировать")).click();
        $("[placeholder='Дата встречи']").setValue(getDate(4));
        $(byText("Необходимо подтверждение")).shouldBe(visible);
        $(byText("Перепланировать")).click();

    }
}
