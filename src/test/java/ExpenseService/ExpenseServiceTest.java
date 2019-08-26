package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
        Project p= new Project(ProjectType.INTERNAL,"test");
        // when
        ExpenseType ex = ExpenseService.getExpenseCodeByProjectTypeAndName(p);
        // then
        Assertions.assertEquals(ExpenseType.INTERNAL_PROJECT_EXPENSE,ex);

    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
        Project p= new Project(ProjectType.EXTERNAL,"Project A");
        // when
        ExpenseType ex = ExpenseService.getExpenseCodeByProjectTypeAndName(p);
        // then
        Assertions.assertEquals(ExpenseType.EXPENSE_TYPE_A,ex);
    }
///
    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
        Project p= new Project(ProjectType.EXTERNAL,"otername");
        // when
        ExpenseType ex = ExpenseService.getExpenseCodeByProjectTypeAndName(p);
        // then
        Assertions.assertEquals(ExpenseType.OTHER_EXPENSE,ex);
    }
//
    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        // given
        Project p= new Project(ProjectType.EXTERNAL,"Project A");
        // when
        UnexpectedProjectTypeException exception = Assertions.assertThrows(UnexpectedProjectTypeException.class,()->{
            ExpenseService.getExpenseCodeByProjectTypeAndName(p);
        });
        // then
        Assertions.assertEquals("You enter invalid project type",exception.getMessage());
    }
}