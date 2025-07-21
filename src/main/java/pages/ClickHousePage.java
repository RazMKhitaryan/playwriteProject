package pages;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import java.util.List;

@Path("/lessons/clickhouse")
public class ClickHousePage extends AbsBasePage<ClickHousePage> {
  Locator teacher1 = page.locator("//*[@id=\"__next\"]/div[1]/main/div/div[9]/section/div/div[2]/div[1]/div/div/div[1]/p[1]");
  Locator teacher4 = page.locator("//*[@id=\"__next\"]/div[1]/main/div/div[9]/section/div/div[2]/div[1]/div/div/div[4]/p[1]");

  public ClickHousePage(Page page) {
    super(page);
  }

  public void checkTeachers() {
    teacher1.waitFor();
    teacher1.scrollIntoViewIfNeeded();
    assertTrue(teacher1.isVisible(), "Teacher is not visible");
  }

  public List<String> scrollTeachers() {
    teacher1.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    String sourceTeacher = teacher1.textContent();
    String toTeacher = teacher4.textContent();
    teacher1.dragTo(teacher4);
    assertNotEquals(sourceTeacher, teacher1.textContent(), "the drag and drop were not worked");
    return List.of(sourceTeacher, toTeacher);
  }

  public void clickTeacher() {
    teacher1.click();
  }
}
