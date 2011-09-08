/*
 * Copyright 2011. Pablo Palazon (pablo.palazon@gmail.com)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.serinus.push;

import org.richfaces.application.push.*;
import org.richfaces.application.push.impl.DefaultMessageDataSerializer;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

/**
 * Created by IntelliJ IDEA.
 * User: ppalazon
 * Date: 9/7/11
 * Time: 8:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class TopicsInitializer implements SystemEventListener {

    public void processEvent(SystemEvent event) throws AbortProcessingException {
        TopicsContext topicsContext = TopicsContext.lookup();
        Topic topic = topicsContext.getOrCreateTopic(new TopicKey("serinustask"));
        topic.setMessageDataSerializer(DefaultMessageDataSerializer.instance());
        topic.addTopicListener(new SessionTopicListener() {

            @Override
            public void processPreSubscriptionEvent(SessionPreSubscriptionEvent event) throws EventAbortedException {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void processSubscriptionEvent(SessionSubscriptionEvent event) throws EventAbortedException {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void processUnsubscriptionEvent(SessionUnsubscriptionEvent event) throws EventAbortedException {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public boolean isListenerForSource(Object source) {
        return true;
    }

}
