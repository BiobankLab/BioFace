package com.bee2code.bioimporter.model.json;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * SampleMaterialNucleicAcid
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-31T11:47:30.450Z")

public class SampleMaterialNucleicAcid {
	/**
	 * Gets or Sets kind
	 */
	public enum KindEnum {
	DNA("DNA"),

	RNA("RNA"),

	MIRNA("miRNA"),

	OTHER("other");

		private String value;

		KindEnum(String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static KindEnum fromValue(String text) {
			for (KindEnum b : KindEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("kind")
	private KindEnum kind = null;

	@JsonProperty("source")
	private String source = null;

	@JsonProperty("method")
	private String method = null;

	public SampleMaterialNucleicAcid kind(KindEnum kind) {
		this.kind = kind;
		return this;
	}

	/**
	 * Get kind
	 * @return kind
	**/
	@ApiModelProperty(value = "")

	public KindEnum getKind() {
		return kind;
	}

	public void setKind(KindEnum kind) {
		this.kind = kind;
	}

	public SampleMaterialNucleicAcid source(String source) {
		this.source = source;
		return this;
	}

	/**
	 * (isolated from blood, tissue, FFPE)
	 * @return source
	**/
	@ApiModelProperty(value = "(isolated from blood, tissue, FFPE)")

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public SampleMaterialNucleicAcid method(String method) {
		this.method = method;
		return this;
	}

	/**
	 * method of isolation
	 * @return method
	**/
	@ApiModelProperty(value = "method of isolation")

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
		SampleMaterialNucleicAcid sampleMaterialNucleicAcid = (SampleMaterialNucleicAcid) o;
		return Objects.equals(this.kind, sampleMaterialNucleicAcid.kind)
				&& Objects.equals(this.source, sampleMaterialNucleicAcid.source)
				&& Objects.equals(this.method, sampleMaterialNucleicAcid.method);
	}

	@Override
	public int hashCode() {
		return Objects.hash(kind, source, method);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SampleMaterialNucleicAcid {\n");

		sb.append("    kind: ").append(toIndentedString(kind)).append("\n");
		sb.append("    source: ").append(toIndentedString(source)).append("\n");
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
