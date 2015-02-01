package com.android_s14.rvh;

public interface DataModel {

	/**
	 * @return a corresponding String field of the class
	 */
	String getTextField(int index);

	/**@return a number of fields of this class showed in the RecyclerView*/
	int getDataFieldsNumber();

}
