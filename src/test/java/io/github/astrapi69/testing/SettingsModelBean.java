package io.github.astrapi69.testing;

public class SettingsModelBean {
	private Integer xAxis = 1;
	private Integer yAxis = 1;
	private Integer intervalOfSeconds = 60;
	private Integer intervalOfMouseMovementsCheckInSeconds = 30;

	public SettingsModelBean(Integer xAxis, Integer yAxis,
							 Integer intervalOfSeconds, Integer intervalOfMouseMovementsCheckInSeconds) {
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.intervalOfSeconds = intervalOfSeconds;
		this.intervalOfMouseMovementsCheckInSeconds = intervalOfMouseMovementsCheckInSeconds;
	}

	protected SettingsModelBean(SettingsModelBeanBuilder<?, ?> b) {
		this.xAxis = b.xAxis$value;
		this.yAxis = b.yAxis$value;
		this.intervalOfSeconds = b.intervalOfSeconds$value;
		this.intervalOfMouseMovementsCheckInSeconds = b.intervalOfMouseMovementsCheckInSeconds$value;
	}

	public SettingsModelBean() {
	}

	public static SettingsModelBeanBuilder<?, ?> builder() {
		return new SettingsModelBeanBuilderImpl();
	}

	public Integer getXAxis() {
		return this.xAxis;
	}

	public Integer getYAxis() {
		return this.yAxis;
	}

	public Integer getIntervalOfSeconds() {
		return this.intervalOfSeconds;
	}

	public Integer getIntervalOfMouseMovementsCheckInSeconds() {
		return this.intervalOfMouseMovementsCheckInSeconds;
	}

	public void setXAxis(Integer xAxis) {
		this.xAxis = xAxis;
	}

	public void setYAxis(Integer yAxis) {
		this.yAxis = yAxis;
	}

	public void setIntervalOfSeconds(Integer intervalOfSeconds) {
		this.intervalOfSeconds = intervalOfSeconds;
	}

	public void setIntervalOfMouseMovementsCheckInSeconds(Integer intervalOfMouseMovementsCheckInSeconds) {
		this.intervalOfMouseMovementsCheckInSeconds = intervalOfMouseMovementsCheckInSeconds;
	}

	public boolean equals(final Object o) {
		if (o == this) return true;
		if (!(o instanceof SettingsModelBean)) return false;
		final SettingsModelBean other = (SettingsModelBean) o;
		if (!other.canEqual((Object) this)) return false;
		final Object this$xAxis = this.getXAxis();
		final Object other$xAxis = other.getXAxis();
		if (this$xAxis == null ? other$xAxis != null : !this$xAxis.equals(other$xAxis)) return false;
		final Object this$yAxis = this.getYAxis();
		final Object other$yAxis = other.getYAxis();
		if (this$yAxis == null ? other$yAxis != null : !this$yAxis.equals(other$yAxis)) return false;
		final Object this$intervalOfSeconds = this.getIntervalOfSeconds();
		final Object other$intervalOfSeconds = other.getIntervalOfSeconds();
		if (this$intervalOfSeconds == null ? other$intervalOfSeconds != null : !this$intervalOfSeconds.equals(other$intervalOfSeconds))
			return false;
		final Object this$intervalOfMouseMovementsCheckInSeconds = this.getIntervalOfMouseMovementsCheckInSeconds();
		final Object other$intervalOfMouseMovementsCheckInSeconds = other.getIntervalOfMouseMovementsCheckInSeconds();
		if (this$intervalOfMouseMovementsCheckInSeconds == null ? other$intervalOfMouseMovementsCheckInSeconds != null : !this$intervalOfMouseMovementsCheckInSeconds.equals(other$intervalOfMouseMovementsCheckInSeconds))
			return false;
		return true;
	}

