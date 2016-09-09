import org.junit.*;
import static org.junit.Assert.*;

public class EventTest {

  @Test
  public void event_returnsAnEvent() {
    Event testEvent = new Event();
    assertEquals(true, testEvent instanceof Event);
  }

}
