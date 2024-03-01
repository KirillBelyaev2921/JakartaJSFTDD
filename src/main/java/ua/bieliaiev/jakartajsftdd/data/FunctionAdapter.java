package ua.bieliaiev.jakartajsftdd.data;

import lombok.Data;
import ua.bieliaiev.jakartajsftdd.model.DoublePoint;
import ua.bieliaiev.jakartajsftdd.model.FunctionResult;

import java.util.ArrayList;
import java.util.List;

@Data
public class FunctionAdapter {
	private List<DoublePoint> pointList;

	public FunctionAdapter(FunctionResult result) {
		pointList = new ArrayList<>(result.y().length);
		for (int i = 0; i < result.y().length; i++) {
			pointList.add(new DoublePoint(result.x()[i], result.y()[i]));
		}
	}
}