	protected boolean canEqual(final Object other) {
		return other instanceof SettingsModelBean;
	}

	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $xAxis = this.getXAxis();
		result = result * PRIME + ($xAxis == null ? 43 : $xAxis.hashCode());
		final Object $yAxis = this.getYAxis();
		result = result * PRIME + ($yAxis == null ? 43 : $yAxis.hashCode());
		final Object $intervalOfSeconds = this.getIntervalOfSeconds();
		result = result * PRIME + ($intervalOfSeconds == null ? 43 : $intervalOfSeconds.hashCode());
		final Object $intervalOfMouseMovementsCheckInSeconds = this.getIntervalOfMouseMovementsCheckInSeconds();
		result = result * PRIME + ($intervalOfMouseMovementsCheckInSeconds == null ? 43 : $intervalOfMouseMovementsCheckInSeconds.hashCode());
		return result;
	}

	public String toString() {
		return "SettingsModelBean(xAxis=" + this.getXAxis() + ", yAxis=" + this.getYAxis() + ", intervalOfSeconds=" + this.getIntervalOfSeconds() + ", intervalOfMouseMovementsCheckInSeconds=" + this.getIntervalOfMouseMovementsCheckInSeconds() + ")";
	}

	public SettingsModelBeanBuilder<?, ?> toBuilder() {
		return new SettingsModelBeanBuilderImpl().$fillValuesFrom(this);
	}

	public static abstract class SettingsModelBeanBuilder<C extends SettingsModelBean, B extends SettingsModelBeanBuilder<C, B>> {
		private Integer xAxis$value;
		private boolean xAxis$set;
		private Integer yAxis$value;
		private boolean yAxis$set;
		private Integer intervalOfSeconds$value;
		private boolean intervalOfSeconds$set;
		private Integer intervalOfMouseMovementsCheckInSeconds$value;
		private boolean intervalOfMouseMovementsCheckInSeconds$set;

		private static void $fillValuesFromInstanceIntoBuilder(SettingsModelBean instance, SettingsModelBeanBuilder<?, ?> b) {
			b.xAxis(instance.xAxis);
			b.yAxis(instance.yAxis);
			b.intervalOfSeconds(instance.intervalOfSeconds);
			b.intervalOfMouseMovementsCheckInSeconds(instance.intervalOfMouseMovementsCheckInSeconds);
		}

		public B xAxis(Integer xAxis) {
			this.xAxis$value = xAxis;
			this.xAxis$set = true;
			return self();
		}

		public B yAxis(Integer yAxis) {
			this.yAxis$value = yAxis;
			this.yAxis$set = true;
			return self();
		}

		public B intervalOfSeconds(Integer intervalOfSeconds) {
			this.intervalOfSeconds$value = intervalOfSeconds;
			this.intervalOfSeconds$set = true;
			return self();
		}

		public B intervalOfMouseMovementsCheckInSeconds(Integer intervalOfMouseMovementsCheckInSeconds) {
			this.intervalOfMouseMovementsCheckInSeconds$value = intervalOfMouseMovementsCheckInSeconds;
			this.intervalOfMouseMovementsCheckInSeconds$set = true;
			return self();
		}

		protected B $fillValuesFrom(C instance) {
			SettingsModelBeanBuilder.$fillValuesFromInstanceIntoBuilder(instance, this);
			return self();
		}

		protected abstract B self();

		public abstract C build();

		public String toString() {
			return "SettingsModelBean.SettingsModelBeanBuilder(xAxis$value=" + this.xAxis$value + ", yAxis$value=" + this.yAxis$value + ", intervalOfSeconds$value=" + this.intervalOfSeconds$value + ", intervalOfMouseMovementsCheckInSeconds$value=" + this.intervalOfMouseMovementsCheckInSeconds$value + ")";
		}
	}

	private static final class SettingsModelBeanBuilderImpl extends SettingsModelBeanBuilder<SettingsModelBean, SettingsModelBeanBuilderImpl> {
		private SettingsModelBeanBuilderImpl() {
		}

		protected SettingsModelBeanBuilderImpl self() {
			return this;
		}

		public SettingsModelBean build() {
			return new SettingsModelBean(this);
		}
	}
}
