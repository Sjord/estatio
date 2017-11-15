package org.estatio.module.capex;

import java.util.Set;

import com.google.common.collect.Sets;

import org.estatio.module.assetfinancial.EstatioAssetFinancialModule;
import org.estatio.module.base.platform.applib.Module;
import org.estatio.module.base.platform.applib.ModuleAbstract;
import org.estatio.module.budget.EstatioBudgetModule;
import org.estatio.module.invoice.EstatioInvoiceModule;

public class EstatioCapexModule extends ModuleAbstract {

    public EstatioCapexModule() {}

    @Override
    public Set<Module> getDependencies(){
        return Sets.newHashSet(
                new EstatioInvoiceModule(),
                new EstatioAssetFinancialModule(),
                new EstatioBudgetModule()
                );
    }

    public abstract static class ActionDomainEvent<S>
            extends org.apache.isis.applib.services.eventbus.ActionDomainEvent<S> { }

    public abstract static class CollectionDomainEvent<S,T>
            extends org.apache.isis.applib.services.eventbus.CollectionDomainEvent<S,T> { }

    public abstract static class PropertyDomainEvent<S,T>
            extends org.apache.isis.applib.services.eventbus.PropertyDomainEvent<S,T> { }

}
