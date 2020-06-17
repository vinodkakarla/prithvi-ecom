package com.farmerzharvest.ecom.service.impl;

import com.farmerzharvest.ecom.model.accounts.Subscriber;
import com.farmerzharvest.ecom.repository.SubscriberRepository;
import com.farmerzharvest.ecom.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    SubscriberRepository subscriberRepo;

    @Override
    public List<Subscriber> getSubscribers() {
        return subscriberRepo.findAll();
    }

    @Override
    public Subscriber getSubscriberById(long subscriberId) {
        return subscriberRepo.getOne(subscriberId);
    }

    @Override
    public Subscriber addSubscriber(Subscriber request) {
        return subscriberRepo.save(request);
    }

    @Override
    public Subscriber updateSubscriber(Subscriber request) {
        return subscriberRepo.save(request);
    }

    @Override
    public void deleteSubscriber(long subscriberId) {
        subscriberRepo.deleteById(subscriberId);
    }
}
