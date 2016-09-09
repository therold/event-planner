import java.util.Arrays;

public class Meal {
  private Integer mId;
  private String[] mMealNames = { "No Meal", "Breakfast", "Lunch", "Light Snack", "Light Dinner", "Full Dinner" };
  private Double[] mMealCosts = { 0.00, 10.00, 12.00, 4.00, 8.00, 15.00 };

  public Meal() {
    mId = 0;
  }

  public Integer getMealId() {
      return mId;
  }

  public void setMealId(Integer id) {
    mId = id;
  }

  public void setMealByName(String name) {
    if (Arrays.asList(mMealNames).indexOf(name) >= 0) {
      Integer id = Arrays.asList(mMealNames).indexOf(name);
    }
  }

  public String getMealName() {
    return mMealNames[mId];
  }

  public Double getMealCost() {
    return mMealCosts[mId];
  }

  public String[] getAllMealNames() {
    return mMealNames;
  }

  public Double[] getAllMealCosts() {
    return mMealCosts;
  }

}
