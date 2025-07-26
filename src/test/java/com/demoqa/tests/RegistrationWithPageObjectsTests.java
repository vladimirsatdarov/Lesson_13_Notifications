package com.demoqa.tests;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.RegistrationPage;
import com.demoqa.pages.components.CalendarComponent;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationWithPageObjectsTests extends TestBase {

    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage()
                .setFirstName("Alex")
                .setLastName("Egorov")
                .setUserEmail("alex@egorov.com")
                .setGender("Other")
                .setUserNumber("1234567890")
                .setBirthDay("30", "July", "2008");

        $("#subjectsInput").setValue("Math").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("img/1.png");
        $("#currentAddress").setValue("Some address 1");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

        $(".modal-dialog").should(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Alex"), text("Egorov"),
                text("alex@egorov.com"), text("1234567890"));

        /* todo
        registrationPage.verifyResult("Full name", "Alex Egorov")
                .verifyResult("Email", "alex@egorov.com")
                ...
         */
        $(".table-responsive").$(byText("Full name"))
                .sibling(0).shouldHave(text("Alex Egorov"));
    }

}
