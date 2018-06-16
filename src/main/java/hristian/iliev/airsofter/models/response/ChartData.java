package hristian.iliev.airsofter.models.response;

import java.util.List;

public class ChartData {
  private List<ChartEntry> chartData;

  public ChartData() {

  }

  public ChartData(List<ChartEntry> chartData) {
    this.setChartData(chartData);
  }

  public List<ChartEntry> getChartData() {
    return chartData;
  }

  public void setChartData(List<ChartEntry> chartData) {
    this.chartData = chartData;
  }

  @Override
  public String toString() {
    return "ChartData{" +
            "chartData=" + chartData +
            '}';
  }
}
