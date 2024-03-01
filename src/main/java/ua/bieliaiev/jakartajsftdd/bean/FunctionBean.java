package ua.bieliaiev.jakartajsftdd.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import ua.bieliaiev.jakartajsftdd.data.FunctionAdapter;
import ua.bieliaiev.jakartajsftdd.data.FunctionParameters;
import ua.bieliaiev.jakartajsftdd.model.DoublePoint;
import ua.bieliaiev.jakartajsftdd.model.FunctionResult;
import ua.bieliaiev.jakartajsftdd.model.Main;

import java.util.List;

@Named
@RequestScoped
@Getter
@Setter
public class FunctionBean {
	private FunctionParameters parameters = new FunctionParameters();
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
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return "result";
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
