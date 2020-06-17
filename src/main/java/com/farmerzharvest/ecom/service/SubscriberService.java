package com.farmerzharvest.ecom.service;

import com.farmerzharvest.ecom.model.accounts.Subscriber;

import java.util.List;

public interface SubscriberService {
    List<Subscriber> getSubscribers();

    Subscriber getSubscriberById(long subscriberId);

    Subscriber addSubscriber(Subscriber request);

    Subscriber updateSubscriber(Subscriber request);

    void deleteSubscriber(long subscriberId);
}
