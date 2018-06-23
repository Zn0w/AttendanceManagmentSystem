package com.znow.attendance_mng_system.comm_interface;

public enum Message {

	// Client messages
	REGISTER,
	SAVE,
	DISCONNECT,
	
	// Server messages
	REGISTER_SUCCESS,
	REGISTER_FAIL,
	SAVE_SUCCESS,
	SAVE_FAIL

};