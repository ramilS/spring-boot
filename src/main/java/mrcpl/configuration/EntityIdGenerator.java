package mrcpl.configuration;

import io.vavr.control.Option;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

public class EntityIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        String seqPostfix = Option.of(obj.getClass().getAnnotation(Table.class))
                .map(Table::name)
                .getOrElse(obj.getClass().getSimpleName())
                .toLowerCase();

        String queryString = String.format("select nextval('seq_%s')", seqPostfix);

        return ((BigInteger) session.createNativeQuery(queryString).getSingleResult()).longValue();
    }
}
