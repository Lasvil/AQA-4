
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;


import java.time.Duration;
import java.time.LocalDate;



import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {


    @Test
    public void shouldHappyTest(){
        LocalDate date = LocalDate.now();
        date = date.plusDays(3);
        int year = date.getYear();
        int month = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth();
        open("http://localhost:9999/");
        SelenideElement form = $(".form_size_m");
        form.$("[placeholder='Город']").setValue("Архангельск");
        form.$("[placeholder='Дата встречи']").setValue(String.valueOf(date));
        form.$("[name='name']").setValue("Иван Царь");
        form.$("[name='phone']").setValue("+79286664455");
        form.$("[data-test-id='agreement']").click();
        form.$(".button_view_extra").click();
        SelenideElement form2 = $(".notification_visible").should(visible, Duration.ofSeconds(15));
        form2.$(".notification__title").shouldHave(exactText("Успешно!")).should(visible);
        form2.$(".notification__content").shouldHave(exactText("Встреча успешно забронирована на " + dayOfMonth + "." + month + "." + year));
    }
}
