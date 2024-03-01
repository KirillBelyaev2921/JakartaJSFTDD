package ua.bieliaiev.jakartajsftdd.model;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		FunctionResult result = getFunctionValuesByDiapason(
				0.002, 0.3, 2.8);
		printMinValue(result);
		printMaxValue(result);
	}

	private static final double A = 2.3;
	public double calculate(double x) {
		double result;

		if (x < 0.2 || x > 2.8) {
			throw new IllegalArgumentException();
		}

		if (x <= 0.3) {
			result = 1.5*A*Math.pow(Math.cos(x), 2);
		} else if (x <= 2.3) {
			result = Math.pow(x - 2, 2) + 6 * A;
		} else {
			result = 3*A*Math.tan(x);
		}
		return result;
	}

	public int getCountOfSteps(double step, double start, double end) {
		return (int)(Math.round((end - start) / step)) + 1;
	}

	public FunctionResult getFunctionValuesByDiapason(double step, double start, double end) {
		int n = getCountOfSteps(step, start, end);
		double[] x = new double[n];
		double[] y = new double[n];

		double current = start;
		for (int i = 0; i < n; i++) {
			x[i] = current;
			y[i] = calculate(current);
			current += step;
		}
		return new FunctionResult(x, y);
	}

	public int getMaxY(FunctionResult functionResult) {
		int max = -1;
		double[] y = functionResult.y();
		for (int i = 0; i < y.length; i++) {
			if (max == -1 || y[max] < y[i]) {
				max = i;
			}
		}
		return max;
	}
	public int getMinY(FunctionResult functionResult) {
		int min = -1;
		double[] y = functionResult.y();
		for (int i = 0; i < y.length; i++) {
			if (min == -1 || y[min] > y[i]) {
				min = i;
			}
		}
		return min;
	}
	public double getSum(FunctionResult functionResult) {
		return Arrays.stream(functionResult.y())
				.sum();
	}
	public double getAvg(FunctionResult functionResult) {
		return Arrays.stream(functionResult.y())
				.average().orElse(0);
	}

	public void printMinValue(FunctionResult functionResult) {
		int index = getMinY(functionResult);
		System.out.printf("""
						Min Value:
							Index - %d,
							x - %f,
							y - %f
						""",
				index, functionResult.x()[index], functionResult.y()[index]);
	}
	public void printMaxValue(FunctionResult functionResult) {
		int index = getMaxY(functionResult);
		System.out.printf("""
						Max Value:
							Index - %d,
							x - %f,
							y - %f
						""",
				index, functionResult.x()[index], functionResult.y()[index]);
	}
}
