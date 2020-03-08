package ic.doc;

public class ForecastInfo {
  private final int temperature;
  private final String summary;

  public ForecastInfo (int temperature, String summary) {
    this.temperature = temperature;
    this.summary = summary;
  }

  public int temperature() {
    return this.temperature;
  }

  public String summary() {
    return this.summary;
  }
}
