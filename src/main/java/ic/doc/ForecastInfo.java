package ic.doc;

public class ForecastInfo {
  private final int temperature;
  private final String summary;

  public ForecastInfo (int temperature, String summary) {
    this.temperature = temperature;
    this.summary = summary;
  }

  public Integer temperature() {
    return this.temperature;
  }

  public String summary() {
    return this.summary;
  }
}
