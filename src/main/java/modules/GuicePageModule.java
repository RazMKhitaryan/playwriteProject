package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import pages.*;
import popup.LoginPopup;
import popup.TeacherAvatarPopup;

public class GuicePageModule extends AbstractModule {

  @Provides
  @Singleton
  public ClickHousePage getClickHousePage() {
    return new ClickHousePage();
  }

  @Provides
  @Singleton
  private TeacherAvatarPopup getTeacherAvatarPopup() {
    return new TeacherAvatarPopup();
  }

  @Provides
  @Singleton
  public CoursesPage getCoursesPage() {
    return new CoursesPage();
  }

  @Provides
  @Singleton
  public CompanyServices getCompanyServices() {
    return new CompanyServices();
  }

  @Provides
  @Singleton
  public CustomCourses getCustomCourses() {
    return new CustomCourses();
  }

  @Provides
  @Singleton
  public SubscriptionPage subscriptionPage() {
    return new SubscriptionPage();
  }

  @Provides
  @Singleton
  public LoginPopup getLoginPopup() {
    return new LoginPopup();
  }
}