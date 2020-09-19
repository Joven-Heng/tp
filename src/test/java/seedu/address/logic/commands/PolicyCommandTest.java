package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Policy;
import seedu.address.testutil.PersonBuilder;


public class PolicyCommandTest {

    private static final String POLICY_STUB = "Medishield";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withPolicy(POLICY_STUB).build();

        PolicyCommand policyCommand = new PolicyCommand(INDEX_FIRST_PERSON, new Policy(editedPerson.getPolicy().value));

        String expectedMessage = String.format(PolicyCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(policyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteRemarkUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withPolicy("").build();

        PolicyCommand policyCommand = new PolicyCommand(INDEX_FIRST_PERSON,
                new Policy(editedPerson.getPolicy().toString()));

        String expectedMessage = String.format(PolicyCommand.MESSAGE_DELETE_REMARK_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(policyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outofBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        PolicyCommand policyCommand = new PolicyCommand(outofBoundIndex, new Policy(VALID_POLICY_BOB));

        assertCommandFailure(policyCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundINdex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        PolicyCommand policyCommand = new PolicyCommand(outOfBoundIndex, new Policy(VALID_POLICY_BOB));
        assertCommandFailure(policyCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final PolicyCommand standardCommand = new PolicyCommand(INDEX_FIRST_PERSON,
                new Policy(VALID_POLICY_AMY));

        //same values -> returns true
        PolicyCommand commandWithSameValues = new PolicyCommand(INDEX_FIRST_PERSON,
                new Policy(VALID_POLICY_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        //different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new PolicyCommand(INDEX_SECOND_PERSON,
                new Policy(VALID_POLICY_AMY))));

        // different policy -> returns false
        assertFalse(standardCommand.equals(new PolicyCommand(INDEX_FIRST_PERSON,
                new Policy(VALID_POLICY_BOB))));
    }


    @Test
    void execute() {
    }

    @Test
    void testEquals() {
    }
}

