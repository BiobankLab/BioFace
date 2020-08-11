package com.bee2code.bioimporter.model.json;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * formalin fixed parafin embedded tissue
 */
@ApiModel(description = "formalin fixed parafin embedded tissue")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class SampleMaterialFFPE {
	@JsonProperty("method")
	private String method = null;

	@JsonProperty("kind")
	private String kind = null;

	@JsonProperty("source")
	private String source = null;

	public SampleMaterialFFPE method(String method) {
		this.method = method;
		return this;
	}

	/**
	 * (sample preparation, fixation, embedding, staining)
	 * @return method
	**/
	@ApiModelProperty(value = "(sample preparation, fixation, embedding, staining)")

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public SampleMaterialFFPE kind(String kind) {
		this.kind = kind;
		return this;
	}

	/**
	 * (healthy, pathologic)
	 * @return kind
	**/
	@ApiModelProperty(value = "(healthy, pathologic)")

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public SampleMaterialFFPE source(String source) {
		this.source = source;
		return this;
	}

	/**
	 * (liver, brain, embrio)
	 * @return source
	**/
	@ApiModelProperty(value = "(liver, brain, embrio)")

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SampleMaterialFFPE sampleMaterialFFPE = (SampleMaterialFFPE) o;
		return Objects.equals(this.method, sampleMaterialFFPE.method)
				&& Objects.equals(this.kind, sampleMaterialFFPE.kind)
				&& Objects.equals(this.source, sampleMaterialFFPE.source);
	}

	@Override
	public int hashCode() {
		return Objects.hash(method, kind, source);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SampleMaterialFFPE {\n");

		sb.append("    method: ").append(toIndentedString(method)).append("\n");
		sb.append("    kind: ").append(toIndentedString(kind)).append("\n");
		sb.append("    source: ").append(toIndentedString(source)).append("\n");
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
