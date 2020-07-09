package com.farmerzharvest.ecom.controller;

import com.farmerzharvest.ecom.model.accounts.Subscriber;
import com.farmerzharvest.ecom.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "subscribers", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SubscriberController {

    private final SubscriberService subscriberService;

    @GetMapping("/get-all")
    List<Subscriber> getAll() {
        return subscriberService.getSubscribers();
    }

    @GetMapping("/get-by-id")
    Subscriber getById(long subscriberId) {
        return subscriberService.getSubscriberById(subscriberId);
    }

    @PostMapping("/add-subscriber")
    Subscriber addSubscriber(@RequestBody Subscriber request) {
        return subscriberService.addSubscriber(request);
    }

    @PutMapping("/update-subscriber")
    Subscriber updateSubscriber(@RequestBody Subscriber request) {
        return subscriberService.updateSubscriber(request);
    }

    @DeleteMapping("/delete-subscriber/{subscriberId}")
    void deleteSubscriber(@PathVariable(value = "subscriberId") long subscriberId) {
        subscriberService.deleteSubscriber(subscriberId);
    }

}
