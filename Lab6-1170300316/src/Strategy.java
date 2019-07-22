public interface Strategy {

  /**
   * 选择 ladder 二维数组的行.
   *
   * @param monkey is the monkey to assign (usually the newly added monkey).
   * @param ladder 是全局使用的.
   * @return true if an appropriate line is found while false if not a single appropriate line is
   *     found.
   */
  public boolean choose(Monkey monkey, Ladder ladder);
}
