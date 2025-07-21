import extensions.UIExtension;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ClickHousePage;
import pages.MainPage;
import popup.TeacherAvatarPopup;
import java.util.List;

@ExtendWith(UIExtension.class)
public class HomeworkTest {
  @Inject
  private MainPage mainPage;

  @Inject
  private ClickHousePage clickHousePage;

  @Inject
  private TeacherAvatarPopup teacherAvatarPopup;

  @Test
  public void clickTest() {
    clickHousePage.open();
    clickHousePage.checkTeachers();
    List<String> teachers = clickHousePage.scrollTeachers();
    clickHousePage.clickTeacher();
    String teacherName = teacherAvatarPopup.getTeacherName();
    Assertions.assertTrue(teachers.get(0).contains(teacherName), "the right teacher page was not opened");
    teacherAvatarPopup.clickLeftIcon();
    String teacherName1 = teacherAvatarPopup.getTeacherName();
    Assertions.assertNotEquals(teacherName, teacherName1, "the left icon was not clicked");
    teacherAvatarPopup.clickRightIcon();
    String teacherName2 = teacherAvatarPopup.getTeacherName();
    Assertions.assertNotEquals(teacherName, teacherName2, "the right icon was not clicked");
  }
}
