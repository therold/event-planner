import org.junit.*;
import static org.junit.Assert.*;

public class MealTest {

  @Test
  public void meal_returnsAMeal() {
    Meal testMeal = new Meal();
    assertEquals(true, testMeal instanceof Meal);
  }

  @Test
  public void getMealId_returnsMealId_isInteger() {
    Meal testMeal = new Meal();
    assertEquals(true, testMeal.getMealId() instanceof Integer);
  }

  @Test
  public void setMealId_returnsMealId_2() {
    Meal testMeal = new Meal();
    testMeal.setMealId(2);
    Integer expected = 2;
    assertEquals(expected, testMeal.getMealId());
  }

  @Test
  public void getMealName_returnsMealName_isString() {
    Meal testMeal = new Meal();
    assertEquals(true, testMeal.getMealName() instanceof String);
  }

  @Test
  public void setMealByName_returnsMealId_NoMeal() {
    Meal testMeal = new Meal();
    testMeal.setMealByName("No Meal");
    String expected = "No Meal";
    assertEquals(expected, testMeal.getMealName());
  }

  @Test
  public void getMealCost_returnsMealCost_000() {
    Meal testMeal = new Meal();
    testMeal.setMealByName("No Meal");
    Double expected = 0.00;
    assertEquals(expected, testMeal.getMealCost());
  }

  @Test
  public void getAllMealNames_returnsStringArray_isStringArray() {
    Meal testMeal = new Meal();
    assertEquals(true, testMeal.getAllMealNames() instanceof String[]);
  }

}
