package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.microsoft.playwright.Page;
import pages.ClickHousePage;
import pages.CoursesPage;
import popup.TeacherAvatarPopup;

public class GuicePageModule extends AbstractModule {
  private Page page;

  public GuicePageModule(Page page) {
    this.page = page;
  }

  @Provides
  @Singleton
  public ClickHousePage getClickHousePage() {
    return new ClickHousePage(page);
  }

  @Provides
  @Singleton
  private TeacherAvatarPopup getTeacherAvatarPopup() {
    return new TeacherAvatarPopup(page);
  }

  @Provides
  @Singleton
  public CoursesPage getCoursesPage() {
    return new CoursesPage(page);
  }
}