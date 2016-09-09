import org.junit.*;
import static org.junit.Assert.*;

public class EventTest {

  @Test
  public void event_returnsAnEvent() {
    Event testEvent = new Event();
    assertEquals(true, testEvent instanceof Event);
  }

  @Test
  public void getNumberOfAttendees_returnsNumberOfAttendees_isInteger() {
    Event testEvent = new Event();
    assertEquals(true, testEvent.getNumberOfAttendees() instanceof Integer);
  }

  @Test
  public void setNumberOfAttendees_returnsNumberOfAttendees_2() {
    Event testEvent = new Event();
    testEvent.setNumberOfAttendees(2);
    Integer expected = 2;
    assertEquals(expected, testEvent.getNumberOfAttendees());
  }

  @Test
  public void getMealCostsPerPerson_returnsMealsCostPerPerson_000() {
    Event testEvent = new Event();
    testEvent.addMealById(0);
    Double expected = 0.00;
    assertEquals(expected, testEvent.getMealCosts().get(0));
  }

  @Test
  public void getTotalEventCost_returnsCostOfEvent_isDouble() {
    Event testEvent = new Event();
    assertEquals(true, testEvent.getTotalEventCost() instanceof Double);
  }

}
