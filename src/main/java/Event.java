import java.util.Arrays;
import java.util.ArrayList;

public class Event {
  private Integer mAttendees;
  private ArrayList<Meal> mMeals;

  public Event() {
    mAttendees = 0;
    mMeals = new ArrayList<Meal>();
  }

  public Integer getNumberOfAttendees() {
    return mAttendees;
  }

  public void setNumberOfAttendees(Integer attendees) {
    mAttendees = attendees;
  }

  public ArrayList<String> getMealNames() {
    ArrayList<String> names = new ArrayList<String>();
    for (Meal meal : mMeals) {
      names.add(meal.getMealName());
    }
    return names;
  }
  public void addMealById(Integer id) {
    Meal meal = new Meal();
    meal.setMealId(id);
    mMeals.add(meal);
  }

  public void addMealByName(String name) {
    Meal meal = new Meal();
    meal.setMealByName(name);
    mMeals.add(meal);
  }

  public Double getTotalEventCost() {
    Double baseCost = mAttendees * 5.00;
    Double mealCost = 0.00;
    for (int i = 0; i < mMeals.size(); i++) {
      mealCost += mAttendees * mMeals.get(i).getMealCost();
    }
    return baseCost + mealCost;
  }

  public ArrayList<Double> getMealCosts() {
    ArrayList<Double> mealCosts = new ArrayList<Double>();
    for (Meal meal : mMeals) {
      mealCosts.add(meal.getMealCost());
    }
    return mealCosts;
  }

}
