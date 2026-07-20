package pages;

import com.codeborne.selenide.Selenide;
import dto.AddWorkout;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class WorkoutDetailsPage extends BasePage {
    private static final String UPDATE_WORKOUT = ".dropdown-toggle";
    private static final String WORKOUT_TIME = "#WorkoutTime";
    private static final String NAME = "#Name";
    private static final String DESC = "#Desc";
    private static final String PLANNED_WORKOUT = "#PlannedWorkout";
    private static final String PLANNED_DISTANCE = "#PDistance";
    private static final String PLANNED_DISTANCE_TYPE = "#PDistType";
    private static final String PLANNED_DURATION = "#PDuration";
    private static final String DISTANCE = "#Distance";
    private static final String DISTANCE_TYPE = "#DistType";
    private static final String DURATION = "#Duration";
    private static final String PACE_TYPE = "#PaceType";
    private static final String PERCEIVED_EFFORT = "#PerEffort";
    private static final String HR_GOOD = "#hf_good";
    private static final String KCAL = "#kCal";
    private static final String SAVE_LIBRARY = "#SaveLibrary";
    private static final String SAVE_UPDATE_WORKOUT = "#saveButton";

    public WorkoutDetailsPage openPage() {
        Selenide.open("/WorkoutDetails");
        return this;
    }

    public WorkoutDetailsPage isPageOpened() {
        log.info("Opening WorkoutDetails Page");
        $(UPDATE_WORKOUT).shouldBe(clickable);
        return this;
    }

    @Step("Update workout")
    public void clickUpdateWorkout() {
        $(UPDATE_WORKOUT).click();
    }

    @Step("Edit workout")
    public void editWorkout(AddWorkout editWorkout) {
        $(WORKOUT_TIME).setValue(editWorkout.getTimeOfDay());
        $(NAME).setValue(editWorkout.getName());
        $(DESC).setValue(editWorkout.getDescription());
        $(PLANNED_WORKOUT).setSelected(true);
        $(PLANNED_DISTANCE).setValue(editWorkout.getPlannedDistance());
        $(PLANNED_DISTANCE_TYPE).selectOption(editWorkout.getPlannedDistanceType());
        $(PLANNED_DURATION).setValue(editWorkout.getPlannedDuration());
        $(DISTANCE).setValue(editWorkout.getDistance());
        $(DISTANCE_TYPE).selectOption(editWorkout.getDistanceType());
        $(DURATION).setValue(editWorkout.getDuration());
        $(PACE_TYPE).selectOption(editWorkout.getPaceType());
        $(PERCEIVED_EFFORT).selectOption(editWorkout.getPerceivedEffort());
        $(HR_GOOD).click();
        $(KCAL).setValue(editWorkout.getCaloriesBurned());
        $(SAVE_LIBRARY).click();
    }

    @Step("Saving changes")
    public void clickSaveUpdateDWorkout() {
        log.info("Saving changes");
        $(SAVE_UPDATE_WORKOUT).click();
    }
}
