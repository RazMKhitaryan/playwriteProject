package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.microsoft.playwright.Page;
import pages.*;
import popup.LoginPopup;
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

  @Provides
  @Singleton
  public CompanyServices getCompanyServices() {
    return new CompanyServices(page);
  }

  @Provides
  @Singleton
  public CustomCourses getCustomCourses() {
    return new CustomCourses(page);
  }

  @Provides
  @Singleton
  public SubscriptionPage subscriptionPage() {
    return new SubscriptionPage(page);
  }

  @Provides
  @Singleton
  public LoginPopup getLoginPopup() {
    return new LoginPopup(page);
  }
}