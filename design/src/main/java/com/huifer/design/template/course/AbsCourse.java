package com.huifer.design.template.course;

public abstract class AbsCourse {

  protected final void some() {
    if (this.hasWork()) {
      this.checkWork();
    }
  }

  /**
   * 检查作业
   */
  protected void checkWork() {

  }

  /**
   * 是否有作业
   */
  protected boolean hasWork() {
    return true;
  }

}
