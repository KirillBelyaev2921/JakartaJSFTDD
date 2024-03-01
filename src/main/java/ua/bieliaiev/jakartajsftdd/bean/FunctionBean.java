package ua.bieliaiev.jakartajsftdd.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import ua.bieliaiev.jakartajsftdd.data.FunctionParameters;

@Named
@RequestScoped
public class FunctionBean {
	@Getter
	@Setter
	private FunctionParameters parameters;

	public String calculate() {
		return null;
	}
}
