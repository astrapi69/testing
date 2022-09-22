package io.github.astrapi69.testing;

public class SettingsModelBean
{
	private Integer xAxis = 1;
	private Integer yAxis = 1;
	private Integer intervalOfSeconds = 60;

	public SettingsModelBean(Integer xAxis, Integer yAxis, Integer intervalOfSeconds)
	{
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.intervalOfSeconds = intervalOfSeconds;
	}

	public SettingsModelBean()
	{
	}

	private static Integer $default$xAxis()
	{
		return 1;
	}

	private static Integer $default$yAxis()
	{
		return 1;
	}

	private static Integer $default$intervalOfSeconds()
	{
		return 60;
	}

	public static SettingsModelBeanBuilder builder()
	{
		return new SettingsModelBeanBuilder();
	}

	public Integer getXAxis()
	{
		return this.xAxis;
	}

	public void setXAxis(Integer xAxis)
	{
		this.xAxis = xAxis;
	}

	public Integer getYAxis()
	{
		return this.yAxis;
	}

	public void setYAxis(Integer yAxis)
	{
		this.yAxis = yAxis;
	}

	public Integer getIntervalOfSeconds()
	{
		return this.intervalOfSeconds;
	}

	public void setIntervalOfSeconds(Integer intervalOfSeconds)
	{
		this.intervalOfSeconds = intervalOfSeconds;
	}

	public boolean equals(final Object o)
	{
		if (o == this)
			return true;
		if (!(o instanceof SettingsModelBean))
			return false;
		final SettingsModelBean other = (SettingsModelBean)o;
		if (!other.canEqual((Object)this))
			return false;
		final Object this$xAxis = this.getXAxis();
		final Object other$xAxis = other.getXAxis();
		if (this$xAxis == null ? other$xAxis != null : !this$xAxis.equals(other$xAxis))
			return false;
		final Object this$yAxis = this.getYAxis();
		final Object other$yAxis = other.getYAxis();
		if (this$yAxis == null ? other$yAxis != null : !this$yAxis.equals(other$yAxis))
			return false;
		final Object this$intervalOfSeconds = this.getIntervalOfSeconds();
		final Object other$intervalOfSeconds = other.getIntervalOfSeconds();
		if (this$intervalOfSeconds == null
			? other$intervalOfSeconds != null
			: !this$intervalOfSeconds.equals(other$intervalOfSeconds))
			return false;
		return true;
	}

	protected boolean canEqual(final Object other)
	{
		return other instanceof SettingsModelBean;
	}

	public int hashCode()
	{
		final int PRIME = 59;
		int result = 1;
		final Object $xAxis = this.getXAxis();
		result = result * PRIME + ($xAxis == null ? 43 : $xAxis.hashCode());
		final Object $yAxis = this.getYAxis();
		result = result * PRIME + ($yAxis == null ? 43 : $yAxis.hashCode());
		final Object $intervalOfSeconds = this.getIntervalOfSeconds();
		result = result * PRIME + ($intervalOfSeconds == null ? 43 : $intervalOfSeconds.hashCode());
		return result;
	}

	public String toString()
	{
		return "SettingsModelBean(xAxis=" + this.getXAxis() + ", yAxis=" + this.getYAxis()
			+ ", intervalOfSeconds=" + this.getIntervalOfSeconds() + ")";
	}

	public static class SettingsModelBeanBuilder
	{
		private Integer xAxis$value;
		private boolean xAxis$set;
		private Integer yAxis$value;
		private boolean yAxis$set;
		private Integer intervalOfSeconds$value;
		private boolean intervalOfSeconds$set;

		SettingsModelBeanBuilder()
		{
		}

		public SettingsModelBeanBuilder xAxis(Integer xAxis)
		{
			this.xAxis$value = xAxis;
			this.xAxis$set = true;
			return this;
		}

		public SettingsModelBeanBuilder yAxis(Integer yAxis)
		{
			this.yAxis$value = yAxis;
			this.yAxis$set = true;
			return this;
		}

		public SettingsModelBeanBuilder intervalOfSeconds(Integer intervalOfSeconds)
		{
			this.intervalOfSeconds$value = intervalOfSeconds;
			this.intervalOfSeconds$set = true;
			return this;
		}

		public SettingsModelBean build()
		{
			Integer xAxis$value = this.xAxis$value;
			if (!this.xAxis$set)
			{
				xAxis$value = SettingsModelBean.$default$xAxis();
			}
			Integer yAxis$value = this.yAxis$value;
			if (!this.yAxis$set)
			{
				yAxis$value = SettingsModelBean.$default$yAxis();
			}
			Integer intervalOfSeconds$value = this.intervalOfSeconds$value;
			if (!this.intervalOfSeconds$set)
			{
				intervalOfSeconds$value = SettingsModelBean.$default$intervalOfSeconds();
			}
			return new SettingsModelBean(xAxis$value, yAxis$value, intervalOfSeconds$value);
		}

		public String toString()
		{
			return "SettingsModelBean.SettingsModelBeanBuilder(xAxis$value=" + this.xAxis$value
				+ ", yAxis$value=" + this.yAxis$value + ", intervalOfSeconds$value="
				+ this.intervalOfSeconds$value + ")";
		}
	}
}
