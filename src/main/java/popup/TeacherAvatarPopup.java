package popup;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import pages.AbsBasePage;

public class TeacherAvatarPopup extends AbsBasePage<TeacherAvatarPopup> {
  Locator xButton = page.locator("//*[@id=\"__PORTAL__\"]/div/div/div/button");
  Locator leftIcon = page.locator("//*[@id=\"__PORTAL__\"]/div/div/div/div/div[2]/button[1]");
  Locator rightIcon = page.locator("//*[@id=\"__PORTAL__\"]/div/div/div/div/div[2]/button[2]");
  Locator teacherName = page.locator("//*[@id=\"__PORTAL__\"]/div/div/div/div/div[1]/div/div/div[3]/div[2]/h3");

  public TeacherAvatarPopup(Page page) {
    super(page);
  }

  public void clickXButton() {
    xButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    xButton.click();
  }

  public void clickLeftIcon() {
    leftIcon.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    leftIcon.click();
  }

  public void clickRightIcon() {
    rightIcon.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
    rightIcon.click();
  }

  public String getTeacherName() {
    teacherName.waitFor();
    return teacherName.textContent();
  }
}
