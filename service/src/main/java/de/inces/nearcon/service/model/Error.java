package de.inces.nearcon.service.model;

import de.inces.nearcon.core.util.Strings;
import lombok.Getter;

public enum Error {


    ;

    @Getter
    private final int code;
    @Getter
    private final String message;

    Error() {
        this.code = 0;
        this.message = Strings.EMPTY;
    }
}
