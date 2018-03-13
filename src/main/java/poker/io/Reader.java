package poker.io;

public interface Reader<T> {

  /**
   * Reads a source a transforms it to T
   */
  T read();

}
