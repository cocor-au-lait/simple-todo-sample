package cocor.au.lait.todolist.core.internal;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.type.UUIDCharType;

import java.util.UUID;

public class UUIDMetadataBuilderContributor implements MetadataBuilderContributor {

	@Override
	public void contribute(MetadataBuilder metadataBuilder) {
		metadataBuilder.applyBasicType(UUIDCharType.INSTANCE, UUID.class.getName());
	}

}
