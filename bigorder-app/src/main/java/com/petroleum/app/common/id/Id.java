package com.petroleum.app.common.id;

/**
 * 此Id生成的随机，serviceId 不能超过31*31， 极其小的概率在不同服务器下可能重复，同一服务器不会重复
 */
public class Id {

  /**
   * Id实例
   */
  private static Id id = null;
  /**
   * snowflake
   */
  private Snowflake snowflake = null;

  /**
   * 初始化ID
   */
  public static synchronized void newInstance(int serviceId) {
    if (id == null) {
      id = new Id();
      id.snowflake = new Snowflake(serviceId % 31, serviceId / 31);
    }
  }

  public static long nextId() {
    return id.snowflake.nextId();
  }

  public static void main(String[] args) {
    newInstance(1);
    System.out.println(nextId());
  }
}
