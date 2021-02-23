package com.voteservice.amqp;
/**
* <p>**************************************************************************************
* <p>                                 AmpqRePublish
* <p>**************************************************************************************
* <p>
* This implementation provides AMQP (Advanced Message Queuing Protocol) RePublisher interface 
* to be used with a message software like RabbitMQ
* <p>
* @param  none
* @return none
*/
public interface AmqpRePublish {
    void rePublish();
}
