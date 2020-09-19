package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class Policy {
    public static final String MESSAGE_CONSTRAINTS = "Policies can take any values, and it should not be blank ";
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Creates a new Policy Object
     *
     * @param policy
     */
    public Policy(String policy) {
        requireNonNull(policy);
        value = policy;
    }

    public static boolean isValidPolicy(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Policy // instanceof handles nulls
                && value.equals(((Policy) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


}
