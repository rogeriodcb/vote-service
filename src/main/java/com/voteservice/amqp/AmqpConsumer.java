package com.voteservice.amqp;
/**
* <p>**************************************************************************************
* <p>                                 AmpqConsumer
* <p>**************************************************************************************
* <p>
* This implementation provides AMQP (Advanced Message Queuing Protocol) consumer interface 
* to be used with a message software like RabbitMQ
* <p>
* @param  T - template
* @return none
*/
public interface AmqpConsumer<T> {
	void consumer(T t);
}
