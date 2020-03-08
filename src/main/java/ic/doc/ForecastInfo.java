package ic.doc;

/** This class stores forecast information fetched from the third party library */
public class ForecastInfo {
  private final int temperature;
  private final String summary;

  public ForecastInfo(int temperature, String summary) {
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
