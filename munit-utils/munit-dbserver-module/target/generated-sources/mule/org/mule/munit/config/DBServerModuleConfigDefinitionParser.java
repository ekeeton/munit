
package org.mule.munit.config;

import javax.annotation.Generated;
import org.mule.munit.adapters.DBServerModuleProcessAdapter;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

@Generated(value = "Mule DevKit Version 3.4.0", date = "2013-06-05T08:16:19-03:00", comments = "Build 3.4.0.1555.8df15c1")
public class DBServerModuleConfigDefinitionParser
    extends AbstractDefinitionParser
{


    public BeanDefinition parse(Element element, ParserContext parserContext) {
        parseConfigName(element);
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(DBServerModuleProcessAdapter.class.getName());
        builder.setScope(BeanDefinition.SCOPE_SINGLETON);
        setInitMethodIfNeeded(builder, DBServerModuleProcessAdapter.class);
        setDestroyMethodIfNeeded(builder, DBServerModuleProcessAdapter.class);
        parseProperty(builder, element, "database", "database");
        parseProperty(builder, element, "sqlFile", "sqlFile");
        parseProperty(builder, element, "csv", "csv");
        BeanDefinition definition = builder.getBeanDefinition();
        setNoRecurseOnDefinition(definition);
        return definition;
    }

}
