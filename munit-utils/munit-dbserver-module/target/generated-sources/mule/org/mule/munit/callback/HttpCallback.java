
package org.mule.munit.callback;

import javax.annotation.Generated;
import org.mule.api.context.MuleContextAware;
import org.mule.api.lifecycle.Startable;
import org.mule.api.lifecycle.Stoppable;

@Generated(value = "Mule DevKit Version 3.4.0", date = "2013-06-05T08:16:19-03:00", comments = "Build 3.4.0.1555.8df15c1")
public interface HttpCallback extends MuleContextAware, Startable, Stoppable
{

    String getUrl();
}
