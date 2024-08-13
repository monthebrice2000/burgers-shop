package com.ecommerce.burgers_shop.jmx;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import javax.management.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Service;

import com.ecommerce.burgers_shop.models.Burger;
import com.ecommerce.burgers_shop.repository.BurgerRepository;

@Service
@ManagedResource
public class TacoCounter extends AbstractRepositoryEventListener<Burger>
        implements NotificationPublisherAware {

    private AtomicLong counter;

    private NotificationPublisher np;

    @Autowired
    private BurgerRepository burgerRepo;

    public TacoCounter() {
    // tacoRepo
    // .count()
    // .subscribe(initialCount -> {
    // this.counter = new AtomicLong(initialCount);
    // });
    // long count = this.burgerRepo.count();
    // long initCount = count != null ? count : 0;
    this.counter = new AtomicLong( 0 );
    }

    @Override
    protected void onAfterCreate(Burger entity) {
        counter.incrementAndGet();
    }

    @ManagedAttribute
    public long getTacoCount() {
        return counter.get();
    }

    // @ManagedOperation
    // public long increment(long delta) {
    //     return counter.addAndGet(delta);
    // }

    @Override
    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
        this.np = notificationPublisher;
    }

    @ManagedOperation
    public long increment(long delta) {
        long before = counter.get();
        long after = counter.addAndGet(delta);
        if ((after / 100) > (before / 100)) {
            Notification notification = new Notification(
                    "taco.count", this,
                    before, after + "th taco created!");
            np.sendNotification(notification);
        }
        return after;
    }
}
