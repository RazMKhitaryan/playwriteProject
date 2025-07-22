package pages;

import annotations.Path;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.Position;
import com.microsoft.playwright.options.WaitForSelectorState;

public abstract class AbsBasePage<T> {
  protected Page page;
  private String baseUrl = System.getProperty("base.url", "https://otus.ru");

  public AbsBasePage(Page page) {
    this.page = page;
  }

  public T open() {
    page.navigate(this.baseUrl + getPath());
    addCookie();
    page.reload();
    page.waitForLoadState();
    return (T) this;
  }

  private String getPath() {
    Class<T> clazz = (Class<T>) getClass();
    if (clazz.isAnnotationPresent(Path.class)) {
      Path path = clazz.getDeclaredAnnotation(Path.class);
      return path.value();
    }
    return "";
  }

  public void addCookie() {
    page.evaluate("() => localStorage.getItem('cookieAccess')");
  }

  public void waitForVisibility(Locator locator) {
    locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
  }

  public String getText(Locator locator) {
    locator.scrollIntoViewIfNeeded();
    waitForVisibility(locator);
    return locator.textContent();
  }

  public void click(Locator locator) {
    locator.scrollIntoViewIfNeeded();
    waitForVisibility(locator);
    locator.click();
  }

  public boolean isLocatorChecked(Locator locator) {
    locator.scrollIntoViewIfNeeded();
    waitForVisibility(locator);
    return locator.isChecked();
  }

  public static void moveSliderHandleOnly(Locator slider, int targetValue) {
    BoundingBox sliderBox = slider.boundingBox();
    if (sliderBox == null) throw new RuntimeException("Cannot get slider bounding box");

    double offsetX = 0;
    double offsetY = sliderBox.height / 2;

    slider.dragTo(slider, new Locator.DragToOptions()
        .setTargetPosition(new Position((int) offsetX, (int) offsetY)));
  }

}