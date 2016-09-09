public class Event {
  private Integer mAttendees;
  private Meal mMeal;

  public Event() {
    mAttendees = 0;
    mMeal = new Meal();
  }

  public Integer getNumberOfAttendees() {
    return mAttendees;
  }

  public void setNumberOfAttendees(Integer attendees) {
    mAttendees = attendees;
  }

  public void setMealId(Integer id) {
    mMeal.setMealId(id);
  }

  public Double getTotalEventCost() {
    Double baseCost = mAttendees * 5.00;
    Double mealCost = mAttendees * getMealCostPerPerson();
    return baseCost + mealCost;
  }

  public Double getMealCostPerPerson() {
    return mMeal.getMealCost();
  }


}
