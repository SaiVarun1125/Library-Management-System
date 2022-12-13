package com.spark.lms.common;

import java.util.ArrayList;
import java.util.List;

public class coonst {

	public static final String roladm = "Admin";
	public static final String rolelibr = "Librarian";
	
	public static final String membpar = "Parent";
	public static final String membstd = "Student";
	public static final String memboth = "Other";
	public static final List<String> memtyp = new ArrayList<String>() {{
	    add(membpar);
	    add(membstd);
	    add(memboth);
	}};
	
	public static final Integer boostatavil = 1;
	public static final Integer boostatiss = 2;
	
	public static final Integer boonotret = 0;
	public static final Integer booretu = 1;
}
