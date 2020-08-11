package com.bee2code.bioimporter.model.json;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * SampleMaterialTissue
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class SampleMaterialTissue {
	@JsonProperty("source")
	private String source = null;

	@JsonProperty("kind")
	private String kind = null;

	@JsonProperty("method")
	private String method = null;

	public SampleMaterialTissue source(String source) {
		this.source = source;
		return this;
	}

	/**
	 * type of tissue (liver, muscle, brain, cells, other)
	 * @return source
	**/
	@ApiModelProperty(value = "type of tissue (liver, muscle, brain, cells, other)")

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public SampleMaterialTissue kind(String kind) {
		this.kind = kind;
		return this;
	}

	/**
	 * healthy or pathologic
	 * @return kind
	**/
	@ApiModelProperty(value = "healthy or pathologic")

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public SampleMaterialTissue method(String method) {
		this.method = method;
		return this;
	}

	/**
	 * method of collection (biopsy, surgery, other)
	 * @return method
	**/
	@ApiModelProperty(value = "method of collection (biopsy, surgery, other)")

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SampleMaterialTissue sampleMaterialTissue = (SampleMaterialTissue) o;
		return Objects.equals(this.source, sampleMaterialTissue.source)
				&& Objects.equals(this.kind, sampleMaterialTissue.kind)
				&& Objects.equals(this.method, sampleMaterialTissue.method);
	}

	@Override
	public int hashCode() {
		return Objects.hash(source, kind, method);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SampleMaterialTissue {\n");

		sb.append("    source: ").append(toIndentedString(source)).append("\n");
		sb.append("    kind: ").append(toIndentedString(kind)).append("\n");
		sb.append("    method: ").append(toIndentedString(method)).append("\n");
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
