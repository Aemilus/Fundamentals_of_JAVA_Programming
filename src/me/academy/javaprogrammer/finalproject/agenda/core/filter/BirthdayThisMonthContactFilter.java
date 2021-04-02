package me.academy.javaprogrammer.finalproject.agenda.core.filter;

import me.academy.javaprogrammer.finalproject.agenda.core.contact.Contact;

import java.time.LocalDate;
import java.time.Month;

public final class BirthdayThisMonthContactFilter extends ContactFilter {
    public BirthdayThisMonthContactFilter() {
        super("birthdate this month");
    }

    @Override
    public boolean test(Contact contact) {
        Month currentMonth = LocalDate.now().getMonth();
        Month contactMonth = contact.getBirthDate().getMonth();
        return contactMonth.equals(currentMonth);
    }
}
