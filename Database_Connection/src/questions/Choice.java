package questions;

public class Choice
{
	private String option;
	private String optionNumber;
	
	public Choice(String option, String optionNumber)
	{
		super();
		this.option = option;
		this.optionNumber = optionNumber;
	}

	public String getOption()
	{
		return option;
	}

	public String getOptionNumber()
	{
		return optionNumber;
	}

	public void setOption(String option)
	{
		this.option = option;
	}

	public void setOptionNumber(String optionNumber)
	{
		this.optionNumber = optionNumber;
	}
	
}
