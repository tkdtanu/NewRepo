package com.tkd.empmgmt.constant;

public class ExceptionMessageConstant {
	
	public static String USER_ROLE_BLANK_EXCEPTION = "Role Can't be Blank. Please provide Role ID";
	
	public static String INVALID_USER_ROLE_EXCEPTION = "Invalid Role ID provided";
	
	public static String INVALID_REPORTEE_EXCEPTION = "Wrong Reporting type given!!";
	
	public static String REPORTEE_NOT_FOUND_EXCEPTION = "Reportee Not Found With Employee Id:=";
	
	public static String REPORTEE_MANDATORY_EXCEPTION = "Reportee Is Mandatory For Current Employee";
	
	public static String BLANK_REPORTEE_EXCEPTION = "Opps!!!! You are giving Blank Reportee";
	
	public static String INVALID_REPORTEE_ARGUMENT_FOR_CEO_EXCEPTION = "Current Employee Shouldn't Report To AnyOne";
	
	public static String EMPLOYEE_NOT_FOUND_EXCEPTION = "There is no Employee for the given id";
	
	public static String INVALID_POSITION_EXCEPTION = "Given Position is Not Valid";

}
