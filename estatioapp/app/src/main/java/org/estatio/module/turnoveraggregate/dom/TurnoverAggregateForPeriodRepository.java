package org.estatio.module.turnoveraggregate.dom;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.RestrictTo;
import org.apache.isis.applib.services.registry.ServiceRegistry2;
import org.apache.isis.applib.services.repository.RepositoryService;

@DomainService(
        nature = NatureOfService.DOMAIN,
        repositoryFor = TurnoverAggregateForPeriod.class
)
public class TurnoverAggregateForPeriodRepository {

    public java.util.List<TurnoverAggregateForPeriod> listAll() {
        return repositoryService.allInstances(TurnoverAggregateForPeriod.class);
    }

    public TurnoverAggregateForPeriod findUnique(
            final TurnoverAggregation aggregation,
            final AggregationPeriod period
    ) {
        return repositoryService.uniqueMatch(
                new org.apache.isis.applib.query.QueryDefault<>(
                        TurnoverAggregateForPeriod.class,
                        "findUnique",
                        "aggregation", aggregation,
                        "period", period));
    }


    public TurnoverAggregateForPeriod create(final TurnoverAggregation aggregation, final AggregationPeriod period) {
        final TurnoverAggregateForPeriod aggregate = new TurnoverAggregateForPeriod();
        aggregate.setAggregation(aggregation);
        aggregate.setAggregationPeriod(period);
        serviceRegistry.injectServicesInto(aggregate);
        repositoryService.persistAndFlush(aggregate);
        return aggregate;
    }

    public TurnoverAggregateForPeriod findOrCreate(
            final TurnoverAggregation aggregation, final AggregationPeriod period
    ) {
        TurnoverAggregateForPeriod aggregate = findUnique(aggregation, period);
        if (aggregate == null) {
            aggregate = create(aggregation, period);
        }
        return aggregate;
    }

    @Inject RepositoryService repositoryService;

    @Inject ServiceRegistry2 serviceRegistry;
}