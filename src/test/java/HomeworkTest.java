import extensions.UIExtension;
import jakarta.inject.Inject;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ClickHousePage;
import pages.CoursesPage;
import popup.TeacherAvatarPopup;
import java.util.List;

@ExtendWith(UIExtension.class)
public class HomeworkTest {

  @Inject
  private ClickHousePage clickHousePage;

  @Inject
  private CoursesPage coursesPage;

  @Inject
  private TeacherAvatarPopup teacherAvatarPopup;

  @Test
  public void teachersCarouselVerification() {
    SoftAssertions softAssertions = new SoftAssertions();
    clickHousePage.open();
    clickHousePage.checkTeachers();
    List<String> teachers = clickHousePage.scrollTeachers();
    clickHousePage.clickTeacher();
    String teacherName = teacherAvatarPopup.getTeacherName(2);
    softAssertions.assertThat(teachers.get(1))
        .as("The right teacher page was not opened")
        .contains(teacherName);
    teacherAvatarPopup.clickLeftIcon();
    String teacherName1 = teacherAvatarPopup.getTeacherName(0);
    softAssertions.assertThat(teacherName1)
        .as("The left icon was not clicked")
        .isNotEqualTo(teacherName);
    teacherAvatarPopup.clickRightIcon();
    String teacherName2 = teacherAvatarPopup.getTeacherName(0);
    softAssertions.assertThat(teacherName2)
        .as("The right icon was not clicked")
        .isNotEqualTo(teacherName);
    softAssertions.assertAll();

  }

  @Test
  public void coursesFilterVerification() {
    coursesPage.open();
    SoftAssertions softAssertions = new SoftAssertions();
    softAssertions.assertThat(coursesPage.isAllCoursesCheckboxSelected())
        .as("The 'All Courses' checkbox should be selected")
        .isTrue();
    softAssertions.assertThat(coursesPage.isDifficultyLevelSelected())
        .as("The 'Difficulty level' checkbox should be selected")
        .isTrue();
    coursesPage.moveLeftSlider(3);
    coursesPage.moveRightSlider(10);
    softAssertions.assertAll();

  }
}
