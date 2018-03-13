package poker.io;

public interface Writer<T> {

  /**
   * Write the result T in any media
   */
  void write(T t);

}
