package ua.bieliaiev.jakartajsftdd.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import ua.bieliaiev.jakartajsftdd.data.FunctionAdapter;
import ua.bieliaiev.jakartajsftdd.data.FunctionParameters;
import ua.bieliaiev.jakartajsftdd.model.DoublePoint;
import ua.bieliaiev.jakartajsftdd.model.FunctionResult;
import ua.bieliaiev.jakartajsftdd.model.Main;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

@Named
@RequestScoped
@Getter
@Setter
public class FunctionBean {
	private FunctionParameters parameters = new FunctionParameters();
	private LineChartModel graphic;
	private FunctionResult result;
	private Main main = new Main();
	private int minIndex;
	private int maxIndex;

	public String calculate() {
		try {
			result = main.getFunctionValuesByDiapason(
					parameters.getStep(),
					parameters.getStart(),
					parameters.getEnd());
			minIndex = main.getMinY(result);
			maxIndex = main.getMaxY(result);
			initGraphic();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return "result";
	}

	private void initGraphic() {
		/* Line graph plot with primefaces. Minor customization details
		 had been taken from documentation example. */
		graphic = new LineChartModel();
		ChartData data = new ChartData();

		LineChartDataSet dataSet = new LineChartDataSet();
		List<Object> values = Arrays.stream(result.y()).mapToObj(e -> (Object) e).toList();
		dataSet.setData(values);
		dataSet.setLabel("Function result");
		dataSet.setBackgroundColor("rgb(75, 192, 192)");
		dataSet.setTension(0.1);
		data.addChartDataSet(dataSet);

		List<String> labels = Arrays.stream(result.x()).mapToObj(e -> new BigDecimal(e).setScale(4, RoundingMode.HALF_UP).toString()).toList();
		data.setLabels(labels);

		graphic.setData(data);
	}

	public List<DoublePoint> getPoints() {
		return new FunctionAdapter(result).getPointList();
	}

	public double getMinX() {
		return result.x()[minIndex];
	}
	public double getMaxX() {
		return result.x()[maxIndex];
	}
	public double getMinY() {
		return result.y()[minIndex];
	}
	public double getMaxY() {
		return result.y()[maxIndex];
	}
}
