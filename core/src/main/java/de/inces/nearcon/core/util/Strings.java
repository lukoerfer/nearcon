package de.inces.nearcon.core.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Strings {

    Empty(""),
    Space(" "),
    Colon(":")

    ;

    @Getter
    private String value;

    public String $() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
