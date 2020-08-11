package com.bee2code.bioimporter.model.json;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * CIELAB scale
 */
@ApiModel(description = "CIELAB scale")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class CIELAB {
	@JsonProperty("L")
	private BigDecimal L = null;

	@JsonProperty("a")
	private BigDecimal a = null;

	@JsonProperty("b")
	private BigDecimal b = null;

	public CIELAB L(BigDecimal L) {
		this.L = L;
		return this;
	}

	/**
	 * brightness, lightness
	 * @return L
	**/
	@ApiModelProperty(value = "brightness, lightness")

	@Valid

	public BigDecimal getL() {
		return L;
	}

	public void setL(BigDecimal L) {
		this.L = L;
	}

	public CIELAB a(BigDecimal a) {
		this.a = a;
		return this;
	}

	/**
	 * green to magenta colour
	 * @return a
	**/
	@ApiModelProperty(value = "green to magenta colour")

	@Valid

	public BigDecimal getA() {
		return a;
	}

	public void setA(BigDecimal a) {
		this.a = a;
	}

	public CIELAB b(BigDecimal b) {
		this.b = b;
		return this;
	}

	/**
	 * blue to yellow colour
	 * @return b
	**/
	@ApiModelProperty(value = "blue to yellow colour")

	@Valid

	public BigDecimal getB() {
		return b;
	}

	public void setB(BigDecimal b) {
		this.b = b;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CIELAB CIELAB = (CIELAB) o;
		return Objects.equals(this.L, CIELAB.L) && Objects.equals(this.a, CIELAB.a) && Objects.equals(this.b, CIELAB.b);
	}

	@Override
	public int hashCode() {
		return Objects.hash(L, a, b);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class CIELAB {\n");

		sb.append("    L: ").append(toIndentedString(L)).append("\n");
		sb.append("    a: ").append(toIndentedString(a)).append("\n");
		sb.append("    b: ").append(toIndentedString(b)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
