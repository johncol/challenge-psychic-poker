package poker.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlainTextFileReader implements Reader<List<String>> {

  private final String filePath;

  @Override
  public List<String> read() {
    try {
      return Files.readAllLines(Paths.get(filePath));
    } catch (IOException e) {
      System.err.println(e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
