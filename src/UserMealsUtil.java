

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );


        List<UserMealWithExcess> userMealWithExcesses = new ArrayList<>();

        int startPosition = 0;
        int lastPosition = 0;

        for (int i = 0; i < meals.size(); ) {
            LocalDate localDate = meals.get(i).getDateTime().toLocalDate();
            int sumOfDayCalories = meals.get(i).getCalories();


            for (int j = i + 1; j <= meals.size(); j++) {
                if (j == meals.size()) {
                    lastPosition = meals.size();
                    break;
                }
                if (meals.get(j).getDateTime().toLocalDate().isEqual(localDate)) {
                    sumOfDayCalories += meals.get(j).getCalories();
                    lastPosition++;
                }
                if (!meals.get(j).getDateTime().toLocalDate().isEqual(localDate)) {
                    lastPosition = j;
                    break;
                }
            }

            for (int m = startPosition; m < lastPosition; m++) {
                userMealWithExcesses.add(new UserMealWithExcess(meals.get(m).getDateTime(), meals.get(m).getDescription(), meals.get(m).getCalories(), sumOfDayCalories > 2000 ? true : false));
                startPosition++;
            }
            i = lastPosition;
        }
    }
}
