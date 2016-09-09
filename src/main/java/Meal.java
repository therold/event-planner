import java.util.Arrays;

public class Meal {
  private static String[] mMealNames = { "No Meal", "Breakfast", "Lunch", "Light Snack", "Light Dinner", "Full Dinner" };
  private static Double[] mMealCosts = { 0.00, 10.00, 12.00, 4.00, 8.00, 15.00 };
  private Integer mId;

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
      mId = Arrays.asList(mMealNames).indexOf(name);
    }
  }

  public String getMealName() {
    return mMealNames[mId];
  }

  public Double getMealCost() {
    return mMealCosts[mId];
  }

  public static String[] getAllMealNames() {
    return mMealNames;
  }

  public static Double[] getAllMealCosts() {
    return mMealCosts;
  }

}
