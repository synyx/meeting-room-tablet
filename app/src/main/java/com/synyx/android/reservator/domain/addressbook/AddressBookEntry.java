package com.synyx.android.reservator.domain.addressbook;

import lombok.Getter;


/**
 * @author  Julia Dasch - dasch@synyx.de
 */
public class AddressBookEntry {

    @Getter
    private String name;

    @Getter
    private String email;

    public AddressBookEntry(String email, String name) {

        this.email = email;
        this.name = name;
    }

    @Override
    public String toString() {

        return getName() + " <" + getEmail() + ">";
    }
}
