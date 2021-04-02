package me.academy.javaprogrammer.finalproject.agenda.core.filter;

import me.academy.javaprogrammer.finalproject.agenda.core.contact.Contact;

import java.time.MonthDay;

public final class BirthdayTodayContactFilter extends ContactFilter {
    public BirthdayTodayContactFilter() {
        super("birthday today");
    }

    @Override
    public boolean test(Contact contact) {
        MonthDay currentMonthDay = MonthDay.now();
        MonthDay contactMonthDay = MonthDay.of(contact.getBirthDate().getMonth(), contact.getBirthDate().getDayOfMonth());
        return contactMonthDay.equals(currentMonthDay);
    }
}
