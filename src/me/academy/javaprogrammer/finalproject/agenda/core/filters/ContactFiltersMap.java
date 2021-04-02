package me.academy.javaprogrammer.finalproject.agenda.core.filters;

import me.academy.javaprogrammer.finalproject.agenda.core.filter.*;

import javax.swing.*;
import java.util.LinkedHashMap;

public final class ContactFiltersMap extends LinkedHashMap<String, ContactFilter> {
    public ContactFiltersMap(JTextField searchStringTextField) {
        NoFilterContactFilter noFilterContactFilter = new NoFilterContactFilter();
        put(noFilterContactFilter.getFilterName(), noFilterContactFilter);

        FixedPhoneNumberContactFilter fixedPhoneNumberContactFilter = new FixedPhoneNumberContactFilter();
        put(fixedPhoneNumberContactFilter.getFilterName(), fixedPhoneNumberContactFilter);

        MobilePhoneNumberContactFilter mobilePhoneNumberContactFilter = new MobilePhoneNumberContactFilter();
        put(mobilePhoneNumberContactFilter.getFilterName(), mobilePhoneNumberContactFilter);

        BirthdayThisMonthContactFilter birthdayThisMonthContactFilter = new BirthdayThisMonthContactFilter();
        put(birthdayThisMonthContactFilter.getFilterName(), birthdayThisMonthContactFilter);

        BirthdayTodayContactFilter birthdayTodayContactFilter = new BirthdayTodayContactFilter();
        put(birthdayTodayContactFilter.getFilterName(), birthdayTodayContactFilter);

        CustomContactFilter customContactFilter = new CustomContactFilter(searchStringTextField);
        put(customContactFilter.getFilterName(), customContactFilter);
    }
}
