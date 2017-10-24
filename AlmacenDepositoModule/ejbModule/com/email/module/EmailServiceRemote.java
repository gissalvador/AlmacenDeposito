package com.email.module;

import javax.ejb.Remote;

@Remote
public interface EmailServiceRemote {

	void send(String addresses, String topic, String textMessage);

}
