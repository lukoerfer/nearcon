package de.inces.nearcon.service.persistence;

import org.atteo.evo.inflector.English;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import de.inces.nearcon.core.util.Strings;

public class PluralNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    private static final String CAMEL_CASE_REGEX = "(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])";

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        String[] words = name.getText().split(CAMEL_CASE_REGEX);
        int last = words.length - 1;
        words[last] = English.plural(words[last]);
        String pluralName = String.join(Strings.EMPTY, words);
        return new Identifier(pluralName, name.isQuoted());
    }
}
