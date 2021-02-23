package com.voteservice.amqp;

/**
* <p>**************************************************************************************
* <p>                                 AmpqProducer
* <p>**************************************************************************************
* <p>
* This implementation provides AMQP (Advanced Message Queuing Protocol) producer interface 
* to be used with a message software like RabbitMQ
* <p>
* @param  T - template
* @return none
*/
public interface AmqpProducer<T> {
	void producer(T t);
}
