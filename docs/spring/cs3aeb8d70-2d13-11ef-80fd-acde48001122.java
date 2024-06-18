package com.huifer.design.template.course;

public class MathCourse extends AbsCourse {

  private boolean hasWork = false;

  public MathCourse(boolean hasWork) {
    this.hasWork = hasWork;
  }

  public static void main(String[] args) {
    AbsCourse course = new MathCourse(true);
    course.some();
  }

  /**
   * 检查作业
   */
  @Override
  protected void checkWork() {
    System.out.println("检查数学作业");
  }

  /**
   * 是否有作业
   */
  @Override
  protected boolean hasWork() {
    return this.hasWork;
  }
}
