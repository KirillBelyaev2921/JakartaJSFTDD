package ua.bieliaiev.jakartajsftdd.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FunctionParameters {
	private double start;
	private double end;
	private double step;
}
