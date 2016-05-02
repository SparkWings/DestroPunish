package org.jbltd.punish.util;

public class UtilTime {

    
    public static String convert(int amount)
    {
	
	
	if(amount == 9999)
	{
	    return "Permanent";
	}
	
	if(amount > 24)
	{
	    
	    double r = amount / 24;
	    return r + " days";
	}
	
	else {
	    
	    return amount + " hours";
	    
	}
	
    }
    
}
